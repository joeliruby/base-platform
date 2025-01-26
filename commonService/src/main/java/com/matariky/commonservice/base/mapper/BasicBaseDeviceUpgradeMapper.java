package com.matariky.commonservice.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.matariky.commonservice.base.bean.BasicBaseDeviceUpgrade;
import com.matariky.commonservice.base.vo.BasicBaseDeviceUpgradeResVO;
import com.matariky.commonservice.base.vo.UpgradeDeviceVO;
import com.matariky.commonservice.base.vo.UpgradeListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Persistence Interface
 *
 * @author AUTOMATION
 */
public interface BasicBaseDeviceUpgradeMapper extends BaseMapper<BasicBaseDeviceUpgrade> {

    /**
     * New方法
     */
    public int createBasicBaseDeviceupgrade(BasicBaseDeviceUpgrade bean);

    /**
     * 已经升级过包的 Device 列表
     * @param packageId
     * @return
     */
    public List<UpgradeDeviceVO> getUpgradeDeviceList(@Param("packageId") Long packageId);

    /**
     * 分页列表
     * @return
     */
    public  List<BasicBaseDeviceUpgradeResVO>  getBasicBaseDeviceupgradeAll(@Param("params") UpgradeListVO vo);
}
