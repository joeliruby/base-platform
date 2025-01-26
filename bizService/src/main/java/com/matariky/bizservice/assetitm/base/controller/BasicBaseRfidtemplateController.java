package com.matariky.bizservice.assetitm.base.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidtemplate;
import com.matariky.bizservice.assetitm.base.service.BasicBaseRfidtemplateService;
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
import com.matariky.utils.AjaxResult;
import com.matariky.exception.QslException;
import com.matariky.commonservice.upload.constant.MessageKey;

/**
 * Controller
 * 
 * @author AUTOMATION
 */
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
public class BasicBaseRfidtemplateController {

	@Value("${message.locale}")
	String locale;
	@Autowired
	private BasicBaseRfidtemplateService basicBaseRfidtemplateService;

	@Autowired
	private CommonDictService commonDictService;

	@Autowired
	private CommonDictTypeService commonDictTypeService;

	@RequestMapping("/basicBaseRfidtemplate/list")
	public Object list(HttpServletRequest request, BasicBaseRfidtemplate bean,
			@ApiParam(value = " Tenant ID", required = true) @PathVariable("tenantId") String tenantId,
			@ApiParam(value = "Page Index", required = true) @RequestParam("index") int pageIndex,
			@ApiParam(value = "Page Size", required = true) @RequestParam("perPage") int perPage,
			@ApiParam(value = "JWT Token", required = true) @RequestHeader("Authorization") String jwt) {
		PageHelper.startPage(pageIndex, perPage);
		PageInfo<BasicBaseRfidtemplate> page = new PageInfo<BasicBaseRfidtemplate>(
				basicBaseRfidtemplateService.getBasicBaseRfidtemplateAll(bean));
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
	}

	@RequestMapping("/basicBaseRfidtemplate/daclist")
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
		List<BasicBaseRfidtemplate> commonDictList = basicBaseRfidtemplateService.getBasicBaseRfidtemplateDAC(params,
				request);
		Long count = basicBaseRfidtemplateService.getBasicBaseRfidtemplateDACCount(params, request);
		PageInfo<BasicBaseRfidtemplate> page = new PageInfo<BasicBaseRfidtemplate>(commonDictList);
		page.setTotal(count);
		page.setPageSize(perPage);
		page.setPageNum(pageIndex);
		page.setPages(Integer.parseInt(
				new Long(count % new Long(perPage) == 0 ? count % new Long(perPage) : count % new Long(perPage) + 1)
						.toString()));
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
	}

	@RequestMapping(value = "/basicBaseRfidtemplate", method = RequestMethod.POST)
	public Object save(@RequestBody BasicBaseRfidtemplate bean, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int success = basicBaseRfidtemplateService.createBasicBaseRfidtemplateWithOrg(bean, request);
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

	@RequestMapping(value = "/basicBaseRfidtemplate", method = RequestMethod.PUT)
	public Object update(@RequestBody BasicBaseRfidtemplate bean, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int success = basicBaseRfidtemplateService.updateBasicBaseRfidtemplate(bean);
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

	@RequestMapping(value = "/basicBaseRfidtemplate", method = RequestMethod.DELETE)
	public Object del(String id, HttpServletRequest request, HttpServletResponse response) {
		try {
			Boolean success = basicBaseRfidtemplateService.deleteById(Long.parseLong(id));
			if (success) {
				return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, null);
			} else {
				throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	@RequestMapping(value = "/basicBaseRfidtemplate/{basicBaseRfidtemplateId}", method = RequestMethod.GET)
	public Object getOne(@PathVariable("/basicBaseRfidtemplateId") Long id, HttpServletRequest request,
			HttpServletResponse response) {
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, basicBaseRfidtemplateService.selectById(id));
	}

}