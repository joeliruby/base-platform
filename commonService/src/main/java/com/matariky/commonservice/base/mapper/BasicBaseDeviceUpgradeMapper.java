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
     * New  Method  
     */
    public int createBasicBaseDeviceupgrade(BasicBaseDeviceUpgrade bean);

    /**
     * Upgraded Device  Pagination 
     * @param packageId
     * @return
     */
    public List<UpgradeDeviceVO> getUpgradeDeviceList(@Param("packageId") Long packageId);

    /**
     *  Pagination
     * @return
     */
    public  List<BasicBaseDeviceUpgradeResVO>  getBasicBaseDeviceupgradeAll(@Param("params") UpgradeListVO vo);
}
