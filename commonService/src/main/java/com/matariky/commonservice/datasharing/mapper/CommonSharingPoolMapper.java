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
* @author AUTOMATION
*/
public interface CommonSharingPoolMapper extends BaseMapper<CommonSharingPool>{
	 
	public List<CommonSharingPool> getCommonSharingPoolAll();
	 
	public int getCommonSharingPoolAllCount();
	 
	public int createCommonSharingPool(CommonSharingPool bean);
	 
	public int updateCommonSharingPool(@Param("params") CommonSharingPool bean);
	 
	public int delCommonSharingPoolById(long l);
	 
	public CommonSharingPool getCommonSharingPoolById(int id);
	//插入一条记录
	@Override
	int insert(CommonSharingPool record);
	//根据 ID 删除
	@Override
	int deleteById(Serializable id);
	//根据 columnMap 条件，删除记录
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件，删除记录 
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<CommonSharingPool> queryWrapper);
	//删除（根据ID 批量删除）
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//根据 ID 修改
	@Override
	int updateById(@Param(Constants.ENTITY) CommonSharingPool entity);
	//根据 whereEntity 条件，Update记录
	@Override
	int update(@Param(Constants.ENTITY) CommonSharingPool entity, @Param(Constants.WRAPPER) Wrapper<CommonSharingPool> updateWrapper);
	//根据 ID  Query   
	@Override
	CommonSharingPool selectById(Serializable id);
	 
	@Override
	List<CommonSharingPool> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<CommonSharingPool> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件， Query 一条记录
	@Override
	CommonSharingPool selectOne(@Param(Constants.WRAPPER) Wrapper<CommonSharingPool> queryWrapper);
	//根据 Wrapper 条件， Query 总记录数
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<CommonSharingPool> queryWrapper);
	//根据 entity 条件， Query 全部记录
	@Override
	List<CommonSharingPool> selectList(@Param(Constants.WRAPPER) Wrapper<CommonSharingPool> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<CommonSharingPool> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<CommonSharingPool> queryWrapper);
	//根据 entity 条件， Query 全部记录（并翻页）
	 Page<CommonSharingPool> selectPage(Page<CommonSharingPool> page, @Param(Constants.WRAPPER) Wrapper<CommonSharingPool> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录（并翻页）
	Page<Map<String, Object>> selectMapsPage(Page<CommonSharingPool> page, @Param(Constants.WRAPPER) Wrapper<CommonSharingPool> queryWrapper);
	
	@Select("select sp.id,sp.sharer_type, sp.receiver_type,p.permission_name as resource_table_name,uo1.organization_code as receiving_org_code,sp.created_by,sp.create_time,sp.updated_by,sp.update_time,sp.tenant_id,sp.delete_time,uo2.organization_code as original_owner_org_code,sp.resource_id from common_sharing_pool sp join user_permission p on sp.resource_id=p.id  and p.is_active=1 and p.delete_time=0 join user_organization uo1 on sp.receiving_org_code = uo1.organization_code join user_organization uo2 on sp.original_owner_org_code=uo2.organization_code where receiving_org_code = #{organizationCode} and sp.delete_time=0 ")
	public List<CommonSharingPool> selectByOrgCode(@Param("organizationCode") String organizationCode);
	@Select("select sp.id,sp.sharer_type, sp.receiver_type,p.permission_name as resource_table_name,uo1.organization_code as receiving_org_code,uo1.organization_name as receiving_org_name,sp.created_by,sp.create_time,sp.updated_by,sp.update_time,sp.tenant_id,sp.delete_time,uo2.organization_code as original_owner_org_code,uo2.organization_name as  original_owner_org_name,sp.resource_id from common_sharing_pool sp join user_permission p on sp.resource_id=p.id  and p.is_active=1 and p.delete_time=0 join user_organization uo1 on sp.receiving_org_code = uo1.organization_code join user_organization uo2 on sp.original_owner_org_code=uo2.organization_code where resource_id=#{resourceId} and sp.tenant_id=#{tenantId} and sp.delete_time=0 ")
	public List<CommonSharingPool>  getCommonSharingPoolByResourceId(@Param("resourceId")Long resourceId, @Param("tenantId")String tenantId);

	/**
	 * @Description: Query the organization code of shared resources
	 * @Author: bo.chen
	 * @Date: 2023/9/5 18:25
	 * @param orgCode
	 * @param selfOrgCode
	 * @return java.util.List<java.lang.String>
	 **/
	List<String> selectOriginalOwnerOrgCodes(@Param("orgCode") String orgCode, @Param("selfOrgCode") String selfOrgCode);
}
