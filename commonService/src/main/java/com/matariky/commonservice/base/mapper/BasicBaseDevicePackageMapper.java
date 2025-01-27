package com.matariky.commonservice.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.matariky.annotation.DataScope;
import com.matariky.commonservice.base.bean.BasicBaseDevicePackage;
import com.matariky.commonservice.base.vo.BasicBaseDevicePackageInfoVO;
import com.matariky.commonservice.base.vo.BasicBaseDeviceUpgradeListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface BasicBaseDevicePackageMapper extends BaseMapper<BasicBaseDevicePackage>{

	/**
	 *  Query   All 
	 */
	@DataScope(alias = "p")
	public List<BasicBaseDevicePackageInfoVO> getBasicBaseDevicepackageAll(@Param("params") BasicBaseDeviceUpgradeListVO vo);

	/**
	 * New  Method  
	 * @param bean
	 * @return
	 */
	public int createBasicBaseDevicepackage(BasicBaseDevicePackage bean);

	/**
	 * Delete   Method  
	 * @param id
	 * @return
	 */
	public int delBasicBaseDevicepackageById(Long id);


}
