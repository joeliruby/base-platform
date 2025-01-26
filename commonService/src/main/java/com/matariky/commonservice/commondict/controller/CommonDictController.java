package com.matariky.commonservice.commondict.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.matariky.annotation.PassToken;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.commonservice.upload.utils.Result;
import com.matariky.exception.QslException;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.TokenUtils;

/**
*Controller
* @author AUTOMATION
*/
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
public class CommonDictController {

	@Autowired
	private CommonDictService commonDictService;
	@Value("${message.locale}")
	String locale;

	 
	@RequestMapping(value = "/commonDict/list", method = RequestMethod.GET)
	public AjaxResult list(HttpServletRequest request,
			/* CommonDict bean, */
			@RequestParam Map<String, Object> params,
		    @PathVariable("tenantId") String tenantId,
			/*
			 * @ApiParam(value = "Page Index", required = true) @RequestParam("index") int
			 * pageIndex,
			 * @ApiParam(value = "Page Size", required = true) @RequestParam("perPage") int
			 * perPage,
			 */
			@RequestHeader("Authorization") String jwt) {
		
		params.put("tenantId", tenantId);
		
		
		
		int pageIndex=1;
		int perPage=20;
		if(params.containsKey("index")) {
			pageIndex=Integer.parseInt(params.get("index").toString());
		}
		
		if(params.containsKey("perPage")) {
			perPage=Integer.parseInt(params.get("perPage").toString());
		}
		
		
		PageHelper.startPage(pageIndex, perPage);
		
		List<CommonDict> commonDictList = commonDictService.getCommonDictAll(params);
		
		PageInfo<CommonDict> page= new PageInfo<CommonDict>(commonDictList);
		return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,page);
//		return page;
	}
	
	
	
	@RequestMapping(value = "/commonDict/edit", method = RequestMethod.GET)
	public Object edit(HttpServletRequest request,Long id) {
		
		CommonDict bean = commonDictService.getCommonDictById(id);
		
		return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,bean);
//		return new Result<CommonDict>().ok(bean);
	}
	
	@RequestMapping(value = "/commonDict/initialList", method = RequestMethod.GET)
	@PassToken
	public Object initialList(@PathVariable("tenantId") String tenantId) {
		List<Map<String, Object>> dictList =commonDictService.initialList(tenantId,locale);
		Map<String,JSONObject> joMap =buildJOMap(dictList);
		return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,joMap);
//		return joMap;
	}

	private Map<String, JSONObject> buildJOMap(List<Map<String, Object>> dictList) {
		Map<String, JSONObject> joMap = new HashMap<String, JSONObject>();
		for(Map<String, Object> map : dictList) {
			if(!joMap.containsKey(map.get("dict_type_key").toString())) {
				JSONObject node = new JSONObject();
				node.put(map.get("dict_key").toString(), map.get("dict_value").toString());
				joMap.put(map.get("dict_type_key").toString(), node);
			}
			else {
				JSONObject jo = joMap.get(map.get("dict_type_key").toString());
				jo.put(map.get("dict_key").toString(), map.get("dict_value").toString());
			}
		}
		return joMap;
	}


	 
	@RequestMapping(value = "/commonDict",method = RequestMethod.POST)
	public Object save(@RequestBody CommonDict bean,HttpServletRequest request, HttpServletResponse response) {
		try{
			bean.setIsActive(true);
			bean.setTenantId(TokenUtils.extractTenantIdFromHttpReqeust(request));
			bean.setId(null);
			int success = commonDictService.createCommonDict(bean);
				if(success > 0){
					return  new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,null);
				}else{
					throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
				}
		}catch (Exception e){
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	 
	@RequestMapping(value = "/commonDict",method = RequestMethod.PUT)
	public Object update(@RequestBody CommonDict bean,HttpServletRequest request, HttpServletResponse response) {
		try{
			boolean success = commonDictService.updateById(bean);
				if(success ){
					return  new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,null);
				}else{
					throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
				}
		}catch (Exception e){
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	 
	@RequestMapping(value = "/commonDict",method = RequestMethod.DELETE)
	public Object del(String id,HttpServletRequest request, HttpServletResponse response) {
		
		String[] split ;
		if(id.contains(",")) {
			split=id.split(",");
		}else {
			split=new String [] {id};
		}
		
		try{
			int success = commonDictService.updateDeleteTimeById(split);
				if(success > 0){
					return  new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,null);
				}else{
					throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
				}
		}catch (Exception e){
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	/**
	 * @Description: 根据字段Type key字典 Data 
	 * @Author: bo.chen
	 * @Date: 2023/9/4 11:04
	 * @param tenantId
	 * @param typeKey
	 * @return java.lang.Object
	 **/
	@RequestMapping(value = "/commonDict/listByTypeKey", method = RequestMethod.GET)
	public Object dictListByTypeKey(@PathVariable("tenantId") String tenantId,
									String typeKey,
									Long isActive,
									Long deleteTime) {
		
		return  new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,commonDictService.getDictListByTypeKey(tenantId, locale + "_" + typeKey,isActive,deleteTime));
//		return commonDictService.getDictListByTypeKey(tenantId, locale + "_" + typeKey,isActive,deleteTime);
	}

}