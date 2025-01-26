package com.matariky.commonservice.upload.mapper;




import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.matariky.commonservice.upload.bean.CommonOss;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface CommonOssMapper extends BaseMapper<CommonOss>{
	 
	public List<CommonOss> getCommonOssAll(@Param("params") Map<String, Object> map);
	 
	public int getCommonOssAllCount(@Param("params") Map<String, Object> map);
	 
	public int createCommonOss(CommonOss bean);
	 
	public int updateCommonOss(@Param("params") CommonOss bean);
	 
	public int delCommonOssById(Long id);
	 
	public CommonOss getCommonOssById(int id);

		@Override
	int insert(CommonOss entity);


	@Override
    int deleteById(Serializable id);


	 @Override
    int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);


	 @Override
    int delete(@Param(Constants.WRAPPER) Wrapper<CommonOss> queryWrapper);

     @Override
    int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);


	 @Override
    int updateById(@Param(Constants.ENTITY) CommonOss entity);


	 @Override
    int update(@Param(Constants.ENTITY) CommonOss entity, @Param(Constants.WRAPPER) Wrapper<CommonOss> updateWrapper);


	 @Override
    CommonOss selectById(Serializable id);


	 @Override
    List<CommonOss> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);


	 @Override
    List<CommonOss> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);


	 @Override
    CommonOss selectOne(@Param(Constants.WRAPPER) Wrapper<CommonOss> queryWrapper);


	 @Override
    Long selectCount(@Param(Constants.WRAPPER) Wrapper<CommonOss> queryWrapper);


	 @Override
    List<CommonOss> selectList(@Param(Constants.WRAPPER) Wrapper<CommonOss> queryWrapper);


	@Override
    List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<CommonOss> queryWrapper);


	 @Override
    List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<CommonOss> queryWrapper);


    Page<CommonOss> selectPage(Page<CommonOss> page, @Param(Constants.WRAPPER) Wrapper<CommonOss> queryWrapper);

    Page<Map<String, Object>> selectMapsPage(Page<CommonOss> page, @Param(Constants.WRAPPER) Wrapper<CommonOss> queryWrapper);
     
	public int updateDeleteTimeById(@Param("array")String[] id);


}
