package com.matariky.bizservice.assetitm.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.matariky.bizservice.assetitm.base.vo.BasicBaseRfidfactoryAddVO;
import com.matariky.exception.QslException;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageInfo;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidfactory;
import com.matariky.bizservice.assetitm.base.service.BasicBaseRfidfactoryService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.utils.TokenUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller
 * 
 * @author AUTOMATION
 */
@Api(value = " Factory  Label  Data  Generation ", tags = " Factory  Label  Data  Generation ")
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
public class BasicBaseRfidfactoryController {

	@Value("${message.locale}")
	String locale;
	@Autowired
	private BasicBaseRfidfactoryService basicBaseRfidfactoryService;

	@ApiOperation(value = "Pagination", response = BasicBaseRfidfactory.class)
	@RequestMapping("/basicBaseRfidfactory/list")
	public AjaxResult list(HttpServletRequest request, BasicBaseRfidfactory bean,
			@ApiParam(value = " Tenant ID", required = true) @PathVariable("tenantId") String tenantId,
			@ApiParam(value = "Page Index", required = true) @RequestParam("index") int pageIndex,
			@ApiParam(value = "Page Size", required = true) @RequestParam("perPage") int perPage,
			@ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt) {
		if (StringUtils.isNotEmpty(bean.getCreateDateStart())) {
			bean.setCreateDateStart(bean.getCreateDateStart() + " 00:00:00");
		}
		if (StringUtils.isNotEmpty(bean.getCreateDateEnd())) {
			bean.setCreateDateEnd(bean.getCreateDateEnd() + " 23:59:59");
		}
		PageInfo<BasicBaseRfidfactory> page = new PageInfo<BasicBaseRfidfactory>(
				basicBaseRfidfactoryService.getBasicBaseRfidfactoryAll(bean, tenantId, request, pageIndex, perPage));
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
	}

	@ApiOperation(value = "New")
	@PostMapping(value = "/basicBaseRfidfactory")
	public AjaxResult save(@RequestBody @Validated BasicBaseRfidfactoryAddVO bean, HttpServletRequest request,
			HttpServletResponse response, @PathVariable("tenantId") String tenantId,
			@RequestHeader("Authorization") String jwt) {
		try {
			bean.setTenantId(tenantId);
			bean.setCreateBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
			bean.setUpdateBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
			int success = basicBaseRfidfactoryService.createBasicBaseRfidfactoryWithOrg(bean, request);
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

	@ApiOperation(value = "Download")
	@GetMapping(value = "/basicBaseRfidfactory/export/{id}")
	public AjaxResult getExport(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id,
			@PathVariable("tenantId") String tenantId, @RequestHeader("Authorization") String jwt) {
		return new AjaxResult(response.getStatus(), AjaxResult.SUCCESS,
				basicBaseRfidfactoryService.download(response, id));
	}

	@ApiOperation(value = "Import")
	@PostMapping("/basicBaseRfidfactory/importData")
	public AjaxResult upload(@RequestParam("file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response,
			@ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt,
			@ApiParam(value = " Tenant ID", required = true) @PathVariable("tenantId") String tenantId,
			@RequestParam("id") Long id) {
		try {

			basicBaseRfidfactoryService.upload(file, tenantId, jwt, id);
			return new AjaxResult(response.getStatus(), AjaxResult.SUCCESS, null);
		} catch (Exception ex) {
			throw new QslException(ex.getMessage());
		}
	}

	@ApiOperation("Download Import ")
	@GetMapping("/basicBaseRfidfactory/downLoadTemplate")
	public void downLoadTemplate() {
		basicBaseRfidfactoryService.downLoadTemplate();
	}

}
