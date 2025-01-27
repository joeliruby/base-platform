package com.matariky.commonservice.base.mapper;

import java.util.List;
import java.util.Map;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.matariky.annotation.DataScope;

import java.util.Collection;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.github.pagehelper.Page;
import com.matariky.commonservice.base.bean.BasicBaseFormConfig;
import com.matariky.commonservice.base.vo.AddExtendFieldDetailVO;
import com.matariky.commonservice.base.vo.AddExtendFieldInfoVO;
import org.apache.ibatis.annotations.Param;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface BasicBaseFormConfigMapper extends BaseMapper<BasicBaseFormConfig>{
	 
@DataScope(alias="t")	public List<BasicBaseFormConfig> getBasicBaseFormConfigAll(@Param("params") BasicBaseFormConfig bean);
	 
	public int getBasicBaseFormConfigAllCount();
	 
	public int createBasicBaseFormConfig(BasicBaseFormConfig bean);
	 
	public int updateBasicBaseFormConfig(@Param("params") BasicBaseFormConfig bean);
	 
	public int delBasicBaseFormConfigById(@Param("id") Long id);

	public int delBasicBaseFormConfigByName(@Param("name") String  name);

	public List<AddExtendFieldDetailVO> getBasicBaseFormConfigByName(@Param("name") String  name);

	public List<Long> selectConfigIds(@Param("tenantId") String tenantId);

	 
	public BasicBaseFormConfig getBasicBaseFormConfigById(Long id);
	//Insert a record
	@Override
	int insert(BasicBaseFormConfig record);
	//Delete By ID
	@Override
	int deleteById(Serializable id);
	//Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormConfig> queryWrapper);
	//Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseFormConfig entity);
	//Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseFormConfig entity, @Param(Constants.WRAPPER) Wrapper<BasicBaseFormConfig> updateWrapper);
	//Query By ID 
	@Override
	BasicBaseFormConfig selectById(Serializable id);
	 
	@Override
	List<BasicBaseFormConfig> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBaseFormConfig> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Query One By Entity
	@Override
	BasicBaseFormConfig selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormConfig> queryWrapper);
	//Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormConfig> queryWrapper);
	//Query All By Entity
	@Override
	List<BasicBaseFormConfig> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormConfig> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormConfig> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormConfig> queryWrapper);
	//Query All By Entity（ With Pagination ）
	 Page<BasicBaseFormConfig> selectPage(Page<BasicBaseFormConfig> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseFormConfig> queryWrapper);
	public List<BasicBaseFormConfig> getBasicBaseFormConfigDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBaseFormConfigDACCount(@Param("params") Map<String, Object> params);
	//Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseFormConfig> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseFormConfig> queryWrapper);


}
