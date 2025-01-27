package com.matariky.userservice.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;

import cn.hutool.core.collection.CollUtil;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.userservice.bean.TreeModel;
import com.matariky.userservice.bean.UserApplication;
import com.matariky.userservice.bean.UserTenant;
import com.matariky.userservice.mapper.UserApplicationMapper;
import com.matariky.userservice.mapper.UserTenantMapper;
import com.matariky.utils.DateUtil;

/**
 * Business Inteface Implementation
 * 
 * @author AUTOMATION
 */
@Service
public class UserApplicationService extends BaseServiceImpl<UserApplicationMapper, UserApplication>
		implements BaseService<UserApplication> {

	@Autowired
	private UserApplicationMapper userApplicationMapper;
	@Autowired
	private UserTenantMapper userTenantMapper;

	public Page<UserApplication> getUserApplicationAll(Map<String, Object> map) {
		return userApplicationMapper.getUserApplicationAll(map);
	}

	public int getUserApplicationAllCount() {
		return userApplicationMapper.getUserApplicationAllCount();
	}

	public int createUserApplication(UserApplication bean) {
		return userApplicationMapper.createUserApplication(bean);
	}

	public int updateUserApplication(UserApplication bean) {
		return userApplicationMapper.updateUserApplication(bean);
	}

	public int delUserApplicationById(int id) {
		return userApplicationMapper.delUserApplicationById(id);
	}

	public UserApplication getUserApplicationById(Long id) {
		return userApplicationMapper.getUserApplicationById(id);
	}

	public Long getDefaultApplicationByTenantIdandUserId(String tenantId, Long id) {
		return userApplicationMapper.getDefaultApplicationByTenantIdandUserId(tenantId, id);
	}

	public Long selectTenantApplicationJointId(String tenantId, Long applicationId) {
		return userApplicationMapper.selectTenantApplicationJointId(tenantId, applicationId);
	}

	public void createTenantApplicationPermissionBound(Long appTenantId, long parseLong) {
		userApplicationMapper.createTenantApplicationPermissionBound(appTenantId, parseLong);

	}

	public List<UserApplication> selectApplication(String tenantId) {

		return userApplicationMapper.selectApplication(tenantId);
	}

	public void save(UserApplication bean) {
		// Create Time
		bean.setCreateTime(DateUtil.getCurrentDateAndTime().getTime());

		// 插入 App 表
		userApplicationMapper.insert(bean);

	}

	@Transactional(rollbackFor = Exception.class)
	public void addApplicationByTenant(UserApplication bean) {
		// App id
		Long applicationId = bean.getId();

		UserTenant userTenant = userTenantMapper.selectByTenantCode(bean.getTenantId());

		if (userTenant == null) {// 没有这个 Tenant
			return;
		}

		// 插入 Tenant 和 App 的中间表
		saveOrUpdateTenant(bean, userTenant);

		// Update App 表 不改 Name 字 入口 Address 图标 Description 过期 Time
		userApplicationMapper.updateUserApplication(bean);

		// 插入 App 和资源的中间表
		saveOrUpdatePermission(applicationId, bean.getPermissionIdList());
	}

	public void saveOrUpdatePermission(Long applicationId, List<Long> permissionIdList) {
		// 先Delete 此 App 和资源的中间关系
		deletePermissionByApplicationIds(new Long[] { applicationId });

		// App 没有 one 资源的情况
		if (CollUtil.isEmpty(permissionIdList)) {
			return;
		}

		// Save App 和资源之间关系
		for (Long permissionId : permissionIdList) {
			userApplicationMapper.saveRApplicationPermission(applicationId, permissionId);
		}

	}

	public void deletePermissionByApplicationIds(Long[] applicationIds) {
		userApplicationMapper.deletePermissionByApplicationIds(applicationIds);
	}

	public void saveOrUpdateTenant(UserApplication bean, UserTenant userTenant) {

		userApplicationMapper.insert(bean);
		// 先Delete 此 App 和 Tenant 的关系
		if (bean.getId() != null)// New
			deleteTenantByApplicationIds(new Long[] { bean.getId() });

		Map<String, Object> map = new HashMap<>();

		// App id
		map.put("application_id", bean.getId());
		// Tenant id
		map.put("tenant_id", userTenant.getId());
		// Tenant Code
		map.put("tenant_code", userTenant.getTenantCode());
		// App Name
		map.put("application_name", bean.getApplicationName());

		// Save App 和 Tenant 关系
		userApplicationMapper.saveRApplicationTenant(map);

	}

	public void deleteTenantByApplicationIds(Long[] applicationIds) {
		userApplicationMapper.deleteTenantByApplicationIds(applicationIds);
	}

	public List<TreeModel> getTreeListByTenantId(String tenantId) {

		return userApplicationMapper.getTreeListByTenantId(tenantId);
	}

	public String getNameById(String tenantId, Long applicationId) {

		return userApplicationMapper.getNameById(tenantId, applicationId);

	}

	public List<Long> getPermissionIdList(Long applicationId) {

		return userApplicationMapper.getPermissionIdList(applicationId);
	}

	public List<UserApplication> getUserApplicationByTenant(Map<String, Object> map) {

		return userApplicationMapper.getUserApplicationByTenant(map);
	}

	public void delUserTenant(String[] id) {
		userApplicationMapper.delUserTenant(id);
	}

	public int updateDeleteTimeById(String[] id) {

		return userApplicationMapper.updateDeleteTimeById(id);
	}

	public List<TreeModel> getTreeListByApplicationId(Long applicationId) {

		return userApplicationMapper.getTreeListByApplicationId(applicationId);
	}

	public List<UserApplication> getAppList(String tenantId) {
		return userApplicationMapper.getAppList(tenantId);
	}

	public List<TreeModel> getRApplicationTenantPermission(Long applicationId) {
		return userApplicationMapper.getRApplicationTenantPermission(applicationId);
	}

	public void insertUserApplicationRelation(Long userId, long parseLong, Long id) {
		userApplicationMapper.insertUserApplicationRelation(userId, parseLong, id);
	}

	public List<UserApplication> getUserApplicationAllWithUserId(Map<String, Object> map) {
		return userApplicationMapper.getUserApplicationAllWithUserId(map);
	}

	public int countUserApplicationTenant(Long userId, String applicationId, Long tenantId) {
		return userApplicationMapper.countUserApplicationTenant(userId, applicationId, tenantId);
	}

	public List<UserApplication> selectList(QueryWrapper<UserApplication> queryWrapper) {
		queryWrapper.eq("delete_time", 0)
				.eq("is_active", 1);
		return userApplicationMapper.selectList(queryWrapper);
	}

	public List<String> getApplicationIdList(Long id) {
		return userApplicationMapper.getApplicationIdList(id);
	}

	public void deleteApplicationByUserId(Long id) {
		userApplicationMapper.deleteApplicationByUserId(id);
	}

}
