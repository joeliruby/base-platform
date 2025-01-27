package com.matariky.userservice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.matariky.id.SnowflakeIdWorker;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.userservice.bean.UserOrganization;
import com.matariky.userservice.bean.UserTenant;
import com.matariky.userservice.mapper.UserOrganizationMapper;
import com.matariky.userservice.mapper.UserTenantMapper;
import com.matariky.utils.DateUtil;
import com.matariky.utils.OrgCodeUtil;

/**
 * Business Inteface Implementation
 * 
 * @author AUTOMATION
 */
@Service
public class UserTenantService extends BaseServiceImpl<UserTenantMapper, UserTenant> {

	@Autowired
	private UserTenantMapper userTenantMapper;

	@Autowired
	private UserOrganizationMapper userOrganizationMapper;

	@Autowired
	private TokenService tokenService;

	public Page<UserTenant> getUserTenantAll(Map<String, Object> map) {
		return userTenantMapper.getUserTenantAll(map);
	}

	public int getUserTenantAllCount() {
		return userTenantMapper.getUserTenantAllCount();
	}

	public int createUserTenant(UserTenant bean) {
		return userTenantMapper.createUserTenant(bean);
	}

	public int updateUserTenant(UserTenant bean) {
		return userTenantMapper.updateUserTenant(bean);
	}

	public int delUserTenantById(int id) {
		return userTenantMapper.delUserTenantById(id);
	}

	public UserTenant getUserTenantById(String id) {
		return userTenantMapper.getUserTenantById(id);
	}

	public UserTenant selectByTenantCode(String tenantId) {
		return userTenantMapper.selectByTenantCode(tenantId);
	}

	public List<UserTenant> selectTenant(String tenantId) {

		return userTenantMapper.selectTenant(tenantId);
	}

	public UserTenant selectBytenantCode(String tenantId) {
		return userTenantMapper.selectByTenantCode(tenantId);
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(UserTenant bean) {
		UserTenant selectByTenantCode = userTenantMapper.getUserTenantById(bean.getParentCode());
		bean.setParentId(selectByTenantCode.getId());
		bean.setCreateTime(DateUtil.getCurrentDateAndTime().getTime());
		bean.setId(SnowflakeIdWorker.getId());
		bean.setTenantCode(selectByTenantCode.getTenantCode() + "_" + bean.getId());
		bean.setDeleteTime(0l);
		bean.setIsActive(true);
		userTenantMapper.createUserTenant(bean);

		// New Tenant System AutomaticCreate, the first -level organization of Tenant,
		// the institution name is Tenant Name
		// , and under its institution
		// One external contact node, tenant administrator login can maintain its
		// organization but cannot be update and delete external contacts.

		// First initialize this one ONE organization under this TENANT
		UserOrganization parentOrganization = new UserOrganization();

		String code = "";
		parentOrganization.setOrganizationCode(code);// Write to work
		parentOrganization.setOrganizationName(bean.getTenantName());
		parentOrganization.setCreateTime(DateUtil.getCurrentDateAndTime().getTime());
		parentOrganization.setDescription(bean.getTenantName());
		parentOrganization.setTenantId(selectByTenantCode.getTenantCode() + "_" + bean.getId());
		parentOrganization.setOrgType(1);
		parentOrganization.setOrderNum(1);
		parentOrganization.setParentId(Long.valueOf(0));

		userOrganizationMapper.insert(parentOrganization);

		code = OrgCodeUtil.generateDeparmentOrganzationCode(selectByTenantCode.getId(), parentOrganization.getId());// "org_0"+"_"+parentOrganization.getId();

		// Configuration organize Code
		userOrganizationMapper.updateOrganizationCodeById(parentOrganization.getId(), code);

		// Organization_Code Organization Code
		// organize_name organization name
		// Create_time Create time
		// Descripting organizational organization Description
		// Tenant_id Tenant Code
		// ORG_TYPE Organization Type: 1 Agency, 2 departments, 3 groups
		// Order_num Sort
		// Parent_id Father Organization ID

	}

	public void deleteApplicationByTenantCodes(String[] tenantCodes) {

		userTenantMapper.deleteApplicationByTenantCodes(tenantCodes);
	}

	public int getCountByParentId(Long id) {

		return userTenantMapper.getCountByParentId(id);
	}

	public int updateDeleteTimeById(String[] ids) {
		return userTenantMapper.updateDeleteTimeById(ids);
	}

	public void update(UserTenant bean) {

		bean.setUpdateTime(DateUtil.getCurrentDateAndTime().getTime());
		// Update Tenant
		userTenantMapper.updateUserTenant(bean);

		// Save Tenant 和 App 关系
		tokenService.expireAllLoginUsersAfterCredentialChanges(bean);
	}

	public List<Map<String, Object>> selectApplication(String tenantCode) {

		return userTenantMapper.selectApplication(tenantCode);
	}

	public List<UserTenant> selectByMap(Map<String, Object> columnMap) {

		return userTenantMapper.selectByMap(columnMap);
	}

}
