package com.matariky.userservice.dto;

import java.util.List;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.matariky.userservice.bean.UserApplication;

public class UserApplicationDTO extends UserApplication implements IdentifierGenerator {

	@Override
	public Number nextId(Object entity) {
		return null;
	}
	
	

	public List<Long> getPermissions() {
		return permissions;
	}



	public void setPermissions(List<Long> permissions) {
		this.permissions = permissions;
	}



	private List<Long> permissions;

}
