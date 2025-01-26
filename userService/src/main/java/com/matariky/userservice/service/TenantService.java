package com.matariky.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.userservice.bean.Tenant;
import com.matariky.userservice.mapper.TenantMapper;

@Service("TenantService")
public class TenantService extends BaseServiceImpl<TenantMapper,Tenant> implements BaseService<Tenant>{
	@Autowired
	TenantMapper tenantMapper;
	
	
	public Tenant getTenantById(String tenantId) {
		return tenantMapper.selectById(tenantId);
	}
	
	public Tenant selectBytenantCode(String tenantCode) {
		return tenantMapper.selectBytenantCode(tenantCode);
	}

}
