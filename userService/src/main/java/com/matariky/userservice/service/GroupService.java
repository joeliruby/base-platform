package com.matariky.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.matariky.userservice.bean.Group;
import com.matariky.userservice.mapper.GroupMapper;

@Service("GroupService")
public class GroupService {
	@Autowired
	GroupMapper groupMapper;

	@Cacheable(key = "'group-'+#tenantId+'-'+#groupNamePrefix", value = "groups")
	public List<Group> searchByGroupNamePrefix(String tenantId, String groupNamePrefix) {
		return groupMapper.searchByGroupNamePrefix( tenantId,  groupNamePrefix);
	}

	


	
}
