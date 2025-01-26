package com.matariky.userservice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.matariky.id.SnowflakeIdWorker;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.userservice.bean.UserOrganization;
import com.matariky.userservice.bean.UserTenant;
import com.matariky.userservice.mapper.UserOrganizationMapper;
import com.matariky.userservice.mapper.UserTenantMapper;
import com.matariky.utils.DateUtil;
import com.matariky.utils.OrgCodeUtil;

/**
* Business Inteface Implementation
* @author AUTOMATION
*/
@Service
public class UserTenantService extends BaseServiceImpl<UserTenantMapper,UserTenant> implements BaseService<UserTenant>{

	@Autowired
	private UserTenantMapper userTenantMapper;
	
	@Autowired
	private UserOrganizationMapper userOrganizationMapper;
	
	@Autowired
	private TokenService tokenService;
	
	 
	public Page<UserTenant> getUserTenantAll(Map<String, Object> map){
		return userTenantMapper.getUserTenantAll(map);
	}

	 
	public int getUserTenantAllCount(){
		return userTenantMapper.getUserTenantAllCount();
	}

	 
	public int createUserTenant(UserTenant bean){
		return userTenantMapper.createUserTenant(bean);
	}

	 
	public int updateUserTenant(UserTenant bean){
		return userTenantMapper.updateUserTenant(bean);
	}

	 
	public int delUserTenantById(int id){
		return userTenantMapper.delUserTenantById(id);
	}

	 
	public UserTenant getUserTenantById(String id){
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
		bean.setTenantCode(selectByTenantCode.getTenantCode()+"_"+bean.getId());
		bean.setDeleteTime(0l);
		bean.setIsActive(true);
		userTenantMapper.createUserTenant(bean);
		
		
		//设置 Tenant 编码
//		
//		userTenantMapper.updateTenantCodeById(bean.getId(),selectByTenantCode.getTenantCode()+"_"+bean.getId());
		
		
		//保存 Tenant 和应用关系  user_r_application_tenant
		saveOrUpdateApplication(bean);
		
		
		//New Tenant 系统自动创建该 Tenant 的第一级组织机构，机构 Name为 Tenant  Name，并在其机构下创建一个外部联系人节点， Tenant 管理员登录后可维护其组织机构但不能编辑和删除外部联系人这个节点。
		
		//先初始化此 Tenant 下第一个组织
		UserOrganization parentOrganization=new UserOrganization();
		
		String code="";
		//int countByParentId = userOrganizationMapper.getCountByParentId(0l,bean.getParentId().toString())+1;
		
		
		parentOrganization.setOrganizationCode(code);//要写活
		parentOrganization.setOrganizationName(bean.getTenantName());
		parentOrganization.setCreateTime(DateUtil.getCurrentDateAndTime().getTime());
		parentOrganization.setDescription(bean.getTenantName());
		parentOrganization.setTenantId(selectByTenantCode.getTenantCode()+"_"+bean.getId());
		parentOrganization.setOrgType(1);
		parentOrganization.setOrderNum(1);
		parentOrganization.setParentId(Long.valueOf(0));
		
		userOrganizationMapper.insert(parentOrganization);
		
		
		code=OrgCodeUtil.generateDeparmentOrganzationCode(selectByTenantCode.getId(), parentOrganization.getId());//"org_0"+"_"+parentOrganization.getId();
		
		
		//设置组织编码
		userOrganizationMapper.updateOrganizationCodeById(parentOrganization.getId(),code);
		
		//organization_code 组织机构编码
		//organization_name 组织机构 Name
		//create_time 创建 Time 
		//description 组织机构描述
		//tenant_id  Tenant 编码
		//org_type 组织机构Type ：1机构，2部门，3小组
		//order_num 排序
		//parent_id 父组织机构ID 
		
		//然后再 Generation 第一个儿子组织机构
//		UserOrganization sonOrganization=new UserOrganization();
//		
//		sonOrganization.setOrganizationName("外部联系人");
//		sonOrganization.setOrganizationCode(code);
//		sonOrganization.setCreateTime(DateUtil.getCurrentDateAndTime().getTime());
//		sonOrganization.setDescription("外部联系人");
//		sonOrganization.setTenantId(selectByTenantCode.getTenantCode()+"_"+bean.getId());
//		sonOrganization.setOrgType(2);
//		sonOrganization.setOrderNum(1);
//		sonOrganization.setParentId(parentOrganization.getId());
//		
//		userOrganizationMapper.createUserOrganization(sonOrganization);
//		
//		
//		
//		userOrganizationMapper.updateOrganizationCodeById(sonOrganization.getId(),code+"_"+sonOrganization.getId());
		
		
	}

	public void saveOrUpdateApplication(UserTenant bean) {
		
		//先删除 Tenant 和应用的关系
		//deleteApplicationByTenantCodes(new String []{bean.getTenantCode()});
		
		// Tenant 没有一个应用的情况
		if(CollectionUtils.isEmpty(bean.getApplicationIds())) {
			return;
		}
		
		//保存 Tenant 和应用关系
//		for (Map<String,Object> applicationId:bean.getApplicationIds()) {
//			Map<String,Object> map =new HashMap<>();
//			
//			map.put("application_id", Long.parseLong(applicationId.get("id").toString()));
//			map.put("tenant_id", bean.getId());
//			map.put("tenant_code", bean.getTenantCode());
//			map.put("application_name", applicationId.get("applicationName").toString());
//			user_tenantMapper.saveRApplicationTenant(map);
//		}
		
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
		//Update Tenant 
		userTenantMapper.updateUserTenant(bean);
		
		//保存 Tenant 和应用关系
		saveOrUpdateApplication(bean);
		
		tokenService.expireAllLoginUsersAfterCredentialChanges(bean);
	}

	public List<Map<String, Object>> selectApplication(String tenantCode) {
		
		return userTenantMapper.selectApplication(tenantCode);
	}

	public List<UserTenant> selectByMap(Map<String, Object> columnMap) {
		
		return userTenantMapper.selectByMap(columnMap);
	}

}
