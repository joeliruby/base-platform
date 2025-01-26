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
import com.matariky.commonservice.base.bean.BasicBaseFormExtend;
import com.matariky.commonservice.base.vo.BasicBaseFormExtendQueryVO;
import org.apache.ibatis.annotations.Param;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface BasicBaseFormExtendMapper extends BaseMapper<BasicBaseFormExtend>{
	 
@DataScope(alias="t")	public List<BasicBaseFormExtend> getBasicBaseFormExtendAll(@Param("params") BasicBaseFormExtend bean);
	 
	public int getBasicBaseFormExtendAllCount();
	 
	public int createBasicBaseFormExtend(BasicBaseFormExtend bean);


	public List<BasicBaseFormExtend>  selectExtendClearList();

	 
	public int updateBasicBaseFormExtend(@Param("params") BasicBaseFormExtend bean);
	 
	public int delBasicBaseFormExtendById(Long id);
	 
	public BasicBaseFormExtend getBasicBaseFormExtendById(Long id);
	//插入一条记录
	@Override
	int insert(BasicBaseFormExtend record);
	//根据 ID 删除
	@Override
	int deleteById(Serializable id);
	//根据 columnMap 条件，删除记录
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件，删除记录
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormExtend> queryWrapper);
	//删除（根据ID 批量删除）
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//根据 ID 修改
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseFormExtend entity);
	//根据 whereEntity 条件，Update记录
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseFormExtend entity, @Param(Constants.WRAPPER) Wrapper<BasicBaseFormExtend> updateWrapper);
	//根据 ID  Query 
	@Override
	BasicBaseFormExtend selectById(Serializable id);
	 
	@Override
	List<BasicBaseFormExtend> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBaseFormExtend> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件， Query 一条记录
	@Override
	BasicBaseFormExtend selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormExtend> queryWrapper);
	//根据 Wrapper 条件， Query 总记录数
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormExtend> queryWrapper);

	public Integer getBasicBaseFormExtendCount(@Param("params") BasicBaseFormExtendQueryVO extend);

	//根据 entity 条件， Query 全部记录
	@Override
	List<BasicBaseFormExtend> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormExtend> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormExtend> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseFormExtend> queryWrapper);
	//根据 entity 条件， Query 全部记录（并翻页）
	 Page<BasicBaseFormExtend> selectPage(Page<BasicBaseFormExtend> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseFormExtend> queryWrapper);
	public List<BasicBaseFormExtend> getBasicBaseFormExtendDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBaseFormExtendDACCount(@Param("params") Map<String, Object> params);
	//根据 Wrapper 条件， Query 全部记录（并翻页）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseFormExtend> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseFormExtend> queryWrapper);


}
