package com.matariky.commonservice.accesslog.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.accesslog.bean.CommonAccessLog;
import com.matariky.commonservice.accesslog.service.CommonAccessLogService;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.constant.PermissionConstant;
import com.matariky.constant.RedisKey;
import com.matariky.exception.QslException;
import com.matariky.redis.RedisUtils;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.DateUtil;
import com.matariky.utils.TokenUtils;

@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
public class CommonAccessLogController {
	@Value("${message.locale}")
	String locale;
	@Autowired
	private CommonAccessLogService commonAccessLogService;
	@Autowired
	private CommonDictService commonDictService;
	@Autowired
	private CommonDictTypeService commonDictTypeService;

	@Autowired
	private RedisUtils redisUtils;

	@RequestMapping("/commonAccessLog/list")
	public AjaxResult list(HttpServletRequest request,
			@RequestParam Map<String, Object> params,
			@PathVariable("tenantId") String tenantId,
			@RequestHeader("Authorization") String jwt) {

		int pageIndex = Integer.parseInt(params.get("index").toString());
		int perPage = Integer.parseInt(params.get("perPage").toString());

		PageHelper.startPage(pageIndex, perPage);
		params.put("tenantId", tenantId);
		List<CommonAccessLog> commonDictList = commonAccessLogService.getCommonAccessLogDynamicCondition(params);

		PageInfo<CommonAccessLog> page = new PageInfo<CommonAccessLog>(commonDictList);
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
	}

	@GetMapping("/commonAccessLog/daclist")
	public AjaxResult daclist(HttpServletRequest request,
			@RequestParam Map<String, Object> params,
			@PathVariable("tenantId") String tenantId,
			@RequestHeader("Authorization") String jwt) {
		String hid = request.getHeader("id");
		String resourceIdDictKey = "dp" + hid.substring(0, hid.length() - 1);
		if (!StringUtils.isEmpty((String) params.get("startTime"))
				&& !StringUtils.isEmpty((String) params.get("endTime"))
				&& params.get("startTime").equals(params.get("endTime"))
				&& Long.parseLong((String) params.get("endTime")) != 0l) {// 同一天
			Long startTime = Long.parseLong((String) params.get("startTime"));
			Long endTime = startTime + 24 * 60 * 60 * 1000;
			params.put("startTime", startTime);
			params.put("endTime", endTime);
		}
		CommonDictType commonDictType = commonDictTypeService.getDictTypeByKey(
				TokenUtils.extractTenantIdFromHttpReqeust(request), PermissionConstant.DATA_ACCESS_PERMISSION);
		CommonDict dict = commonDictService.getCommonDictByIdTenantIdAndDictType(resourceIdDictKey, tenantId,
				commonDictType.getId());
		if (dict == null) {
			params.put("strategyCode", PermissionConstant.COMMON_DATA_ACCESS_PRIVATE);
		} else {
			params.put("strategyCode", dict.getDictValue());
		}

		int pageIndex = Integer.parseInt(params.get("index").toString());
		int perPage = Integer.parseInt(params.get("perPage").toString());

		params.put("tenantId", tenantId);
		params.put("pageStart", (pageIndex - 1) * perPage);
		params.put("pageSize", perPage);
		List<CommonAccessLog> commonDictList = commonAccessLogService.getCommonAccessLogDAC(params, request);
		String locale = TokenUtils.extractLocaleFromToken(jwt);
		for (CommonAccessLog cl : commonDictList) {
			String operationName = (String) redisUtils.hGet(RedisKey.MENU_NAMES + locale, cl.getOperationName() + "");
			if (StringUtils.isNotEmpty(operationName))
				cl.setOperationNameString(operationName);
		}
		Long count = commonAccessLogService.getCommonAccessLogDACCount(params, request);
		PageInfo<CommonAccessLog> page = new PageInfo<CommonAccessLog>(commonDictList);
		page.setTotal(count);
		page.setPageSize(perPage);
		page.setPageNum(pageIndex);
		page.setPages(Integer.parseInt(String.valueOf(count % perPage == 0 ? count % perPage : count % perPage + 1)));
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
	}

	@RequestMapping(value = "/commonAccessLog/{commonAccessLogId}", method = RequestMethod.GET)
	public AjaxResult getOne(@PathVariable("commonAccessLogId") String id, HttpServletRequest request,
			HttpServletResponse response) {
		CommonAccessLog log = commonAccessLogService.getCommonAccessLogById(id);
		Date date = new Date(log.getAccessTime());
		String s = DateUtil.parseDate2String(date, "yyyy-MM-dd HH:mm:ss");
		log.setAccessTimeStr(s);
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, log);
	}

	@RequestMapping("/commonAccessLog/list/excel/{fileName}")
	public void listExcel(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> params,
			@PathVariable("tenantId") String tenantId,
			@PathVariable("fileName") String fileName,
			@RequestHeader("Authorization") String jwt) {

		String[] titlesCN = { "序号", " User  Name ", "所属 Tenant ", "访问IP Address ", "Client ", " Request  Interface",
				" Operation Time " };
		String[] titlesEN = { "Sequence Number", "User Name", "Tenant", "IP Address", "Client", "Http API",
				"Operation Time" };
		String[] titles = null;
		String locale = TokenUtils.extractLocaleForRequest(request);
		if ("CN".equals(locale)) {
			titles = titlesCN;
		}
		if ("EN".equals(locale)) {
			titles = titlesEN;
		}

		List<CommonAccessLog> commonAccessLog = commonAccessLogService.getCommonAccessLogDynamicCondition(params);

		try {
			exportExcel(titles, response.getOutputStream(), commonAccessLog, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/commonAccessLog/list/excel/{fileName}/{ids}", method = RequestMethod.GET)
	public void listExcelByIds(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("ids") String ids,
			@PathVariable("tenantId") String tenantId,
			@RequestHeader("Authorization") String jwt,
			@PathVariable("fileName") String fileName) {

		String[] titles = { "序号", " User  Name ", "所属 Tenant ", " Login IP Address ", "Client ", " Request  Interface",
				" Operation Time " };
		List<CommonAccessLog> commonAccessLogList = commonAccessLogService.getCommonAccessLogByIds(ids.split(","));

		try {
			exportExcel(titles, response.getOutputStream(), commonAccessLogList, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static String exportExcel(String[] titles, ServletOutputStream out, List<CommonAccessLog> userList,
			String fileName) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		try {
			HSSFSheet hssfSheet = workbook.createSheet("sheet1");
			HSSFRow row = hssfSheet.createRow(0);
			HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
			hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			HSSFCell hssfCell = null;
			for (int i = 0; i < titles.length; i++) {
				hssfCell = row.createCell(i);
				hssfCell.setCellValue(titles[i]);
				hssfCell.setCellStyle(hssfCellStyle);
			}

			for (int i = 0; i < userList.size(); i++) {
				row = hssfSheet.createRow(i + 1);
				CommonAccessLog log = (CommonAccessLog) userList.get(i);
				String id = null;
				if (log.getId() != null) {
					id = log.getId();
				}
				row.createCell(0).setCellValue(id);
				String realName = "";
				if (log.getRealName() != null) {
					realName = (String) log.getRealName();
				}
				row.createCell(1).setCellValue(realName);
				String tenantName = "";
				if (log.getTenantName() != null) {
					tenantName = (String) log.getTenantName();
				}
				row.createCell(2).setCellValue(tenantName);
				if (log.getClientIp() != null) {
					row.createCell(3).setCellValue((String) log.getClientIp());
				}

				String client = (String) log.getClient();
				row.createCell(4).setCellValue(client);
				String requestUrl = log.getRequestUrl();
				row.createCell(5).setCellValue(requestUrl);

				row.createCell(6).setCellValue(log.getAccessTime());

			}

			try {
				workbook.write(out);
				workbook.close();
				out.flush();
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Export  Information Failed！");

		} finally {
			if (workbook != null)
				workbook.close();
			if (out != null) {
				out.close();
			}
		}
		return null;
	}

	@RequestMapping(value = "/commonAccessLog", method = RequestMethod.POST)
	public AjaxResult save(CommonAccessLog bean, HttpServletRequest request, HttpServletResponse response) {
		try {
			int success = commonAccessLogService.createCommonAccessLog(bean);
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

	@RequestMapping(value = "/commonAccessLog", method = RequestMethod.PUT)
	public AjaxResult update(CommonAccessLog bean, HttpServletRequest request, HttpServletResponse response) {
		try {
			int success = commonAccessLogService.updateCommonAccessLog(bean);
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

	@RequestMapping(value = "/commonAccessLog", method = RequestMethod.DELETE)
	public AjaxResult del(String id, HttpServletRequest request, HttpServletResponse response) {
		try {
			int success = commonAccessLogService.delCommonAccessLogById(Integer.parseInt(id));
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

}
