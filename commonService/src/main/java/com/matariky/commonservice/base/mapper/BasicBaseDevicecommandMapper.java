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

    //插入一条记录
    @Override
    int insert(BasicBaseDevicecommand record);

    //根据 ID 删除
    @Override
    int deleteById(Serializable id);

    //根据 columnMap 条件，删除记录
    @Override
    int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    //根据 entity 条件，删除记录
    @Override
    int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommand> queryWrapper);

    //删除（根据ID 批量删除）
    @Override
    int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

    //根据 ID 修改
    @Override
    int updateById(@Param(Constants.ENTITY) BasicBaseDevicecommand entity);

    //根据 whereEntity 条件，Update记录
    @Override
    int update(@Param(Constants.ENTITY) BasicBaseDevicecommand entity, @Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommand> updateWrapper);

    //根据 ID  Query 
    @Override
    BasicBaseDevicecommand selectById(Serializable id);

     
    @Override
    List<BasicBaseDevicecommand> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

     
    @Override
    List<BasicBaseDevicecommand> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    //根据 entity 条件， Query 一条记录
    @Override
    BasicBaseDevicecommand selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommand> queryWrapper);

    //根据 Wrapper 条件， Query 总记录数
    @Override
    Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommand> queryWrapper);

    //根据 entity 条件， Query 全部记录
    @Override
    List<BasicBaseDevicecommand> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommand> queryWrapper);

    //根据 Wrapper 条件， Query 全部记录
    @Override
    List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommand> queryWrapper);

    //根据 Wrapper 条件， Query 全部记录
    @Override
    List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommand> queryWrapper);

    //根据 entity 条件， Query 全部记录（并翻页）
    Page<BasicBaseDevicecommand> selectPage(Page<BasicBaseDevicecommand> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommand> queryWrapper);

    public List<BasicBaseDevicecommand> getBasicBaseDevicecommandDAC(@Param("params") Map<String, Object> params);

    public Long getBasicBaseDevicecommandDACCount(@Param("params") Map<String, Object> params);

    //根据 Wrapper 条件， Query 全部记录（并翻页）
    Page<Map<String, Object>> selectMapsPage(Page<BasicBaseDevicecommand> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseDevicecommand> queryWrapper);


}
