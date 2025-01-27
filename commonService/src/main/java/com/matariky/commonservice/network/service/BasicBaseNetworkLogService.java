package com.matariky.commonservice.network.service;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.network.excel.NetworkLogExcelVO;
import com.matariky.utils.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.commonservice.network.bean.BasicBaseNetworkLog;
import com.matariky.commonservice.network.mapper.BasicBaseNetworkLogMapper;

import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.util.StringUtil;

import com.matariky.constant.PermissionConstant;

import com.matariky.utils.TokenUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * Business Inteface Implementation
 * 
 * @author AUTOMATION
 */
@Service
public class BasicBaseNetworkLogService extends BaseServiceImpl<BasicBaseNetworkLogMapper, BasicBaseNetworkLog> {

	@Autowired
	private BasicBaseNetworkLogMapper basicBaseNetworkLogMapper;
	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private CommonDictService commonDictService;

	@Autowired
	private CommonDictTypeService commonDictTypeService;

	public List<BasicBaseNetworkLog> getBasicBaseNetworkLogAll(BasicBaseNetworkLog vo) {

		String hid = request.getHeader("id");
		String resourceIdDictKey = "dp" + hid.substring(0, hid.length() - 1);
		String tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);
		CommonDictType commonDictType = commonDictTypeService.getDictTypeByKey(
				TokenUtils.extractTenantIdFromHttpReqeust(request), PermissionConstant.DATA_ACCESS_PERMISSION);
		CommonDict dict = commonDictService.getCommonDictByIdTenantIdAndDictType(resourceIdDictKey, tenantId,
				commonDictType.getId());
		if (dict == null) {
			vo.setStrategyCode(PermissionConstant.COMMON_DATA_ACCESS_ALL);
		} else {
			vo.setStrategyCode(dict.getDictValue());
		}
		vo.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
		vo.setSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
		vo.setTenantId(tenantId);
		return basicBaseNetworkLogMapper.getBasicBaseNetworkLogAll(vo);
	}

	/**
	 * Export
	 * 
	 * @param bean
	 */
	public void export(BasicBaseNetworkLog bean) {
		List<BasicBaseNetworkLog> list = basicBaseNetworkLogMapper.getBasicBaseNetworkLogAll(bean);
		List<NetworkLogExcelVO> newList = list.stream().map(item -> {
			NetworkLogExcelVO vo = new NetworkLogExcelVO();
			BeanUtils.copyProperties(item, vo);
			return vo;
		}).collect(Collectors.toList());

		response.setContentType("application/vnd.ms-excel; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			response.setHeader("Content-Disposition",
					"attachment;filename=" + URLEncoder.encode(System.currentTimeMillis() + ".xlsx", "utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			EasyExcel.write(outputStream, NetworkLogExcelVO.class)
					.excelType(ExcelTypeEnum.XLSX)
					.sheet(" Network Log ")
					.doWrite(newList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getBasicBaseNetworkLogAllCount() {
		return basicBaseNetworkLogMapper.getBasicBaseNetworkLogAllCount();
	}

	public int createBasicBaseNetworkLog(BasicBaseNetworkLog bean) {
		return basicBaseNetworkLogMapper.createBasicBaseNetworkLog(bean);
	}

	public int createBasicBaseNetworkLogWithOrg(List<BasicBaseNetworkLog> beanList, HttpServletRequest request) {
		for (BasicBaseNetworkLog bean : beanList) {
			bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
			bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
			bean.setCreateTime(System.currentTimeMillis());
			bean.setCreateBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request)));
			bean.setTenantId(TokenUtils.extractTenantIdFromHttpReqeust(request));
			bean.setDeleteTime(0L);
			if (Objects.isNull(bean.getAccessAccount()))
				bean.setAccessAccount(TokenUtils.extractLoginNameFromHttpRequest(request));
			if (Objects.isNull(bean.getDeviceType()))
				bean.setDeviceType(request.getParameter("User-Agent"));
			basicBaseNetworkLogMapper.createBasicBaseNetworkLog(bean);
		}
		return 1;
	}

	public int updateBasicBaseNetworkLog(BasicBaseNetworkLog bean) {
		return basicBaseNetworkLogMapper.updateById(bean);
	}

	public int delBasicBaseNetworkLogById(int id) {
		return basicBaseNetworkLogMapper.delBasicBaseNetworkLogById(id);
	}

	public BasicBaseNetworkLog getBasicBaseNetworkLogById(int id) {
		return basicBaseNetworkLogMapper.getBasicBaseNetworkLogById(id);
	}

	public List<BasicBaseNetworkLog> getBasicBaseNetworkLogDAC(Map<String, Object> params, HttpServletRequest request) {
		strategyCodeToParams(params, request);
		return basicBaseNetworkLogMapper.getBasicBaseNetworkLogDAC(params);
	}

	public Long getBasicBaseNetworkLogDACCount(Map<String, Object> params, HttpServletRequest request) {

		String strategyCode = (String) params.get("strategyCode");
		if (StringUtil.isEmpty(strategyCode))
			strategyCode = PermissionConstant.COMMON_DATA_ACCESS_PRIVATE;// By default only visible by owner
		switch (strategyCode) {
			case PermissionConstant.COMMON_DATA_ACCESS_PRIVATE:// Visible to owner with special sharing rules
				Map<String, List<String>> sharingOrgCodes0 = extractedSharingOrgCodes(request);
				params.put("selfOrgCode", TokenUtils.extractSelfOrgCode(request));
				params.putAll(sharingOrgCodes0);
				break;
			case PermissionConstant.COMMON_DATA_ACCESS_ALL:// All visible to all without special sharing rules
				break;
			case PermissionConstant.COMMON_DATA_ACCESS_ORG:// Visible to organizations of same or upper level
				Map<String, List<String>> sharingOrgCodes3 = extractedSharingOrgCodes(request);
				params.put("orgCode", TokenUtils.extractOrgCode(request));
				params.putAll(sharingOrgCodes3);
				break;
			case PermissionConstant.COMMON_DATA_ACCESS_LEVEL:// Visible to organizations of same level with special
																// sharing rules
				Map<String, List<String>> sharingOrgCodes2 = extractedSharingOrgCodes(request);
				params.put("orgCode", TokenUtils.extractOrgCode(request));
				params.putAll(sharingOrgCodes2);
				break;
			default:
				break;
		}

		return basicBaseNetworkLogMapper.getBasicBaseNetworkLogDACCount(params);
	}

}
