package com.matariky.commonservice.loginlog.controller;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.loginlog.bean.CommonLoginLog;
import com.matariky.commonservice.loginlog.service.CommonLoginLogService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.DateUtil;

/**
 * Controller
 * 
 * @author AUTOMATION
 */
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
public class CommonLoginLogController {

	@Autowired
	private CommonLoginLogService commonLoginLogService;

	@Autowired
	CommonDictService commonDictService;

	private static NumberFormat nf = NumberFormat.getInstance();
	// 保留小数位4位
	{
		nf.setMaximumFractionDigits(0);
		nf.setGroupingUsed(false);
	}

	@Value("${storage.dict.key}")
	String storageDictType;

	@RequestMapping("/commonLoginLog/list")
	public Object list(HttpServletRequest request,
			@RequestParam Map<String, Object> params,
			@PathVariable("tenantId") String tenantId,
			@RequestHeader("Authorization") String jwt) {

		if (!StringUtils.isEmpty((String) params.get("startTime"))
				&& !StringUtils.isEmpty((String) params.get("endTime"))
				&& params.get("startTime").equals(params.get("endTime"))
				&& Long.parseLong((String) params.get("endTime")) != 0l) {// 同一天
			Long startTime = Long.parseLong((String) params.get("startTime"));
			Long endTime = startTime + 24 * 60 * 60 * 1000;
			params.put("startTime", startTime);
			params.put("endTime", endTime);
		}
		params.put("tenantId", tenantId);
		int pageIndex = Integer.parseInt(params.get("index").toString());
		int perPage = Integer.parseInt(params.get("perPage").toString());

		PageHelper.startPage(pageIndex, perPage);

		List<CommonLoginLog> commonDictList = commonLoginLogService.getCommonLoginLogDynamicCondition(params);

		PageInfo<CommonLoginLog> page = new PageInfo<CommonLoginLog>(commonDictList);
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
	}

	@RequestMapping(value = "/commonLoginLog/list/excel/{fileName}", method = RequestMethod.GET)
	public void listExcel(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> params,
			@PathVariable("tenantId") String tenantId,
			@RequestHeader("Authorization") String jwt,
			@PathVariable("fileName") String fileName) {

		String[] titles = { "序号", " User  Name ", "所属 Tenant ", " Login IP Address ", "Client ", " Operation",
				" Operation Time " };

		List<CommonLoginLog> commonDictList = commonLoginLogService.getCommonLoginLogDynamicCondition(params);

		try {
			exportExcel(titles, response.getOutputStream(), commonDictList, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/commonLoginLog/list/excel/{fileName}/{ids}", method = RequestMethod.GET)
	public void listExcelByIds(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("ids") String ids,
			@PathVariable("tenantId") String tenantId,
			@RequestHeader("Authorization") String jwt,
			@PathVariable("fileName") String fileName) {

		String[] titles = { "序号", " User  Name ", "所属 Tenant ", " Login IP Address ", "Client ", " Operation",
				" Operation Time " };

		List<CommonLoginLog> commonDictList = commonLoginLogService.getCommonLoginLogByIds(ids.split(","));

		try {
			exportExcel(titles, response.getOutputStream(), commonDictList, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/commonLoginLog", method = RequestMethod.POST)
	public Object save(CommonLoginLog bean, HttpServletRequest request, HttpServletResponse response) {
		try {
			int success = commonLoginLogService.createCommonLoginLog(bean);
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

	@RequestMapping(value = "/commonLoginLog/{commonLoginLogId}", method = RequestMethod.GET)
	public Object getOne(@PathVariable("commonLoginLogId") Long id, HttpServletRequest request,
			HttpServletResponse response) {
		return commonLoginLogService.selectById(id);

	}

	@RequestMapping(value = "/commonLoginLog", method = RequestMethod.PUT)
	public Object update(CommonLoginLog bean, HttpServletRequest request, HttpServletResponse response) {
		try {
			int success = commonLoginLogService.updateCommonLoginLog(bean);
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

	@RequestMapping(value = "/commonLoginLog", method = RequestMethod.DELETE)
	public Object del(String id, HttpServletRequest request, HttpServletResponse response) {
		try {
			int success = commonLoginLogService.delCommonLoginLogById(Integer.parseInt(id));
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

	private static String exportExcel(String[] titles, ServletOutputStream out, List<CommonLoginLog> userList,
			String fileName) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {
			XSSFSheet hssfSheet = workbook.createSheet("sheet1");
			hssfSheet.setColumnWidth(0, 32 * 256);
			hssfSheet.setColumnWidth(1, 32 * 256);
			hssfSheet.setColumnWidth(2, 32 * 256);
			hssfSheet.setColumnWidth(3, 32 * 256);
			hssfSheet.setColumnWidth(4, 128 * 256);
			hssfSheet.setColumnWidth(5, 32 * 256);
			hssfSheet.setColumnWidth(6, 32 * 256);

			XSSFRow row = hssfSheet.createRow(0);
			XSSFCellStyle hssfCellStyle = workbook.createCellStyle();

			hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			XSSFCell hssfCell = null;
			for (int i = 0; i < titles.length; i++) {
				hssfCell = row.createCell(i);
				hssfCell.setCellValue(titles[i]);
				hssfCell.setCellStyle(hssfCellStyle);
			}

			for (int i = 0; i < userList.size(); i++) {
				row = hssfSheet.createRow(i + 1);
				CommonLoginLog log = (CommonLoginLog) userList.get(i);
				Long id = null;
				if (log.getId() != null) {
					id = log.getId();
				}
				XSSFCell c0 = row.createCell(0);
				c0.setCellType(CellType.STRING);

				// Wether Keep a thousand points
				c0.setCellValue(nf.format(id));

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
				if (log.getIp() != null) {
					row.createCell(3).setCellValue((String) log.getIp());
				}

				String client = (String) log.getClient();
				row.createCell(4).setCellValue(client);
				String operation = log.getFailMessage();
				if (operation != null)
					try {
						row.createCell(5).setCellValue(JSONObject.parseObject(operation).getString("message"));
					} catch (Exception e) {
						row.createCell(5).setCellValue(operation);
					}
				row.createCell(6).setCellType(CellType.STRING);
				row.createCell(6)
						.setCellValue(DateUtil.parseDate2String(new Date(log.getLoginTime()), "yyyy-MM-dd hh:mm:ss"));

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
			if (workbook != null)
				workbook.close();
			if (out != null) {
				out.close();
			}
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

}