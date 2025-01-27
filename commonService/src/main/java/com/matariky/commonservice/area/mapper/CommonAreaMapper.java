package com.matariky.commonservice.area.mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.pagehelper.Page;

import com.matariky.commonservice.area.bean.CommonArea;
import com.matariky.commonservice.area.bean.CommonAreaVo;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface CommonAreaMapper extends BaseMapper<CommonArea>{
	 
	public Page<CommonArea> getCommonAreaAll();
	 
	public int getCommonAreaAllCount();
	 
	public int createCommonArea(CommonArea bean);
	 
	public int updateCommonArea(@Param("params") CommonArea bean);
	 
	public int delCommonAreaById(int id);
	 
	public CommonArea getCommonAreaById(int id);
	//Insert a record 
	@Override
	int insert(CommonArea record);
	//Delete By ID
	@Override
	int deleteById(Serializable id);
	//Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<CommonArea> queryWrapper);
	//Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) CommonArea entity);
	//Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) CommonArea entity, @Param(Constants.WRAPPER) Wrapper<CommonArea> updateWrapper);
	//Query By ID   
	@Override
	CommonArea selectById(Serializable id);
	 
	@Override
	List<CommonArea> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<CommonArea> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	// Query a record based on entity condition
	@Override
	CommonArea selectOne(@Param(Constants.WRAPPER) Wrapper<CommonArea> queryWrapper);

	// Query total record count based on Wrapper condition
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<CommonArea> queryWrapper);

	// Query all records based on entity condition
	@Override
	List<CommonArea> selectList(@Param(Constants.WRAPPER) Wrapper<CommonArea> queryWrapper);

	// Query all records based on Wrapper condition
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<CommonArea> queryWrapper);

	// Query all records based on Wrapper condition
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<CommonArea> queryWrapper);

	// Query all records (with pagination) based on entity condition
	Page<CommonArea> selectPage(Page<CommonArea> page, @Param(Constants.WRAPPER) Wrapper<CommonArea> queryWrapper);

	// Query all records (with pagination) based on Wrapper condition

	
	Page<Map<String, Object>> selectMapsPage(Page<CommonArea> page, @Param(Constants.WRAPPER) Wrapper<CommonArea> queryWrapper);
	
	@Select("select id,area_code,area_name,level,city_code,center,parent_id from common_area where parent_id=#{parentId}")
	public List<CommonArea> getAreaByParentId(@Param("parentId") Long parentId);
	
	@Select("select * , count(c.id ) as childrenNo from (select * from common_area where parent_id =#{nodeId}) k left join common_area c on k.id=c.parent_id group by k.area_code")
	public List<CommonAreaVo> subNodesById(@Param("nodeId") Long nodeId);


}
