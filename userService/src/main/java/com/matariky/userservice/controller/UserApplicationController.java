package com.matariky.userservice.controller;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.matariky.userservice.bean.Permission;
import com.matariky.userservice.mapper.PermissionMapper;
import com.matariky.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.loginlog.bean.CommonLoginLog;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.commonservice.upload.utils.Result;
import com.matariky.exception.QslException;
import com.matariky.userservice.bean.TreeModel;
import com.matariky.userservice.bean.UserApplication;
import com.matariky.userservice.bean.UserTenant;
import com.matariky.userservice.dto.UserApplicationDTO;
import com.matariky.userservice.service.UserApplicationService;
import com.matariky.userservice.service.UserService;
import com.matariky.userservice.service.UserTenantService;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.BeanUtils;
import com.matariky.utils.DateUtil;
import com.matariky.utils.TreeUtils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import io.jsonwebtoken.lang.Collections;

/**
 * Controller
 * 
 * @author AUTOMATION
 */
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
public class UserApplicationController {

	@Autowired
	private UserApplicationService userApplicationService;

	@Autowired
	UserTenantService tenantService;

	@Autowired
	UserService userService;

	@Value("${message.locale}")
	String locale;

	@Autowired
	CommonDictService commonDictService;

	@Autowired
	CommonDictTypeService commonDictTypeService;
	@Autowired
	private PermissionMapper permissionMapper;

	// 查看 App Detail
	@RequestMapping(value = "/userApplication/{applicationId}", method = RequestMethod.GET)
	public Object getOne(@PathVariable("applicationId") Long id, CommonLoginLog bean, HttpServletRequest request,
			HttpServletResponse response) {
		UserApplication application = userApplicationService.selectById(id);

		if (application == null) {
			throw new QslException(MessageKey.APPLICATION_NOT_EXIST);
		}
		UserApplicationDTO uaDTO = new UserApplicationDTO();
		BeanUtils.copyProperties(application, uaDTO);

		List<Long> permissions = userApplicationService.getPermissionIdList(id);
		uaDTO.setPermissions(permissions);
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, uaDTO);
	}

	@RequestMapping(value = "/userApplication/list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request,
			@RequestParam Map<String, Object> map,
			@PathVariable("tenantId") String tenantId,
			@RequestHeader("Authorization") String jwt) {

		int pageIndex = 1;
		int perPage = 20;

		if (map.containsKey("index")) {
			pageIndex = Integer.parseInt(map.get("index").toString());
		}

		if (map.containsKey("perPage")) {
			perPage = Integer.parseInt(map.get("perPage").toString());
		}
		PageHelper.startPage(pageIndex, perPage);

		map.put("tenantId", TokenUtils.extractTenantIdFromHttpReqeust(request));

		List<UserApplication> userApplicationList = userApplicationService.getUserApplicationAll(map);

		PageInfo<UserApplication> page = new PageInfo<UserApplication>(userApplicationList);
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
	}

	@RequestMapping(value = "/userApplication/list/user/{userId}", method = RequestMethod.GET)
	public Object listByUserId(HttpServletRequest request,
			@RequestParam Map<String, Object> map,
			@PathVariable("tenantId") String tenantId,
			@RequestHeader("Authorization") String jwt,
			@PathVariable("userId") String userId) {

		int pageIndex = 1;
		int perPage = 20;

		if (map.containsKey("index")) {
			pageIndex = Integer.parseInt(map.get("index").toString());
		}

		if (map.containsKey("perPage")) {
			perPage = Integer.parseInt(map.get("perPage").toString());
		}
		PageHelper.startPage(pageIndex, perPage);

		map.put("tenantId", tenantId);// Retrieve父 Tenant ID
		map.put("userId", userId);
		List<UserApplication> userApplicationList = userApplicationService.getUserApplicationAllWithUserId(map);

		PageInfo<UserApplication> page = new PageInfo<UserApplication>(userApplicationList);

		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
	}

	@RequestMapping(value = "/userApplication/edit", method = RequestMethod.GET)
	public Object edit(HttpServletRequest request, Long id) {

		UserApplication bean = userApplicationService.getUserApplicationById(id);

		// Integer applicationType = bean.getApplicationType();

		List<CommonDict> dictsByDictTypeKey = commonDictTypeService.getDictsByDictTypeKey(bean.getTenantId(),
				"APPLICATION_TYPE");

		// Retrieve Cloud Storage ConfigurationInformation
		// Map<String, String> maps =
		// dictsByDictTypeKey.stream().collect(Collectors.toMap(CommonDict::getDictKey,
		// CommonDict::getDictValue, (key1, key2) -> key2));
		//
		// bean.setApplicationTypeName(maps.get(applicationType+"").toString());

		for (CommonDict cd : dictsByDictTypeKey) {
			if (cd.getDictKey().equals(bean.getApplicationType().toString()))
				bean.setApplicationTypeName(cd.getDictValue());
		}

		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, bean);

		// return new Result<UserApplication>().ok(bean);
	}

	@RequestMapping(value = "/userApplication/{applicationId}", method = RequestMethod.POST)
	public Object bindResourcesToTenantAndApplication(@RequestHeader("Authorization") String token,
			@PathVariable("tenantId") String tenantId, @PathVariable("applicationId") Long applicationId,
			@RequestBody String permissions) {
		try {
			Long appTenantId = userApplicationService.selectTenantApplicationJointId(tenantId, applicationId);
			if (appTenantId == 0) {
				throw new QslException(MessageKey.TENANT_APPLICATION_UNRELATED);
			} else {
				UserApplication application = userApplicationService.selectById(applicationId);
				if (application == null) {
					throw new QslException(MessageKey.APPLICATION_NOT_EXIST);
				}

				UserTenant tenant = tenantService.selectByTenantCode(tenantId);
				if (tenant == null) {
					throw new QslException(MessageKey.TENANT_NOT_EXIST);
				}
				for (String permissionId : permissions.split(",")) {
					userApplicationService.createTenantApplicationPermissionBound(appTenantId,
							Long.parseLong(permissionId));
				}
				return new AjaxResult(HttpStatus.OK.value(),
						commonDictService.getServiceMessage(locale + "_SERVICE_CONSTANT_MESSAGE",
								"RESOURCE_BOUND_TO_TENANT_APPLICATION", true, tenantId).getString("message"));
				// return new
				// ResponseEntity<JSONObject>(commonDictService.getServiceMessage(locale+"_SERVICE_CONSTANT_MESSAGE","RESOURCE_BOUND_TO_TENANT_APPLICATION",true,tenantId),
				// HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	@RequestMapping(value = "/userApplication", method = RequestMethod.POST)
	public Object save(@RequestBody UserApplicationDTO bean,
			@RequestHeader("Authorization") String jwt,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("deleteTime", 0);
		params.put("applicationName", bean.getApplicationName());
		params.put("tenantId", bean.getTenantId());
		List<UserApplication> existingApps = userApplicationService.getUserApplicationByTenant(params);
		if (!Collections.isEmpty(existingApps)) {
			throw new QslException(MessageKey.APPLICATION_EXISTS);

		}
		bean.setCreateTime(Calendar.getInstance().getTimeInMillis());
		bean.setUpdateTime(Calendar.getInstance().getTimeInMillis());
		bean.setCreatedBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
		bean.setUpdatedBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
		userApplicationService.save(bean);
		Long[] appIds = new Long[1];
		appIds[0] = bean.getId();
		userApplicationService.deletePermissionByApplicationIds(appIds);
		if (!Collections.isEmpty(bean.getPermissions())) {
			for (Long perm : bean.getPermissions()) {
				userApplicationService.createTenantApplicationPermissionBound(bean.getId(), perm);
			}
		}
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
		// return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}

	@RequestMapping(value = "/userApplication", method = RequestMethod.PUT)
	public AjaxResult update(@RequestBody UserApplicationDTO bean, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 非管理员不能分配 Code Rule 菜单权限 Tenant 1 ,不受限制 ,因为他是最大的管理员
			String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
			if (!tenantId.equals("1")) {
				String userId = TokenUtils.extractUserIdFromHttpReqeust(request);
				Integer adminTenantCount = userService.isAdmin(Long.valueOf(userId));
				if (adminTenantCount == 0) {
					List<Permission> permissionsList = permissionMapper.selectList(
							Wrappers.lambdaQuery(Permission.class).in(Permission::getId, bean.getPermissions()));
					List<String> permissionsNameList = permissionsList.stream().map(Permission::getPermissionName)
							.collect(Collectors.toList());
					if (permissionsNameList.contains(" Code  Rule  Configuration ")) {
						throw new QslException(MessageKey.CODINGRULES_NOT_ALLOCATION);
					}
				}
			}
			bean.setUpdateTime(DateUtil.getCurrentDateAndTime().getTime());
			List<Long> permissions = bean.getPermissions();
			Long[] appIds = new Long[1];
			appIds[0] = bean.getId();

			if (!Collections.isEmpty(permissions)) {
				userApplicationService.deletePermissionByApplicationIds(appIds);
				for (Long perm : permissions) {
					userApplicationService.createTenantApplicationPermissionBound(bean.getId(), perm);
				}
			}
			int success = userApplicationService.updateUserApplication(bean);
			if (success > 0) {
				return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
				// return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			} else {
				throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new QslException(e.getMessage());
		}
	}

	@RequestMapping(value = "/userApplication", method = RequestMethod.DELETE)
	public Object del(String id, HttpServletRequest request, HttpServletResponse response) {
		try {
			String[] split;
			if (id.contains(",")) {
				split = id.split(",");
			} else {
				split = new String[] { id };
			}

			int success = userApplicationService.updateDeleteTimeById(split);
			if (success > 0) {
				return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
				// return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			} else {
				throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	// App 中的查看资源
	@RequestMapping(value = "/userApplication/TreeModel", method = RequestMethod.GET)
	public Object getPermissionTreeModel(Long applicationId, @PathVariable("tenantId") String tenantId) {
		// 根据 App id Query
		List<TreeModel> list = userApplicationService.getTreeListByApplicationId(applicationId);
		List<TreeModel> build;
		if (CollUtil.isNotEmpty(list)) {
			build = TreeUtils.build(list);
		} else {
			build = new ArrayList<>();
		}
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, build);
		// return new Result<List<TreeModel>>().ok(build);
	}

	// App 的下拉框
	@GetMapping("/userApplication/box")
	public Object selectApp(@PathVariable("tenantId") String tenantId) {
		List<UserApplication> roleList = userApplicationService.selectApplication(tenantId);
		// Result<Object> result=new Result<>();
		UserTenant selectBytenantCode = tenantService.selectBytenantCode(tenantId);
		// result.setMessage(selectBytenantCode.getTenantName());// Tenant Name
		// result.setCode(0);
		// result.setData(roleList);
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, roleList);
		// return result;
	}

	// 所有 App
	@GetMapping("/userApplication/appList")
	public Object getAppList(@RequestParam("tenantId") String tenantId) {
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, userApplicationService.getAppList(tenantId));
	}

	@GetMapping("/userApplication/appListAll")
	public Object getAppListAll(@PathVariable("tenantId") String tenantId) {
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, userApplicationService.selectApplication(""));
		// return userApplicationService.selectApplication("");
	}

	// Tenant 管理中的 Tenant App New
	@RequestMapping(value = "/userApplication/userTenant", method = RequestMethod.PUT)
	public Object addApplicationByTenant(@RequestBody UserApplication bean, HttpServletRequest request,
			HttpServletResponse response) {

		userApplicationService.addApplicationByTenant(bean);
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
		// return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}

	// Tenant 管理中的 Tenant App 查看 Detail
	@RequestMapping(value = "/userApplication/userTenant/edit", method = RequestMethod.GET)
	public Object getAppById(Long applicationId) {
		UserApplication bean = userApplicationService.getUserApplicationById(applicationId);

		// 查看中间表存在的 Name
		String name = userApplicationService.getNameById(bean.getTenantId(), applicationId);

		if (StringUtils.isNotEmpty(name)) {
			bean.setApplicationName(name);
		}

		// App 资源
		/*
		 * List<TreeModel>
		 * list=userApplicationService.getTreeListByApplicationId(applicationId);
		 * if(CollUtil.isNotEmpty(list)) {
		 * bean.setPermissionTreeModels(TreeUtils.build(list)); }
		 */

		// App 资源此 Tenant 下的
		List<TreeModel> listR = userApplicationService.getRApplicationTenantPermission(applicationId);
		if (CollUtil.isNotEmpty(listR)) {
			bean.setPermissionTreeModels(TreeUtils.build(listR));
		}

		// App 资源id
		List<Long> permissionIdList = userApplicationService.getPermissionIdList(applicationId);
		if (CollUtil.isNotEmpty(permissionIdList)) {
			bean.setPermissionIdList(permissionIdList);
		}
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, bean);
		// return new Result<UserApplication>().ok(bean);
	}

	// Tenant 管理的 Tenant App 分页
	// SELECT a.* FROM user_application a,user_r_application_tenant t WHERE
	// a.id=t.application_id AND a.tenant_id=1
	@RequestMapping(value = "/userApplication/userTenant/list", method = RequestMethod.GET)
	public Object tenantList(HttpServletRequest request,
			@RequestParam Map<String, Object> map,
			@PathVariable("tenantId") String tenantId, @RequestParam("id") String bizTenantId
	/*
	 * @ApiParam(value = "JWT Token", required =
	 * true) @RequestHeader("Authorization") String jwt
	 */) {

		int pageIndex = 1;
		int perPage = 20;

		if (map.containsKey("index")) {
			pageIndex = Integer.parseInt(map.get("index").toString());
		}

		if (map.containsKey("perPage")) {
			perPage = Integer.parseInt(map.get("perPage").toString());
		}

		PageHelper.startPage(pageIndex, perPage);

		map.put("tenantId", bizTenantId);

		List<UserApplication> userApplicationList = userApplicationService.getUserApplicationByTenant(map);

		PageInfo<UserApplication> page = new PageInfo<UserApplication>(userApplicationList);
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
		// return page;
	}

	// Tenant 管理的 Tenant App 的Delete

	@RequestMapping(value = "/userApplication/userTenant/del", method = RequestMethod.DELETE)
	public Object delUserTenant(String id, @PathVariable("tenantId") String tenantId) {

		try {
			String[] split;
			if (id.contains(",")) {
				split = id.split(",");
			} else {
				split = new String[] { id };
			}
			userApplicationService.delUserTenant(split);
			return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	// App 中 Image 的 Source
	@RequestMapping(value = "/userApplication/img", method = RequestMethod.GET)
	public Object getImg(@PathVariable("tenantId") String tenantId) {
		List<Map<String, Object>> lsitMaps = new ArrayList<>();
		if (ClassUtils.getDefaultClassLoader().getResource("static/image") == null) {
			return lsitMaps;
		}
		// Retrieve项目classes/static的 Address
		String staticPath = ClassUtils.getDefaultClassLoader().getResource("static/image").getPath();

		File file = new File(staticPath);
		if (file.exists()) {
			File[] listFiles = file.listFiles();

			if (ArrayUtil.isNotEmpty(listFiles)) {
				for (File file1 : listFiles) {
					Map<String, Object> map = new HashMap<>();
					map.put("name", getFileNameNoEx(file1.getName()));
					map.put("url", "/static/image/" + file1.getName());
					lsitMaps.add(map);
				}
			}
		}
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, lsitMaps);
		// return lsitMaps;
	}

	// Retrieve文件扩展 Name
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	// Retrieve不带扩展 Name 的 File Name
	public static String getFileNameNoEx(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}

}
