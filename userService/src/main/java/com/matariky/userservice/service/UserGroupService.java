package com.matariky.userservice.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;
import com.matariky.userservice.bean.UserOrganization;
import com.matariky.userservice.mapper.UserOrganizationMapper;
import com.matariky.utils.TokenUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;

import cn.hutool.core.collection.CollUtil;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.userservice.bean.TreeModel;
import com.matariky.userservice.bean.UserGroup;
import com.matariky.userservice.mapper.UserGroupMapper;
import com.matariky.utils.DateUtil;
import com.matariky.utils.TreeUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Business Inteface Implementation
 * 
 * @author AUTOMATION
 */
@Service
public class UserGroupService extends BaseServiceImpl<UserGroupMapper, UserGroup> {

	@Autowired
	private UserGroupMapper userGroupMapper;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private UserOrganizationMapper userOrganizationMapper;

	public Page<UserGroup> getUserGroupAll(Map<String, Object> map) {
		return userGroupMapper.getUserGroupAll(map);
	}

	public int getUserGroupAllCount() {
		return userGroupMapper.getUserGroupAllCount();
	}

	@Transactional(rollbackFor = Exception.class)
	public void createUserGroup(UserGroup bean) {
		String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
		Long count = userGroupMapper.selectCount(Wrappers.lambdaQuery(UserGroup.class)
				.eq(UserGroup::getGroupName, bean.getGroupName())
				.eq(UserGroup::getDeleteTime, 0)
				.eq(UserGroup::getTenantId, tenantId));
		if (count > 0) {
			throw new QslException(MessageKey.USER_GROUP_NAME_NOT_REPEAT);
		}
		bean.setCreateTime(DateUtil.getCurrentDateAndTime().getTime());
		bean.setDeleteTime(Long.parseLong("0"));
		bean.setTenantId(tenantId);
		userGroupMapper.createUserGroup(bean);

		// Character middle watch
		saveOrUpdateRole(bean.getId(), bean.getRoleIdList());

		// User Intermediate table
		saveOrUpdateUser(bean.getId(), bean.getUserIdList());
	}

	public void saveOrUpdateUser(Long groupId, List<Long> userIdList) {

		// First DELETE group and user relationship
		deleteUserByGroupIds(new Long[] { groupId });

		// The group does not have one user situation
		if (CollUtil.isEmpty(userIdList)) {
			return;
		}

		// Save group and user relationship
		for (Long userId : userIdList) {
			userGroupMapper.savaRGoupAndUser(groupId, userId);
		}

	}

	public void deleteUserByGroupIds(Long[] groupIds) {

		userGroupMapper.deleteUserByGroupIds(groupIds);
	}

	public void saveOrUpdateRole(Long groupId, List<Long> roleIdList) {

		// First DELETE group and user relationship
		deleteRoleByGroupIds(new Long[] { groupId });

		// The group does not have one user situation
		if (CollUtil.isEmpty(roleIdList)) {
			return;
		}

		// Save Group and role relationship
		for (Long roleId : roleIdList) {
			userGroupMapper.savaRGoupAndRole(groupId, roleId);

		}

	}

	private void deleteRoleByGroupIds(Long[] groupIds) {
		userGroupMapper.deleteRoleByGroupIds(groupIds);
	}

	public void updateUserGroup(UserGroup bean) {
		bean.setUpdateTime(DateUtil.getCurrentDateAndTime().getTime());

		userGroupMapper.updateUserGroup(bean);

		// Character middle watch
		saveOrUpdateRole(bean.getId(), bean.getRoleIdList());

		// User Intermediate table
		saveOrUpdateUser(bean.getId(), bean.getUserIdList());

		tokenService.expireAllLoginUsersAfterCredentialChanges(bean);

	}

	public Set<String> getGroupsByUserId(Long userId, String tenantId) {
		return userGroupMapper.getGroupsByUserId(userId, tenantId);
	}

	public int delUserGroupById(int id) {
		return userGroupMapper.delUserGroupById(id);
	}

	public UserGroup getUserGroupById(Long id) {
		return userGroupMapper.getUserGroupById(id);
	}

	public List<UserGroup> selectGroup(String tenantId) {

		return userGroupMapper.selectGroup(tenantId);
	}

	public List<Map<String, Object>> getAppByTenantId(String tenantId) {

		return userGroupMapper.getApplicationByTenantId(tenantId);
	}

	public List<TreeModel> getPermissionByUserAndRoleAndGroup(Long groupId, Long applicationId) {

		return TreeUtils.build(userGroupMapper.getPermissionByUserAndRoleAndGroup(groupId, applicationId));
	}

	public int updateDeleteTimeById(String[] id) {
		Long count = userOrganizationMapper.selectCount(Wrappers.lambdaQuery(UserOrganization.class)
				.in(UserOrganization::getUserGroupId, (Object[]) id)
				.eq(UserOrganization::getDeleteTime, 0));
		if (count > 0) {
			throw new QslException(MessageKey.ORG_CODE_USED_BY_USER);
		}
		return userGroupMapper.updateDeleteTimeById(id);
	}

	public List<Long> getGroupIdsByTenantIdAndGroupNames(String groupNames, String tenantId) {
		return userGroupMapper.getGroupIdsByTenantIdAndGroupNames(groupNames.split(","), tenantId);
	}

	public List<Long> getRoleIdList(Long id) {

		return userGroupMapper.getRoleIdList(id);
	}

	public List<Long> getUserIdList(Long id) {

		return userGroupMapper.getUserIdList(id);
	}

	public List<UserGroup> selectByMap(Map<String, Object> columnMap) {
		return userGroupMapper.selectByMap(columnMap);
	}

	public List<Long> getPermissionIdByGroupId(Long groupId) {

		return userGroupMapper.getPermissionIdByGroupId(groupId);
	}

	public void deletePreviousGroupPermissionBound(String groupId) {
		userGroupMapper.deletePreviousGroupPermissionBound(groupId);

	}

	public List<UserGroup> searchByGroupNamePrefix(String tenantId, String groupNamePrefix) {
		return userGroupMapper.searchByGroupNamePrefix(tenantId, groupNamePrefix);
	}

	public void insertGroupUserRelation(@Param("userId") Long id, @Param("groupId") Long groupId) {
		userGroupMapper.insertUserGroupRelation(id, groupId);
	}

	public List<String> getUserNameList(Long groupId) {
		return userGroupMapper.getUserNameList(groupId);
	}

	public List<String> getRoleNameList(Long groupId) {
		return userGroupMapper.getRoleNameList(groupId);
	}

}
