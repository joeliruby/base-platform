package com.matariky.commonservice.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.matariky.commonservice.base.bean.BasicBaseDeviceRule;

/**
 *  Persistence Interface
 *
 * @author AUTOMATION
 */
public interface BasicBaseDeviceRuleMapper extends BaseMapper<BasicBaseDeviceRule> {


    /**
     * New  Method  
     */
    public Long createBasicBaseDevicerule(BasicBaseDeviceRule bean);


}
