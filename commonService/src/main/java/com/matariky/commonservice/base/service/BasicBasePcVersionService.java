package com.matariky.commonservice.base.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.matariky.commonservice.base.bean.BasicBasePcVersion;
import com.matariky.commonservice.base.mapper.BasicBasePcVersionMapper;
import com.matariky.commonservice.base.vo.BasicBasePcVersionAddVO;
import com.matariky.commonservice.base.vo.BasicBasePcVersionListVO;
import com.matariky.commonservice.base.vo.BasicBasePcVersionQueryVO;
import com.matariky.commonservice.base.vo.BasicBasePcVersionUpdateVO;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.constant.PermissionConstant;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.utils.BeanUtils;
import com.matariky.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *  Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
public class BasicBasePcVersionService extends BaseServiceImpl<BasicBasePcVersionMapper, BasicBasePcVersion> implements BaseService<BasicBasePcVersion> {

    @Autowired
    private BasicBasePcVersionMapper basicBasePcversionMapper;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CommonDictService commonDictService;
    @Autowired
    private CommonDictTypeService commonDictTypeService;

    /**
     *  Query   All 
     */
    public List<BasicBasePcVersionListVO> getBasicBasePcversionAll(BasicBasePcVersionQueryVO vo) {
        String hid = request.getHeader("id");
        String resourceIdDictKey = "dp" + hid.substring(0, hid.length() - 1);
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        CommonDictType commonDictType = commonDictTypeService.getDictTypeByKey(TokenUtils.extractTenantIdFromHttpReqeust(request), PermissionConstant.DATA_ACCESS_PERMISSION);
        CommonDict dict = commonDictService.getCommonDictByIdTenantIdAndDictType(resourceIdDictKey, tenantId, commonDictType.getId());
        if (dict == null) {
            vo.setStrategyCode(PermissionConstant.COMMON_DATA_ACCESS_ALL);
        } else {
            vo.setStrategyCode(dict.getDictValue());
        }
        vo.setSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        vo.setOrgCode(TokenUtils.extractOrgCode(request));
        vo.setTenantId(tenantId);
        PageHelper.startPage(vo.getIndex(), vo.getPerPage());
        return basicBasePcversionMapper.getBasicBasePcversionAll(vo);
    }


    /**
     * New  Method  
     */
    public int createBasicBasePcversionWithOrg(BasicBasePcVersionAddVO addVO) {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);

        long userId = Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request));
        BasicBasePcVersion add = new BasicBasePcVersion();
        BeanUtils.copyProperties(addVO, add);
        add.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
        add.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        add.setCreateTime(System.currentTimeMillis());
        add.setCreateBy(userId);
        add.setTenantId(tenantId);
        add.setUpdateBy(userId);
        add.setUpdateTime(System.currentTimeMillis());
        return basicBasePcversionMapper.createBasicBasePcversion(add);
    }

    /**
     * Update  Method  
     *
     * @param updateVO
     * @return
     */
    public int updateBasicBasePcversion(BasicBasePcVersionUpdateVO updateVO) {
        return basicBasePcversionMapper.update(null, Wrappers.lambdaUpdate(BasicBasePcVersion.class)
                .set(BasicBasePcVersion::getVersionNo, updateVO.getVersionNo())
                .set(BasicBasePcVersion::getVersionName, updateVO.getVersionName())
                .set(BasicBasePcVersion::getVersionContent, updateVO.getVersionContent())
                .set(BasicBasePcVersion::getMessageShutdownTime, updateVO.getMessageShutdownTime())
                .set(BasicBasePcVersion::getUpdateTime, System.currentTimeMillis())
                .set(BasicBasePcVersion::getUpdateBy, Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request)))
                .set(BasicBasePcVersion::getRequirementDate,updateVO.getRequirementDate())
                .eq(BasicBasePcVersion::getId, updateVO.getId()));
    }

    /**
     * Delete   Method  
     */
    public int delBasicBasePcversionById(Long id) {
        return basicBasePcversionMapper.delBasicBasePcversionById(id);
    }

    /**
     * 批量Delete 
     *
     * @param ids
     * @return
     */
    public int delBasicBasePcversionByIds(List<Long> ids) {
        int update = basicBasePcversionMapper.update(null, Wrappers.lambdaUpdate(BasicBasePcVersion.class)
                .set(BasicBasePcVersion::getDeleteTime, System.currentTimeMillis())
                .in(BasicBasePcVersion::getId, ids));
        return update;
    }


}
