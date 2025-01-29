package com.matariky.userservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;
import com.matariky.userservice.bean.TreeModel;
import com.matariky.userservice.bean.UserGroup;
import com.matariky.userservice.bean.UserOrganization;
import com.matariky.userservice.service.UserGroupService;
import com.matariky.userservice.service.UserOrganizationService;
import com.matariky.userservice.service.UserTenantService;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.TokenUtils;

import cn.hutool.core.collection.CollUtil;

/**
 * Controller
 * 
 * @author AUTOMATION
 */
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
public class UserGroupController {

	@Value("${message.locale}")
	String locale;

	@Autowired
	private UserGroupService userGroupService;

	@Autowired
	private UserTenantService userTenantService;

	@Autowired
	private UserOrganizationService orgService;

	@RequestMapping(value = "/userGroup/list", method = RequestMethod.GET)
	public AjaxResult list(
			HttpServletRequest request,
			@RequestParam Map<String, Object> map,
			@PathVariable("tenantId") String tenantId
	/*
	 * @ApiParam(value = "JWT Token", required =
	 * true) @RequestHeader("Authorization") String jwt
	 */) {
		map.put("tenantId", TokenUtils.extractTenantIdFromHttpReqeust(request));
		int pageIndex = 1;
		int perPage = 20;
		if (map.containsKey("index")) {
			pageIndex = Integer.parseInt(map.get("index").toString());
		}

		if (map.containsKey("perPage")) {
			perPage = Integer.parseInt(map.get("perPage").toString());
		}
		PageHelper.startPage(pageIndex, perPage);

		if (!StringUtil.isEmpty((String) map.get("begin")) && !StringUtil.isEmpty((String) map.get("end"))
				&& map.get("begin").equals(map.get("end")) && Long.parseLong((String) map.get("end")) != 0l) {// 同一天
			Long begin = Long.parseLong((String) map.get("begin"));
			Long end = begin + 24 * 60 * 60 * 1000;
			map.put("begin", begin);
			map.put("end", end);
		}

		PageHelper.startPage(pageIndex, perPage);

		List<UserGroup> userGroupList = userGroupService.getUserGroupAll(map);

		PageInfo<UserGroup> page = new PageInfo<UserGroup>(userGroupList);

		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
	}

	@RequestMapping(value = "/userGroup/edit", method = RequestMethod.GET)
	public AjaxResult edit(
			HttpServletRequest request,
			@RequestParam Long id,
			@PathVariable("tenantId") String tenantId) {
		UserGroup bean = userGroupService.getUserGroupById(id);
		bean.setTenantName(userTenantService.selectById(bean.getTenantId()).getTenantName());

		// Role collection
		List<Long> roleIdList = userGroupService.getRoleIdList(id);

		// User gather
		List<Long> userIdList = userGroupService.getUserIdList(id);

		List<String> userNameList = userGroupService.getUserNameList(id);

		List<String> roleNameList = userGroupService.getRoleNameList(id);

		if (CollUtil.isNotEmpty(roleIdList)) {
			bean.setRoleIdList(roleIdList);
		}

		if (CollUtil.isNotEmpty(userIdList)) {
			bean.setUserIdList(userIdList);

		}
		bean.setUserNameList(userNameList);
		bean.setRoleNameList(roleNameList);
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, bean);
	}

	@RequestMapping(value = "/userGroup", method = RequestMethod.POST)
	public AjaxResult save(
			@RequestBody @Valid UserGroup bean,
			@PathVariable("tenantId") String tenantId,
			HttpServletRequest request,
			HttpServletResponse response) {
		if (StringUtil.isEmpty(bean.getGroupName())) {
			throw new QslException(MessageKey.GROUP_NAME_NULL);
		}
		userGroupService.createUserGroup(bean);
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);

	}

	@RequestMapping(value = "/userGroup", method = RequestMethod.PUT)
	public AjaxResult update(
			@RequestBody @Valid UserGroup bean,
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("tenantId") String tenantId) {
		try {

			if (StringUtil.isEmpty(bean.getGroupName())) {
				throw new QslException(MessageKey.GROUP_NAME_NULL);
			}
			// Group Name Keep Unique

			userGroupService.updateUserGroup(bean);
			UserGroup grp = userGroupService.getUserGroupById(bean.getId());
			UserOrganization organization = orgService.selectByOrgCode(grp.getOrgCode());
			if (organization == null)
				return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);

			organization.setOrganizationName(bean.getGroupName());

			orgService.updateById(organization);
			return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	@RequestMapping(value = "/userGroup", method = RequestMethod.DELETE)
	public AjaxResult del(String id, HttpServletRequest request, HttpServletResponse response) {
		String[] split = id.split(",");
		int success = userGroupService.updateDeleteTimeById(split);
		if (success > 0) {
			return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
		} else {
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}

	}

	// The drop -down box of the group
	@GetMapping("/userGroup/box")
	public AjaxResult selectGroup(@PathVariable("tenantId") String tenantId) {
		List<UserGroup> groupList = userGroupService.selectGroup(tenantId);
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, groupList);
	}

	// View authorization Detail
	@RequestMapping(value = "/userGroup", method = RequestMethod.GET)
	public AjaxResult getPermissionByGroup(HttpServletRequest request,
			Long groupId,
			@PathVariable("tenantId") String tenantId) {

		// according to Tenant id Query App
		List<Map<String, Object>> listMaps = userGroupService.getAppByTenantId(tenantId);
		List<Map<String, Object>> maps = new ArrayList<>();

		for (Map<String, Object> map : listMaps) {
			Long applicationId = Long.parseLong(map.get("id").toString());
			List<TreeModel> treeList = userGroupService.getPermissionByUserAndRoleAndGroup(groupId, applicationId);
			if (CollUtil.isNotEmpty(treeList)) {
				map.put("tree", treeList);
				maps.add(map);
			}
		}
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, maps);
	}

}
