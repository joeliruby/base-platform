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
	//插入一条记录
	@Override
	int insert(BasicBaseFormConfig record);
	//根据 ID 删除
	@Override
	int deleteById(Serializable id);
	//根据 columnMap 条件，删除记录
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件，删除记录
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormConfig> queryWrapper);
	//删除（根据ID 批量删除）
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//根据 ID 修改
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseFormConfig entity);
	//根据 whereEntity 条件，Update记录
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseFormConfig entity, @Param(Constants.WRAPPER) Wrapper<BasicBaseFormConfig> updateWrapper);
	//根据 ID  Query 
	@Override
	BasicBaseFormConfig selectById(Serializable id);
	 
	@Override
	List<BasicBaseFormConfig> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBaseFormConfig> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件， Query 一条记录
	@Override
	BasicBaseFormConfig selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormConfig> queryWrapper);
	//根据 Wrapper 条件， Query 总记录数
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormConfig> queryWrapper);
	//根据 entity 条件， Query 全部记录
	@Override
	List<BasicBaseFormConfig> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormConfig> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormConfig> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormConfig> queryWrapper);
	//根据 entity 条件， Query 全部记录（并翻页）
	 Page<BasicBaseFormConfig> selectPage(Page<BasicBaseFormConfig> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseFormConfig> queryWrapper);
	public List<BasicBaseFormConfig> getBasicBaseFormConfigDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBaseFormConfigDACCount(@Param("params") Map<String, Object> params);
	//根据 Wrapper 条件， Query 全部记录（并翻页）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseFormConfig> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseFormConfig> queryWrapper);


}
