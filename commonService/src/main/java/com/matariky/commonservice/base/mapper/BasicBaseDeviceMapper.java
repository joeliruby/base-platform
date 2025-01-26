package com.matariky.commonservice.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.matariky.annotation.DataScope;
import com.matariky.commonservice.base.bean.BasicBaseDevice;
import com.matariky.commonservice.base.vo.*;
import com.matariky.model.QueryDataIsolation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Persistence Interface
 *
 * @author AUTOMATION
 */
public interface BasicBaseDeviceMapper extends BaseMapper<BasicBaseDevice> {

    /**
     *  Query 所有分页
     */
    @DataScope(alias = "d")
    public List<BasicBaseDeviceInfoVO> getBasicBaseDeviceAll(@Param("params") BasicBaseDeviceListVO vo);

    /**
     * New方法
     *
     * @param bean
     * @return
     */
    public int createBasicBaseDevice(BasicBaseDevice bean);


    public int selectCountFromPrint(@Param("deviceId") Long deviceId);

    /**
     * 删除方法
     */
    public int delBasicBaseDeviceById(Long id);

    /**
     *  Print 机-下拉选
     *
     * @param queryDataIsolation
     * @return
     */
    @DataScope(alias = "a")
    public List<PrintOptionInfo> getPrintOptionList(@Param("params") QueryDataIsolation queryDataIsolation);

    /**
     *  Device 编码下拉选
     *
     * @return
     */
    @DataScope(alias = "a")
    public List<DeviceCodeInfo> getCodeOptionList(@Param("params") CodeOptionListVO vo);

    public BasicBaseDevice selectPrint(String mac);

    public BasicBaseDevice selectPrintByCode(String code);

}
