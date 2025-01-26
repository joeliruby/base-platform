package com.matariky.commonservice.base.service;

import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageHelper;
import com.matariky.commonservice.base.bean.BasicBaseAntifake;
import com.matariky.commonservice.base.bean.BasicBaseAntifakeDetail;
import com.matariky.commonservice.base.mapper.BasicBaseAntifakeDetailMapper;
import com.matariky.commonservice.base.mapper.BasicBaseAntifakeMapper;
import com.matariky.commonservice.base.vo.IOTAuthenticationVO;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.constant.IOTAuthConstants;
import com.matariky.constant.PermissionConstant;
import com.matariky.id.SnowflakeIdWorker;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.utils.CodeUtils;
import com.matariky.utils.IPUtil;
import com.matariky.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *  Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
public class BasicBaseAntifakeService extends BaseServiceImpl<BasicBaseAntifakeMapper, BasicBaseAntifake> implements BaseService<BasicBaseAntifake> {

    @Autowired
    private BasicBaseAntifakeMapper basicBaseAntifakeMapper;
    @Autowired
    private BasicBaseAntifakeDetailMapper basicBaseAntifakeDetailMapper;
    @Autowired
    private CommonDictService commonDictService;
    @Autowired
    private CommonDictTypeService commonDictTypeService;
    @Autowired
    private HttpServletRequest request;

    /**
     *  Query 所有分页
     *
     * @param bean
     * @param pageIndex
     * @param perPage
     * @return
     */
    public List<BasicBaseAntifake> getBasicBaseAntifakeAll(BasicBaseAntifake bean, Integer pageIndex, Integer perPage) {
        String hid = request.getHeader("id");
        String resourceIdDictKey = "dp" + hid.substring(0, hid.length() - 1);
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        CommonDictType commonDictType = commonDictTypeService.getDictTypeByKey(TokenUtils.extractTenantIdFromHttpReqeust(request), PermissionConstant.DATA_ACCESS_PERMISSION);
        CommonDict dict = commonDictService.getCommonDictByIdTenantIdAndDictType(resourceIdDictKey, tenantId, commonDictType.getId());
        if (dict == null) {
            bean.setStrategyCode(PermissionConstant.COMMON_DATA_ACCESS_ALL);
        } else {
            bean.setStrategyCode(dict.getDictValue());
        }
        bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        bean.setSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        bean.setTenantId(tenantId);
        bean.setOrgCode(TokenUtils.extractOrgCode(request));
        PageHelper.startPage(pageIndex, perPage);
        return basicBaseAntifakeMapper.getBasicBaseAntifakeAll(bean);
    }


    /**
     * New方法
     *
     * @param bean
     * @return
     */
    public int createBasicBaseAntifakeWithOrg(BasicBaseAntifake bean) {
        bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
        bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        return basicBaseAntifakeMapper.createBasicBaseAntifake(bean);
    }

    /**
     * Update方法
     *
     * @param bean
     * @return
     */
    public int updateBasicBaseAntifake(BasicBaseAntifake bean) {
        return basicBaseAntifakeMapper.updateById(bean);
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int delBasicBaseAntifakeById(Long id) {
        return basicBaseAntifakeMapper.delBasicBaseAntifakeById(id);
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    public BasicBaseAntifake getBasicBaseAntifakeById(Long id) {
        return basicBaseAntifakeMapper.getBasicBaseAntifakeById(id);
    }

    public void persistIOTAuthenticateResult(String result, HttpServletRequest request, String authUrl) {
        IOTAuthenticationVO iav = JSONObject.parseObject(result, IOTAuthenticationVO.class);
        String batchNo = CodeUtils.CreateBatchCode();
        String orgCode = TokenUtils.extractOrgCode(request);
        String selfOrgCode = TokenUtils.extractSelfOrgCode(request);
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        long userId = Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request));
        long bbaId = SnowflakeIdWorker.getId();
        int successNo = 0, failedNo = 0, totalNo = 0;
        totalNo = iav.getTagValidity() == null ? 0 : iav.getTagValidity().size();
        for (Map<String, String> mp : iav.getTagValidity()) {
            BasicBaseAntifakeDetail bbad = new BasicBaseAntifakeDetail();
            String validity = mp.get("tagValid");
            if ("true".equals(validity)) {
                successNo += 1;
                bbad.setValidateResult("1");
            }
            if ("false".equals(validity)) {
                failedNo += 1;
                bbad.setValidateResult("0");
            }
            bbad.setOperatorOrgCode(orgCode);
            bbad.setOperatorSelfOrgCode(selfOrgCode);
            bbad.setTid(mp.get("tid"));
            bbad.setCreateTime(System.currentTimeMillis());
            bbad.setDeleteTime(0L);
            bbad.setCreateBy(userId);
            bbad.setCreateTime(System.currentTimeMillis());
            bbad.setValidateResult(result);
            bbad.setAntifakeId(bbaId);

            basicBaseAntifakeDetailMapper.createBasicBaseAntifakeDetail(bbad);
        }
        BasicBaseAntifake bba = new BasicBaseAntifake();
        bba.setFailNum(failedNo);
        bba.setSuccessNum(successNo);
        if (totalNo == 0) {
            bba.setValidateStatus(IOTAuthConstants.NO_TID);
        } else if (totalNo == successNo) {
            bba.setValidateStatus(IOTAuthConstants.ALL_PASS);
        } else if (totalNo == failedNo) {
            bba.setValidateStatus(IOTAuthConstants.ALL_FAIL);
        } else {
            bba.setValidateStatus(IOTAuthConstants.PARTIAL_PASS);
        }
        bba.setValidateNum(totalNo);
        bba.setCreateTime(System.currentTimeMillis());
        bba.setDeleteTime(0L);
        bba.setValidateBatchCode(batchNo);

        bba.setTenantId(tenantId);

        bba.setOperatorOrgCode(orgCode);
        bba.setOperatorSelfOrgCode(selfOrgCode);

        bba.setCreateBy(userId);
        bba.setIpAddress(IPUtil.getIPAddress(request));
        bba.setValidateUrl(authUrl);
        bba.setValidateTiime(System.currentTimeMillis());

        bba.setId(bbaId);
        basicBaseAntifakeMapper.createBasicBaseAntifake(bba);
    }


}
