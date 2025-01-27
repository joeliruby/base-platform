package com.matariky.userservice.mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.pagehelper.Page;
import com.matariky.userservice.bean.TreeModel;
import com.matariky.userservice.bean.UserApplication;
/**
* Persistence Interface
* @author AUTOMATION
*/
@Mapper
public interface UserApplicationMapper extends BaseMapper<UserApplication>{
	 
	public Page<UserApplication> getUserApplicationAll(@Param("params")Map<String, Object> map);
	 
	public int getUserApplicationAllCount();
	 
	public int createUserApplication(UserApplication bean);
	 
	public int updateUserApplication(@Param("params") UserApplication bean);
	 
	public int delUserApplicationById(int id);
	 
	public UserApplication getUserApplicationById(Long id);
	//Insert a record
	@Override
	int insert(UserApplication record);
	//Delete By ID
	@Override
	int deleteById(Serializable id);
	//Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<UserApplication> queryWrapper);
	//Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) UserApplication entity);
	//Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) UserApplication entity, @Param(Constants.WRAPPER) Wrapper<UserApplication> updateWrapper);
	//Query By ID   
	@Override
	UserApplication selectById(Serializable id);
	 
	@Override
	List<UserApplication> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<UserApplication> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Query One By Entity
	@Override
	UserApplication selectOne(@Param(Constants.WRAPPER) Wrapper<UserApplication> queryWrapper);
	//Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<UserApplication> queryWrapper);
	//Query All By Entity
	@Override
	List<UserApplication> selectList(@Param(Constants.WRAPPER) Wrapper<UserApplication> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<UserApplication> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<UserApplication> queryWrapper);
	//Query All By Entity（ With Pagination ）
	Page<UserApplication> selectPage(Page<UserApplication> page, @Param(Constants.WRAPPER) Wrapper<UserApplication> queryWrapper);
	//Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<UserApplication> page, @Param(Constants.WRAPPER) Wrapper<UserApplication> queryWrapper);
	
	@Select("select r.application_id from user_r_user_application r, user_application a  where a.id=r.application_id and a.is_active=1 and a.delete_time=0 and r.user_id=#{userId} and r.tenant_id=#{tenantId} limit 1")
	public Long getDefaultApplicationByTenantIdandUserId(@Param("tenantId") String tenantId, @Param("userId") Long userId);
	
	@Select("select id from user_r_application_tenant where tenant_code=#{tenantId} and application_id=#{applicationId}")
	public Long selectTenantApplicationJointId(@Param("tenantId") String tenantId, @Param("applicationId") Long applicationId);
	
	@Insert("insert into user_r_application_permission values (#{appTenantId},#{permissionId})")
	public void createTenantApplicationPermissionBound(@Param("appTenantId") Long appTenantId, @Param("permissionId") long parseLong);
	
	public List<UserApplication> selectApplication(@Param("tenantId")String tenantId);
	
	public void deleteTenantByApplicationIds(Long[] applicationIds);
	
	public void deletePermissionByApplicationIds(Long[] applicationIds);
	
	@Insert("insert into user_r_application_tenant_permission values (#{applicationId},#{permissionId})")
	public void saveRApplicationPermission(@Param("applicationId")Long applicationId,@Param("permissionId")Long permissionId);
	
	
	public void saveRApplicationTenant(Map<String, Object> map);
	
	public List<TreeModel> getTreeListByTenantId(String tenantId);
	
	@Select("select application_name from user_r_application_tenant where tenant_code=#{tenantId} and application_id=#{applicationId}")
	public String getNameById(@Param("tenantId")String tenantId,@Param("applicationId")Long applicationId);
	
	
	public List<Long> getPermissionIdList(Long applicationId);
	
	public List<UserApplication> getUserApplicationByTenant(@Param("params")Map<String, Object> map);
	
	public void delUserTenant(@Param("array")String[] id);
	
	public int updateDeleteTimeById(@Param("array") String[] id);
	
	
	public List<TreeModel> getTreeListByApplicationId(Long applicationId);
	
	public List<UserApplication> getAppList(String tenantId);
	
	public List<TreeModel> getRApplicationTenantPermission(Long applicationId);
	
	@Insert("insert into user_r_user_application values(#{userId},#{applicationId},#{tenantId})")
	public void insertUserApplicationRelation(@Param("userId")Long userId, @Param("applicationId")Long parseLong, @Param("tenantId")Long id);
	
	public List<UserApplication> getUserApplicationAllWithUserId(@Param("params") Map<String, Object> map);
	
	@Select ("select count(*) from user_r_user_application r , user_user u where r.user_id =u.id and u.is_active=1 and u.delete_time=0 and r.user_id=#{userId} and r.application_id=#{applicationId} and r.tenant_id=#{tenantId}")
	public int countUserApplicationTenant(@Param("userId") Long userId,@Param("applicationId") String applicationId,@Param("tenantId") Long tenantId);
	
	@Select ("select application_id from user_r_user_application where user_id=#{userId}")
	public List<String> getApplicationIdList(@Param("userId") Long id);
	
	@Delete ("delete from user_r_user_application where user_id=#{userId}")
	public void deleteApplicationByUserId(@Param("userId") Long id);
	
	
	
	
	

}
