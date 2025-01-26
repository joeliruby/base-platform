package com.matariky.bizservice.assetitm.base.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidprintParameter;
import com.matariky.bizservice.assetitm.base.mapper.BasicBaseRfidprintParameterMapper;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class BasicBaseRfidprintParameterService
		extends BaseServiceImpl<BasicBaseRfidprintParameterMapper, BasicBaseRfidprintParameter>
		implements BaseService<BasicBaseRfidprintParameter> {

	@Autowired
	private BasicBaseRfidprintParameterMapper basicBaseRfidprintParameterMapper;

	public List<BasicBaseRfidprintParameter> getBasicBaseRfidprintParameterAll(Integer pageIndex, Integer perPage) {
		PageHelper.startPage(pageIndex, perPage);
		return basicBaseRfidprintParameterMapper.getBasicBaseRfidprintParameterAll();
	}

	public int getBasicBaseRfidprintParameterAllCount() {
		return basicBaseRfidprintParameterMapper.getBasicBaseRfidprintParameterAllCount();
	}

	public int createBasicBaseRfidprintParameter(BasicBaseRfidprintParameter bean) {
		return basicBaseRfidprintParameterMapper.createBasicBaseRfidprintParameter(bean);
	}

	public int createBasicBaseRfidprintParameterWithOrg(BasicBaseRfidprintParameter bean, HttpServletRequest request) {
		bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
		bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
		return basicBaseRfidprintParameterMapper.createBasicBaseRfidprintParameter(bean);
	}

	public int updateBasicBaseRfidprintParameter(BasicBaseRfidprintParameter bean) {
		return basicBaseRfidprintParameterMapper.updateById(bean);
	}

	public int delBasicBaseRfidprintParameterById(int id) {
		return basicBaseRfidprintParameterMapper.delBasicBaseRfidprintParameterById(id);
	}

	public BasicBaseRfidprintParameter getBasicBaseRfidprintParameterById(int id) {
		return basicBaseRfidprintParameterMapper.getBasicBaseRfidprintParameterById(id);
	}

	public List<BasicBaseRfidprintParameter> getBasicBaseRfidprintParameterDAC(Map<String, Object> params,
			HttpServletRequest request) {
		strategyCodeToParams(params, request);
		return basicBaseRfidprintParameterMapper.getBasicBaseRfidprintParameterDAC(params);
	}

	public Long getBasicBaseRfidprintParameterDACCount(Map<String, Object> params, HttpServletRequest request) {

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
		case PermissionConstant.COMMON_DATA_ACCESS_LEVEL:// Visible to organizations of same level with special sharing
															// rules
			Map<String, List<String>> sharingOrgCodes2 = extractedSharingOrgCodes(request);
			params.put("orgCode", TokenUtils.extractOrgCode(request));
			params.putAll(sharingOrgCodes2);
			break;
		default:
			break;
		}

		return basicBaseRfidprintParameterMapper.getBasicBaseRfidprintParameterDACCount(params);
	}

}
