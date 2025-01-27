package com.matariky.userservice.mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.pagehelper.Page;

import com.matariky.userservice.bean.TreeModel;
import com.matariky.userservice.bean.UserRole;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface UserRoleMapper extends BaseMapper<UserRole>{
	 
	public Page<UserRole> getUserRoleAll(@Param("params")Map<String, Object> map);

	 
	public int getUserRoleAllCount();
	 
	public int createUserRole(UserRole bean);
	 
	public int updateUserRole(@Param("params") UserRole bean);
	 
	public int delUserRoleById(int id);
	 
	public UserRole getUserRoleById(Long id);

	public Integer  getRoleCountById(@Param("roleId") Long roleId);

	//Insert a record
	@Override
	int insert(UserRole record);
	//Delete By ID
	@Override
	int deleteById(Serializable id);
	//Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<UserRole> queryWrapper);
	//Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) UserRole entity);
	//Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) UserRole entity, @Param(Constants.WRAPPER) Wrapper<UserRole> updateWrapper);
	//Query By ID 
	@Override
	UserRole selectById(Serializable id);
	 
	@Override
	List<UserRole> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<UserRole> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Query One By Entity
	@Override
	UserRole selectOne(@Param(Constants.WRAPPER) Wrapper<UserRole> queryWrapper);
	//Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<UserRole> queryWrapper);
	//Query All By Entity
	@Override
	List<UserRole> selectList(@Param(Constants.WRAPPER) Wrapper<UserRole> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<UserRole> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<UserRole> queryWrapper);
	//Query All By Entity（ With Pagination ）
	Page<UserRole> selectPage(Page<UserRole> page, @Param(Constants.WRAPPER) Wrapper<UserRole> queryWrapper);
	//Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<UserRole> page, @Param(Constants.WRAPPER) Wrapper<UserRole> queryWrapper);

	public List<UserRole> selectRole(String tenantId);

	public int updateDeleteTimeById(@Param("array") String[] id);

//	@Select("select id from user_role where tenant_id=#{tenantId} and role_name in ( #{roleNames})")
	  @Select({
          "<script>",
              "select",
              "id",
              "from user_role",
              "where tenant_id =#{tenantId} and role_name in",
                  "<foreach collection='array' item='id' open='(' separator=',' close=')'>",
                  "#{id}",
                  "</foreach>",
          "</script>"

	    })
	public List<Long> getRoleIdsByTenantIdAndRoleNames(@Param("array") String[] strings, @Param("tenantId") String tenantId);

	public List<TreeModel> getPermissionByRole(@Param("roleId")Long roleId, @Param("tenantId")String tenantId, @Param("applicationId")Long applicationId);

	@Delete("delete from user_r_user_role where user_id =#{userId}")
	public void deleteByUserId(@Param("userId") Long id);

	@Insert("insert into user_r_user_role values (#{userId},#{roleId})")

	public void insertUserRoleRelation(@Param("userId") Long id, @Param("roleId") Long roleId);

	@Select("SELECT permission_id FROM user_r_role_permission WHERE role_id=#{roleId}")
	public List<Long> getPermissionIdByRoleId(Long roleId);




}
