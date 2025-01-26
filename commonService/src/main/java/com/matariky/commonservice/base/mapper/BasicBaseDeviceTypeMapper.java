package com.matariky.commonservice.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.matariky.annotation.DataScope;
import com.matariky.commonservice.base.bean.BasicBaseDeviceType;
import com.matariky.commonservice.base.vo.BasicBaseDeviceTypeInfoVO;
import com.matariky.commonservice.base.vo.BasicBaseDeviceTypeListVO;
import com.matariky.commonservice.base.vo.DeviceTypeOption;
import com.matariky.model.QueryDataIsolation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Persistence Interface
 *
 * @author AUTOMATION
 */
public interface BasicBaseDeviceTypeMapper extends BaseMapper<BasicBaseDeviceType> {
    /**
     *  Query 所有分页
     */
    @DataScope(alias = "t")
    public List<BasicBaseDeviceTypeInfoVO> getBasicBaseDevicetypeAll(@Param("params") BasicBaseDeviceTypeListVO vo);

    /**
     *  Device Type 下拉选
     *
     * @return
     */
    @DataScope(alias = "t")
    public List<DeviceTypeOption> getOptionList(QueryDataIsolation vo);

    /**
     * New方法
     */
    public int createBasicBaseDevicetype(BasicBaseDeviceType bean);


    /**
     * 删除方法
     *
     * @param id
     * @return
     */
    public int delBasicBaseDevicetypeById(Long id);

    /**
     * Query Detail By ID
     * @param id
     * @return
     */
    public BasicBaseDeviceType  getBasicBaseDevicetypeById(@Param("id") Long id);

}
