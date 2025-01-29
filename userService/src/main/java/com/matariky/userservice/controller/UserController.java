package com.matariky.userservice.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.matariky.annotation.RequirePermission;
import com.matariky.annotation.VerifyTenantId;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.loginlog.service.ICommonLoginLogService;
import com.matariky.commonservice.minio.utils.MinioUtil;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;
import com.matariky.orderservice.bean.OrderInfo;
import com.matariky.orderservice.mapper.OrderInfoMapper;
import com.matariky.orderservice.service.OrderInfoService;
import com.matariky.redis.RedisUtils;
import com.matariky.userservice.bean.Permission;
import com.matariky.userservice.bean.TreeModel;
import com.matariky.userservice.bean.User;
import com.matariky.userservice.bean.UserApplication;
import com.matariky.userservice.bean.UserOrganization;
import com.matariky.userservice.bean.UserRole;
import com.matariky.userservice.bean.UserTenant;
import com.matariky.userservice.mapper.UserMapper;
import com.matariky.userservice.service.CaptchaService;
import com.matariky.userservice.service.PermissionService;
import com.matariky.userservice.service.TokenService;
import com.matariky.userservice.service.UserApplicationService;
import com.matariky.userservice.service.UserGroupService;
import com.matariky.userservice.service.UserOrganizationService;
import com.matariky.userservice.service.UserRoleService;
import com.matariky.userservice.service.UserService;
import com.matariky.userservice.service.UserTenantService;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.DateUtil;
import com.matariky.utils.EncryptionUtils;
import com.matariky.utils.ExcelTemplateUtil;
import com.matariky.utils.FileUtil;
import com.matariky.utils.OrgCodeUtil;
import com.matariky.utils.TokenUtils;

import cn.hutool.core.collection.CollUtil;
import io.jsonwebtoken.lang.Collections;

@RestController
@RequestMapping("api/v1/tenant/{tenantId}")
@Component
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	TokenService tokenService;
	@Autowired
	UserTenantService tenantService;

	@Autowired
	PermissionService permissionService;

	@Autowired
	UserGroupService groupService;

	@Autowired
	UserRoleService roleService;

	@Autowired
	UserApplicationService applicationService;

	@Autowired
	private RedisUtils redisUtils;

	@Autowired
	ICommonLoginLogService commonLoginLogService;

	@Autowired
	CaptchaService captchaService;

	@Autowired
	UserOrganizationService userOrganizationService;

	@Value("${admin.pazzword}")
	private String pazzword;

	@Value("${message.locale}")
	String locale;

	@Value("${storage.dict.key}")
	String storageDictType;
	@Autowired
	CommonDictService commonDictService;
	@Autowired
	OrderInfoService orderInfoService;
	@Autowired
	private MinioUtil minioUtil;
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	@Autowired
	private UserMapper userMapper;

	@PostMapping("/password/reset/user/{userId}")
	@RequirePermission()
	@VerifyTenantId
	public AjaxResult resetPassword(@PathVariable("userId") Long userId,
			@PathVariable("tenantId") String tenantId) {
		User user = userService.selectById(userId);
		if (user == null) {
			throw new QslException(MessageKey.USER_NOT_EXIST);
		}
		user.setPazzword(EncryptionUtils.getHash3(pazzword, "SHA"));
		userService.updateById(user);

		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
	}

	// designation User Execl Export
	@RequestMapping(value = "/user/list/excel/{fileName}", method = RequestMethod.POST)
	public @ResponseBody String listExcelForSpecificUsers(HttpServletResponse response,
			@RequestBody List<Map<String, Object>> userList,
			@PathVariable("tenantId") String tenantId,
			@PathVariable("fileName") String fileName,
			@RequestHeader("Authorization") String jwt)

	{
		response.setContentType("application/x-msdownload");
		response.setCharacterEncoding("UTF-8");
		try {
			ServletOutputStream out = response.getOutputStream();
			try {
				response.setHeader("Content-Disposition",
						"attachment;fileName=" + URLEncoder.encode(fileName + ".xls", "UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}

			String[] titles = { " Tenant   Code ", " Name ", "账号", "性别", "所属角色", "机构 Code ", "所属分组", "手机号", " Status ",
					"Creater", "Import Error " };
			return exportExcel(titles, out, userList, fileName);

		} catch (Exception e) {
			e.printStackTrace();
			return " Export  Information Failed";
		}

	}

	@RequestMapping(value = "/tempFile/{fileName}", method = RequestMethod.GET)
	public void downloadFile(@PathVariable("fileName") String fileName, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String fileStoreConfigJson = commonDictService.getDictValueByTenantIdAndKey("1", storageDictType);
		JSONObject jo = JSONObject.parseObject(fileStoreConfigJson);
		String fileFolder = jo.getString("localPath");
		String fullPath = fileFolder + File.separator + "tmp" + File.separator + fileName + ".xlsx";
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		File file = null;
		try {
			file = new File(fullPath);
			response.setContentType("application/x-excel");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			response.setHeader("Content-Length", String.valueOf(file.length()));
			in = new BufferedInputStream(new FileInputStream(file));
			out = new BufferedOutputStream(response.getOutputStream());
			byte[] data = new byte[1024];
			int len = 0;
			while (-1 != (len = in.read(data, 0, data.length))) {
				out.write(data, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
			if (file != null) {
				// Delete temporary File
				file.delete();
			}
		}

	}

	// User dynamic Query Execl Export
	@RequestMapping(value = "/user/list/excel/{fileName}", method = RequestMethod.GET)
	public void listExcel(HttpServletResponse response,
			@RequestParam Map<String, Object> map,
			@PathVariable("tenantId") String tenantId,
			@PathVariable("fileName") String fileName) {

		map.put("tenantId", tenantId);

		if (map.containsKey("organizationCode")) {
			String organizationCode = map.get("organizationCode").toString();
			if (StringUtils.isNotEmpty(organizationCode)) {
				Long[] ids = userOrganizationService.getChildrenOrganization(organizationCode, tenantId);
				// Choose all departments below the organization through recursively Query
				map.put("organizations", ids);
			}
		}

		String beginst = null;
		String endst = null;

		if (map.containsKey("begin")) {
			beginst = map.get("begin").toString();
		}

		if (map.containsKey("end")) {
			endst = map.get("end").toString();
		}

		// Turn time intolongType
		try {
			if (StringUtil.isNotEmpty(beginst)) {
				long begin = DateUtil.string2Datetime(beginst).getTime();
				map.put("begin", begin);
			}
			if (StringUtil.isNotEmpty(endst)) {
				long end = DateUtil.string2Datetime(map.get("end").toString()).getTime();
				map.put("end", end);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Map<String, Object>> userList = userService.getUserAllWithRoleAndGroup(map);
		try {
			ServletOutputStream out = response.getOutputStream();
			try {
				response.setHeader("Content-Disposition",
						"attachment;fileName=" + URLEncoder.encode(fileName + ".xls", "UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}

			String[] titles = { " Tenant   Code ", " Name ", "账号", "性别", "所属角色", "机构 Code ", "所属分组", "手机号", " Status ",
					"Creater" };
			exportExcel(titles, out, userList, fileName);
		} catch (Exception e) {
		}

	}

	@RequestMapping(value = "/user/list/override", method = RequestMethod.PUT)
	public AjaxResult overrideUsers(HttpServletResponse response,
			@RequestBody List<User> userList,
			@PathVariable("tenantId") String tenantId) {
		for (User user : userList) {
			User fetched = userService.findByUsername(user.getLoginName());
			user.setId(fetched.getId());
			userService.forcedUpdate(user);
		}
		return new AjaxResult(response.getStatus(), AjaxResult.SUCCESS, null);
	}

	// designation User ID Query Execl Export
	@RequestMapping(value = "/selecteduser/list/excel/{fileName}", method = RequestMethod.POST, produces = "application/octet-stream")
	public String getExcel(HttpServletResponse response,
			@RequestBody Map<String, String> map,
			@PathVariable("tenantId") String tenantId,
			@PathVariable("fileName") String fileName,
			@RequestHeader("Authorization") String jwt)

	{
		List<Map<String, Object>> userList = userService.getUserAllWithRoleAndGroupByIds(map);
		try {
			ServletOutputStream out = response.getOutputStream();
			try {
				response.setHeader("Content-Disposition",
						"attachment;fileName=" + URLEncoder.encode(fileName + ".xls", "UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}

			String[] titles = { " Tenant   Code ", " Name ", "账号", "性别", "所属角色", "机构 Code ", "所属分组", "手机号", " Status ",
					"Creater" };
			return exportExcel(titles, out, userList, fileName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private String exportExcel(String[] titles, ServletOutputStream out, List<Map<String, Object>> userList,
			String fileName) throws Exception {
		try {
			// The first step, Create One Workbook, corresponding one Excel File
			HSSFWorkbook workbook = new HSSFWorkbook();

			// The second step is the ADD One Sheet in WebBook, corresponding to the excel
			// filesheet
			HSSFSheet hssfSheet = workbook.createSheet("sheet1");

			// The third step is the 0th line of ADD head head in Sheet. Note that the old
			// VersionPoi has a limit on the number of rows of Excelshort

			HSSFRow row = hssfSheet.createRow(0);
			// The fourth step, Create cells, and configuration value watch head
			// Configuration
			HSSFCellStyle hssfCellStyle = workbook.createCellStyle();

			// Centered style
			hssfCellStyle.setAlignment(HorizontalAlignment.CENTER);

			HSSFCell hssfCell = null;
			for (int i = 0; i < titles.length; i++) {
				hssfCell = row.createCell(i);// Column index from0 Start
				hssfCell.setCellValue(titles[i]);// List Name 1
				hssfCell.setCellStyle(hssfCellStyle);// Liney Display
			}

			for (int i = 0; i < userList.size(); i++) {
				row = hssfSheet.createRow(i + 1);
				Map<String, Object> person = (Map<String, Object>) userList.get(i);

				// 第Six steps, create cells, and configuration value
				String tenantId = null;
				if (person.get("tenantId") != null) {
					tenantId = (String) person.get("tenantId");
				}
				row.createCell(0).setCellValue(tenantId);
				String name = "";
				try {
					if (person.get("realName") != null) {
						name = (String) person.get("realName");
					}
					row.createCell(1).setCellValue(name);
					String loginName = "";
					if (person.get("loginName") != null) {
						loginName = (String) person.get("loginName");
						row.createCell(2).setCellValue(loginName);
					}
				} catch (Exception e) {

				}
				try {
					if (person.get("gender") instanceof Integer) {
						row.createCell(3).setCellValue((Integer) person.get("gender"));
					} else if (person.get("gender") instanceof String) {
						row.createCell(3).setCellValue(Integer.parseInt(person.get("gender").toString()));
					} else {
						Boolean gender = (Boolean) person.get("gender");
						row.createCell(3).setCellValue(gender ? 1 : 0);
					}
				} catch (Exception e) {

				}
				try {
					String roles = (String) person.get("roleIdList");
					row.createCell(4).setCellValue(roles);
				} catch (Exception e) {

				}
				try {
					String orgCode = (String) person.get("selfOrganizationCode");
					row.createCell(5).setCellValue(orgCode);
				} catch (Exception e) {

				}
				try {
					String groupName = (String) person.get("groupIdList");
					row.createCell(6).setCellValue(groupName);
				} catch (Exception e) {

				}
				try {
					String cellphone = (String) person.get("cellPhone");
					row.createCell(7).setCellValue(cellphone);
				} catch (Exception e) {

				}
				try {
					if (person.get("isActive") instanceof Boolean) {
						Boolean isActive = (Boolean) person.get("isActive");
						row.createCell(8).setCellValue(isActive ? "正常" : "禁用");
					} else if (person.get("isActive") instanceof String) {
						row.createCell(8).setCellValue(person.get("isActive").toString());
					}
				} catch (Exception e) {

				}
				if (person.get("createdBy") instanceof Long) {
					Long creatorId = (Long) person.get("createdBy");
					row.createCell(9).setCellValue(userService.selectById(creatorId).getRealName());
				} else if (person.get("createdBy") instanceof String) {
					row.createCell(9).setCellValue(
							userService.selectById(Long.parseLong(person.get("createdBy").toString())).getRealName());
				} else {
					String creatorId = (String) person.get("createdBy");
					row.createCell(9).setCellValue(userService.selectById(Long.parseLong(creatorId)).getRealName());
				}
				if (person.get("error") != null) {
					row.createCell(10).setCellValue(person.get("error").toString());
				}
			}

			// Step 7, output file to Client Browser

			try {
				String fileStoreConfigJson = commonDictService
						.getDictValueByTenantIdAndKey(TokenUtils.extractTenantIdFromHttpReqeust(
								((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes()))
										.getRequest()),
								storageDictType);
				JSONObject jo = JSONObject.parseObject(fileStoreConfigJson);
				String fileFolder = jo.getString("localPath");
				File file = new File(fileFolder + File.separator + "tmp" + File.separator + fileName + ".xlsx");
				FileOutputStream outputStream = new FileOutputStream(file);
				workbook.write(outputStream);
				outputStream.flush();
				outputStream.close();
				workbook.close();
				return fileName + ".xlsx";

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Export  Information Failed！");

		}
		return null;
	}

	// Pagination
	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public AjaxResult list(HttpServletRequest request, @RequestParam Map<String, Object> map,
			@PathVariable("tenantId") String tenantId) {

		map.put("tenantId", TokenUtils.extractTenantIdFromHttpReqeust(request));

		if (map.containsKey("organizationCode")) {
			String organizationCode = map.get("organizationCode").toString();
			map.put("organizations", organizationCode);
		}
		int pageIndex = 1;
		int perPage = 20;
		if (map.containsKey("index")) {
			pageIndex = Integer.parseInt(map.get("index").toString());
		}

		if (map.containsKey("perPage")) {
			perPage = Integer.parseInt(map.get("perPage").toString());
		}
		PageHelper.startPage(pageIndex, perPage);

		String beginst = null;
		String endst = null;

		if (map.containsKey("begin")) {
			beginst = map.get("begin").toString();
		}

		if (map.containsKey("end")) {
			endst = map.get("end").toString();
		}

		// Turn time intolongType
		if (StringUtil.isNotEmpty(beginst)) {
			long begin = DateUtil.string2Dateyyyymmdd(beginst).getTime();
			map.put("begin", begin);
		}
		if (StringUtil.isNotEmpty(endst)) {
			long end = DateUtil.string2Dateyyyymmdd(endst).getTime();
			map.put("end", end);
		}

		List<User> userList = userService.getUserAll(map);

		// Organizational Name traversed to assignment
		for (User user : userList) {
			String depCode = user.getDepartmentOrganizationCode();
			String[] codes;

			if (StringUtils.isNotEmpty(depCode)) {
				if (depCode.contains(",")) {
					codes = depCode.split(",");
				} else {
					codes = new String[] { depCode };
				}

				// According to the organization Code Query organization name (multiple
				// stitching)
				String name = userOrganizationService.getOrgNamesByCode(codes);
				if (StringUtils.isNotEmpty(name)) {
					user.setOrganizationName(name);
				}
			}

		}

		PageInfo<User> page = new PageInfo<User>(userList);

		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
	}

	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
	public AjaxResult edit(HttpServletRequest request, Long id,
			@PathVariable("tenantId") String tenantId) {

		User bean = userService.getUserById(id);

		// There is an organization in itself
		// Role collection
		List<Long> roleIdList = userService.getRoleIdList(id);
		// Packet collection
		List<Long> groupIdList = userService.getGroupIdList(id);

		List<String> applicationIdList = applicationService.getApplicationIdList(id);

		List<String> roleNameList = userService.getRoleNameList(id);

		List<String> groupNameList = userService.getGourpNameList(id);

		List<String> applicationNameList = userService.getApplicationNameList(id);

		bean.setApplicationNameLis(applicationNameList);

		bean.setGroupNameList(groupNameList);

		bean.setRoleNameList(roleNameList);

		if (CollUtil.isNotEmpty(groupIdList)) {
			bean.setGroupIdList(groupIdList);
		}
		if (CollUtil.isNotEmpty(roleIdList)) {
			bean.setRoleIdList(roleIdList);
		}
		if (CollUtil.isNotEmpty(applicationIdList)) {
			bean.setApplicationIdList(applicationIdList);
		}
		if (StringUtils.isNotEmpty(bean.getDepartmentOrganizationCode())) {

			// Multi -organization CODE is separated by comma
			String code = bean.getDepartmentOrganizationCode();

			String[] codes;

			if (code.contains(",")) {
				codes = code.split(",");
			} else {
				codes = new String[] { code };
			}
			bean.setOrganizationCodesString(codes[codes.length - 1]);
			List<String[]> stringList = new ArrayList<>();

			for (int i = 0; i < codes.length; i++) {

				String organizationCode = codes[i];

				List<UserOrganization> parentOrgListByCode = userOrganizationService
						.getParentOrgListByCode(organizationCode, tenantId);

				java.util.Collections.sort(parentOrgListByCode);

				if (CollUtil.isNotEmpty(parentOrgListByCode)) {
					String[] ids = new String[parentOrgListByCode.size()];

					for (int j = 0; j < parentOrgListByCode.size(); j++) {
						ids[j] = parentOrgListByCode.get(j).getOrganizationCode();
					}
					stringList.add(ids);
				}
			}
			bean.setTenantName(tenantService.selectById(bean.getTenantId()).getTenantName());
			UserOrganization userOrganization = userOrganizationService
					.selectByOrgCode(bean.getOrganizationCodesString());
			if (userOrganization != null) {
				bean.setOrganizationName(userOrganization.getOrganizationName());
			}
			bean.setOrganizationCodeList(stringList);
		}
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, bean);
	}

	// View personal authorization permission
	@RequestMapping(value = "/user/permission", method = RequestMethod.GET)
	public AjaxResult getPermissionByUser(HttpServletRequest request, Long id,
			@PathVariable("tenantId") String tenantId) {
		String tenantIdData = "";
		String[] tenantStr = tenantId.split("_");
		if (tenantStr != null && tenantStr.length > 1) {
			tenantIdData = tenantStr[1];
		}
		// Find personal belonging App user_id tenant_id
		List<Map<String, Object>> listMaps = userService.getApplicationByUser(id, tenantIdData);

		for (Map<String, Object> map : listMaps) {
			Long applicationId = Long.parseLong(map.get("id").toString());
			List<TreeModel> treeList = userService.getPermissionByUser(id, tenantId, applicationId);
			map.put("tree", treeList);
		}
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, listMaps);
		// return listMaps;
	}

	// Check the final authorization
	@RequestMapping(value = "/user/permission/all", method = RequestMethod.GET)
	public AjaxResult getPermissionByAll(HttpServletRequest request,
			Long id,
			@PathVariable("tenantId") String tenantId) {

		// Find personal belonging App user_id tenant_id
		String tenantIdData = "";
		String[] tenantStr = tenantId.split("_");
		if (tenantStr != null && tenantStr.length > 1) {
			tenantIdData = tenantStr[1];
		}
		List<Map<String, Object>> listMaps = userService.getApplicationByUser(id, tenantIdData);

		for (Map<String, Object> map : listMaps) {
			Long applicationId = Long.parseLong(map.get("id").toString());
			List<TreeModel> treeList = userService.getPermissionByUserAndRoleAndGroup(id, tenantId, applicationId);
			map.put("tree", treeList);
		}

		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, listMaps);
	}

	// Save Operation
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public AjaxResult save(@RequestBody User bean,
			@PathVariable("tenantId") String tenantId,
			HttpServletRequest request, HttpServletResponse response) {
		// Account number: Need to do unique certification, the User account in the
		// entire system must Unique
		// If the input account is repeated, System needs to display the recommended
		// account. If Lafee repeats, System needs to display the recommendation of
		// lafee2020, lafee_2020 similar。
		Map<String, Object> columnMap = new HashMap<>();
		// 表Directly passed over Tenant id
		String tenantId2 = bean.getTenantId();

		columnMap.put("delete_time", 0);
		columnMap.put("login_name", bean.getLoginName());

		List<User> selectByMap = userService.selectByMap(columnMap);

		if (selectByMap != null && selectByMap.size() > 0) {
			throw new QslException(MessageKey.USER_EXISTED);
		}
		bean.setTenantId(tenantId2);
		// loginName The account cannot be empty
		if (StringUtil.isEmpty(bean.getLoginName())) {
			throw new QslException(MessageKey.USER_NOT_LOGINNAME_EXIST);
		}

		// realName Name Can't be empty
		if (StringUtil.isEmpty(bean.getRealName())) {
			throw new QslException(MessageKey.USER_NOT_REALNAME_EXIST);
		}

		// cellPhone The mobile phone number cannot be empty
		if (StringUtil.isEmpty(bean.getCellPhone())) {
			throw new QslException(MessageKey.USER_NOT_CELLPHONE_EXIST);
		}

		// 联系 Tenant Can't be empty tenantId
		if (StringUtil.isEmpty(bean.getTenantId())) {
			throw new QslException(MessageKey.USER_NOT_TENANTID_EXIST);
		}

		// Gender cannot be empty
		if (bean.getGender() == null) {
			throw new QslException(MessageKey.USER_NOT_GENDER_EXIST);
		}
		Map<String, Object> columnMap2 = new HashMap<>();

		columnMap2.put("tenant_id", tenantId2);
		columnMap2.put("delete_time", 0);

		List<User> selectByMap2 = userService.selectByMap(columnMap2);
		if (CollUtil.isNotEmpty(selectByMap2)) {
			bean.setOrderCode(selectByMap2.get(0).getOrderCode());
		}
		if (StringUtils.isNotEmpty(bean.getOrderCode())) {
			if (!"1".equals(tenantId)) {
				OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderCode(bean.getOrderCode());
				if (orderInfo == null) {
					throw new QslException(MessageKey.ORDER_DOES_NOT_EXIST);
				}
				if (orderInfo.getAccountNumber() < selectByMap2.size()) {
					throw new QslException(MessageKey.TENANT_USE_NUMBER_RANGE_OUT);
				}
			}
		}

		String orgCodeString = bean.getOrganizationCodesString();

		if (!StringUtils.isBlank(orgCodeString)) {
			bean.setDepartmentOrganizationCode(orgCodeString);
		} else {
			throw new QslException(MessageKey.USER_NOT_ORG_EXIST);
		}

		// According to Code Queryid
		try {
			userService.save(bean);
			if (!Collections.isEmpty(bean.getApplicationIdList())) {
				bean.setApplicationId(Long.parseLong(bean.getApplicationIdList().get(0)));
				userService.updateById(bean);
				for (String applicationId : bean.getApplicationIdList()) {
					UserTenant tnnt = tenantService.selectByTenantCode(tenantId);
					applicationService.insertUserApplicationRelation(bean.getId(), Long.parseLong(applicationId),
							tnnt.getId());
				}

			}
			return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	// Update Operation
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public AjaxResult update(@RequestBody User bean, @PathVariable("tenantId") String tenantId,
			HttpServletRequest request,
			HttpServletResponse response) {

		// loginNameThe account cannot be empty
		if (StringUtil.isEmpty(bean.getLoginName())) {
			throw new QslException(MessageKey.USER_NOT_LOGINNAME_EXIST);
		}

		// realName Name Can't be empty
		if (StringUtil.isEmpty(bean.getRealName())) {
			throw new QslException(MessageKey.USER_NOT_REALNAME_EXIST);
		}

		// cellPhone The mobile phone number cannot be empty
		if (StringUtil.isEmpty(bean.getCellPhone())) {
			throw new QslException(MessageKey.USER_NOT_CELLPHONE_EXIST);
		}

		// Tenant cannot be emptytenantId
		if (StringUtil.isEmpty(bean.getTenantId())) {
			throw new QslException(MessageKey.USER_NOT_TENANTID_EXIST);
		}

		// Gender cannot be empty
		if (bean.getGender() == null) {
			throw new QslException(MessageKey.USER_NOT_GENDER_EXIST);
		}

		if (StringUtils.isNotEmpty(bean.getOrganizationCodesString())) {
			bean.setDepartmentOrganizationCode(bean.getOrganizationCodesString());
			bean.setSelfOrganizationCode(
					OrgCodeUtil.generateSelfOrganizationCode(bean.getOrganizationCodesString(), bean.getId()));
		} else {
			throw new QslException(MessageKey.USER_NOT_ORG_EXIST);
		}

		try {
			userService.update(bean);
			// Query Personal institution
			UserOrganization org = userOrganizationService.getUserSelfOrganization(bean.getId());
			if (org == null) {// Not exist ,Create
				UserOrganization newOrg = new UserOrganization();
				newOrg.setOrganizationName(bean.getRealName());
				newOrg.setOrganizationCode(
						OrgCodeUtil.generateSelfOrganizationCode(bean.getOrganizationCodesString(), bean.getId()));
				userOrganizationService.createUserOrganization(newOrg);
				org = userOrganizationService.getUserSelfOrganization(bean.getId());
			}
			org.setOrganizationCode(
					OrgCodeUtil.generateSelfOrganizationCode(bean.getOrganizationCodesString(), bean.getId()));
			org.setParentId(userOrganizationService.selectByOrgCode(bean.getOrganizationCodesString()).getId());
			org.setTenantId(bean.getTenantId());
			org.setDeleteTime(0l);
			org.setOrgType(4);
			org.setOrganizationName(bean.getRealName());
			userOrganizationService.updateById(org);
			Long[] userIds = new Long[1];
			userIds[0] = bean.getId();
			userService.deleteGroupByUserIds(userIds);
			userService.deleteRoleByUserIds(userIds);
			applicationService.deleteApplicationByUserId(bean.getId());

			if (bean.getGroupIdList() != null)
				for (Long groupId : bean.getGroupIdList()) {
					groupService.insertGroupUserRelation(bean.getId(), groupId);
				}

			if (bean.getRoleIdList() != null)
				for (Long roleId : bean.getRoleIdList()) {
					userService.insertUserRoleRelation(bean.getId(), roleId);
				}

			if (bean.getApplicationIdList() != null)
				for (String applicationId : bean.getApplicationIdList()) {
					applicationService.insertUserApplicationRelation(bean.getId(), Long.parseLong(applicationId),
							tenantService.selectByTenantCode(tenantId).getId());
				}
			return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}

	}

	@RequestMapping(value = "/userInfo", method = RequestMethod.PUT)
	public AjaxResult updateUserInfo(@RequestBody User bean, HttpServletRequest request,
			HttpServletResponse response) {

		// Effect Data
		Long count = userMapper.selectCount(Wrappers.lambdaQuery(User.class)
				.eq(User::getLoginName, bean.getLoginName())
				.eq(User::getDeleteTime, 0)
				.ne(User::getId, bean.getId()));
		if (count > 0) {
			throw new QslException(MessageKey.USER_EXISTED);
		}

		try {
			userService.updateById(bean);
			return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}

	}

	@RequestMapping(value = "/userDetail/{userId}", method = RequestMethod.GET)
	public AjaxResult getUserDetail(@PathVariable("tenantId") String tenantId, @PathVariable("userId") Long userId,
			HttpServletRequest request, HttpServletResponse response) {

		// Effect Data

		try {
			User user = userService.selectById(userId);
			UserOrganization organization = userOrganizationService
					.getOrganizationByCode(user.getDepartmentOrganizationCode(), tenantId);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("userDetail", user);
			resultMap.put("organization", organization);
			return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, resultMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}

	}

	// Delete Operation
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public AjaxResult del(String id, HttpServletRequest request, HttpServletResponse response) {

		String[] split;
		if (id.contains(",")) {
			split = id.split(",");
		} else {
			split = new String[] { id };
		}

		try {
			userService.updateDeleteTimeById(split);
			return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	// Download Import
	@RequestMapping(value = "/user/templateDownload", method = RequestMethod.GET)
	public void templateDownload(HttpServletResponse response) {
		new ExcelTemplateUtil().downloadExcel(response, "user.xlsx", " User Import Import .xlsx");
	}

	// User Drop -down box
	@GetMapping("/user/box")
	public AjaxResult selectUserAllByTenantId(@PathVariable("tenantId") String tenantId) {
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("tenant_id", tenantId);
		columnMap.put("delete_time", 0);
		List<User> userList = userService.selectByMap(columnMap);
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, userList);
	}

	// Switch theme
	@RequestMapping(value = "/user/{userId}/theme/{theme}", method = RequestMethod.PUT)
	public AjaxResult switchTheme(@PathVariable("tenantId") String tenantId,
			@PathVariable("userId") Long userId,
			@PathVariable("theme") String theme,
			HttpServletRequest request) {
		if (userId == null) {
			throw new QslException(MessageKey.EMPTY_USER_ID);
		}
		if (tenantId == null) {
			throw new QslException(MessageKey.EMPTY_TENANT_ID);
		}
		User user = userService.selectById(userId);
		if (user == null) {
			throw new QslException(MessageKey.USER_NOT_EXIST);
		}
		UserTenant tenant = tenantService.selectBytenantCode(tenantId);
		if (tenant == null) {
			throw new QslException(MessageKey.TENANT_NOT_EXIST);
		}
		user.setTheme(theme);
		Boolean success = userService.updateById(user);
		if (success)
			return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
		else {
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	// Import schedule
	@RequestMapping(value = "/user/bulk/nonce/{nonce}", method = RequestMethod.POST)
	public Object uploadPercentage(@RequestParam(value = "uploadFile", required = false) MultipartFile file,
			@PathVariable("nonce") String nonce) {
		if (redisUtils.get(nonce) != null && 100 == (Integer) redisUtils.get(nonce)) {
			Object complete = redisUtils.get(nonce);
			redisUtils.delete(nonce);
			return complete;
		}

		return redisUtils.get(nonce);
	}

	@GetMapping("/admin")
	public AjaxResult getAdmin(@RequestHeader("Authorization") String jwt,
			@PathVariable("tenantCode") String tenantCode) {

		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, userService.getAdminByTenantCode(tenantCode));

	}

	// Tenant Configuration administrator
	@PostMapping("/admin")
	@Transactional(rollbackFor = Exception.class)
	public AjaxResult addAdmin(@RequestHeader("Authorization") String jwt,
			@PathVariable("tenantId") String tenantId,
			@RequestBody User user, HttpServletRequest request) {
		if (StringUtil.isEmpty(user.getCellPhone())) {
			throw new QslException(MessageKey.EMPTY_PHONE_NUMBER);
		}
		if (StringUtil.isEmpty(user.getRealName())) {
			throw new QslException(MessageKey.EMPTY_REAL_NAME);
		}
		if (user.getGender() == null) {
			throw new QslException(MessageKey.EMPTY_GENDER);
		}
		User admin = userService.findByUsername(user.getLoginName());
		if (admin != null) {
			throw new QslException(MessageKey.USER_EXISTED);
		}

		String realTenantId = null;
		if (tenantId.contains("_")) {
			realTenantId = tenantId.split("_")[1];
		}
		// Tenant If you don't buy an order, you can't Create administrator
		Long c = orderInfoMapper.selectCount(Wrappers.lambdaQuery(OrderInfo.class)
				.eq(OrderInfo::getOrderTenantId, realTenantId)
				.eq(OrderInfo::getDeleteTime, 0)
				.in(OrderInfo::getOrderStatus, 1, 3));
		if (c == 0) {
			throw new QslException(MessageKey.TENANT_HAVA_NOT_ORDER);
		}

		// Create New administrator
		// UserTenant tenant=tenantService.selectById(tenantId);
		UserTenant tenant = tenantService.selectBytenantCode(tenantId);
		user.setTenantId(tenant.getTenantCode());

		userService.newUserDefaults(user);
		UserOrganization topOrganization = userOrganizationService.selectTopOrganization(tenantId);
		if (topOrganization == null) {
			throw new QslException(MessageKey.ORIGINAL_ORGANIZATION_NOT_EXIST);
		}

		Boolean success = false;
		user.setDepartmentOrganizationCode(topOrganization.getOrganizationCode());
		success = userService.insert(user);
		user.setSelfOrganizationCode(
				OrgCodeUtil.generateSelfOrganizationCode(topOrganization.getOrganizationCode(), user.getId()));
		userService.updateById(user);
		// insert Tenant User Intermediate table
		userService.deleteUserTenantRelation(user.getId(), tenant.getTenantCode(), 1);
		userService.insertUserTenantRelation(user.getId(), tenant.getTenantCode(), topOrganization.getId(),
				topOrganization.getOrganizationCode(), true, tenant.getId(), user.getSelfOrganizationCode());

		// Create new Tenant Administrator
		UserRole newAdminRole = new UserRole();
		newAdminRole.setRoleName(tenant.getTenantName() + " Administrator");
		newAdminRole.setCreatedBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request)));
		newAdminRole.setCreateTime(System.currentTimeMillis());
		newAdminRole.setDeleteTime(0L);
		newAdminRole.setTenantId(tenant.getTenantCode());
		roleService.createUserRole(newAdminRole);

		Set<Permission> permissionList = permissionService.selectPermissionByTenantId(tenant.getId().toString());
		// Create new Tenantmanage App
		UserApplication application = new UserApplication();
		application.setCreateTime(System.currentTimeMillis());
		application.setCreatedBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request)));
		application.setDeleteTime(0l);
		application.setIsActive(true);
		application.setApplicationName(tenant.getTenantName() + " Management Application");
		application.setApplicationType(1);
		application.setTenantId(tenant.getTenantCode());
		applicationService.saveOrUpdateTenant(application, tenant);

		// ConfigurationNew administrator's default app is managed by Tenant App
		user.setApplicationId(application.getId());
		userService.updateById(user);
		// Create New administrator and new management APP association
		applicationService.insertUserApplicationRelation(user.getId(), application.getId(), tenant.getId());
		// Create Tenant Affrobot with APP

		Map<Long, Long> dupPermTreeMap = new HashMap<Long, Long>();
		for (Permission newperm : permissionList) {
			newperm.setApplicationId(application.getId());
			newperm.setTenantId(tenant.getTenantCode());
			Long origId = newperm.getId();
			newperm.setId(null);
			newperm.setOrigId(origId);
			newperm.setApplicationId(application.getId());
			permissionService.createPermission(newperm);
			Long newId = newperm.getId();
			dupPermTreeMap.put(origId, newId);

		}
		Set<Long> permIdSet = new HashSet<Long>();
		permIdSet.addAll(dupPermTreeMap.values());
		for (Long permId : permIdSet) {
			permissionService.createResourceAllocationToRole(newAdminRole.getId(), permId);
			applicationService.createTenantApplicationPermissionBound(application.getId(), permId);
		}
		for (Permission newperm : permissionList) {
			if (dupPermTreeMap.keySet().contains(newperm.getParentId())) {
				newperm.setParentId(dupPermTreeMap.get(newperm.getParentId()));
				permissionService.updateById(newperm);
			}
		}
		// ParentidUpDate of the resource as a new resourceID

		// Allocate the role of the new Tenant administrator to the new Tenant
		// administrator
		userService.insertUserRoleRelation(user.getId(), newAdminRole.getId());
		// Add the administrator to the organization tree
		UserOrganization admOrg = new UserOrganization();
		admOrg.setTenantId(tenantId);
		admOrg.setParentId(topOrganization.getId());
		admOrg.setOrganizationName(user.getRealName());
		admOrg.setOrganizationCode(user.getSelfOrganizationCode());
		admOrg.setOrgType(4);// personal
		userOrganizationService.insert(admOrg);

		if (success)
			return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
		else {
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	// Import
	@RequestMapping(value = "/user/bulk", method = RequestMethod.POST)
	public Map<String, Object> userBulkUpload(
			@RequestParam("file") MultipartFile multipartfile,
			@RequestParam("nonce") String nonce,
			@RequestHeader("Authorization") String jwt)
			throws IOException {
		String tenantId = TokenUtils.extractTenantIdFromToken(jwt);
		if (StringUtils.isEmpty(nonce)) {
			throw new QslException(MessageKey.NONCE_NOT_EXIST);
		}
		File file = FileUtil.convertMultipartFileToFile(multipartfile);
		int successCount = 0, failureCount = 0, repeatCount = 0, processedCount = 0;
		List<User> existingUserList = new ArrayList<User>();
		List<Map<String, Object>> failedUserList = new ArrayList<Map<String, Object>>();
		InputStream is = new FileInputStream(file);
		// Get the excel workbook Object
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		// Get Excel worksheet Object
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
		BigDecimal totalRows = new BigDecimal(xssfSheet.getLastRowNum());
		// Get the designated line of the excel worksheet Object
		for (int i = 1; i <= xssfSheet.getLastRowNum(); i++) {
			XSSFRow xssfRow = xssfSheet.getRow(i);
			// Get the cells specified by the excel work table
			User user = null;
			try {
				user = excelRowToUser(xssfRow, tenantId);
			} catch (Exception e) {
				failureCount++;
				processedCount++;
				BigDecimal successPercentage = new BigDecimal(processedCount).divide(totalRows, 2,
						RoundingMode.HALF_UP);
				redisUtils.set(nonce, successPercentage.multiply(new BigDecimal(100)).intValue());
				failedUserList.add(rowToMap(xssfRow, e.getMessage()));
				e.printStackTrace();
				continue;
			}

			User existingUser = userService.findByUsername(user.getLoginName());
			if (existingUser != null) {
				existingUserList.add(user);
				repeatCount++;
				processedCount++;
				BigDecimal successPercentage = new BigDecimal(processedCount).divide(totalRows, 2,
						RoundingMode.HALF_UP);
				redisUtils.set(nonce, successPercentage.multiply(new BigDecimal(100)).intValue());

				continue;
			}
			userService.newUserDefaults(user);
			boolean insertedStatus = userService.insert(user);
			if (insertedStatus) {
				successCount++;
				processedCount++;
				BigDecimal successPercentage = new BigDecimal(processedCount).divide(totalRows, 2,
						RoundingMode.HALF_UP);
				redisUtils.set(nonce, successPercentage.multiply(new BigDecimal(100)).intValue());
				User insertedUser = null;
				if (!CollectionUtils.isEmpty(user.getRoleIdList())) {
					insertedUser = userService.findByUsername(user.getLoginName());
					userService.saveOrUpdateRole(user.getId(), user.getRoleIdList());
				}
				if (!CollectionUtils.isEmpty(user.getGroupIdList())) {
					if (insertedUser != null)
						insertedUser = userService.findByUsername(user.getLoginName());
					userService.saveOrUpdateGroup(user.getId(), user.getGroupIdList());
				}
			} else {
				failureCount++;
				processedCount++;
				failedUserList.add(rowToMap(xssfRow, commonDictService.getServiceErrorText(
						locale + "_SERVICE_CONSTANT_MESSAGE", "ERROR_INSERTING_DATABASE", false, tenantId)));
				BigDecimal successPercentage = new BigDecimal(processedCount).divide(totalRows, 2,
						RoundingMode.HALF_UP);
				redisUtils.set(nonce, successPercentage.multiply(new BigDecimal(100)).intValue());
			}

		}
		xssfWorkbook.close();
		is.close();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("successCount", successCount);
		resultMap.put("failureCount", failureCount);
		resultMap.put("repeatCount", repeatCount);
		resultMap.put("existingUserList", existingUserList);
		resultMap.put("failedUserList", failedUserList);
		// Get the cell style
		return resultMap;

	}

	private Map<String, Object> rowToMap(XSSFRow xssfRow, String errorMessage) {
		Map<String, Object> user = new HashMap<String, Object>();
		user.put("tenantId", getCellValue(xssfRow.getCell(0)).toString());
		user.put("realName", getCellValue(xssfRow.getCell(1)).toString());
		user.put("loginName", getCellValue(xssfRow.getCell(2)).toString());
		user.put("gender", getCellValue(xssfRow.getCell(3)).toString());
		user.put("roleIdList", getCellValue(xssfRow.getCell(4)).toString());
		user.put("selfOrganizationCode", getCellValue(xssfRow.getCell(5)).toString());
		user.put("groupIdList", getCellValue(xssfRow.getCell(6)).toString());
		user.put("cellPhone", getCellValue(xssfRow.getCell(7)).toString());
		user.put("isActive", getCellValue(xssfRow.getCell(8)).toString());
		user.put("createdBy", Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(
				((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest())));
		user.put("error", errorMessage);
		return user;
	}

	private Object getCellValue(XSSFCell cell) {
		if (cell == null) {
			return "";
		}
		switch (cell.getCellTypeEnum()) {
			case NUMERIC: // Numeric
				// If it is a time format content
				return cell.getNumericCellValue();

			case STRING: // String
				return cell.getStringCellValue();
			case BOOLEAN: // Boolean
				return cell.getBooleanCellValue() + "";
			case FORMULA: // Formula
				return cell.getCellFormula() + "";
			case BLANK: // Blank
				return "";
			case ERROR: // Error
				return "Invalid character";
			default:
				return "Unknown type";
		}

	}

	private User excelRowToUser(XSSFRow xssfRow, String tenantId) throws Exception {
		User user = new User();
		try {
			if (StringUtil.isEmpty(xssfRow.getCell(0).getRawValue()))
				throw new Exception();
			user.setTenantId(xssfRow.getCell(0).getRawValue());
		} catch (Exception e) {
			throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
					"INVALID_TENANT_ID", false, tenantId));
		}
		try {
			user.setRealName(xssfRow.getCell(1).getStringCellValue());
		} catch (Exception e) {
			throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
					"INVALID_REAL_NAME", false, tenantId));
		}
		try {
			user.setLoginName(xssfRow.getCell(2).getStringCellValue());
		} catch (Exception e) {
			throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
					"INVALID_LOGIN_NAME", false, tenantId));
		}
		try {
			user.setGender(Integer
					.parseInt((Double.valueOf(xssfRow.getCell(3).getNumericCellValue()).toString().substring(0, 1))));
		} catch (Exception e) {
			throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
					"INVALID_GENDER", false, tenantId));
		}
		try {
			user.setRoleIdList(stringToLongArrayRole(xssfRow.getCell(4).getStringCellValue(),
					TokenUtils.extractTenantIdFromHttpReqeust(
							((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes()))
									.getRequest())));
		} catch (Exception e) {
			throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
					"INVALID_ROLE_LIST", false, tenantId));
		}
		try {
			user.setSelfOrganizationCode(xssfRow.getCell(5).getStringCellValue());
		}

		catch (Exception e) {
			throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
					"INVALID_ORGANIZATION_CODE", false, tenantId));
		}
		try {
			user.setGroupIdList(stringToLongArrayGroup(xssfRow.getCell(6).getStringCellValue(),
					TokenUtils.extractTenantIdFromHttpReqeust(
							((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes()))
									.getRequest())));
		} catch (Exception e) {
			throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
					"INVALID_GROUP_LIST", false, tenantId));
		}
		try {
			user.setCellPhone(xssfRow.getCell(7).getRawValue());
		} catch (Exception e) {
			throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
					"INVALID_CELL_PHONE", false, tenantId));
		}
		try {
			user.setIsActive("1".equals(xssfRow.getCell(8).getRawValue()) ? true : false);
		}

		catch (Exception e) {
			throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
					"INVALID_USER_STATUS", false, tenantId));
		}
		try {
			user.setCreatedBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(
					((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest())));
		} catch (Exception e) {
			throw new Exception(commonDictService.getServiceErrorText(locale + "_SERVICE_CONSTANT_MESSAGE",
					"INVALID_CREATOR", false, tenantId));
		}
		return user;
	}

	private List<Long> stringToLongArrayGroup(String groupNames, String tenantId) {
		return groupService.getGroupIdsByTenantIdAndGroupNames(groupNames, tenantId);
	}

	private List<Long> stringToLongArrayRole(String roleNames, String tenantId) {
		return roleService.getRoleIdsByTenantIdAndRoleNames(roleNames, tenantId);
	}

	// Update Password
	@RequestMapping(value = "/user/newPassword/user/{userId}", method = RequestMethod.POST)
	public AjaxResult password(@RequestBody Map<String, Object> map, @PathVariable("userId") Long userId,
			@PathVariable("tenantId") String tenantId, HttpServletRequest request) {

		String pazzword = EncryptionUtils.getHash3(map.get("password").toString(), "SHA");

		String newPassword = EncryptionUtils.getHash3(map.get("newPassword").toString(), "SHA");

		User user = userService.selectById(userId);

		if (user == null) {
			throw new QslException(MessageKey.USER_NOT_EXIST);
		}
		// The original password is incorrect
		if (!pazzword.equals(user.getPazzword())) {
			throw new QslException(MessageKey.PASSWORD_ERROR);
		}
		userService.updatePassword(userId, newPassword);

		return new AjaxResult(HttpStatus.OK.value(),
				commonDictService
						.getServiceMessage(locale + "_SERVICE_CONSTANT_MESSAGE", "PASSWORD_UPDATE", true, tenantId)
						.getString("message"),
				null);

	}

	// Tenant Drop -down box
	@GetMapping("/admin/{tenantCode}")
	public AjaxResult selectTenant(@PathVariable("tenantId") String tenantId,
			@PathVariable("tenantCode") String tenantCode) {
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, userService.getAdminByTenantCode(tenantCode));
	}

	@RequestMapping(value = "/user/userimg", method = RequestMethod.POST)
	public AjaxResult updateLogo(@RequestParam MultipartFile uploadfile, @RequestParam String bucket,
			@RequestParam(required = false) String objectName, @RequestParam("userId") Long userId) throws Exception {
		minioUtil.createBucket(bucket);
		User user = userService.selectById(userId);

		minioUtil.uploadFile(uploadfile.getInputStream(), bucket,
				userId + "." + uploadfile.getOriginalFilename().split("\\.")[1]);
		user.setUserImg("api/v1/tenant/1/file/downloadFile?bucket=userimg&objectName=" + userId + "."
				+ uploadfile.getOriginalFilename().split("\\.")[1]);
		userService.updateById(user);
		return AjaxResult.success(user.getUserImg());
	}

}
