package com.matariky.aop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.github.pagehelper.util.StringUtil;
import com.matariky.annotation.SourcePermission;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.mapper.CommonDictMapper;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.datasharing.service.CommonSharingPoolService;
import com.matariky.constant.PermissionConstant;
import com.matariky.model.BaseDataIsolation;
import com.matariky.model.QueryDataIsolation;
import com.matariky.model.SaveDataIsolation;
import com.matariky.mybatis.MybatisPlusDataScopeInterceptor;
import com.matariky.utils.IpUtils;
import com.matariky.utils.TokenUtils;

@Aspect
@Component
public class DataIsolationAspect implements GetCurrentRequest {
    @Autowired
    private CommonDictService commonDictService;

    @Autowired
    private CommonDictTypeService commonDictTypeService;

    @Autowired
    private CommonSharingPoolService sharingPoolService;

    @Autowired
    private CommonDictMapper commonDictMapper;
    
    @Pointcut("execution(* com.matariky..*Controller.*(..))")
    public void controllerPointcut() {

    }

    private HttpServletRequest getCurrentRequest() {
        final ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<>();
    	HttpServletRequest httpServletRequest=currentRequest.get();
    	return httpServletRequest;
    }

    @Around("controllerPointcut()")
    public Object dataFilter(ProceedingJoinPoint joinPoint) throws Throwable {
    	HttpServletRequest httpServletRequest=getCurrentRequest();
        String tenantId = TokenUtils.extractTenantIdFromHttpRequest(httpServletRequest);

        if (StringUtils.isNotEmpty(tenantId) && Objects.nonNull(joinPoint.getArgs()) && joinPoint.getArgs().length > NumberUtils.INTEGER_ZERO) {
            String strategyCode;
            if (Arrays.stream(joinPoint.getArgs()).anyMatch(arg -> Objects.nonNull(arg) && arg instanceof QueryDataIsolation)) {
                strategyCode = getStrategyCode(joinPoint.getSignature(), tenantId);
            } else {
                strategyCode = null;
            }
            for (Object param: joinPoint.getArgs()) {
                if (Objects.nonNull(param) && param instanceof BaseDataIsolation) {
                    ((BaseDataIsolation) param).setLocal(TokenUtils.extractLocaleForRequest(httpServletRequest));
                    ((BaseDataIsolation) param).setTenantId(tenantId);
                    ((BaseDataIsolation) param).setUserId(NumberUtils.toLong(TokenUtils.extractUserIdFromHttpReqeust(httpServletRequest), NumberUtils.INTEGER_MINUS_ONE));
                    ((BaseDataIsolation) param).setApplication(TokenUtils.extractApplicationFromToken(httpServletRequest));
                    if (param instanceof QueryDataIsolation) {
                        QueryDataIsolation dataIsolation = (QueryDataIsolation) param;
                        dataIsolation.setStrategyCode(strategyCode);
                        setQueryDataIsolation(dataIsolation, TokenUtils.extractOrgCode(httpServletRequest), TokenUtils.extractSelfOrgCode(httpServletRequest));
                    } else if (param instanceof SaveDataIsolation) {
                        SaveDataIsolation dataIsolation = (SaveDataIsolation) param;
                        dataIsolation.setOperatorOrgCode(TokenUtils.extractOrgCode(httpServletRequest));
                        dataIsolation.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(httpServletRequest));
                        dataIsolation.setOperationIp(IpUtils.getIpAddr(httpServletRequest));
                    }
                }
            }
        }
        return joinPoint.proceed();
    }

    private String getStrategyCode(Signature signature, String tenantId) {
        /** request resource id **/
        String id = getCurrentRequest().getHeader("id");
        SourcePermission sourcePermission = AnnotationUtils.getAnnotation(((MethodSignature) signature).getMethod(), SourcePermission.class);
        if (Objects.isNull(sourcePermission)) {
            sourcePermission = AnnotationUtils.getAnnotation(signature.getDeclaringType(), SourcePermission.class);
        }
        String sourceId;
        if (Objects.nonNull(sourcePermission) && StringUtils.isNotEmpty(sourcePermission.oId())) {
            sourceId = sourcePermission.oId();
        } else if (StringUtils.isNotEmpty(id) && id.length() > NumberUtils.INTEGER_ONE) {
            sourceId = id.substring(NumberUtils.INTEGER_ZERO, id.length() - NumberUtils.INTEGER_ONE);
        }  else {
            return PermissionConstant.COMMON_DATA_ACCESS_PRIVATE;
        }
        Long pId = commonDictMapper.getPermissionId(tenantId, sourceId);
        if (Objects.isNull(pId)) {
            return PermissionConstant.COMMON_DATA_ACCESS_PRIVATE;
        }
        /** Obtain Resource Dictionary Type **/
        CommonDictType commonDictType = commonDictTypeService.getDictTypeByKey(TokenUtils.extractTenantIdFromHttpReqeust(getCurrentRequest()), PermissionConstant.DATA_ACCESS_PERMISSION);
        if (Objects.nonNull(commonDictType) )  {
            /** Obtain cache resource data **/
            CommonDict dict = commonDictService.getCommonDictByIdTenantIdAndDictType("dp" + sourceId, tenantId, commonDictType.getId());
            if (Objects.nonNull(dict) && StringUtils.isNotEmpty(dict.getDictValue())) {
                return dict.getDictValue();
            }
        }
        return PermissionConstant.COMMON_DATA_ACCESS_PRIVATE;
    }


    private void setQueryDataIsolation(QueryDataIsolation dataIsolation, String orgCode, String selfOrgCode) {
        if (StringUtil.isEmpty(dataIsolation.getStrategyCode())) {
            /** Default personal visibility **/
            dataIsolation.setStrategyCode(PermissionConstant.COMMON_DATA_ACCESS_PRIVATE);
        }
        TokenUtils.extractSelfOrgCode(getCurrentRequest());
        switch (dataIsolation.getStrategyCode()) {
            case PermissionConstant.COMMON_DATA_ACCESS_PRIVATE:
                setSharingOrgCodes(dataIsolation, orgCode, selfOrgCode);
                dataIsolation.setSelfOrgCode(selfOrgCode);
                break;
            case PermissionConstant.COMMON_DATA_ACCESS_ORG:
            case PermissionConstant.COMMON_DATA_ACCESS_LEVEL:
                setSharingOrgCodes(dataIsolation, orgCode, selfOrgCode);
                dataIsolation.setOrgCode(orgCode);
                break;
            default:
                break;
        }
    }

    public void setSharingOrgCodes(QueryDataIsolation  queryDataIsolation, String orgCode, String selfOrgCode) {
        /** Query the organization code of shared resources **/
        List<String> orgCodes = sharingPoolService.getOriginalOwnerOrgCodeList(orgCode, selfOrgCode);
        if (CollectionUtils.isNotEmpty(orgCodes)) {
            List<String> sharingSelfOrgCodes = new ArrayList<>(orgCodes.size());
            List<String> sharingOrgCodes = new ArrayList<>(orgCodes.size());
            orgCodes.stream().forEach(ownerOrgCode -> {
                if (ownerOrgCode.startsWith("ind_")) {
                    sharingSelfOrgCodes.add(ownerOrgCode);
                } else {
                    sharingOrgCodes.add(ownerOrgCode);
                }
            });
            queryDataIsolation.setSharingSelfOrgCodes(sharingSelfOrgCodes);
            queryDataIsolation.setSharingOrgCodes(sharingOrgCodes);
        }
    }
}
