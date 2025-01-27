package com.matariky.userservice.service;

import java.util.*;

import com.matariky.userservice.bean.*;
import com.matariky.utils.NumberUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.github.pagehelper.util.StringUtil;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.constant.TokenConstant;
import com.matariky.exception.QslException;
import com.matariky.redis.RedisUtils;

@Service("TokenService")
public class TokenService {

	@Value("${message.locale}")
	String locale;

	@Autowired
	CommonDictService commonDictService;

	// Token expires Time 30 minutes (User Login expires twice the time of this
	// time, based on the token cache at the REIDS cache)
	@Autowired
	PermissionService permissionService;
	@Autowired
	UserTenantService tenantService;
	@Autowired
	UserApplicationService applicationService;
	@Autowired
	UserService userService;
	@Autowired
	RedisUtils redisUtils;
	@Autowired
	UserGroupService userGroupService;

	public Map<String, String> getToken(User user) throws QslException {

		Set<Permission> permissionSet = null;
		Long applicationId = user.getApplicationId();
		List<Long> appPermList = applicationService.getPermissionIdList(applicationId);
		Integer adminTenantCount = userService.isAdmin(user.getId());
		if (applicationId != null && !applicationId.equals(0l)) {// Not for the first time Login
			permissionSet = permissionService.getPermissionsByUserId(user.getId(), user.getTenantId(),
					user.getApplicationId());

		} else {// Retrieve The User's first One APP under the Tenant
			applicationId = applicationService
					.getDefaultApplicationByTenantIdandUserId(user.getTenantId().split("_")[0], user.getId());
			// Current User As an administrator app, the number
			if (applicationId == null && adminTenantCount == 0) {
				throw new QslException(MessageKey.EMPTY_APPLICATION_ID);
			}
			permissionSet = permissionService.getPermissionsByUserId(user.getId(), user.getTenantId(), applicationId);
			if (CollectionUtils.isEmpty(permissionSet) && adminTenantCount == 0) {
				throw new QslException(MessageKey.PERMISSION_NOT_ASSIGNED);
			}
			user.setApplicationId(applicationId);
			updateLoginInfo(user);
		}
		List<Permission> filteredAppPermList = new ArrayList<Permission>();
		// The intersection of user permissions and APP permissions
		for (Permission permission : permissionSet) {
			if (appPermList.contains(permission.getId())) {
				filteredAppPermList.add(permission);
			}
		}
		Set<String> groupSet = userGroupService.getGroupsByUserId(user.getId(), user.getTenantId());
		Map<String, String> tokenMap = new HashMap<String, String>();
		String permissionIds = strinify(permissionSet);
		String groupIds = strinifyGroupId(groupSet);
		UserTenant tenant = tenantService.selectBytenantCode(user.getTenantId());
		String tenantName = tenant.getTenantName();
		if (user.getTheme() != null) {
			tokenMap.put("theme", user.getTheme());
		} else if (tenant.getTheme() != null) {
			tokenMap.put("theme", tenant.getTheme());
		} else {
			tokenMap.put("theme", "default");
		}
		UserApplication userApplication = applicationService.selectById(applicationId);
		Long EXPIRE_TIME = userApplication.getActivityTimeout() * 1000;// 转毫秒
		Date expirationDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
		String token = "";
		token = JWT.create().withAudience(user.getId().toString()).withSubject(user.getLoginName())
				.withClaim("loginName", user.getLoginName())
				.withClaim("organizationId", user.getOrganizationId())
				.withClaim("organizationCode", user.getDepartmentOrganizationCode())
				.withClaim("selfOrganizationCode", user.getSelfOrganizationCode())
				.withClaim("tenantId", user.getTenantId()).withClaim("applicationId", applicationId)
				.withClaim("applicationType",
						Objects.isNull(userApplication) ? NumberUtils.INTEGER_ONE
								: userApplication.getApplicationType())
				.withClaim("permissions", permissionIds).withClaim("realName", user.getRealName())
				.withClaim("groups", groupIds)
				.withClaim("tenantName", tenantName).withClaim("isAdmin", adminTenantCount)
				.withClaim("theme", tokenMap.get("theme")).withClaim("locale", user.getLocale())
				.withClaim("logo", tenant.getDomainName()).withExpiresAt(expirationDate)
				.sign(Algorithm.HMAC256(user.getTenantId().toString()));// 以 password 作为 token 的密钥
		tokenMap.put("permissions", permissionIds);
		tokenMap.put("groups", groupIds);
		tokenMap.put("token", token);
		tokenMap.put("tenantName", tenantName);
		tokenMap.put("userId", user.getId().toString());
		tokenMap.put("tenantId", user.getTenantId());
		tokenMap.put("tenantPK", tenant.getId().toString());
		tokenMap.put("tokenCode", tenant.getTenantCode());
		tokenMap.put("organzationCode", user.getDepartmentOrganizationCode());
		tokenMap.put("selfOrganizationCode", user.getSelfOrganizationCode());
		tokenMap.put("applicationId", applicationId == null ? "-1" : applicationId.toString());
		tokenMap.put("realName", user.getRealName());
		tokenMap.put("locale", user.getLocale());
		tokenMap.put("logo", tenant.getDomainName());
		tokenMap.put("isAdmin", adminTenantCount + "");
		if (userApplication != null) {
			tokenMap.put("applicationType", userApplication.getApplicationType() == null ? "0"
					: userApplication.getApplicationType().toString());
		}
		return tokenMap;
	}

	public void updateLoginInfo(User userForBase) {
		userForBase.setLoginTime(System.currentTimeMillis());
		userForBase.setLastLoginTime(System.currentTimeMillis());
		userForBase.setLoginCount(userForBase.getLoginCount() + 1);
		userService.updateById(userForBase);
	}

	private String strinifyGroupId(Set<String> groupSet) {
		StringBuilder sb = new StringBuilder();
		for (String groupId : groupSet) {
			sb.append(",").append(groupId);
		}
		String groupIds = sb.toString();
		if (StringUtil.isEmpty(groupIds)) {
			return null;
		} else
			return groupIds.substring(1);
	}

	private String strinify(Collection<Permission> permissionSet) {
		StringBuilder sb = new StringBuilder();
		for (Permission permission : permissionSet) {
			sb.append(",").append(permission.getOrigId());
		}
		String permissionIds = sb.toString();
		if (StringUtil.isEmpty(permissionIds)) {
			return null;
		} else
			return permissionIds.substring(1);
	}

	public void expireAllLoginUsersAfterCredentialChanges(Object changedCredential) {
		if (changedCredential instanceof UserTenant) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("delete_time", 0);
			map.put("is_active", 1);
			map.put("tenant_id", ((UserTenant) changedCredential).getTenantCode());
			List<User> usersToExpire = userService.selectByMap(map);
			for (User userToExpire : usersToExpire) {
				expireUserLogin(userToExpire.getId());
			}

		} else if (changedCredential instanceof UserRole) {
			List<Long> usersToExpire = userService.getUsersByRoleId(((UserRole) changedCredential).getId());
			for (Long userToExpire : usersToExpire) {
				expireUserLogin(userToExpire);
			}
		} else if (changedCredential instanceof UserGroup) {
			List<Long> usersToExpire = userGroupService.getUserIdList(((UserGroup) changedCredential).getId());
			for (Long userToExpire : usersToExpire) {
				expireUserLogin(userToExpire);
			}
		}

	}

	private void expireUserLogin(Long userToExpire) {
		if (redisUtils.get(TokenConstant.LAST_ACCESS_TIME + "_" + userToExpire) != null)
			redisUtils.expire(TokenConstant.LAST_ACCESS_TIME + "_" + userToExpire, 1l);
	}

}
