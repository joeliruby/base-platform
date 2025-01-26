package com.matariky.commonservice.device.service;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.matariky.commonservice.base.vo.ErrorLogExeclVO;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.device.excel.DeviceLogExcelVO;
import com.matariky.commonservice.error.bean.BasicBaseErrorLog;
import com.matariky.utils.BeanUtils;
import com.matariky.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.commonservice.device.bean.BasicBaseDeviceLog;
import com.matariky.commonservice.device.mapper.BasicBaseDeviceLogMapper;

import java.util.ArrayList;

 import java.util.HashMap;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

 import com.github.pagehelper.util.StringUtil;

 import com.matariky.commonservice.accesslog.bean.CommonAccessLog;

 import com.matariky.commonservice.accesslog.mapper.CommonAccessLogMapper;

 import com.matariky.commonservice.datasharing.bean.CommonSharingPool;

 import com.matariky.commonservice.datasharing.service.CommonSharingPoolService;

 import com.matariky.constant.PermissionConstant;

 import com.matariky.utils.TokenUtils;

 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Business Inteface Implementation
* @author AUTOMATION
*/
@Service
public class BasicBaseDeviceLogService extends BaseServiceImpl<BasicBaseDeviceLogMapper,BasicBaseDeviceLog> implements BaseService<BasicBaseDeviceLog>{

	@Autowired
	private BasicBaseDeviceLogMapper basicBaseDeviceLogMapper;
	@Autowired
	private HttpServletResponse response;
	
	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private CommonDictTypeService commonDictTypeService;
	@Autowired
	private CommonDictService commonDictService;

	 
	public List<BasicBaseDeviceLog> getBasicBaseDeviceLogAll(BasicBaseDeviceLog vo){
		if (StringUtils.isNotBlank(vo.getBusinessTimeEnd())) {
			Long endTime = Long.valueOf(vo.getBusinessTimeEnd()) + 24 * 60 * 60 * 1000;
			vo.setBusinessTimeEnd(endTime.toString());
		}
		
		if (StringUtils.isNotBlank(vo.getBusinessTimeEnd())) {
			Long endTime = Long.valueOf(vo.getBusinessTimeEnd()) + 24 * 60 * 60 * 1000;
			vo.setBusinessTimeEnd(endTime.toString());
		}
		
		String hid = request.getHeader("id");
        String resourceIdDictKey = "dp" + hid.substring(0, hid.length() - 1);
        String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
        CommonDictType commonDictType = commonDictTypeService.getDictTypeByKey(TokenUtils.extractTenantIdFromHttpReqeust(request), PermissionConstant.DATA_ACCESS_PERMISSION);
        CommonDict dict = commonDictService.getCommonDictByIdTenantIdAndDictType(resourceIdDictKey, tenantId, commonDictType.getId());
        if (dict == null) {
            vo.setStrategyCode(PermissionConstant.COMMON_DATA_ACCESS_ALL);
        } else {
            vo.setStrategyCode(dict.getDictValue());
        }
        vo.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        vo.setSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
        vo.setTenantId(tenantId);
		return basicBaseDeviceLogMapper.getBasicBaseDeviceLogAll(vo);
	}

	/**
	 *  Export 
	 */
	public void export(BasicBaseDeviceLog bean){
		List<BasicBaseDeviceLog> list = basicBaseDeviceLogMapper.getBasicBaseDeviceLogAll(bean);
		List<DeviceLogExcelVO> newList = list.stream().map(item -> {
			DeviceLogExcelVO vo = new DeviceLogExcelVO();
			BeanUtils.copyProperties(item, vo);
			return vo;
		}).collect(Collectors.toList());
		response.setContentType("application/vnd.ms-excel; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode( System.currentTimeMillis() + ".xlsx", "utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			EasyExcel.write(outputStream, DeviceLogExcelVO.class)
					.excelType(ExcelTypeEnum.XLSX)
					.sheet(" Device 日志")
					.doWrite(newList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 
	public int getBasicBaseDeviceLogAllCount(){
		return basicBaseDeviceLogMapper.getBasicBaseDeviceLogAllCount();
	}

	 
	public int createBasicBaseDeviceLog(BasicBaseDeviceLog bean){
		return basicBaseDeviceLogMapper.createBasicBaseDeviceLog(bean);
	}

	 
	 
	public int createBasicBaseDeviceLogWithOrg(BasicBaseDeviceLog bean, HttpServletRequest request){
		bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
		bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
		bean.setCreateTime(System.currentTimeMillis());
		bean.setCreateBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request)));
		bean.setTenantId(TokenUtils.extractTenantIdFromHttpReqeust(request));
		bean.setDeleteTime(0L);
		return basicBaseDeviceLogMapper.createBasicBaseDeviceLog(bean);
	}
	public int updateBasicBaseDeviceLog(BasicBaseDeviceLog bean){
		return basicBaseDeviceLogMapper.updateById(bean);
	}

	 
	public int delBasicBaseDeviceLogById(int id){
		return basicBaseDeviceLogMapper.delBasicBaseDeviceLogById(id);
	}

	 
	public BasicBaseDeviceLog getBasicBaseDeviceLogById(int id){
		return basicBaseDeviceLogMapper.getBasicBaseDeviceLogById(id);
	}

	public List<BasicBaseDeviceLog> getBasicBaseDeviceLogDAC(Map<String, Object> params, HttpServletRequest request) {
		strategyCodeToParams(params, request);
		return basicBaseDeviceLogMapper.getBasicBaseDeviceLogDAC( params);
	}

	public Long getBasicBaseDeviceLogDACCount(Map<String, Object> params,HttpServletRequest request) {

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

		return basicBaseDeviceLogMapper.getBasicBaseDeviceLogDACCount(params);
	}

}
