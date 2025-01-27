package com.matariky.commonservice.log.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import com.matariky.commonservice.log.bean.BasicLogBusiness;
import com.matariky.commonservice.log.service.BasicLogBusinessService;

import io.swagger.annotations.ApiParam;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestHeader;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Value;

import com.matariky.commonservice.commondict.service.CommonDictService;

import com.matariky.commonservice.commondict.bean.CommonDict;

import com.matariky.commonservice.commondict.bean.CommonDictType;

import com.matariky.commonservice.commondict.service.CommonDictTypeService;

import com.matariky.constant.PermissionConstant;

import com.matariky.utils.TokenUtils;

import springfox.documentation.annotations.ApiIgnore;

import com.matariky.exception.QslException;

import com.matariky.commonservice.upload.constant.MessageKey;

/**
 * Controller
 * 
 * @author AUTOMATION
 */
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
public class BasicLogBusinessController {

	@Value("${message.locale}")
	String locale;
	@Autowired
	private BasicLogBusinessService basicLogBusinessService;

	@Autowired
	private CommonDictService commonDictService;

	@Autowired
	private CommonDictTypeService commonDictTypeService;

	@RequestMapping("/basicLogBusiness/list")
	public PageInfo<BasicLogBusiness> list(HttpServletRequest request, BasicLogBusiness bean,
			@ApiParam(value = " Tenant ID", required = true) @PathVariable("tenantId") String tenantId,
			@ApiParam(value = "Page Index", required = true) @RequestParam("index") int pageIndex,
			@ApiParam(value = "Page Size", required = true) @RequestParam("perPage") int perPage,
			@ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt) {
		PageHelper.startPage(pageIndex, perPage);
		PageInfo<BasicLogBusiness> page = new PageInfo<>(basicLogBusinessService.getBasicLogBusinessAll());
		return page;
	}

	@RequestMapping("/basicLogBusiness/daclist")
	public Object daclist(HttpServletRequest request, @ApiIgnore @RequestParam Map<String, Object> params,
			@ApiParam(value = " Tenant ID", required = true) @PathVariable("tenantId") String tenantId,
			@ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt) {
		String hid = request.getHeader("id");
		String resourceIdDictKey = "dp" + hid.substring(0, hid.length() - 1);
		CommonDictType commonDictType = commonDictTypeService.getDictTypeByKey(
				TokenUtils.extractTenantIdFromHttpReqeust(request), PermissionConstant.DATA_ACCESS_PERMISSION);
		CommonDict dict = commonDictService.getCommonDictByIdTenantIdAndDictType(resourceIdDictKey, tenantId,
				commonDictType.getId());
		if (dict == null) {
			params.put("strategyCode", PermissionConstant.COMMON_DATA_ACCESS_ALL);
		} else {
			params.put("strategyCode", dict.getDictValue());
		}
		int pageIndex = Integer.parseInt(params.get("index").toString());
		int perPage = Integer.parseInt(params.get("perPage").toString());
		params.put("tenantId", tenantId);
		params.put("pageStart", (pageIndex - 1) * perPage);
		params.put("pageSize", perPage);
		List<BasicLogBusiness> commonDictList = basicLogBusinessService.getBasicLogBusinessDAC(params, request);
		Long count = basicLogBusinessService.getBasicLogBusinessDACCount(params, request);
		PageInfo<BasicLogBusiness> page = new PageInfo<BasicLogBusiness>(commonDictList);
		page.setTotal(count);
		page.setPageSize(perPage);
		page.setPageNum(pageIndex);
		page.setPages(Integer.parseInt(
				Long.valueOf(count % Long.valueOf(perPage) == 0 ? count % Long.valueOf(perPage)
						: count % Long.valueOf(perPage) + 1)
						.toString()));
		return page;
	}

	@RequestMapping(value = "/basicLogBusiness", method = RequestMethod.POST)
	public ResponseEntity<String> save(@RequestBody BasicLogBusiness bean, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int success = basicLogBusinessService.createBasicLogBusinessWithOrg(bean, request);
			if (success > 0) {
				return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			} else {
				throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	@RequestMapping(value = "/basicLogBusiness", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@RequestBody BasicLogBusiness bean, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int success = basicLogBusinessService.updateBasicLogBusiness(bean);
			if (success > 0) {
				return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			} else {
				throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	@RequestMapping(value = "/basicLogBusiness", method = RequestMethod.DELETE)
	public ResponseEntity<String> del(String id, HttpServletRequest request, HttpServletResponse response) {
		try {
			Boolean success = basicLogBusinessService.deleteById(Long.parseLong(id));
			if (success) {
				return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			} else {
				throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	@RequestMapping(value = "/basicLogBusiness/{basicLogBusinessId}", method = RequestMethod.GET)
	public Object getOne(@PathVariable("/basicLogBusinessId") Long id, HttpServletRequest request,
			HttpServletResponse response) {
		return basicLogBusinessService.selectById(id);
	}

}