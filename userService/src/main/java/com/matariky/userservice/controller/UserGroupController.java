package com.matariky.userservice.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.matariky.commonservice.upload.utils.Result;
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
*Controller
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

	@RequestMapping(value ="/userGroup/list", method = RequestMethod.GET)
	public Object list(
			HttpServletRequest request,
			 @RequestParam Map<String, Object> map,
			 @PathVariable("tenantId") String tenantId
			/*@ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt*/) {
		map.put("tenantId", TokenUtils.extractTenantIdFromHttpReqeust(request));
		int pageIndex=1;
		int perPage=20;
		if(map.containsKey("index")) {
			pageIndex=Integer.parseInt(map.get("index").toString());
		}

		if(map.containsKey("perPage")) {
			perPage=Integer.parseInt(map.get("perPage").toString());
		}
		PageHelper.startPage(pageIndex, perPage);


		if(!StringUtil.isEmpty((String)map.get("begin"))&&!StringUtil.isEmpty((String)map.get("end"))&&map.get("begin").equals(map.get("end"))&&Long.parseLong((String)map.get("end"))!=0l) {//同一天
			Long begin=Long.parseLong((String)map.get("begin"));
			Long end=begin+24*60*60*1000;
			map.put("begin", begin);
			map.put("end", end);
		}

//		if(map.containsKey("end")) {
//			endst = map.get("end").toString();
//		}
//
//		//把 Time 转成longType 
//		if(StringUtil.isNotEmpty(beginst)) {
//			long begin = DateUtil.string2Dateyyyymmdd(beginst).getTime();
//			map.put("begin", begin);
//		}
//		if(StringUtil.isNotEmpty(endst)) {
//			long end = DateUtil.string2Dateyyyymmdd(endst).getTime();
//			map.put("end", end);
//		}
		PageHelper.startPage(pageIndex, perPage);

		List<UserGroup> userGroupList = userGroupService.getUserGroupAll(map);

		PageInfo<UserGroup> page= new PageInfo<UserGroup>(userGroupList);
		
		return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,page);
//		return page;
	}


	@RequestMapping(value ="/userGroup/edit",method = RequestMethod.GET)
	public Object edit(
			HttpServletRequest request,
			@RequestParam Long id,
			 @PathVariable("tenantId") String tenantId)
	{
		UserGroup bean = userGroupService.getUserGroupById(id);
		bean.setTenantName(userTenantService.selectById(bean.getTenantId()).getTenantName());

		//角色集合
		List<Long> roleIdList = userGroupService.getRoleIdList(id);

		//用户集合
		List<Long> userIdList = userGroupService.getUserIdList(id);


		List<String> userNameList= userGroupService.getUserNameList(id);

		List<String> roleNameList=userGroupService.getRoleNameList(id);

		if(CollUtil.isNotEmpty(roleIdList)) {
			bean.setRoleIdList(roleIdList);
		}

		if(CollUtil.isNotEmpty(userIdList)) {
			bean.setUserIdList(userIdList);

		}
		bean.setUserNameList(userNameList);
		bean.setRoleNameList(roleNameList);
		return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,bean);
	}

	 
	@RequestMapping(value = "/userGroup",method = RequestMethod.POST)
	public Object save(
			@RequestBody @Valid UserGroup bean,
			 @PathVariable("tenantId") String tenantId,
			HttpServletRequest request,
			HttpServletResponse response)
	{
			if(StringUtil.isEmpty(bean.getGroupName())) {
				throw new QslException(MessageKey.GROUP_NAME_NULL);
			}
//			//分组 Name保持唯一
//			Map<String, Object> columnMap=new HashMap<>();
//			columnMap.put("group_name", bean.getGroupName());
//			columnMap.put("tenant_id", tenantId);
//			columnMap.put("delete_time", 0);
//			List<UserGroup> userGroups=userGroupService.selectByMap(columnMap);
//			if(CollUtil.isNotEmpty(userGroups)) {
//				throw new QslException(MessageKey.GROUP_NAME_ERR);
//			}
//			bean.setTenantId(tenantId);
			userGroupService.createUserGroup(bean);
			return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,null);
//			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);

	}

	 
	@RequestMapping(value = "/userGroup",method = RequestMethod.PUT)
	public Object update(
			@RequestBody @Valid UserGroup bean,
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("tenantId") String tenantId)
	{
		try{

			if(StringUtil.isEmpty(bean.getGroupName())) {
				throw new QslException(MessageKey.GROUP_NAME_NULL);
			}
			//分组 Name保持唯一
//			Map<String, Object> columnMap=new HashMap<>();
//			columnMap.put("group_name", bean.getGroupName());
//			columnMap.put("tenant_id", bean.getTenantId());

			userGroupService.updateUserGroup(bean);
			UserGroup grp= userGroupService.getUserGroupById(bean.getId());
			UserOrganization organization= orgService.selectByOrgCode(grp.getOrgCode());
			if(organization==null)
				return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,null);
			
			organization.setOrganizationName(bean.getGroupName());

			orgService.updateById(organization);
			return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,null);
		}catch (Exception e){
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	 
	@RequestMapping(value = "/userGroup",method = RequestMethod.DELETE)
	public Object del(String id,HttpServletRequest request, HttpServletResponse response) {
		String[] split = id.split(",");
			int success = userGroupService.updateDeleteTimeById(split);
			if(success > 0){
				return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,null);
//				return new ResponseEntity<String>("SUCCESS",  HttpStatus.OK);
			}else{
				throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
			}

	}


	//组的下拉框
	@GetMapping("/userGroup/box")
	public Object selectGroup(@PathVariable("tenantId") String tenantId) {
		List<UserGroup> groupList = userGroupService.selectGroup(tenantId);
		return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,groupList);
	}


	//查看授权详情
	@RequestMapping(value = "/userGroup",method = RequestMethod.GET)
	public Object getPermissionByGroup(HttpServletRequest request,
			Long groupId,
			 @PathVariable("tenantId") String tenantId){

		//根据 Tenant id Query 应用
		List<Map<String,Object>> listMaps=userGroupService.getAppByTenantId(tenantId);
		List<Map<String, Object>> maps=new ArrayList<>();

		for (Map<String, Object> map : listMaps) {
			Long applicationId = Long.parseLong(map.get("id").toString());
			List<TreeModel> treeList=userGroupService.getPermissionByUserAndRoleAndGroup(groupId,applicationId);
			if(CollUtil.isNotEmpty(treeList)) {
				map.put("tree", treeList);
				maps.add(map);
			}
		}
		return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,maps);
	}



}
