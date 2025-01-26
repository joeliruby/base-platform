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
     *  Query 所有分页
     */
    @DataScope(alias = "r")
    public List<BasicBaseDeviceRuleDetailVO> getBasicBaseDeviceruleDetailAll(BasicBaseDeviceRuleDetailListVO vo);

    /**
     * New方法
     */
    public int createBasicBaseDeviceruleDetail(BasicBaseDeviceRuleDetail bean);


    /**
     * 删除方法
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
