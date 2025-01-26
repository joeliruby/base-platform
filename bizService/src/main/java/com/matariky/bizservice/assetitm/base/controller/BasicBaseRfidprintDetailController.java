package com.matariky.bizservice.assetitm.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidprintDetail;
import com.matariky.bizservice.assetitm.base.service.BasicBaseRfidprintDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.matariky.utils.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Controller
 * 
 * @author AUTOMATION
 */
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
@Api(value = " Print  Task Detail Information ", tags = " Print  Task Detail Information ")
public class BasicBaseRfidprintDetailController {

	@Value("${message.locale}")
	String locale;
	@Autowired
	private BasicBaseRfidprintDetailService basicBaseRfidprintDetailService;

	@ApiOperation(value = " Submit TID")
	@RequestMapping(value = "/basicBaseRfidprintDetail", method = RequestMethod.PUT)
	public AjaxResult update(@RequestBody BasicBaseRfidprintDetail bean, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int success = basicBaseRfidprintDetailService.updateBasicBaseRfidprintDetail(bean);
			if (success > 0) {
				return AjaxResult.success();
			} else {
				return AjaxResult.error();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error();
		}
	}

	@ApiOperation(value = "Query  Print  Task  Detail By Print Task ID ", response = BasicBaseRfidprintDetail.class)
	@RequestMapping(value = "/basicBaseRfidprintDetail/{id}", method = RequestMethod.GET)
	public AjaxResult getAll(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
		return AjaxResult.success(basicBaseRfidprintDetailService.getBasicBaseRfidprintDetailAllById(id));
	}

	@ApiOperation(value = "Query Print Failure By Task Id", response = BasicBaseRfidprintDetail.class)
	@RequestMapping(value = "/basicBaseRfidprintDetail/unprint/{id}", method = RequestMethod.GET)
	public AjaxResult getUnprintAll(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
		return AjaxResult.success(basicBaseRfidprintDetailService.getBasicBaseRfidprintDetailUnprintById(id));
	}

}
