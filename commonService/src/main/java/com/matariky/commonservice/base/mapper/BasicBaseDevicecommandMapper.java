package com.matariky.commonservice.base.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.pagehelper.Page;
import com.matariky.annotation.DataScope;
import com.matariky.commonservice.base.bean.BasicBaseDevicecommand;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *  Persistence Interface
 *
 * @author AUTOMATION
 */
public interface BasicBaseDevicecommandMapper extends BaseMapper<BasicBaseDevicecommand> {
     
    @DataScope(alias = "t")
    public List<BasicBaseDevicecommand> getBasicBaseDevicecommandAll(@Param("params") BasicBaseDevicecommand bean);

     
    public int getBasicBaseDevicecommandAllCount();

     
    public int createBasicBaseDevicecommand(BasicBaseDevicecommand bean);

     
    public int updateBasicBaseDevicecommand(@Param("params") BasicBaseDevicecommand bean);

     
    public int delBasicBaseDevicecommandById(int id);

     
    public BasicBaseDevicecommand getBasicBaseDevicecommandById(int id);

    //Insert a record
    @Override
    int insert(BasicBaseDevicecommand record);

    //Delete By ID
    @Override
    int deleteById(Serializable id);

    //Delete By Column Map
    @Override
    int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    //Delete By Entity
    @Override
    int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommand> queryWrapper);

    //Delete Batch By IDs
    @Override
    int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

    //Update By IDs
    @Override
    int updateById(@Param(Constants.ENTITY) BasicBaseDevicecommand entity);

    //Update By Entity
    @Override
    int update(@Param(Constants.ENTITY) BasicBaseDevicecommand entity, @Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommand> updateWrapper);

    //Query By ID 
    @Override
    BasicBaseDevicecommand selectById(Serializable id);

     
    @Override
    List<BasicBaseDevicecommand> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

     
    @Override
    List<BasicBaseDevicecommand> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    //Query One By Entity
    @Override
    BasicBaseDevicecommand selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommand> queryWrapper);

    //Query Count By Wrapper
    @Override
    Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommand> queryWrapper);

    //Query All By Entity
    @Override
    List<BasicBaseDevicecommand> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommand> queryWrapper);

    //Query All Maps By Wrapper
    @Override
    List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommand> queryWrapper);

    //Query All Maps By Wrapper
    @Override
    List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommand> queryWrapper);

    //Query All By Entity（ With Pagination ）
    Page<BasicBaseDevicecommand> selectPage(Page<BasicBaseDevicecommand> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommand> queryWrapper);

    public List<BasicBaseDevicecommand> getBasicBaseDevicecommandDAC(@Param("params") Map<String, Object> params);

    public Long getBasicBaseDevicecommandDACCount(@Param("params") Map<String, Object> params);

    //Query All Maps By Wrapper（ With Pagination ）
    Page<Map<String, Object>> selectMapsPage(Page<BasicBaseDevicecommand> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommand> queryWrapper);


}
