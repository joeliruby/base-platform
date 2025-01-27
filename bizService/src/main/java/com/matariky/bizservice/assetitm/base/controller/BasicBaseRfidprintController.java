package com.matariky.bizservice.assetitm.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidPrint;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidprintDetail;
import com.matariky.bizservice.assetitm.base.service.BasicBaseRfidprintDetailService;
import com.matariky.bizservice.assetitm.base.service.BasicBaseRfidprintService;
import com.matariky.bizservice.assetitm.base.vo.BasicBaseRfidprintAddVO;
import com.matariky.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;

import com.matariky.commonservice.minio.utils.MinioUtil;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;
import com.matariky.utils.StringUtils;
import com.matariky.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Controller
 * 
 * @author AUTOMATION
 */
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
@Api(value = " Print  Task  Information ", tags = " Print  Task  Information ")
public class BasicBaseRfidprintController {

	@Value("${message.locale}")
	String locale;

	@Autowired
	private BasicBaseRfidprintService basicBaseRfidprintService;

	@Autowired
	private MinioUtil minioUtil;

	@Autowired
	private BasicBaseRfidprintDetailService baseRfidprintDetailService;

	@ApiOperation(value = "Pagination", response = BasicBaseRfidPrint.class)
	@RequestMapping("/basicBaseRfidprint/list")
	public Object list(HttpServletRequest request, BasicBaseRfidPrint bean,
			@ApiParam(value = " Tenant ID", required = true) @PathVariable("tenantId") String tenantId,
			@ApiParam(value = "Page Index", required = true) @RequestParam("index") int pageIndex,
			@ApiParam(value = "Page Size", required = true) @RequestParam("perPage") int perPage,
			@ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt) {
		if (StringUtils.isNotEmpty(bean.getOperateDateStart())) {
			bean.setOperateDateStart(bean.getOperateDateStart() + " 00:00:00");
		}
		if (StringUtils.isNotEmpty(bean.getOperateDateEnd())) {
			bean.setOperateDateEnd(bean.getOperateDateEnd() + " 23:59:59");
		}
		PageInfo<BasicBaseRfidPrint> page = new PageInfo<BasicBaseRfidPrint>(
				basicBaseRfidprintService.getBasicBaseRfidprintAll(bean, tenantId, request, pageIndex, perPage));
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
	}

	@ApiOperation(value = "App Print  Task Pagination", response = BasicBaseRfidPrint.class)
	@RequestMapping("/basicBaseRfidprint/applist")
	public Object applist(HttpServletRequest request, BasicBaseRfidPrint bean,
			@ApiParam(value = " Tenant ID", required = true) @PathVariable("tenantId") String tenantId,
			@RequestParam String deviceCode,
			@ApiParam(value = "Page Index", required = true) @RequestParam("index") int pageIndex,
			@ApiParam(value = "Page Size", required = true) @RequestParam("perPage") int perPage,
			@ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt) {
		if (StringUtils.isNotEmpty(bean.getOperateDateStart())) {
			bean.setOperateDateStart(bean.getOperateDateStart() + " 00:00:00");
		}
		if (StringUtils.isNotEmpty(bean.getOperateDateEnd())) {
			bean.setOperateDateEnd(bean.getOperateDateEnd() + " 23:59:59");
		}
		PageInfo<BasicBaseRfidPrint> page = new PageInfo<BasicBaseRfidPrint>(
				basicBaseRfidprintService.getBasicBaseRfidprintAppAll(bean,
						deviceCode, tenantId, request, pageIndex, perPage));
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);

	}

	@ApiOperation(value = "New")
	@RequestMapping(value = "/basicBaseRfidprint", method = RequestMethod.POST)
	public Object save(@RequestBody BasicBaseRfidprintAddVO bean, HttpServletRequest request,
			HttpServletResponse response, @PathVariable("tenantId") String tenantId,
			@RequestHeader("Authorization") String jwt) {
		try {
			bean.setTenantId(tenantId);
			bean.setCreateBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
			bean.setUpdateBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
			int success = basicBaseRfidprintService.createBasicBaseRfidprintWithOrg(bean, request);
			if (success > 0) {
				return new AjaxResult(response.getStatus(), AjaxResult.SUCCESS, null);
			} else {
				throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	@ApiOperation(value = "Query Detail By ID")
	@GetMapping(value = "/basicBaseRfidprint/{id}")
	public Object getOne(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
		return new AjaxResult(response.getStatus(), AjaxResult.SUCCESS,
				basicBaseRfidprintService.getBasicBaseRfidprintById(id));
	}

	@ApiOperation(value = "Upload Image")
	@RequestMapping(value = "/basicBaseRfidprint/uploadimg", method = RequestMethod.PUT)
	public Object uploadImg(@RequestParam MultipartFile file, @RequestParam String bucket,
			@RequestParam(required = false) String objectName, @PathVariable("tenantId") String tenantId)
			throws Exception {
		minioUtil.createBucket(bucket);
		minioUtil.uploadFile(file.getInputStream(), bucket, file.getOriginalFilename());
		String fileName = "api/v1/tenant/1/file/downloadFile?bucket=rfidprintimg&objectName="
				+ file.getOriginalFilename();
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, fileName);

	}

	@ApiOperation(value = "Query Print Pagination")
	@RequestMapping("/basicBaseRfidprint/print")
	public Object print(HttpServletRequest request, BasicBaseRfidprintDetail bean,
			@ApiParam(value = " Tenant ID", required = true) @PathVariable("tenantId") String tenantId,
			@ApiParam(value = "Page Index", required = true) @RequestParam("index") int pageIndex,
			@ApiParam(value = "Page Size", required = true) @RequestParam("perPage") int perPage) {
		if (StringUtils.isNotEmpty(bean.getPrintTimeStart())) {
			bean.setPrintTimeStart(bean.getPrintTimeStart() + " 00:00:00");
		}
		if (StringUtils.isNotEmpty(bean.getPrintTimeEnd())) {
			bean.setPrintTimeEnd(bean.getPrintTimeEnd() + " 23:59:59");
		}
		PageInfo<BasicBaseRfidprintDetail> page = new PageInfo<BasicBaseRfidprintDetail>(
				baseRfidprintDetailService.getBasicBaseRfidprintDetailAll(bean, pageIndex, perPage));
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
	}

	@ApiOperation(value = "Compensation Print")
	@GetMapping(value = "/basicBaseRfidprint/suppprint/{id}")
	public Object suppprint(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id,
			@RequestHeader("Authorization") String jwt) {
		try {
			int success = basicBaseRfidprintService.createBasicBaseRfidSuppprintWithOrg(id, jwt);
			if (success > 0) {
				return new AjaxResult(response.getStatus(), AjaxResult.SUCCESS, null);
			} else {
				throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	@ApiOperation(value = "Query Printer by Mac Address")
	@GetMapping(value = "/basicBaseRfidprint/selectprint")
	public AjaxResult suppprint(@RequestParam String mac) {
		return AjaxResult.success(basicBaseRfidprintService.selectPrint(mac));
	}

	@ApiOperation(value = " Printer Binding")
	@GetMapping(value = "/basicBaseRfidprint/printlock")
	public AjaxResult printlock(@RequestParam Long printId, @RequestParam String deviceCode) {
		if (printId == null || deviceCode == null) {
			throw new QslException("Wrong Parameter!");
		}
		return AjaxResult.success(basicBaseRfidprintService.printLock(printId, deviceCode));
	}

	@ApiOperation(value = " Printer Unbind")
	@GetMapping(value = "/basicBaseRfidprint/printunlock")
	public AjaxResult printunlock(@RequestParam Long printId) {
		if (printId == null) {
			throw new QslException("Wrong Parameter!");
		}
		return AjaxResult.success(basicBaseRfidprintService.printUnlock(printId));
	}

	@ApiOperation(value = "Query Lock Flag")
	@GetMapping(value = "/basicBaseRfidprint/selectlock")
	public AjaxResult selectlock(@RequestParam Long printId) {
		return AjaxResult.success(basicBaseRfidprintService.selectLock(printId));
	}

}
