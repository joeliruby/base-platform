package com.matariky.userservice.mapper;



import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.github.pagehelper.Page;

import com.matariky.userservice.bean.TreeModel;
import com.matariky.userservice.bean.UserGroup;

/**
* Persistence Interface
* @author AUTOMATION
*/
public interface UserGroupMapper extends BaseMapper<UserGroup>{
	 
	public Page<UserGroup> getUserGroupAll(@Param("params")Map<String, Object> map);
	 
	public int getUserGroupAllCount();
	 
	public int createUserGroup(UserGroup bean);
	 
	public int updateUserGroup(@Param("params") UserGroup bean);
	 
	public int delUserGroupById(int id);
	 
	public UserGroup getUserGroupById(Long id);
	//Insert a record
	@Override
	int insert(UserGroup record);
	//Delete By ID
	@Override
	int deleteById(Serializable id);
	//Delete By Column Map
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Delete By Entity
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<UserGroup> queryWrapper);
	//Delete Batch By IDs
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//Update By IDs
	@Override
	int updateById(@Param(Constants.ENTITY) UserGroup entity);
	//Update By Entity
	@Override
	int update(@Param(Constants.ENTITY) UserGroup entity, @Param(Constants.WRAPPER) Wrapper<UserGroup> updateWrapper);
	//Query By ID 
	@Override
	UserGroup selectById(Serializable id);
	 
	@Override
	List<UserGroup> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<UserGroup> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//Query One By Entity
	@Override
	UserGroup selectOne(@Param(Constants.WRAPPER) Wrapper<UserGroup> queryWrapper);
	//Query Count By Wrapper
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<UserGroup> queryWrapper);
	//Query All By Entity
	@Override
	List<UserGroup> selectList(@Param(Constants.WRAPPER) Wrapper<UserGroup> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<UserGroup> queryWrapper);
	//Query All Maps By Wrapper
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<UserGroup> queryWrapper);
	//Query All By Entity（ With Pagination ）
	Page<UserGroup> selectPage(Page<UserGroup> page, @Param(Constants.WRAPPER) Wrapper<UserGroup> queryWrapper);
	//Query All Maps By Wrapper（ With Pagination ）
	Page<Map<String, Object>> selectMapsPage(Page<UserGroup> page, @Param(Constants.WRAPPER) Wrapper<UserGroup> queryWrapper);

	List<UserGroup> selectGroup(String tenantId);

	public void deleteUserByGroupIds(Long[] groupIds);

	public void savaRGoupAndUser(@Param("groupId")Long groupId,@Param("userId")Long userId);

	public void deleteRoleByGroupIds(Long[] groupIds);

	public void savaRGoupAndRole(@Param("groupId")Long groupId,@Param("roleId") Long roleId);

	public List<TreeModel> getPermissionByUserAndRoleAndGroup(@Param("groupId")Long groupId, @Param("applicationId")Long applicationId);

	public List<Map<String, Object>> getApplicationByTenantId(String tenantId);

	public int updateDeleteTimeById(@Param("array")String [] id);

//	@Select("select id from user_group where tenant_id=#{tenantId} and group_name in ( #{groupNames})")
	@Select({
        "<script>",
            "select",
            "id",
            "from user_group",
            "where tenant_id =#{tenantId} and group_name in",
                "<foreach collection='array' item='id' open='(' separator=',' close=')'>",
                "#{id}",
                "</foreach>",
        "</script>"

	    })
	public List<Long> getGroupIdsByTenantIdAndGroupNames(@Param("array") String[] strings, @Param("tenantId") String tenantId);

	public List<Long> getRoleIdList(Long id);


	public Integer getRoleIdCount(@Param("roleId") Long roleId);


	public List<Long> getUserIdList(Long id);

	@Delete("delete from user_r_group_user where user_id= #{userId}")
	public void deleteByUserId(@Param("userId") Long id);

	@Insert("insert into user_r_group_user values (#{groupId},#{userId})")
	public void insertUserGroupRelation(@Param("userId") Long id, @Param("groupId") Long groupId);

	@Select("select u.id from user_user u, user_r_user_role rur where rur.role_id=#{roleId} and u.id=rur.user_id and u.delete_time=0 and u.is_active=1")
	public List<Long> getUsersByRoleId(@Param("roleId") Long id);

	@Select("select group_id from user_r_group_user r, user_group g where g.id = r.group_id and r.user_id=#{userId} and g.tenant_id = #{tenantId}")
	Set<String> getGroupsByUserId(@Param("userId") Long userId, @Param("tenantId") String tenantId);

	@Select("SELECT permission_id FROM user_r_group_permission WHERE group_id=#{groupId}")
	public List<Long> getPermissionIdByGroupId(Long groupId);

	@Delete("delete from user_r_group_permission where group_id=#{groupId}")
	public void deletePreviousGroupPermissionBound(String groupId);


	public List<UserGroup> searchByGroupNamePrefix(@Param("tenantId")String tenantId, @Param("groupNamePrefix") String groupNamePrefix);

/**	<select id="getRoleIdList" resultType="long">
	SELECT 		r.role_id 	FROM   		user_group g, 		user_r_group_role r 	WHERE 		r.group_id = #{value} 	AND g.id= r.group_id	AND g.delete_time =0
</select>

<select id="getUserIdList" resultType="long">
SELECT 	u.user_id FROM	user_group g,	user_r_group_user u WHERE	u.group_id = #{value} AND g.id = u.group_id AND g.delete_time =0
</select>**/

	@Select("SELECT 	uu.login_name FROM	user_group g,	user_r_group_user u , user_user uu WHERE	u.group_id = #{value} AND g.id = u.group_id AND g.delete_time =0 and u.user_id=uu.id ")
	public List<String> getUserNameList(@Param("value") Long groupId);

	@Select("SELECT 		url.role_name 	FROM   		user_group g, 		user_r_group_role r , user_role url	WHERE 		r.group_id = #{value} 	AND g.id= r.group_id	AND g.delete_time =0 and r.role_id=url.id")
	public List<String> getRoleNameList(@Param("value") Long groupId);

}
