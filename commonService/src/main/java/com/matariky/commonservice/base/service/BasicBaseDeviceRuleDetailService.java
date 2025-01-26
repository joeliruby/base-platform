package com.matariky.commonservice.base.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.matariky.commonservice.base.bean.BasicBaseDeviceRule;
import com.matariky.commonservice.base.bean.BasicBaseDeviceRuleDetail;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceRuleDetailMapper;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceRuleMapper;
import com.matariky.commonservice.base.vo.BasicBaseDeviceRuleDetailInfo;
import com.matariky.commonservice.base.vo.BasicBaseDeviceRuleDetailListVO;
import com.matariky.commonservice.base.vo.BasicBaseDeviceRuleDetailUpdateByIdVO;
import com.matariky.commonservice.base.vo.BasicBaseDeviceRuleDetailVO;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.constant.PermissionConstant;
import com.matariky.exception.QslException;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.utils.BeanUtils;
import com.matariky.utils.StringUtils;
import com.matariky.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *  Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
public class BasicBaseDeviceRuleDetailService extends BaseServiceImpl<BasicBaseDeviceRuleDetailMapper, BasicBaseDeviceRuleDetail> implements BaseService<BasicBaseDeviceRuleDetail> {

    @Autowired
    private BasicBaseDeviceRuleDetailMapper basicBaseDeviceruleDetailMapper;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CommonDictService commonDictService;
    @Autowired
    private CommonDictTypeService commonDictTypeService;
    @Autowired
    private BasicBaseDeviceRuleMapper basicBaseDeviceruleMapper;

    /**
     *  Query 所有分页
     */
    public BasicBaseDeviceRuleDetailInfo getBasicBaseDeviceruleDetailAll(BasicBaseDeviceRuleDetailListVO vo, String jwt) {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        BasicBaseDeviceRuleDetailInfo result = new BasicBaseDeviceRuleDetailInfo();
        String hid = request.getHeader("id");
        String resourceIdDictKey = "dp" + hid.substring(0, hid.length() - 1);
        CommonDictType commonDictType = commonDictTypeService.getDictTypeByKey(TokenUtils.extractTenantIdFromHttpReqeust(request), PermissionConstant.DATA_ACCESS_PERMISSION);
        CommonDict dict = commonDictService.getCommonDictByIdTenantIdAndDictType(resourceIdDictKey, tenantId, commonDictType.getId());
        if (dict == null) {
            vo.setStrategyCode(PermissionConstant.COMMON_DATA_ACCESS_ALL);
        } else {
            vo.setStrategyCode(dict.getDictValue());
        }
        vo.setSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        vo.setOrgCode(TokenUtils.extractOrgCode(request));
        Long typeId = vo.getTypeId();
        BasicBaseDeviceRule basicBaseDevicerule = basicBaseDeviceruleMapper.selectOne(Wrappers.lambdaQuery(BasicBaseDeviceRule.class)
                .eq(BasicBaseDeviceRule::getTypeId, typeId)
                .eq(BasicBaseDeviceRule::getDeleteTime, 0));
        if (basicBaseDevicerule == null) {
            BasicBaseDeviceRule add = new BasicBaseDeviceRule();
            add.setTypeId(typeId);
            add.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
            add.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
            add.setCreateTime(System.currentTimeMillis());
            add.setCreateBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
            add.setTenantId(tenantId);
            basicBaseDeviceruleMapper.createBasicBaseDevicerule(add);
            result.setList(new ArrayList<>());
            result.setRuleId(add.getId());
            result.setIsRecordLog(false);
            return result;
        }
        vo.setRuleId(basicBaseDevicerule.getId());
        result.setRuleId(basicBaseDevicerule.getId());
        List<BasicBaseDeviceRuleDetailVO> list = basicBaseDeviceruleDetailMapper.getBasicBaseDeviceruleDetailAll(vo);
        list.stream().forEach(item -> {
            if (StringUtils.isBlank(item.getSetValueName())) {
                item.setSetValueName(item.getSetValue());
            }
        });
        result.setList(list);
        result.setIsRecordLog((basicBaseDevicerule.getIsRecordLog() != null && basicBaseDevicerule.getIsRecordLog() == 1) ? true : false);
        return result;
    }


    /**
     * 修改 Detail
     *
     * @param updateVO
     * @param jwt
     * @return
     */
    public int updateBasicBaseDeviceruleDetail(BasicBaseDeviceRuleDetailUpdateByIdVO updateVO, String jwt) {
        BasicBaseDeviceRuleDetail ruleDetail = basicBaseDeviceruleDetailMapper.selectById(updateVO.getId());
        if (ruleDetail == null) {
            throw new QslException(MessageKey.DEVICE_RULE_DETAIL_NOT_EXIST);
        }
        Long count = basicBaseDeviceruleDetailMapper.selectCount(Wrappers.lambdaQuery(BasicBaseDeviceRuleDetail.class)
                .eq(BasicBaseDeviceRuleDetail::getDeleteTime, 0)
                .eq(BasicBaseDeviceRuleDetail::getFilterConditions, updateVO.getFilterConditions())
                .eq(BasicBaseDeviceRuleDetail::getConditionSetting, updateVO.getConditionSetting())
                .eq(BasicBaseDeviceRuleDetail::getRuleId, ruleDetail.getRuleId())
                .ne(BasicBaseDeviceRuleDetail::getId, updateVO.getId()));
        if (count > 0) {
            throw new QslException(MessageKey.DEVICE_RULE_NOT_REPEAT);
        }
        BasicBaseDeviceRuleDetail update = new BasicBaseDeviceRuleDetail();
        BeanUtils.copyProperties(updateVO, update);
        update.setUpdateBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
        update.setUpdateTime(System.currentTimeMillis());
        return basicBaseDeviceruleDetailMapper.updateById(update);
    }

    /**
     * 删除方法
     *
     * @param id
     * @return
     */
    public int delBasicBaseDeviceruleDetailById(Long id) {
        return basicBaseDeviceruleDetailMapper.delBasicBaseDeviceruleDetailById(id);
    }


}
