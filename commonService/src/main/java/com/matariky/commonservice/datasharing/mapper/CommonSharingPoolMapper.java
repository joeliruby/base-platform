package com.matariky.commonservice.datasharing.mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.matariky.commonservice.datasharing.bean.CommonSharingPool;

/**
 * Persistence Interface
 * 
 * @author AUTOMATION
 */
public interface CommonSharingPoolMapper extends BaseMapper<CommonSharingPool> {

	public List<CommonSharingPool> getCommonSharingPoolAll();

	public int getCommonSharingPoolAllCount();

	public int createCommonSharingPool(CommonSharingPool bean);

	public int updateCommonSharingPool(@Param("params") CommonSharingPool bean);

	public int delCommonSharingPoolById(long l);

	public CommonSharingPool getCommonSharingPoolById(int id);

	// Insert a record
	@Override
	int insert(CommonSharingPool record);

	// Delete By ID
	@Override
	int deleteById(Serializable id);

	// Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	// Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<CommonSharingPool> queryWrapper);

	// Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	// Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) CommonSharingPool entity);

	// Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) CommonSharingPool entity,
			@Param(Constants.WRAPPER) Wrapper<CommonSharingPool> updateWrapper);

	// Query By ID
	@Override
	CommonSharingPool selectById(Serializable id);

	@Override
	List<CommonSharingPool> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

	@Override
	List<CommonSharingPool> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

	// Query One By Entity
	@Override
	CommonSharingPool selectOne(@Param(Constants.WRAPPER) Wrapper<CommonSharingPool> queryWrapper);

	// Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<CommonSharingPool> queryWrapper);

	// Query All By Entity
	@Override
	List<CommonSharingPool> selectList(@Param(Constants.WRAPPER) Wrapper<CommonSharingPool> queryWrapper);

	// Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<CommonSharingPool> queryWrapper);

	// Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<CommonSharingPool> queryWrapper);

	// Query All By Entity（ With Pagination ）
	Page<CommonSharingPool> selectPage(Page<CommonSharingPool> page,
			@Param(Constants.WRAPPER) Wrapper<CommonSharingPool> queryWrapper);

	// Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<CommonSharingPool> page,
			@Param(Constants.WRAPPER) Wrapper<CommonSharingPool> queryWrapper);

	@Select("select sp.id,sp.sharer_type, sp.receiver_type,p.permission_name as resource_table_name,uo1.organization_code as receiving_org_code,sp.created_by,sp.create_time,sp.updated_by,sp.update_time,sp.tenant_id,sp.delete_time,uo2.organization_code as original_owner_org_code,sp.resource_id from common_sharing_pool sp join user_permission p on sp.resource_id=p.id  and p.is_active=1 and p.delete_time=0 join user_organization uo1 on sp.receiving_org_code = uo1.organization_code join user_organization uo2 on sp.original_owner_org_code=uo2.organization_code where receiving_org_code = #{organizationCode} and sp.delete_time=0 ")
	public List<CommonSharingPool> selectByOrgCode(@Param("organizationCode") String organizationCode);

	@Select("select sp.id,sp.sharer_type, sp.receiver_type,p.permission_name as resource_table_name,uo1.organization_code as receiving_org_code,uo1.organization_name as receiving_org_name,sp.created_by,sp.create_time,sp.updated_by,sp.update_time,sp.tenant_id,sp.delete_time,uo2.organization_code as original_owner_org_code,uo2.organization_name as  original_owner_org_name,sp.resource_id from common_sharing_pool sp join user_permission p on sp.resource_id=p.id  and p.is_active=1 and p.delete_time=0 join user_organization uo1 on sp.receiving_org_code = uo1.organization_code join user_organization uo2 on sp.original_owner_org_code=uo2.organization_code where resource_id=#{resourceId} and sp.tenant_id=#{tenantId} and sp.delete_time=0 ")
	public List<CommonSharingPool> getCommonSharingPoolByResourceId(@Param("resourceId") Long resourceId,
			@Param("tenantId") String tenantId);

	List<String> selectOriginalOwnerOrgCodes(@Param("orgCode") String orgCode,
			@Param("selfOrgCode") String selfOrgCode);
}
