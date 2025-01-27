package com.matariky.commonservice.printlog.service;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.github.pagehelper.util.StringUtil;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.printlog.bean.BasicBasePrintLog;
import com.matariky.commonservice.printlog.excel.PrintLogExcelVO;
import com.matariky.commonservice.printlog.mapper.BasicBasePrintLogMapper;
import com.matariky.constant.PermissionConstant;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.utils.BeanUtils;
import com.matariky.utils.IpUtils;
import com.matariky.utils.StringUtils;
import com.matariky.utils.TokenUtils;

/**
 * Business Inteface Implementation
 * 
 * @author AUTOMATION
 */
@Service
public class BasicBasePrintLogService extends BaseServiceImpl<BasicBasePrintLogMapper, BasicBasePrintLog> {

	@Autowired
	private BasicBasePrintLogMapper basicBasePrintLogMapper;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private CommonDictTypeService commonDictTypeService;
	@Autowired
	private CommonDictService commonDictService;

	public List<BasicBasePrintLog> getBasicBasePrintLogAll(BasicBasePrintLog vo) {
		if (StringUtils.isNotBlank(vo.getBusinessTimeEnd())) {
			Long endTime = Long.valueOf(vo.getBusinessTimeEnd()) + 24 * 60 * 60 * 1000;
			vo.setBusinessTimeEnd(endTime.toString());
		}

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
		return basicBasePrintLogMapper.getBasicBasePrintLogAll(vo);
	}

	/**
	 * Export
	 * 
	 * @param bean
	 */
	public void export(BasicBasePrintLog bean) {
		List<BasicBasePrintLog> list = basicBasePrintLogMapper.getBasicBasePrintLogAll(bean);
		List<PrintLogExcelVO> newList = list.stream().map(item -> {
			PrintLogExcelVO vo = new PrintLogExcelVO();
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
			EasyExcel.write(outputStream, PrintLogExcelVO.class)
					.excelType(ExcelTypeEnum.XLSX)
					.sheet(" Print  Log ")
					.doWrite(newList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getBasicBasePrintLogAllCount() {
		return basicBasePrintLogMapper.getBasicBasePrintLogAllCount();
	}

	public int createBasicBasePrintLog(BasicBasePrintLog bean) {
		return basicBasePrintLogMapper.createBasicBasePrintLog(bean);
	}

	public int createBasicBasePrintLogWithOrg(List<BasicBasePrintLog> beanList, HttpServletRequest request) {
		for (BasicBasePrintLog bean : beanList) {
			bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
			bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
			bean.setCreateTime(System.currentTimeMillis());
			bean.setCreateBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request)));
			bean.setTenantId(TokenUtils.extractTenantIdFromHttpReqeust(request));
			bean.setDeleteTime(0L);
			bean.setAccessAccount(TokenUtils.extractLoginNameFromHttpRequest(request));
			bean.setServerIp(IpUtils.getIpAddr(request));
			basicBasePrintLogMapper.createBasicBasePrintLog(bean);
		}
		return 1;
	}

	public int updateBasicBasePrintLog(BasicBasePrintLog bean) {
		return basicBasePrintLogMapper.updateById(bean);
	}

	public int delBasicBasePrintLogById(int id) {
		return basicBasePrintLogMapper.delBasicBasePrintLogById(id);
	}

	public BasicBasePrintLog getBasicBasePrintLogById(int id) {
		return basicBasePrintLogMapper.getBasicBasePrintLogById(id);
	}

	public List<BasicBasePrintLog> getBasicBasePrintLogDAC(Map<String, Object> params, HttpServletRequest request) {
		strategyCodeToParams(params, request);
		return basicBasePrintLogMapper.getBasicBasePrintLogDAC(params);
	}

	public Long getBasicBasePrintLogDACCount(Map<String, Object> params, HttpServletRequest request) {

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

		return basicBasePrintLogMapper.getBasicBasePrintLogDACCount(params);
	}

}
