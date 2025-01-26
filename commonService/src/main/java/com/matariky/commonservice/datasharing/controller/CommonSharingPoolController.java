package com.matariky.commonservice.datasharing.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.matariky.commonservice.datasharing.bean.CommonSharingPool;
import com.matariky.commonservice.datasharing.service.CommonSharingPoolService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.constant.PermissionConstant;
import com.matariky.exception.QslException;
import com.matariky.utils.AjaxResult;
//import com.matariky.userservice.service.UserOrganizationService;
import com.matariky.utils.ConstantUtil;
import com.matariky.utils.TokenUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
*Controller
* @author AUTOMATION
*/
@RestController
@RequestMapping("/api/v1/tenant/{tenantId}")
@Api(tags = "10资源共享池")
public class CommonSharingPoolController {
	
	@Autowired
	ConfigurableApplicationContext applicationContext;

	@Autowired
	private CommonSharingPoolService CommonSharingPoolService;
	
	@Value("${message.locale}")
	String locale;
	@Autowired CommonDictService commonDictService;
	

	 
	@RequestMapping("/CommonSharingPool/list")
	@ApiOperation(value = "特殊 Rule 分页列表")
	public Object list(HttpServletRequest request,CommonSharingPool bean,  @PathVariable("tenantId") String tenantId,  @RequestParam("index") int pageIndex, @RequestParam("perPage") int perPage,  @RequestHeader("Authorization") String jwt, @RequestParam("resourceId") Long resourceId) {
		PageHelper.startPage(pageIndex, perPage);
		PageInfo<CommonSharingPool> page = new PageInfo<CommonSharingPool>(CommonSharingPoolService.getCommonSharingPoolByResourceId(resourceId, tenantId));
		return  new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,page);
	}


	 
	@RequestMapping(value = "/CommonSharingPool",method = RequestMethod.PUT)
	public Object update(CommonSharingPool bean,HttpServletRequest request, HttpServletResponse response) {
		try{
			int success = CommonSharingPoolService.updateCommonSharingPool(bean);
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
	
	//New特殊 Rule 
		@RequestMapping(value = "/CommonSharingPool/specialRule",method = RequestMethod.POST)
		public Object newSpecialRule(@RequestHeader("Authorization") String token, @RequestBody JSONObject resourceAllocation,HttpServletRequest request, HttpServletResponse response) {
			String visitorType= resourceAllocation.getString("receiverType");
			String visitors =resourceAllocation.getString("receivingOrgCode");
			String visibleScopeType = resourceAllocation.getString("sharerType");
			String visibleScopes = resourceAllocation.getString("originalOwnerOrgCode");
			String tenantId = resourceAllocation.getString("tenantId");
			Long resourceId =resourceAllocation.getLong("id");
			if(!ConstantUtil.getAllConstantValuesByCategory(PermissionConstant.class, "DATA_PERMISSION_VISITOR_TYPE").contains(visitorType)) {
				throw new QslException(MessageKey.INVALID_DA_VISITOR_TYPE);
			}
			
			if(!ConstantUtil.getAllConstantValuesByCategory(PermissionConstant.class, "DATA_PERMISSION_VISIBLE_SCOPE_TYPE").contains(visibleScopeType)) {
				throw new QslException(MessageKey.INVALID_DA_VISIBLE_SCOPE_TYPE);
			}
			if(StringUtils.isEmpty(visitors)) {
				throw new QslException(MessageKey.INVALID_DA_VISITOR_TYPE);
			}
			if(StringUtils.isEmpty(visibleScopes)) {
				throw new QslException(MessageKey.INVALID_DA_NULL_VISIBLE_SCOPE);
			}
			if(StringUtils.isEmpty(tenantId)) {
				throw new QslException(MessageKey.EMPTY_TENANT_ID);
			}
			for(String visitorScope:resovleOrgCodesByType(visibleScopes)) {
				for(String visitor : resovleOrgCodesByType(visitors)) {
					CommonSharingPool sharingPool =new CommonSharingPool();
					sharingPool.setOriginalOwnerOrgCode(visitorScope);
					sharingPool.setReceivingOrgCode(visitor);
					sharingPool.setTenantId(tenantId);
					sharingPool.setCreatedBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request)));
					sharingPool.setCreateTime(System.currentTimeMillis());
					sharingPool.setDeleteTime(0l);
					sharingPool.setSharerType(Long.parseLong(visibleScopeType));
					sharingPool.setReceiverType(Long.parseLong(visitorType));
					sharingPool.setResourceId(resourceId);
					try {
					CommonSharingPoolService.insert(sharingPool);
					}
					catch (Exception e) {
						//有重复的插入不成功不影响其他插入
						
					}
				}
			}
			return  new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,null);
		}

	private Set<String> resovleOrgCodesByType(String scopes) {
		Set<String> toReturn = new HashSet<String>();
		for(String s : scopes.split(",")) {
			toReturn.add(stripSquareBrackets(s));
		}
		return toReturn;
	}
	
	private  String stripSquareBrackets(String s) {
		String ss =s.replace("[","").trim();
		ss= ss.replace("]", "").trim();
		return ss;
	}

	 
	@RequestMapping(value = "/CommonSharingPool",method = RequestMethod.DELETE)
	public Object del(String id,HttpServletRequest request, HttpServletResponse response) {
		try{
			int success = CommonSharingPoolService.delCommonSharingPoolById(Long.parseLong(id));
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
	
	@RequestMapping(value = "/CommonSharingPool/{organizationCode}",method = RequestMethod.GET)
	public Object getOne(@PathVariable("organizationCode") String organizationCode, HttpServletRequest request, HttpServletResponse response) {
		 return  new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS, (List<CommonSharingPool>)CommonSharingPoolService.selectByOrgCode(organizationCode));
				
	}
	
	@RequestMapping(value = "/CommonSharingPool/alias",method = RequestMethod.GET)
	public Object getAliases() {
			List<String> toReturn = new ArrayList<String>();
			String[] beans = applicationContext.getBeanDefinitionNames();
			for(String bean:beans) {
				if(bean.endsWith("Controller")) {
					toReturn.add(toAlias(bean));
				}
			}
		 return  new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,toReturn);
				
	}

	private String toAlias(String bean) {
		int cidx = bean.indexOf("Controller");
		String cname = bean.substring(1,cidx);
		String fst = bean.substring(0,1);
		String cfst =fst.toUpperCase();
		return cfst+cname;
	}

}