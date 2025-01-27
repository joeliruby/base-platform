package com.matariky.userservice.mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.pagehelper.Page;

import com.matariky.userservice.bean.UserTenant;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface UserTenantMapper extends BaseMapper<UserTenant>{
	 
	public Page<UserTenant> getUserTenantAll(@Param("params")Map<String, Object> map);
	 
	public int getUserTenantAllCount();
	 
	public int createUserTenant(UserTenant bean);
	 
	public int updateUserTenant(@Param("params") UserTenant bean);
	 
	public int delUserTenantById(int id);
	 
	public UserTenant getUserTenantById(String id);
	//Insert a record
	@Override
	int insert(UserTenant record);
	//Delete By ID
	@Override
	int deleteById(Serializable id);
	//Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<UserTenant> queryWrapper);
	//Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) UserTenant entity);
	//Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) UserTenant entity, @Param(Constants.WRAPPER) Wrapper<UserTenant> updateWrapper);
	//Query By ID   
	@Override
	UserTenant selectById(Serializable id);
	 
	@Override
	List<UserTenant> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<UserTenant> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Query One By Entity
	@Override
	UserTenant selectOne(@Param(Constants.WRAPPER) Wrapper<UserTenant> queryWrapper);
	//Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<UserTenant> queryWrapper);
	//Query All By Entity
	@Override
	List<UserTenant> selectList(@Param(Constants.WRAPPER) Wrapper<UserTenant> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<UserTenant> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<UserTenant> queryWrapper);
	//Query All By Entity（ With Pagination ）
	Page<UserTenant> selectPage(Page<UserTenant> page, @Param(Constants.WRAPPER) Wrapper<UserTenant> queryWrapper);
	//Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<UserTenant> page, @Param(Constants.WRAPPER) Wrapper<UserTenant> queryWrapper);
	
	public UserTenant selectByTenantCode(@Param("tenantCode") String tenantCode);
	
	@Select("SELECT id,tenant_code as tenantCode,tenant_name , parent_id as parentId FROM user_tenant WHERE  delete_time=0 and tenant_code like concat(#{tenantId},'%')")
	public List<UserTenant> selectTenant(@Param("tenantId") String tenantId);
	
	@Select("SELECT COUNT(*) FROM user_tenant WHERE  delete_time=0 and parent_id=#{id}")
	public int getCountByParentId(@Param("id")Long id);
	
	public void deleteApplicationByTenantCodes(String[] tenantCodes);
	
	public void saveRApplicationTenant(Map<String, Object> map);
	
	public int updateDeleteTimeById(@Param("array") String[] ids);
	
	@Select("SELECT application_id as id , application_name as applicationName FROM user_r_application_tenant WHERE  tenant_code=#{tenantId}")
	public  List<Map<String, Object>> selectApplication(@Param("tenantId")String tenantCode);
	
	@Select("select user_id from user_r_user_tenant where is_admin=1 and tenant_code=#{tenantCode}")
	public Long getAdminUserIdByTenantCode(@Param("tenantCode") String tenantCode);
	
	@Update ("update user_tenant set tenant_code=#{tenant_code} where id=#{id}")
	public void updateTenantCodeById(@Param("id")Long id,@Param("tenant_code") String tenantCode);
	


}
