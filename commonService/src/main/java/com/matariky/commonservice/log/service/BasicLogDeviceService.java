package com.matariky.commonservice.log.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.commonservice.log.bean.BasicLogDevice;
import com.matariky.commonservice.log.mapper.BasicLogDeviceMapper;

import java.util.ArrayList;

 import java.util.HashMap;

 import javax.servlet.http.HttpServletRequest;

 import com.github.pagehelper.util.StringUtil;

 import com.matariky.commonservice.accesslog.bean.CommonAccessLog;

 import com.matariky.commonservice.accesslog.mapper.CommonAccessLogMapper;

 import com.matariky.commonservice.datasharing.bean.CommonSharingPool;

 import com.matariky.commonservice.datasharing.service.CommonSharingPoolService;

 import com.matariky.constant.PermissionConstant;

 import com.matariky.utils.TokenUtils;

 import javax.servlet.http.HttpServletRequest;

/**
* Business Inteface Implementation
* @author AUTOMATION
*/
@Service
public class BasicLogDeviceService extends BaseServiceImpl<BasicLogDeviceMapper,BasicLogDevice> implements BaseService<BasicLogDevice>{

	@Autowired
	private BasicLogDeviceMapper basicLogDeviceMapper;

	 
	public List<BasicLogDevice> getBasicLogDeviceAll(){
		return basicLogDeviceMapper.getBasicLogDeviceAll();
	}

	 
	public int getBasicLogDeviceAllCount(){
		return basicLogDeviceMapper.getBasicLogDeviceAllCount();
	}

	 
	public int createBasicLogDevice(BasicLogDevice bean){
		return basicLogDeviceMapper.createBasicLogDevice(bean);
	}

	 
	 
	public int createBasicLogDeviceWithOrg(BasicLogDevice bean, HttpServletRequest request){
	bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
		bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
		return basicLogDeviceMapper.createBasicLogDevice(bean);
	}
	public int updateBasicLogDevice(BasicLogDevice bean){
		return basicLogDeviceMapper.updateById(bean);
	}

	 
	public int delBasicLogDeviceById(int id){
		return basicLogDeviceMapper.delBasicLogDeviceById(id);
	}

	 
	public BasicLogDevice getBasicLogDeviceById(int id){
		return basicLogDeviceMapper.getBasicLogDeviceById(id);
	}

	public List<BasicLogDevice> getBasicLogDeviceDAC(Map<String, Object> params, HttpServletRequest request) {
		strategyCodeToParams(params, request);
		return basicLogDeviceMapper.getBasicLogDeviceDAC( params);
	}

	public Long getBasicLogDeviceDACCount(Map<String, Object> params,HttpServletRequest request) {

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

		return basicLogDeviceMapper.getBasicLogDeviceDACCount(params);
	}

}
