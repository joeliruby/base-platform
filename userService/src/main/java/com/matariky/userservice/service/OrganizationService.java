package com.matariky.userservice.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matariky.userservice.mapper.OrganizationMapper;

@Service("OrganzationService")
public class OrganizationService {
	@Autowired
	OrganizationMapper orgMapper;

	public Map<String, String> getOrgIdByUserId(String tenantId, String userId) {
		return orgMapper.getOrgIdByUserId( tenantId,  userId);
	}

	
}
