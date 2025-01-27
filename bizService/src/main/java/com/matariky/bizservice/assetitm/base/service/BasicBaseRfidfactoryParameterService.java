package com.matariky.bizservice.assetitm.base.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidfactoryParameter;
import com.matariky.bizservice.assetitm.base.mapper.BasicBaseRfidfactoryParameterMapper;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.util.StringUtil;

import com.matariky.constant.PermissionConstant;

import com.matariky.utils.TokenUtils;

/**
 * Business Inteface Implementation
 * 
 * @author AUTOMATION
 */
@Service
public class BasicBaseRfidfactoryParameterService
		extends BaseServiceImpl<BasicBaseRfidfactoryParameterMapper, BasicBaseRfidfactoryParameter> {

	@Autowired
	private BasicBaseRfidfactoryParameterMapper basicBaseRfidfactoryParameterMapper;

	public List<BasicBaseRfidfactoryParameter> getBasicBaseRfidfactoryParameterAll() {
		return basicBaseRfidfactoryParameterMapper.getBasicBaseRfidfactoryParameterAll();
	}

	public int getBasicBaseRfidfactoryParameterAllCount() {
		return basicBaseRfidfactoryParameterMapper.getBasicBaseRfidfactoryParameterAllCount();
	}

	public int createBasicBaseRfidfactoryParameter(BasicBaseRfidfactoryParameter bean) {
		return basicBaseRfidfactoryParameterMapper.createBasicBaseRfidfactoryParameter(bean);
	}

	public int createBasicBaseRfidfactoryParameterWithOrg(BasicBaseRfidfactoryParameter bean,
			HttpServletRequest request) {
		bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
		bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
		return basicBaseRfidfactoryParameterMapper.createBasicBaseRfidfactoryParameter(bean);
	}

	public int updateBasicBaseRfidfactoryParameter(BasicBaseRfidfactoryParameter bean) {
		return basicBaseRfidfactoryParameterMapper.updateById(bean);
	}

	public int delBasicBaseRfidfactoryParameterById(Long id) {
		return basicBaseRfidfactoryParameterMapper.delBasicBaseRfidfactoryParameterById(id);
	}

	public BasicBaseRfidfactoryParameter getBasicBaseRfidfactoryParameterById(Long id) {
		return basicBaseRfidfactoryParameterMapper.getBasicBaseRfidfactoryParameterById(id);
	}

	public List<BasicBaseRfidfactoryParameter> getBasicBaseRfidfactoryParameterDAC(Map<String, Object> params,
			HttpServletRequest request) {
		strategyCodeToParams(params, request);
		return basicBaseRfidfactoryParameterMapper.getBasicBaseRfidfactoryParameterDAC(params);
	}

	public Long getBasicBaseRfidfactoryParameterDACCount(Map<String, Object> params, HttpServletRequest request) {

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
																// sharing
																// rules
				Map<String, List<String>> sharingOrgCodes2 = extractedSharingOrgCodes(request);
				params.put("orgCode", TokenUtils.extractOrgCode(request));
				params.putAll(sharingOrgCodes2);
				break;
			default:
				break;
		}

		return basicBaseRfidfactoryParameterMapper.getBasicBaseRfidfactoryParameterDACCount(params);
	}

}
