package com.matariky.userservice.service;

import java.util.*;

import com.matariky.userservice.dto.PermissionInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.userservice.bean.Permission;
import com.matariky.userservice.bean.TreeModel;
import com.matariky.userservice.dto.PermissionDTO;
import com.matariky.userservice.mapper.PermissionMapper;
import com.matariky.utils.TokenUtils;
import com.matariky.utils.TreeUtils;

@Component
public class PermissionService extends BaseServiceImpl<PermissionMapper, Permission> {
	@Autowired
	PermissionMapper permissionMapper;

	public Set<Permission> getPermissionsByUserId(Long userId, String tenantId, Long applicationId) {
		SortedSet<Permission> permissionSet = new TreeSet<Permission>();
		permissionSet
				.addAll(permissionMapper.getDirectlyAssignedPermissionIdsByUserId(userId, tenantId, applicationId));
		permissionSet.addAll(permissionMapper.getRoleAssignedPermissionIdsByUserId(userId, tenantId, applicationId));
		permissionSet.addAll(permissionMapper.getGroupAssignedPermissionIdsByUserId(userId, tenantId, applicationId));
		permissionSet
				.addAll(permissionMapper.getGroupRoleAssignedPermissionIdsByUserId(userId, tenantId, applicationId));
		return permissionSet;
	}

	public Set<Permission> getOrderSuitePermissionByUserId(Long applicationId, String orderTenantId) {
		SortedSet<Permission> permissionSet = new TreeSet<Permission>();

		Map<String, Object> params = new HashMap<>();
		params.put("applicationId", applicationId);
		params.put("orderTenantId", orderTenantId);

		permissionSet.addAll(permissionMapper.getOrderSuitePermissionByUserId(params));
		return permissionSet;
	}

	public List<PermissionDTO> getPermissionList(Long userId, String tenantId) {
		List<PermissionDTO> dtoList = permissionMapper.getPermissionList(userId, tenantId);
		return TreeUtils.build(dtoList);
	}

	public Set<Permission> getPermissionTreeByTenantIdApplicationIdUserId(String tenantId, Long applicationId,
			Long userId) {
		Set<Permission> sortedPermissionList = getPermissionsByUserId(userId, tenantId, applicationId);
		return sortedPermissionList;
	}

	public void createResourceAllocationToUser(Long userId, long permissionId) {

		permissionMapper.createResourceAllocationToUser(userId, permissionId);

	}

	public List<Map<Long, String>> findPermissionsByPermissionNamePrefix(String tenantId, Long applicationId,
			String permissionName) {
		return permissionMapper.findPermissionsByPermissionNamePrefix(tenantId, applicationId, permissionName);
	}

	public Set<Permission> getDataPermissionTreeByTenantIdApplicationId(String tenantId, Long applicationId) {
		return permissionMapper.getDataPermissionTreeByTenantIdApplicationId(tenantId, applicationId);
	}

	public void createResourceAllocationToGroup(Long groupId, long permissionId) {

		permissionMapper.createResourceAllocationToGroup(groupId, permissionId);
	}

	public List<TreeModel> getTreeByApplicationId(String tenantId, Long applicationId) {

		return TreeUtils.build(permissionMapper.getTreeByApplicationId(tenantId, applicationId, null));
	}

	public List<TreeModel> getTreeByApplicationId(String tenantId, Long applicationId, Long userId) {
		return TreeUtils.build(permissionMapper.getTreeByApplicationId(tenantId, applicationId, userId));
	}

	public void createResourceAllocationToRole(Long roleId, long parseLong) {

		permissionMapper.createResourceAllocationToRole(roleId, parseLong);
	}

	public void deleteRRolePermission(Long roleId) {
		permissionMapper.deleteRRolePermission(roleId);
	}

	public void deleteRUserPermission(Long userId) {
		permissionMapper.deleteRUserPermission(userId);
	}

	public List<TreeModel> getPermissionTreeByTenantId(String tenantId, Long applicationId, Long inSuiteVaild) {

		return permissionMapper.getPermissionTreeByTenantId(tenantId, applicationId, inSuiteVaild);
	}

	public List<TreeModel> getAllMenuList(Map<String, Object> map) {

		return permissionMapper.getAllMenuList(map);
	}

	public PermissionInfoVO getPermissionsById(String id) {
		return permissionMapper.getPermissionsById(id);
	}

	public List<TreeModel> getPermissionTreeByPid(String pid) {

		return null;
	}

	public int updatePermission(Permission bean) {
		// permissionMapper.updateById(bean)
		return permissionMapper.updateById(bean);
	}

	public void updateDeleteTimeById(String[] id) {

		permissionMapper.updateDeleteTimeById(id);
	}

	public Permission selectByTenantIdAndPermissionName(String tenantId, String permissionName) {
		return permissionMapper.selectByTenantIdAndPermissionName(tenantId, permissionName);
	}

	public void newPermissionDefaults(Permission permission) {
		permission.setDeleteTime(0l);
		permission.setCreatedBy((Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(
				((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest()))));
		permission.setCreateTime(System.currentTimeMillis());

	}

	public List<Permission> selectByMap(Map<String, Object> map) {

		return permissionMapper.selectByMap(map);
	}

	public List<Permission> selectList(QueryWrapper<Permission> queryWrapper) {
		queryWrapper.eq("delete_time", 0)
				.eq("is_active", 1);
		return permissionMapper.selectList(queryWrapper);
	}

	public List<TreeModel> selectTreeList(QueryWrapper<Permission> queryWrapper) {
		queryWrapper.eq("delete_time", 0)
				.eq("is_active", 1).orderByDesc("id");
		List<Permission> selectList = permissionMapper.selectList(queryWrapper);
		List<TreeModel> treeModels = new ArrayList<>();
		for (Permission permission : selectList) {
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

	public void createPermission(Permission newperm) {
		permissionMapper.createPermission(newperm);
	}

	public void insertSelective(Permission newperm) {
		permissionMapper.insertSelective(newperm);
	}

	public List<Permission> getOrderSuitePermissionByParam(Map<String, Object> params) {
		return permissionMapper.getOrderSuitePermissionByParam(params);
	}

	public Set<Permission> selectPermissionByTenantId(String orderTenantId) {

		SortedSet<Permission> permissionSet = new TreeSet<Permission>();

		Map<String, Object> params = new HashMap<>();
		params.put("orderTenantId", orderTenantId);

		permissionSet.addAll(permissionMapper.selectPermissionByTenantId(params));
		return permissionSet;
	}

}
