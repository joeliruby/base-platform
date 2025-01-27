package com.matariky.commonservice.base.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.commonservice.base.bean.BasicBaseRfidInfo;
import com.matariky.commonservice.base.mapper.BasicBaseRfidInfoMapper;

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
public class BasicBaseRfidInfoService extends BaseServiceImpl<BasicBaseRfidInfoMapper, BasicBaseRfidInfo> {

	@Autowired
	private BasicBaseRfidInfoMapper basicBaseRfidInfoMapper;

	public List<BasicBaseRfidInfo> getBasicBaseRfidInfoAll(Integer pageIndex, Integer perPage) {
		PageHelper.startPage(pageIndex, perPage);
		return basicBaseRfidInfoMapper.getBasicBaseRfidInfoAll();
	}

	public int getBasicBaseRfidInfoAllCount() {
		return basicBaseRfidInfoMapper.getBasicBaseRfidInfoAllCount();
	}

	public int createBasicBaseRfidInfo(BasicBaseRfidInfo bean) {
		return basicBaseRfidInfoMapper.createBasicBaseRfidInfo(bean);
	}

	public int createBasicBaseRfidInfoWithOrg(BasicBaseRfidInfo bean, HttpServletRequest request) {
		bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
		bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
		return basicBaseRfidInfoMapper.createBasicBaseRfidInfo(bean);
	}

	public int updateBasicBaseRfidInfo(BasicBaseRfidInfo bean) {
		return basicBaseRfidInfoMapper.updateById(bean);
	}

	public int delBasicBaseRfidInfoById(int id) {
		return basicBaseRfidInfoMapper.delBasicBaseRfidInfoById(id);
	}

	public BasicBaseRfidInfo getBasicBaseRfidInfoById(int id) {
		return basicBaseRfidInfoMapper.getBasicBaseRfidInfoById(id);
	}

	public List<BasicBaseRfidInfo> getBasicBaseRfidInfoDAC(Map<String, Object> params, HttpServletRequest request) {
		strategyCodeToParams(params, request);
		return basicBaseRfidInfoMapper.getBasicBaseRfidInfoDAC(params);
	}

	public Long getBasicBaseRfidInfoDACCount(Map<String, Object> params, HttpServletRequest request) {

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

		return basicBaseRfidInfoMapper.getBasicBaseRfidInfoDACCount(params);
	}

}
