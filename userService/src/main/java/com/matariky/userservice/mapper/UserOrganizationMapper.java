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

import com.matariky.userservice.bean.TreeModel;
import com.matariky.userservice.bean.UserOrganization;
/**
* Persistence Interface
* @author AUTOMATION
*/
public interface UserOrganizationMapper extends BaseMapper<UserOrganization>{
	 
	public Page<UserOrganization> getUserOrganizationAll();
	 
	public int getUserOrganizationAllCount();
	 
	public int createUserOrganization(UserOrganization bean);
	 
	public int updateUserOrganization(@Param("params") UserOrganization bean);
	 
	public int delUserOrganizationById(int id);
	 
	public UserOrganization getUserOrganizationById(String id);
	//插入一条记录
	@Override
	int insert(UserOrganization record);
	//根据 ID 删除
	@Override
	int deleteById(Serializable id);
	//根据 columnMap 条件，删除记录
	@Override
	int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件，删除记录
	@Override
	int delete(@Param(Constants.WRAPPER) Wrapper<UserOrganization> queryWrapper);
	//删除（根据ID 批量删除）
	@Override
	int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	//根据 ID 修改
	@Override
	int updateById(@Param(Constants.ENTITY) UserOrganization entity);
	//根据 whereEntity 条件，Update记录
	@Override
	int update(@Param(Constants.ENTITY) UserOrganization entity, @Param(Constants.WRAPPER) Wrapper<UserOrganization> updateWrapper);
	//根据 ID  Query 
	@Override
	UserOrganization selectById(Serializable id);
	 
	@Override
	List<UserOrganization> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
	 
	@Override
	List<UserOrganization> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
	//根据 entity 条件， Query 一条记录
	@Override
	UserOrganization selectOne(@Param(Constants.WRAPPER) Wrapper<UserOrganization> queryWrapper);
	//根据 Wrapper 条件， Query 总记录数
	@Override
	Long selectCount(@Param(Constants.WRAPPER) Wrapper<UserOrganization> queryWrapper);
	//根据 entity 条件， Query 全部记录
	@Override
	List<UserOrganization> selectList(@Param(Constants.WRAPPER) Wrapper<UserOrganization> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<UserOrganization> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录
	@Override
	List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<UserOrganization> queryWrapper);
	//根据 entity 条件， Query 全部记录（并翻页）
	 Page<UserOrganization> selectPage(Page<UserOrganization> page, @Param(Constants.WRAPPER) Wrapper<UserOrganization> queryWrapper);
	//根据 Wrapper 条件， Query 全部记录（并翻页）
	Page<Map<String, Object>> selectMapsPage(Page<UserOrganization> page, @Param(Constants.WRAPPER) Wrapper<UserOrganization> queryWrapper);

	public UserOrganization selectByOrgCode(@Param("organizationCode") String orgCode);

	public List<TreeModel> getOrganizationList(String tenantId);

	public Long[] getChildrenOrganization(@Param("organizationCode")String organizationCode,@Param("tenantId")String tenantId);

	public int getCountByParentId(@Param("parentId")Long parentId,@Param("tenantId")String tenantId);

	public String getParentcodeByParentId(@Param("parentId")Long parentId,@Param("tenantId")String tenantId);

	public void updateDeleteTimeById(@Param("id")Long id,@Param("tenantId")String tenantId);

	public String selectNameById(Long parentId);

	public UserOrganization getOrganizationByCode(@Param("code")String code,@Param("tenantId")String tenantId);

	public List<TreeModel> queryTreeListView(@Param("tenantId")String tenantId,@Param("code")String code);

	@Select("select * from user_organization where tenant_id=#{tenantId} and parent_id=0")
	public UserOrganization selectTopOrganization(String tenantId);

	@Update ("update user_organization set organization_code=#{organization_code} where id=#{id}")
	public void updateOrganizationCodeById(@Param("id")Long id, @Param("organization_code")String codeString);

	@Update ("update user_organization set organization_code=#{organization_code} , parent_id =#{parentId} where id=#{id}")
	public void updateOrganizationCodeAndParentIdById(@Param("id")Long id, @Param("organization_code")String codeString, @Param("parentId") Long parentId);

	public String getOrgNamesByCode(@Param("array")String[] codes);

	public List<TreeModel> getOrganizationListWithInd(String tenantId);
	
	@Select("select * from user_organization where organization_code like concat ('ind\\_org\\_%%\\_',#{id}) ")
	public UserOrganization getUserSelfOrganization(@Param("id") Long id);



}
