package com.matariky.commonservice.commondict.mapper;





import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.pagehelper.Page;

import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;

/**
* Persistence Interface
* @author AUTOMATION
*/
public interface CommonDictTypeMapper extends BaseMapper<CommonDictType>{
	 
	public Page<CommonDictType> getCommonDictTypeAll(@Param("params") Map<String, Object> params);
	 
	public int getCommonDictTypeAllCount(@Param("params") Map<String, Object> params);
	 
	public int createCommonDictType(CommonDictType bean);
	 
	public int updateCommonDictType(@Param("params") CommonDictType bean);
	 
	public int delCommonDictTypeById(Long id);
	 
	public CommonDictType getCommonDictTypeById(String id);

	//根据字典组的key Query 下面所有的字典详情
	public List<CommonDict> getDictsByDictTypeKey(@Param("tenantId")String tenantId,
												  @Param("dictTypeKey")String dictTypeKey,
												  @Param("isActive")Long isActive,
												  @Param("deleteTime")Long deleteTime) ;

		@Override
	int insert(CommonDictType entity);


	@Override
    int deleteById(Serializable id);


	 @Override
    int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);


	 @Override
    int delete(@Param(Constants.WRAPPER) Wrapper<CommonDictType> queryWrapper);

     @Override
    int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);


	 @Override
    int updateById(@Param(Constants.ENTITY) CommonDictType entity);


	 @Override
    int update(@Param(Constants.ENTITY) CommonDictType entity, @Param(Constants.WRAPPER) Wrapper<CommonDictType> updateWrapper);


	 @Override
    CommonDictType selectById(Serializable id);


	 @Override
    List<CommonDictType> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);


	 @Override
    List<CommonDictType> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);


	 //@Override
    //CommonDictType selectOne(@Param(Constants.WRAPPER) Wrapper<CommonDictType> queryWrapper);


	 @Override
    Long selectCount(@Param(Constants.WRAPPER) Wrapper<CommonDictType> queryWrapper);


	 @Override
    List<CommonDictType> selectList(@Param(Constants.WRAPPER) Wrapper<CommonDictType> queryWrapper);


	@Override
    List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<CommonDictType> queryWrapper);


	 @Override
    List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<CommonDictType> queryWrapper);


    Page<CommonDictType> selectPage(Page<CommonDictType> page, @Param(Constants.WRAPPER) Wrapper<CommonDictType> queryWrapper);

    Page<Map<String, Object>> selectMapsPage(Page<CommonDictType> page, @Param(Constants.WRAPPER) Wrapper<CommonDictType> queryWrapper);


    public int updateDeleteTimeById(@Param("array")String[] id);

	public CommonDictType getDictTypeByKey(@Param("tenantId")String tenantId, @Param("dictTypeKey")String dictTypeKey);




}
