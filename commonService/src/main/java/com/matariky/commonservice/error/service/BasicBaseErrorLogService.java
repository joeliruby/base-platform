package com.matariky.commonservice.error.service;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.matariky.commonservice.base.vo.ErrorLogExeclVO;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.utils.BeanUtils;
import com.matariky.utils.IpUtils;
import com.matariky.utils.StringUtils;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.APIConstants;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.commonservice.error.bean.BasicBaseErrorLog;
import com.matariky.commonservice.error.mapper.BasicBaseErrorLogMapper;

import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.util.StringUtil;

import com.matariky.constant.PermissionConstant;

import com.matariky.utils.TokenUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
public class BasicBaseErrorLogService extends BaseServiceImpl<BasicBaseErrorLogMapper, BasicBaseErrorLog>
        implements BaseService<BasicBaseErrorLog> {

    @Autowired
    private BasicBaseErrorLogMapper basicBaseErrorLogMapper;
    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CommonDictTypeService commonDictTypeService;
    @Autowired
    private CommonDictService commonDictService;

    public List<BasicBaseErrorLog> getBasicBaseErrorLogAll(BasicBaseErrorLog vo) {
        if (StringUtils.isNotBlank(vo.getBusinessTimeEnd())) {
            Long endTime = Long.valueOf(vo.getBusinessTimeEnd()) + 24 * 60 * 60 * 1000;
            vo.setBusinessTimeEnd(endTime.toString());
        }

        if (StringUtils.isNotBlank(vo.getBusinessTimeEnd())) {
            Long endTime = Long.valueOf(vo.getBusinessTimeEnd()) + 24 * 60 * 60 * 1000;
            vo.setBusinessTimeEnd(endTime.toString());
        }

        String hid = request.getHeader("id");
        String resourceIdDictKey = "dp" + hid.substring(0, hid.length() - 1);
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        CommonDictType commonDictType = commonDictTypeService.getDictTypeByKey(
                TokenUtils.extractTenantIdFromHttpReqeust(request), PermissionConstant.DATA_ACCESS_PERMISSION);
        CommonDict dict = commonDictService.getCommonDictByIdTenantIdAndDictType(resourceIdDictKey, tenantId,
                commonDictType.getId());
        if (dict == null) {
            vo.setStrategyCode(PermissionConstant.COMMON_DATA_ACCESS_ALL);
        } else {
            vo.setStrategyCode(dict.getDictValue());
        }
        vo.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        vo.setSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        vo.setTenantId(tenantId);
        return basicBaseErrorLogMapper.getBasicBaseErrorLogAll(vo);
    }

    /**
     * Export
     * 
     * @param bean
     */
    public void export(BasicBaseErrorLog bean) {
        List<BasicBaseErrorLog> list = basicBaseErrorLogMapper.getBasicBaseErrorLogAll(bean);
        List<ErrorLogExeclVO> newList = list.stream().map(item -> {
            ErrorLogExeclVO vo = new ErrorLogExeclVO();
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).collect(Collectors.toList());

        response.setContentType("application/vnd.ms-excel; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(System.currentTimeMillis() + ".xlsx", "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            EasyExcel.write(outputStream, ErrorLogExeclVO.class)
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet(" Error  Log ")
                    .doWrite(newList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getBasicBaseErrorLogAllCount() {
        return basicBaseErrorLogMapper.getBasicBaseErrorLogAllCount();
    }

    public int createBasicBaseErrorLog(BasicBaseErrorLog bean) {
        return basicBaseErrorLogMapper.createBasicBaseErrorLog(bean);
    }

    public int createBasicBaseErrorLogWithOrg(List<BasicBaseErrorLog> beanList, HttpServletRequest request) {
        for (BasicBaseErrorLog bean : beanList) {
            bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
            bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
            bean.setCreateTime(System.currentTimeMillis());
            bean.setCreateBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request)));
            bean.setTenantId(TokenUtils.extractTenantIdFromHttpReqeust(request));
            bean.setAccessAccount(TokenUtils.extractLoginNameFromHttpRequest(request));
            if (Objects.isNull(bean.getDeviceType()))
                bean.setDeviceType(request.getParameter("User-Agent"));
            bean.setServerIp(IpUtils.getIpAddr(request));
            bean.setDeleteTime(0L);
            bean.setUrl(request.getRequestURL().toString());
            String apiid = request.getHeader("Ip");
            String opr = "";
            if (NumberUtils.isDigits(apiid)) {
                char lastChar = apiid.charAt(apiid.length() - 1);
                String c = String.valueOf(lastChar);
                switch (c) {
                    case APIConstants.POST:
                        opr = "New";
                    case APIConstants.DELETE:
                        opr = "Delete ";
                    case APIConstants.PUT:
                        opr = "  Update";
                    case APIConstants.GET:
                        opr = " Read ";
                    default:
                        opr = "";
                }
            }
            bean.setApiName(opr + bean.getBusinessModule());
            basicBaseErrorLogMapper.createBasicBaseErrorLog(bean);
        }
        return 1;
    }

    public int updateBasicBaseErrorLog(BasicBaseErrorLog bean) {
        return basicBaseErrorLogMapper.updateById(bean);
    }

    public int delBasicBaseErrorLogById(int id) {
        return basicBaseErrorLogMapper.delBasicBaseErrorLogById(id);
    }

    public BasicBaseErrorLog getBasicBaseErrorLogById(int id) {
        return basicBaseErrorLogMapper.getBasicBaseErrorLogById(id);
    }

    public List<BasicBaseErrorLog> getBasicBaseErrorLogDAC(Map<String, Object> params, HttpServletRequest request) {
        strategyCodeToParams(params, request);
        return basicBaseErrorLogMapper.getBasicBaseErrorLogDAC(params);
    }

    public Long getBasicBaseErrorLogDACCount(Map<String, Object> params, HttpServletRequest request) {

        String strategyCode = (String) params.get("strategyCode");
        if (StringUtil.isEmpty(strategyCode))
            strategyCode = PermissionConstant.COMMON_DATA_ACCESS_PRIVATE;// By default only visible by owner
        switch (strategyCode) {
            case PermissionConstant.COMMON_DATA_ACCESS_PRIVATE:// Visible to owner with special sharing rules
                Map<String, List<String>> sharingOrgCodes0 = extractedSharingOrgCodes(request);
                params.put("selfOrgCode", TokenUtils.extractSelfOrgCode(request));
                params.putAll(sharingOrgCodes0);
                break;
            case PermissionConstant.COMMON_DATA_ACCESS_ALL:// All visible to all without special sharing rules
                break;
            case PermissionConstant.COMMON_DATA_ACCESS_ORG:// Visible to organizations of same or upper level
                Map<String, List<String>> sharingOrgCodes3 = extractedSharingOrgCodes(request);
                params.put("orgCode", TokenUtils.extractOrgCode(request));
                params.putAll(sharingOrgCodes3);
                break;
            case PermissionConstant.COMMON_DATA_ACCESS_LEVEL:// Visible to organizations of same level with special
                                                             // sharing rules
                Map<String, List<String>> sharingOrgCodes2 = extractedSharingOrgCodes(request);
                params.put("orgCode", TokenUtils.extractOrgCode(request));
                params.putAll(sharingOrgCodes2);
                break;
            default:
                break;
        }

        return basicBaseErrorLogMapper.getBasicBaseErrorLogDACCount(params);
    }

}
