package com.matariky.commonservice.base.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.commonservice.base.bean.BasicBaseDevicecommand;
import com.matariky.commonservice.base.mapper.BasicBaseDevicecommandMapper;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.util.StringUtil;

import com.matariky.constant.PermissionConstant;

import com.matariky.utils.TokenUtils;

import com.matariky.commonservice.commondict.service.CommonDictService;

import com.matariky.commonservice.commondict.service.CommonDictTypeService;

import com.matariky.commonservice.commondict.bean.CommonDict;

import com.matariky.commonservice.commondict.bean.CommonDictType;

/**
 * Business Inteface Implementation
 * 
 * @author AUTOMATION
 */
@Service
public class BasicBaseDevicecommandService
		extends BaseServiceImpl<BasicBaseDevicecommandMapper, BasicBaseDevicecommand> {

	@Autowired
	private BasicBaseDevicecommandMapper basicBaseDevicecommandMapper;

	@Autowired

	private HttpServletRequest request;

	@Autowired

	private CommonDictService commonDictService;

	@Autowired

	private CommonDictTypeService commonDictTypeService;

	public List<BasicBaseDevicecommand> getBasicBaseDevicecommandAll(BasicBaseDevicecommand vo) {
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
		vo.setSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
		vo.setTenantId(tenantId);
		return basicBaseDevicecommandMapper.getBasicBaseDevicecommandAll(vo);
	}

	public int getBasicBaseDevicecommandAllCount() {
		return basicBaseDevicecommandMapper.getBasicBaseDevicecommandAllCount();
	}

	public int createBasicBaseDevicecommand(BasicBaseDevicecommand bean) {
		return basicBaseDevicecommandMapper.createBasicBaseDevicecommand(bean);
	}

	public int createBasicBaseDevicecommandWithOrg(BasicBaseDevicecommand bean, HttpServletRequest request) {
		bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
		bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
		bean.setCreateTime(System.currentTimeMillis());
		bean.setCreateBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request)));
		bean.setTenantId(TokenUtils.extractTenantIdFromHttpReqeust(request));
		bean.setDeleteTime(0l);
		return basicBaseDevicecommandMapper.createBasicBaseDevicecommand(bean);
	}

	public int updateBasicBaseDevicecommand(BasicBaseDevicecommand bean) {
		return basicBaseDevicecommandMapper.updateById(bean);
	}

	public int delBasicBaseDevicecommandById(int id) {
		return basicBaseDevicecommandMapper.delBasicBaseDevicecommandById(id);
	}

	public BasicBaseDevicecommand getBasicBaseDevicecommandById(int id) {
		return basicBaseDevicecommandMapper.getBasicBaseDevicecommandById(id);
	}

	public List<BasicBaseDevicecommand> getBasicBaseDevicecommandDAC(Map<String, Object> params,
			HttpServletRequest request) {
		strategyCodeToParams(params, request);
		return basicBaseDevicecommandMapper.getBasicBaseDevicecommandDAC(params);
	}

	public Long getBasicBaseDevicecommandDACCount(Map<String, Object> params, HttpServletRequest request) {

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

		return basicBaseDevicecommandMapper.getBasicBaseDevicecommandDACCount(params);
	}

}
