package com.matariky.commonservice.base.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.matariky.commonservice.base.bean.BasicBaseCodingRules;
import com.matariky.commonservice.base.bean.BasicBaseDeviceRuleDetail;
import com.matariky.commonservice.base.mapper.BasicBaseCodingRulesMapper;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceRuleDetailMapper;
import com.matariky.commonservice.base.vo.*;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.constant.PermissionConstant;
import com.matariky.exception.QslException;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.model.QueryDataIsolation;
import com.matariky.utils.BeanUtils;
import com.matariky.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  Business Inteface Implementation
 *
 * @author AUTOMATION
 */
@Service
public class BasicBaseCodingRulesService extends BaseServiceImpl<BasicBaseCodingRulesMapper, BasicBaseCodingRules> implements BaseService<BasicBaseCodingRules> {

    @Autowired
    private BasicBaseCodingRulesMapper basicBaseCodingrulesMapper;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CommonDictTypeService commonDictTypeService;
    @Autowired
    private CommonDictService commonDictService;
    @Autowired
    private BasicBaseDeviceRuleDetailMapper basicBaseDeviceRuleDetailMapper;

    /**
     *  Query 所有分页
     */
    public List<BasicBaseCodingRulesInfoVO> getBasicBaseCodingrulesAll(BasicBaseCodingRulesListVO vo) {
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
        return basicBaseCodingrulesMapper.getBasicBaseCodingrulesAll(vo);
    }

    /**
     * 获取 Rule 唯一编码
     *
     * @return
     */
    public String getCodingrulesCode() {
        String code = "R-00001";
        BasicBaseCodingRules basicBaseCodingRules = basicBaseCodingrulesMapper.selectOne(Wrappers.lambdaQuery(BasicBaseCodingRules.class)
                .eq(BasicBaseCodingRules::getDeleteTime, 0)
                .orderByDesc(BasicBaseCodingRules::getCreateTime)
                .last("limit 1"));
        if (basicBaseCodingRules == null) {
            return code;
        }
        code = basicBaseCodingRules.getRulesCode();
        if (code == null) {
            return "R-00001";
        }
        String[] split = code.split("-");
        Integer number = Integer.valueOf("1" + split[1]);
        number++;
        String numberStr = number.toString().substring(1);
        code = split[0] + "-" + numberStr;
        return code;
    }

    /**
     * New方法
     */
    public int createBasicBaseCodingrulesWithOrg(BasicBaseCodingRulesAddVO addVO) {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        Long nameCount = basicBaseCodingrulesMapper.selectCount(Wrappers.lambdaQuery(BasicBaseCodingRules.class)
                .eq(BasicBaseCodingRules::getRulesName, addVO.getRulesName())
                .eq(BasicBaseCodingRules::getDeleteTime, 0)
                .eq(BasicBaseCodingRules::getTenantId, tenantId));
        if (nameCount > 0) {
            throw new QslException(MessageKey.RULES_NAME_NOT_REPEAT);
        }
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            String uniqueCode = "E1";
            BasicBaseCodingRules basicBaseCodingrules = basicBaseCodingrulesMapper.selectOne(Wrappers.lambdaQuery(BasicBaseCodingRules.class)
                    .eq(BasicBaseCodingRules::getDeleteTime, 0)
                    .eq(BasicBaseCodingRules::getTenantId, tenantId)
                    .orderByDesc(BasicBaseCodingRules::getCreateTime)
                    .last("limit  1"));
            if (basicBaseCodingrules != null) {
                uniqueCode = basicBaseCodingrules.getUniqueCode();
                uniqueCode = uniqueCode.substring(1);
                uniqueCode = String.valueOf(Integer.valueOf(uniqueCode) + 1);
                uniqueCode = "E" + uniqueCode;
            }
            long userId = Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request));
            BasicBaseCodingRules add = new BasicBaseCodingRules();
            BeanUtils.copyProperties(addVO, add);
            add.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
            add.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
            add.setCreateTime(System.currentTimeMillis());
            add.setCreateBy(userId);
            add.setTenantId(tenantId);
            add.setUniqueCode(uniqueCode);
            add.setUpdateBy(userId);
            add.setUpdateTime(System.currentTimeMillis());
            add.setRulesCode(addVO.getRulesCode());
            return basicBaseCodingrulesMapper.createBasicBaseCodingrules(add);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 修改方法
     *
     * @param updateVO
     * @return
     */
    public int updateBasicBaseCodingrules(BasicBaseCodingRulesUpdateVO updateVO) {
        Integer count = basicBaseCodingrulesMapper.selectRuleCountFromrfidfactory(updateVO.getId());
        if (count > 0) {
            throw new QslException(MessageKey.RULE_USED_CANT_NOT_EDIT);
        }
        count = basicBaseCodingrulesMapper.selectRuleCountFromrrfidprint(updateVO.getId());
        if (count > 0) {
            throw new QslException(MessageKey.RULE_USED_CANT_NOT_EDIT);
        }
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        Long nameCount = basicBaseCodingrulesMapper.selectCount(Wrappers.lambdaQuery(BasicBaseCodingRules.class)
                .eq(BasicBaseCodingRules::getRulesName, updateVO.getRulesName())
                .eq(BasicBaseCodingRules::getDeleteTime, 0)
                .eq(BasicBaseCodingRules::getTenantId, tenantId)
                .ne(BasicBaseCodingRules::getId, updateVO.getId())
        );
        if (nameCount > 0) {
            throw new QslException(MessageKey.RULES_NAME_NOT_REPEAT);
        }
        BasicBaseCodingRules update = new BasicBaseCodingRules();
        BeanUtils.copyProperties(updateVO, update);
        update.setUpdateTime(System.currentTimeMillis());
        update.setUpdateBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request)));
        return basicBaseCodingrulesMapper.updateById(update);
    }

    /**
     * Update Status 
     *
     * @param vo
     */
    public void updateBasicBaseCodingrulesStatus(BasicBaseCodingRulesUpdateStatusVO vo) {
        BasicBaseCodingRules updateStatus = new BasicBaseCodingRules();
        updateStatus.setId(vo.getId());
        updateStatus.setStatus(vo.getStatus());
        basicBaseCodingrulesMapper.updateById(updateStatus);
    }


    /**
     * 删除方法
     */
    public int delBasicBaseCodingrulesById(Long id) {
        Long count = basicBaseDeviceRuleDetailMapper.selectCount(Wrappers.lambdaQuery(BasicBaseDeviceRuleDetail.class)
                .eq(BasicBaseDeviceRuleDetail::getSetValue, id)
                .eq(BasicBaseDeviceRuleDetail::getDeleteTime, 0));
        if (count > 0) {
            throw new QslException(MessageKey.BASE_CODING_RULES_USED);
        }
        Integer ruleCount = basicBaseDeviceRuleDetailMapper.getCountByEpcRule(id);
        if (ruleCount > 0) {
            throw new QslException(MessageKey.BASE_CODING_RULES_USED);
        }
        ruleCount = basicBaseDeviceRuleDetailMapper.getRfidPrictCountByEpcRule(id);
        if (ruleCount > 0) {
            throw new QslException(MessageKey.BASE_CODING_RULES_USED);
        }
        return basicBaseCodingrulesMapper.delBasicBaseCodingrulesById(id);
    }

    /**
     * 下拉选列表
     *
     * @param
     */
    public List<CodingRulesOptionInfo> optionList() {
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        QueryDataIsolation vo = new QueryDataIsolation();
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
        vo.setTenantId(tenantId);
        List<CodingRulesOptionInfo> optionList = basicBaseCodingrulesMapper.getOptionList(vo);
        optionList.stream().forEach(item -> {
            item.setLabel(item.getRulesName());
        });
        return optionList;
    }

}
