package com.matariky.commonservice.base.mapper;

import java.util.List;
import java.util.Map;

import java.io.Serializable;

import com.baomidou.mybatisplus.core.toolkit.Constants;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import java.util.Collection;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.github.pagehelper.Page;
import com.matariky.commonservice.base.vo.TapeRfidCreateCNExeclReqVo;
import org.apache.ibatis.annotations.Param;
import com.matariky.commonservice.base.bean.BasicBaseRfidInfo;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface BasicBaseRfidInfoMapper extends BaseMapper<BasicBaseRfidInfo>{
	 
	public List<BasicBaseRfidInfo> getBasicBaseRfidInfoAll();
	 
	public int getBasicBaseRfidInfoAllCount();


	/**
	 * New方法
	 */
	public int createBasicBaseRfidInfo(BasicBaseRfidInfo bean);


	 
	public int updateBasicBaseRfidInfo(BasicBaseRfidInfo bean);
	 
	public int delBasicBaseRfidInfoById(int id);
	 
	public BasicBaseRfidInfo getBasicBaseRfidInfoById(int id);
	//插入一条记录
	@Override
	int insert(BasicBaseRfidInfo record);
	//根据 ID 删除
	@Override
	int deleteById(Serializable id);
	//根据 columnMap 条件，删除记录
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件，删除记录
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidInfo> queryWrapper);
	//删除（根据ID 批量删除）
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//根据 ID 修改
	@Override
	int updateById(@Param(Constants.ENTITY) BasicBaseRfidInfo entity);
	//根据 whereEntity 条件，Update记录
	@Override
	int update(@Param(Constants.ENTITY) BasicBaseRfidInfo entity, @Param(Constants.WRAPPER) Wrapper<BasicBaseRfidInfo> updateWrapper);
	//根据 ID  Query 
	@Override
	BasicBaseRfidInfo selectById(Serializable id);
	 
	@Override
	List<BasicBaseRfidInfo> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<BasicBaseRfidInfo> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件， Query 一条记录

	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidInfo> queryWrapper);
	//根据 entity 条件， Query 全部记录
	@Override
	List<BasicBaseRfidInfo> selectList(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidInfo> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidInfo> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<BasicBaseRfidInfo> queryWrapper);
	//根据 entity 条件， Query 全部记录（并翻页）
	 Page<BasicBaseRfidInfo> selectPage(Page<BasicBaseRfidInfo> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseRfidInfo> queryWrapper);
	public List<BasicBaseRfidInfo> getBasicBaseRfidInfoDAC(@Param("params") Map<String, Object> params);
	public Long getBasicBaseRfidInfoDACCount(@Param("params") Map<String, Object> params);
	//根据 Wrapper 条件， Query 全部记录（并翻页）
	Page<Map<String, Object>> selectMapsPage(Page<BasicBaseRfidInfo> page, @Param(Constants.WRAPPER) Wrapper<BasicBaseRfidInfo> queryWrapper);

	 
	public BasicBaseRfidInfo getBasicBaseRfidInfoByEpc(String epc);

	 
	public BasicBaseRfidInfo getBasicBaseRfidInfoByOdqr(TapeRfidCreateCNExeclReqVo tapeRfidCreateCNExeclReqVo);

	 
	public BasicBaseRfidInfo getBasicBaseRfidInfo();

	 
	public List<BasicBaseRfidInfo> getBasicBaseRfidInfoByFactoryId(Long Id);

}
