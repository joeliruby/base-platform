package com.matariky.commonservice.commondict.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.TokenUtils;

/**
 * Controller
 * 
 * @author AUTOMATION
 */
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
public class CommonDictTypeController {

	@Autowired
	private CommonDictTypeService commonDictTypeService;

	@RequestMapping(value = "/commonDictType/list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request,
			@RequestParam Map<String, Object> params,
			@PathVariable("tenantId") String tenantId) {
		params.put("tenantId", tenantId);

		int pageIndex = 1;
		int perPage = 20;
		if (params.containsKey("index")) {
			pageIndex = Integer.parseInt(params.get("index").toString());
		}

		if (params.containsKey("perPage")) {
			perPage = Integer.parseInt(params.get("perPage").toString());
		}

		PageHelper.startPage(pageIndex, perPage);
		Page<CommonDictType> page1 = commonDictTypeService.getCommonDictTypeAll(params);
		PageInfo<CommonDictType> page = new PageInfo<CommonDictType>(page1.getResult());
		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, page);
	}

	@RequestMapping(value = "/commonDictType/edit", method = RequestMethod.GET)
	public Object edit(HttpServletRequest request, String id) {

		CommonDictType bean = commonDictTypeService.getCommonDictTypeById(id);

		return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, bean);
	}

	@RequestMapping(value = "/commonDictType", method = RequestMethod.POST)
	public Object save(@RequestBody CommonDictType bean, HttpServletRequest request, HttpServletResponse response) {
		try {
			bean.setIsActive(true);
			bean.setTenantId(TokenUtils.extractTenantIdFromHttpReqeust(request));
			int success = commonDictTypeService.createCommonDictType(bean);
			if (success > 0) {
				return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, bean);
			} else {
				throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	@RequestMapping(value = "/commonDictType", method = RequestMethod.PUT)
	public Object update(@RequestBody CommonDictType bean, HttpServletRequest request, HttpServletResponse response) {
		try {
			int success = commonDictTypeService.updateCommonDictType(bean);
			if (success > 0) {
				return new AjaxResult(HttpStatus.OK.value(), AjaxResult.SUCCESS, bean);
			} else {
				throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	@RequestMapping(value = "/commonDictType", method = RequestMethod.DELETE)
	public Object del(String id, HttpServletRequest request, HttpServletResponse response) {

		String[] split;
		if (id.contains(",")) {
			split = id.split(",");
		} else {
			split = new String[] { id };
		}
		try {
			int success = commonDictTypeService.updateDeleteTimeById(split);
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
