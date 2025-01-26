package com.matariky.user.customer.external;

import lombok.Data;

import java.util.List;

/**
 * @author matariky
 */
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
