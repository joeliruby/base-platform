package com.matariky.userservice.service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import com.matariky.commonservice.message.bean.Message;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;
import com.matariky.userservice.mapper.UserGroupMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;

import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.userservice.bean.Permission;
import com.matariky.userservice.bean.TreeModel;
import com.matariky.userservice.bean.UserRole;
import com.matariky.userservice.mapper.PermissionMapper;
import com.matariky.userservice.mapper.UserRoleMapper;
import com.matariky.utils.TreeUtils;

/**
* Business Inteface Implementation
* @author AUTOMATION
*/
@Service
public class UserRoleService extends BaseServiceImpl<UserRoleMapper,UserRole> implements BaseService<UserRole>{

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private UserGroupMapper userGroupMapper;

	 
	public Page<UserRole> getUserRoleAll(Map<String, Object> map){
		return userRoleMapper.getUserRoleAll(map);
	}

	 
	public int getUserRoleAllCount(){
		return userRoleMapper.getUserRoleAllCount();
	}

	 
	public int createUserRole(UserRole bean){
		return userRoleMapper.createUserRole(bean);
	}

	 
	public int updateUserRole(UserRole bean){
		int updateUserRole = userRoleMapper.updateUserRole(bean);
		tokenService.expireAllLoginUsersAfterCredentialChanges(bean);
		return updateUserRole;
	}

	 
	public int delUserRoleById(int id){
		return userRoleMapper.delUserRoleById(id);
	}

	 
	public UserRole getUserRoleById(Long id){
		return userRoleMapper.getUserRoleById(id);
	}

	public List<UserRole> selectRole(String tenantId) {

		return userRoleMapper.selectRole(tenantId);
	}

	public int updateDeleteTimeById(String[] ids) {
		for (int i=0;i<ids.length;i++) {
			String id = ids[i];
			Integer roleIdCount = userGroupMapper.getRoleIdCount(Long.parseLong(id));
			if (roleIdCount > 0) {
				throw new QslException(MessageKey.USER_ROLE_USED);
			}
			 roleIdCount = userRoleMapper.getRoleCountById(Long.parseLong(id));
			if (roleIdCount>0){
				throw new QslException(MessageKey.USER_ROLE_USED);
			}
		}
		return userRoleMapper.updateDeleteTimeById(ids);
	}


	public List<UserRole> selectByMap(Map<String, Object> columnMap) {
		return userRoleMapper.selectByMap(columnMap);
	}

	public List<Long> getRoleIdsByTenantIdAndRoleNames(String roleNames, String tenantId) {
		return userRoleMapper.getRoleIdsByTenantIdAndRoleNames(roleNames.split(","),  tenantId);
	}

	public List<TreeModel> getPermissionByRole(Long roleId, String tenantId, Long applicationId) {

		List<Long> permissionIdList = userRoleMapper.getPermissionIdByRoleId(roleId);
		if (CollectionUtils.isEmpty(permissionIdList)) {
			return null;
		}
		QueryWrapper<Permission> queryWrapper =new QueryWrapper<>();
		queryWrapper.eq("tenant_id", tenantId)
		.eq("application_id", applicationId)
		.in("id", permissionIdList)
		.eq("delete_time",0)
		.eq("is_active", 1)
		;

		List<Permission> permissionList = permissionMapper.selectList(queryWrapper);
		List<TreeModel> treeModels=new ArrayList<>();
		for (Permission permission : permissionList) {
			TreeModel t = new TreeModel();

			t.setId(permission.getId());
			t.setUrl(permission.getUrl());
			t.setResourceType(permission.getResourceType());
			t.setIcon(permission.getIcon());
			t.setActive(permission.getIsActive());
			t.setSortOrder(permission.getSortOrder());
			t.setName(permission.getPermissionName());
			t.setPid(permission.getParentId());
			treeModels.add(t);
		}

		return TreeUtils.build(treeModels);
	}

	public List<TreeModel> getPermissionByRoleNotree(Long roleId, String tenantId, Long applicationId) {

		return userRoleMapper.getPermissionByRole(roleId,tenantId,applicationId);
	}

	public List<Long> getPermissionIdByRoleId(Long roleId) {
		return userRoleMapper.getPermissionIdByRoleId(roleId);
	}

}
