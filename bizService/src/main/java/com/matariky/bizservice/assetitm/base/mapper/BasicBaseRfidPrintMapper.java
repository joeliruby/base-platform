package com.matariky.bizservice.assetitm.base.mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.pagehelper.Page;
import com.matariky.annotation.DataScope;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidPrint;
import com.matariky.mybatis.EnhanceBaseMapper;

/**
 * Persistence Interface
 * 
 * @author AUTOMATION
 */
public interface BasicBaseRfidPrintMapper extends EnhanceBaseMapper<BasicBaseRfidPrint> {

	@DataScope(alias = "a")
	public List<BasicBaseRfidPrint> getBasicBaseRfidprintAll(@Param("params") BasicBaseRfidPrint bean);

	public int getBasicBaseRfidprintAllCount();

	public int createBasicBaseRfidprint(BasicBaseRfidPrint bean);

	public int updateBasicBaseRfidprint(BasicBaseRfidPrint bean);

	public int updateBasicBaseRfidprintById(BasicBaseRfidPrint bean);

	public int delBasicBaseRfidprintById(int id);

	public BasicBaseRfidPrint getBasicBaseRfidprintById(Long id);

	@Override
	int insert(BasicBaseRfidPrint record);

	@Override
	int deleteById(Serializable id);

	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidPrint> queryWrapper);

	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseRfidPrint entity);

	@Override
	int update(@Param(Constants.ENTITY) BasicBaseRfidPrint entity,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidPrint> updateWrapper);

	@Override
	BasicBaseRfidPrint selectById(Serializable id);

	@Override
	List<BasicBaseRfidPrint> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	List<BasicBaseRfidPrint> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	@Override
	BasicBaseRfidPrint selectOne(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidPrint> queryWrapper);

	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidPrint> queryWrapper);

	@Override
	List<BasicBaseRfidPrint> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidPrint> queryWrapper);

	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidPrint> queryWrapper);

	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidPrint> queryWrapper);

	Page<BasicBaseRfidPrint> selectPage(Page<BasicBaseRfidPrint> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidPrint> queryWrapper);

	public List<BasicBaseRfidPrint> getBasicBaseRfidprintDAC(@Param("params") Map<String, Object> params);

	public Long getBasicBaseRfidprintDACCount(@Param("params") Map<String, Object> params);

	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseRfidPrint> page,
			@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidPrint> queryWrapper);

	public int printLock(@Param("printId") Long printId, @Param("deviceId") Long deviceId);

	public int printUnlock(Long printId);

	public int selectLock(Long printId);

}
