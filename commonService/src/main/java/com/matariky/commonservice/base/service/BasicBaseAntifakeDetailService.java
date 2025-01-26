package com.matariky.commonservice.base.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.matariky.commonservice.base.bean.BasicBaseAntifakeDetail;
import com.matariky.commonservice.base.mapper.BasicBaseAntifakeDetailMapper;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.constant.PermissionConstant;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
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
public class BasicBaseAntifakeDetailService extends BaseServiceImpl<BasicBaseAntifakeDetailMapper, BasicBaseAntifakeDetail> implements BaseService<BasicBaseAntifakeDetail> {

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
    public List<BasicBaseAntifakeDetail> getBasicBaseAntifakeDetailAll(BasicBaseAntifakeDetail bean, Integer pageIndex, Integer perPage) {
        String hid = request.getHeader("id");
        String resourceIdDictKey = "dp" + hid.substring(0, hid.length() - 1);
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        CommonDictType commonDictType = commonDictTypeService.getDictTypeByKey(TokenUtils.extractTenantIdFromHttpReqeust(request), PermissionConstant.DATA_ACCESS_PERMISSION);
        CommonDict dict = commonDictService.getCommonDictByIdTenantIdAndDictType(resourceIdDictKey, tenantId, commonDictType.getId());
        if (dict == null) {
            bean.setOperatorOrgCode(PermissionConstant.COMMON_DATA_ACCESS_ALL);
        } else {
            bean.setOperatorOrgCode(dict.getDictValue());
        }
        bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        bean.setOrgCode(TokenUtils.extractOrgCode(request));
        bean.setTenantId(tenantId);
        PageHelper.startPage(pageIndex, perPage);
        return basicBaseAntifakeDetailMapper.getBasicBaseAntifakeDetailAll(bean);
    }


    /**
     * New方法
     *
     * @param bean
     * @return
     */
    public int createBasicBaseAntifakeDetailWithOrg(BasicBaseAntifakeDetail bean) {
        bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
        bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        return basicBaseAntifakeDetailMapper.createBasicBaseAntifakeDetail(bean);
    }

    /**
     * Update
     *
     * @param bean
     * @return
     */
    public int updateBasicBaseAntifakeDetail(BasicBaseAntifakeDetail bean) {
        return basicBaseAntifakeDetailMapper.updateById(bean);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int delBasicBaseAntifakeDetailById(Long id) {
        return basicBaseAntifakeDetailMapper.delBasicBaseAntifakeDetailById(id);
    }


    /**
     * 详情
     * @param id
     * @return
     */
    public BasicBaseAntifakeDetail getBasicBaseAntifakeDetailById(Long id) {
        return basicBaseAntifakeDetailMapper.getBasicBaseAntifakeDetailById(id);
    }


}
