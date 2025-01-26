package com.matariky.commonservice.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.matariky.annotation.DataScope;
import com.matariky.commonservice.base.bean.BasicBaseCodingRules;
import com.matariky.commonservice.base.vo.BasicBaseCodingRulesInfoVO;
import com.matariky.commonservice.base.vo.BasicBaseCodingRulesListVO;
import com.matariky.commonservice.base.vo.CodingRulesOptionInfo;
import com.matariky.model.QueryDataIsolation;
import org.apache.ibatis.annotations.Param;

/**
 *  Persistence Interface
 *
 * @author AUTOMATION
 */
public interface BasicBaseCodingRulesMapper extends BaseMapper<BasicBaseCodingRules> {
    /**
     *  Query 所有分页
     */
    @DataScope(alias = "r")
    public List<BasicBaseCodingRulesInfoVO> getBasicBaseCodingrulesAll(@Param("params") BasicBaseCodingRulesListVO vo);

    /**
     * 删除方法
     */
    public int delBasicBaseCodingrulesById(Long id);


    /**
     * New方法
     */
    public int createBasicBaseCodingrules(BasicBaseCodingRules bean);

    /**
     * 下拉选
     * @param queryDataIsolation
     * @return
     */
    @DataScope(alias = StringUtils.EMPTY)
    public  List<CodingRulesOptionInfo> getOptionList(@Param("params") QueryDataIsolation queryDataIsolation);


    /**
     *  Query   rule id  Wether 被  Label  使用
     * @param id
     * @return
     */
    public Integer selectRuleCountFromrfidfactory(@Param("id") Long id);


    public Integer selectRuleCountFromrrfidprint(@Param("id") Long id);
}
