package com.matariky.commonservice.base.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.matariky.commonservice.base.bean.BasicBaseDeviceRule;
import com.matariky.commonservice.base.bean.BasicBaseDeviceRuleDetail;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceRuleDetailMapper;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceRuleMapper;
import com.matariky.commonservice.base.vo.BasicBaseDeviceRuleAddVO;
import com.matariky.commonservice.base.vo.BasicBaseDeviceRuleDetailAddVO;
import com.matariky.commonservice.base.vo.BasicBaseDeviceRuleUpdateVO;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.utils.BeanUtils;
import com.matariky.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 *  Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
public class BasicBaseDeviceRuleService extends BaseServiceImpl<BasicBaseDeviceRuleMapper, BasicBaseDeviceRule> implements BaseService<BasicBaseDeviceRule> {

    @Autowired
    private BasicBaseDeviceRuleMapper basicBaseDeviceruleMapper;
    @Autowired
    private BasicBaseDeviceRuleDetailMapper basicBaseDeviceruleDetailMapper;
    @Autowired
    private HttpServletRequest request;

    /**
     * New Rule  Configuration 
     */
    public Long createBasicBaseDeviceruleWithOrg(BasicBaseDeviceRuleAddVO addVO, String jwt) {
        String  tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);

        long currentTime = System.currentTimeMillis();
        long userId = Long.parseLong(TokenUtils.extractUserIdFromToken(jwt));
        String operatorOrgCode = TokenUtils.extractOrgCode(request);
        String operatorSelfOrgCode = TokenUtils.extractSelfOrgCode(request);
        BasicBaseDeviceRule basicBaseDevicerule = basicBaseDeviceruleMapper.selectOne(Wrappers.lambdaQuery(BasicBaseDeviceRule.class)
                .eq(BasicBaseDeviceRule::getDeleteTime, 0)
                .eq(BasicBaseDeviceRule::getTypeId, addVO.getTypeId()));
        if (basicBaseDevicerule != null) {
            return basicBaseDevicerule.getId();
        }
        BasicBaseDeviceRule add = new BasicBaseDeviceRule();
        BeanUtils.copyProperties(addVO, add);
        add.setOperatorOrgCode(operatorOrgCode);
        add.setOperatorSelfOrgCode(operatorSelfOrgCode);
        add.setCreateTime(currentTime);
        add.setCreateBy(userId);
        add.setTenantId(tenantId);
        basicBaseDeviceruleMapper.createBasicBaseDevicerule(add);
        return add.getId();
    }

    /**
     *   Update Device  Rule  Configuration 
     *
     * @param updateVO
     * @param jwt
     * @return
     */
    public int updateBasicBaseDevicerule(BasicBaseDeviceRuleUpdateVO updateVO, String jwt) {
        BasicBaseDeviceRule update = new BasicBaseDeviceRule();
        update.setId(updateVO.getId());
        update.setIsRecordLog(updateVO.getIsRecordLog() ? 1 : 0);
        update.setUpdateBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
        update.setUpdateTime(System.currentTimeMillis());
        return basicBaseDeviceruleMapper.updateById(update);
    }

    /**
     * New Rule  Detail
     *
     * @param addVO
     * @param jwt
     * @return
     */
    public int createBasicBaseDeviceruleDetailWithOrg(BasicBaseDeviceRuleDetailAddVO addVO, String jwt) {
        long currentTime = System.currentTimeMillis();
        long userId = Long.parseLong(TokenUtils.extractUserIdFromToken(jwt));
        String operatorOrgCode = TokenUtils.extractOrgCode(request);
        String operatorSelfOrgCode = TokenUtils.extractSelfOrgCode(request);

        Long count = basicBaseDeviceruleDetailMapper.selectCount(Wrappers.lambdaQuery(BasicBaseDeviceRuleDetail.class)
                .eq(BasicBaseDeviceRuleDetail::getDeleteTime, 0)
                .eq(BasicBaseDeviceRuleDetail::getFilterConditions, addVO.getFilterConditions())
                .eq(BasicBaseDeviceRuleDetail::getConditionSetting, addVO.getConditionSetting())
                .eq(BasicBaseDeviceRuleDetail::getRuleId, addVO.getRuleId()));
        if (count > 0) {
            throw new QslException(MessageKey.DEVICE_RULE_NOT_REPEAT);
        }

        BasicBaseDeviceRuleDetail detail = new BasicBaseDeviceRuleDetail();
        BeanUtils.copyProperties(addVO, detail);
        detail.setOperatorOrgCode(operatorOrgCode);
        detail.setOperatorSelfOrgCode(operatorSelfOrgCode);
        detail.setCreateTime(currentTime);
        detail.setCreateBy(userId);
        detail.setUpdateTime(currentTime);
        detail.setUpdateBy(userId);
        return basicBaseDeviceruleDetailMapper.createBasicBaseDeviceruleDetail(detail);
    }

}
