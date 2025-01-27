package com.matariky.userservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.minio.utils.MinioUtil;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.commonservice.upload.utils.Result;
import com.matariky.exception.QslException;
import com.matariky.userservice.bean.UserTenant;
import com.matariky.userservice.service.UserTenantService;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.DateUtil;

import cn.hutool.core.collection.CollUtil;

/**
 * Controller
 * 
 * @author AUTOMATION
 */
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
public class UserTenantController {

	@Autowired
	private UserTenantService userTenantService;

	@Autowired
	private CommonDictService commonDictService;

	@Autowired
	private CommonDictTypeService commonDictTypeService;

	@Autowired
	private MinioUtil minioUtil;

	@Autowired
	private IdentifierGenerator idGenerator;

	@Value("${message.locale}")
	String locale;

	@RequestMapping(value = "/userTenant/list", method = RequestMethod.GET)
	public Object list(
			HttpServletRequest request,
			@RequestParam Map<String, Object> map,
			@PathVariable("tenantId") String tenantId
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

		// if(!StringUtil.isEmpty(tenantId)) {
		// map.put("tenantId", tenantId);
		// }
		PageHelper.startPage(pageIndex, perPage);

		String beginst = null;
		String endst = null;

		if (map.containsKey("begin")) {
			beginst = map.get("begin").toString();
		}

		if (map.containsKey("end")) {
			endst = map.get("end").toString();
		}

		// 把 Time 转成longType
		if (StringUtil.isNotEmpty(beginst)) {
			long begin = DateUtil.string2Dateyyyymmdd(beginst).getTime();
			map.put("begin", begin);
		}
		if (StringUtil.isNotEmpty(endst)) {
			long end = DateUtil.string2Dateyyyymmdd(endst).getTime();
			map.put("end", end);
		}

		List<UserTenant> userTenantList = userTenantService.getUserTenantAll(map);

		PageInfo<UserTenant> page = new PageInfo<UserTenant>(userTenantList);
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
		// return page;
	}

	@RequestMapping(value = "/userTenant/edit", method = RequestMethod.GET)
	public Object edit(
			HttpServletRequest request,
			@RequestParam String id,
			@PathVariable("tenantId") String tenantId) {
		if (id == null) {
			throw new QslException(MessageKey.EMPTY_TENANT_ID);
		}
		UserTenant bean = userTenantService.getUserTenantById(id);

		// 上级 Tenant Name
		if (bean.getParentId() != null) {
			UserTenant userTenant = userTenantService.getUserTenantById(bean.getParentId().toString());
			if (userTenant != null) {
				if (StringUtils.isNotEmpty(userTenant.getTenantName())) {
					bean.setParentName(userTenant.getTenantName());
				}
			}
		}

		// Tenant App 去中间表 Query 一遍
		List<Map<String, Object>> applicationList = userTenantService.selectApplication(bean.getTenantCode());

		// 多个 App
		bean.setApplicationIds(applicationList);

		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, bean);
	}

	@RequestMapping(value = "/userTenant/parentNameAndId", method = RequestMethod.GET)
	public Object getParentNameAndId(@PathVariable("tenantId") String tenantId) {
		UserTenant bean = new UserTenant();

		UserTenant selectBytenantCode = userTenantService.selectBytenantCode(tenantId);

		bean.setParentName(selectBytenantCode.getTenantName());

		String tenantCode = selectBytenantCode.getTenantCode();

		String code = "";
		// Code Automatic Generation 01父 00x子
		int countByParentId = userTenantService.getCountByParentId(selectBytenantCode.getId()) + 1;

		code = tenantCode + "_" + countByParentId;

		bean.setTenantCode(code);

		bean.setParentId(selectBytenantCode.getId());

		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, bean);
	}

	@RequestMapping(value = "/userTenant", method = RequestMethod.POST)
	@Transactional(rollbackFor = Exception.class)
	public Object save(@RequestBody @Valid UserTenant bean, HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("tenantId") String tenantId) {
		try {
			// Tenant Name保持 Unique
			if (StringUtils.isEmpty(tenantId)) {
				throw new QslException(MessageKey.EMPTY_PARENT_TENANT_ID);
			} else {
				bean.setParentCode(tenantId);
			}
			if (bean.getTenantName() == null) {
				throw new QslException(MessageKey.EMPTY_TENANT_NAME);
			}
			bean.setParentCode(tenantId);

			Map<String, Object> columnMap = new HashMap<>();
			columnMap.put("tenant_name", bean.getTenantName());
			columnMap.put("delete_time", 0);
			List<UserTenant> selectByMap = userTenantService.selectByMap(columnMap);
			if (CollUtil.isNotEmpty(selectByMap)) {
				throw new QslException(MessageKey.TENANT_NAME_ERR);
			}
			userTenantService.save(bean);
			// userTenantService.insert(bean);
			Map<String, Object> typeParam = new HashMap<>();
			typeParam.put("tenantId", tenantId);
			List<CommonDictType> dictTypeList = commonDictTypeService.getCommonDictTypeAll(typeParam);
			CommonDictType dacDictType = commonDictTypeService.getCommonDictTypeById("5");
			dictTypeList.add(dacDictType);
			for (CommonDictType dictType : dictTypeList) {
				Long tmpDictTypeId = dictType.getId();
				dictType.setId(null);
				dictType.setTenantId(tenantId + "_" + bean.getId());
				Long newDictTypeId = idGenerator.nextId(dictType).longValue();
				dictType.setId(newDictTypeId);
				commonDictTypeService.insert(dictType);
				Map<String, Object> params = new HashMap<>();
				params.put("tenantId", tenantId);
				params.put("dictTypeId", tmpDictTypeId);

				List<CommonDict> dictList = commonDictService.getCommonDictAll(params);
				for (CommonDict dict : dictList) {

					dict.setTenantId(tenantId + "_" + bean.getId());
					dict.setDictTypeId(newDictTypeId);
					commonDictService.createCommonDict(dict);

				}

			}
			return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
		} catch (Exception e) {
			// throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);;
			throw new QslException(e.getMessage());
		}
	}

	@RequestMapping(value = "/userTenant", method = RequestMethod.PUT)
	public Object update(@RequestBody UserTenant bean, HttpServletRequest request, HttpServletResponse response,
			@PathVariable("tenantId") String tenantId) {
		// if(StringUtils.isEmpty(bean.getParentCode())) {
		// throw new QslException(MessageKey.EMPTY_PARENT_TENANT_ID);
		// }
		if (StringUtils.isEmpty(bean.getTenantName())) {
			throw new QslException(MessageKey.EMPTY_TENANT_NAME);
		}
		try {
			userTenantService.update(bean);
			return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	@RequestMapping(value = "/userTenant/logo", method = RequestMethod.PUT)
	public Object updateLogo(@RequestParam MultipartFile uploadfile, @RequestParam String bucket,
			@RequestParam(required = false) String objectName, @PathVariable("tenantId") String tenantId)
			throws Exception {
		minioUtil.createBucket(bucket);
		UserTenant tenant = userTenantService.selectBytenantCode(tenantId);

		minioUtil.uploadFile(uploadfile.getInputStream(), bucket,
				tenantId + "." + uploadfile.getOriginalFilename().split("\\.")[1]);
		tenant.setDomainName("api/v1/tenant/1/file/downloadFile?bucket=tenantlogos&objectName=" + tenantId + "."
				+ uploadfile.getOriginalFilename().split("\\.")[1]);
		userTenantService.updateById(tenant);
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
	}

	// 切换主题
	@RequestMapping(value = "/userTenant/{theme}", method = RequestMethod.PUT)
	public Object switchThem(@PathVariable("tenantId") String tenantId, @PathVariable("theme") String theme,
			HttpServletRequest request) {
		if (StringUtils.isEmpty(tenantId)) {
			throw new QslException(MessageKey.EMPTY_TENANT_ID);
		}
		UserTenant tenant = userTenantService.selectById(tenantId);
		if (tenant == null) {
			throw new QslException(MessageKey.TENANT_NOT_EXIST);
		}
		tenant.setTheme(theme);
		Boolean success = userTenantService.updateById(tenant);
		if (success)
			return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
		else {
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	@RequestMapping(value = "/userTenant", method = RequestMethod.DELETE)
	public Object del(String id, HttpServletRequest request, HttpServletResponse response) {
		try {
			String[] split = id.split(",");

			int success = userTenantService.updateDeleteTimeById(split);
			if (success > 0) {
				return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
			} else {
				throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	// Tenant 下拉框
	@GetMapping("/subTenants")
	public Object selectTenant(@PathVariable("tenantId") String tenantId, HttpServletRequest request) {
		UserTenant tenant = userTenantService.selectBytenantCode(tenantId);
		if (tenant == null) {
			throw new QslException(MessageKey.TENANT_NOT_EXIST);
		}
		List<UserTenant> tenantList = userTenantService.selectTenant(tenantId);
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, tenantList);
	}

}
