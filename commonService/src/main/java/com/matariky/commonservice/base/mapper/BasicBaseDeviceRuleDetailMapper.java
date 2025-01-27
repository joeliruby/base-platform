package com.matariky.commonservice.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.matariky.annotation.DataScope;
import com.matariky.commonservice.base.bean.BasicBaseDeviceRuleDetail;
import com.matariky.commonservice.base.vo.BasicBaseDeviceRuleDetailListVO;
import com.matariky.commonservice.base.vo.BasicBaseDeviceRuleDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Persistence Interface
 *
 * @author AUTOMATION
 */
public interface BasicBaseDeviceRuleDetailMapper extends BaseMapper<BasicBaseDeviceRuleDetail> {
    /**
     *  Query   All 
     */
    @DataScope(alias = "r")
    public List<BasicBaseDeviceRuleDetailVO> getBasicBaseDeviceruleDetailAll(BasicBaseDeviceRuleDetailListVO vo);

    /**
     * New  Method  
     */
    public int createBasicBaseDeviceruleDetail(BasicBaseDeviceRuleDetail bean);


    /**
     * Delete   Method  
     */
    public int delBasicBaseDeviceruleDetailById(Long id);

    /**
     *
     * @param epcRule
     * @return
     */
    public int getCountByEpcRule(@Param("epcRule") Long epcRule);

    /**
     *
     * @param epcRule
     * @return
     */
    public int getRfidPrictCountByEpcRule(@Param("epcRule") Long epcRule);
}
