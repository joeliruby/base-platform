package com.matariky.bo;

import lombok.Data;

import java.util.List;

@Data
public class Customer {
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String birthday;
	private String gender;
	private List<String> groups;
	private List<String> roles;
}
