package com.matariky.commonservice.accesslog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.util.StringUtil;
import com.matariky.commonservice.accesslog.bean.CommonAccessLog;
import com.matariky.commonservice.accesslog.mapper.CommonAccessLogMapper;
import com.matariky.commonservice.datasharing.bean.CommonSharingPool;
import com.matariky.commonservice.datasharing.service.CommonSharingPoolService;
import com.matariky.constant.PermissionConstant;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.utils.TokenUtils;

/**
* Business Inteface Implementation
* @author AUTOMATION
*/
@Service
public class CommonAccessLogService extends BaseServiceImpl<CommonAccessLogMapper,CommonAccessLog> implements BaseService<CommonAccessLog>{

	@Autowired
	private CommonAccessLogMapper commonAccessLogMapper;
	
	@Autowired
	private CommonSharingPoolService sharingPoolService;

	 
	public Page<CommonAccessLog> getCommonAccessLogAll(){
		return commonAccessLogMapper.getCommonAccessLogAll();
	}

	 
	public int getCommonAccessLogAllCount(){
		return commonAccessLogMapper.getCommonAccessLogAllCount();
	}

	 
	public int createCommonAccessLog(CommonAccessLog bean){
		return commonAccessLogMapper.createCommonAccessLog(bean);
	}

	 
	public int updateCommonAccessLog(CommonAccessLog bean){
		return commonAccessLogMapper.updateCommonAccessLog(bean);
	}

	 
	public int delCommonAccessLogById(int id){
		return commonAccessLogMapper.delCommonAccessLogById(id);
	}

	 
	public CommonAccessLog getCommonAccessLogById(String id){
		return commonAccessLogMapper.getCommonAccessLogById(id);
	}

	public List<CommonAccessLog> getCommonAccessLogDynamicCondition(Map<String, Object> params) {
		return commonAccessLogMapper.getCommonAccessLogDynamicCondition( params);
	}

	public List<CommonAccessLog> getCommonAccessLogByIds(String[] split) {
		return commonAccessLogMapper.getCommonAccessLogByIds( split);
	}

	public List<CommonAccessLog> getCommonAccessLogDAC(Map<String, Object> params, HttpServletRequest request) {
		String strategyCode =(String)params.get("strategyCode");
		if(StringUtil.isEmpty(strategyCode))
			strategyCode=PermissionConstant.COMMON_DATA_ACCESS_PRIVATE;//  By default only visible by owner
		switch (strategyCode) {
		case PermissionConstant.COMMON_DATA_ACCESS_PRIVATE://Visible to owner with special sharing rules
			Map<String, List<String>> sharingOrgCodes0=extractedSharingOrgCodes(request);
			params.put("selfOrgCode", TokenUtils.extractSelfOrgCode(request));
			params.putAll(sharingOrgCodes0);
			break;
		case PermissionConstant.COMMON_DATA_ACCESS_ALL://All visible to all without special sharing rules
			break;
		case PermissionConstant.COMMON_DATA_ACCESS_ORG://Visible to organizations of same or upper level
			Map<String, List<String>> sharingOrgCodes3=extractedSharingOrgCodes(request);
			params.put("orgCode", TokenUtils.extractOrgCode(request));
			params.putAll(sharingOrgCodes3);
			break;
			
		case PermissionConstant.COMMON_DATA_ACCESS_LEVEL://Visible to organizations of same level with special sharing rules
			Map<String, List<String>> sharingOrgCodes2=extractedSharingOrgCodes(request);
			params.put("orgCode", TokenUtils.extractOrgCode(request));
			params.putAll(sharingOrgCodes2);
			break;

		default:
			break;
		}
		return commonAccessLogMapper.getCommonAccessLogDAC( params);
	}

	public  Map<String, List<String>> extractedSharingOrgCodes(HttpServletRequest request) {
		List<CommonSharingPool> obtainedBySelfOrgCodes0 =sharingPoolService.selectByOrgCode(TokenUtils.extractSelfOrgCode(request));
		List<CommonSharingPool> obtainedByOrgCodes0 =sharingPoolService.selectByOrgCode(TokenUtils.extractOrgCode(request));
		obtainedByOrgCodes0.addAll(obtainedBySelfOrgCodes0);
		List<String> selfOrgCodes= new ArrayList<String>();
		List<String> orgCodes= new ArrayList<String>();
		for(CommonSharingPool shareingPoolItem: obtainedByOrgCodes0) {
			if(shareingPoolItem.getOriginalOwnerOrgCode().startsWith("ind_")) {
				selfOrgCodes.add(shareingPoolItem.getOriginalOwnerOrgCode());
			}
			else {
				orgCodes.add(shareingPoolItem.getOriginalOwnerOrgCode());
			}
		}
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		if(!selfOrgCodes.isEmpty())
		map.put("sharingSelfOrgCodes", selfOrgCodes);
		if(!orgCodes.isEmpty())
		map.put("sharingOrgCodes", orgCodes);
		return map;
	}

	public Long getCommonAccessLogDACCount(Map<String, Object> params,HttpServletRequest request) {
		String strategyCode =(String)params.get("strategyCode");
		if(StringUtil.isEmpty(strategyCode))
			strategyCode=PermissionConstant.COMMON_DATA_ACCESS_PRIVATE;//  By default only visible by owner
		switch (strategyCode) {
		case PermissionConstant.COMMON_DATA_ACCESS_PRIVATE://Visible to owner with special sharing rules
			Map<String, List<String>> sharingOrgCodes0=extractedSharingOrgCodes(request);
			params.put("selfOrgCode", TokenUtils.extractSelfOrgCode(request));
			params.putAll(sharingOrgCodes0);
			break;
		case PermissionConstant.COMMON_DATA_ACCESS_ALL://All visible to all without special sharing rules
			break;
		case PermissionConstant.COMMON_DATA_ACCESS_ORG://Visible to organizations of same or upper level
			Map<String, List<String>> sharingOrgCodes3=extractedSharingOrgCodes(request);
			params.put("orgCode", TokenUtils.extractOrgCode(request));
			params.putAll(sharingOrgCodes3);
			break;
			
		case PermissionConstant.COMMON_DATA_ACCESS_LEVEL://Visible to organizations of same level with special sharing rules
			Map<String, List<String>> sharingOrgCodes2=extractedSharingOrgCodes(request);
			params.put("orgCode", TokenUtils.extractOrgCode(request));
			params.putAll(sharingOrgCodes2);
			break;

		default:
			break;
		}
		return commonAccessLogMapper.getCommonAccessLogDACCount(params);
	}

}
