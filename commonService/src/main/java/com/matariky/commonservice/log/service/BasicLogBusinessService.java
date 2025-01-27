package com.matariky.commonservice.log.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.commonservice.log.bean.BasicLogBusiness;
import com.matariky.commonservice.log.mapper.BasicLogBusinessMapper;

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
public class BasicLogBusinessService extends BaseServiceImpl<BasicLogBusinessMapper, BasicLogBusiness> {

	@Autowired
	private BasicLogBusinessMapper basicLogBusinessMapper;

	public List<BasicLogBusiness> getBasicLogBusinessAll() {
		return basicLogBusinessMapper.getBasicLogBusinessAll();
	}

	public int getBasicLogBusinessAllCount() {
		return basicLogBusinessMapper.getBasicLogBusinessAllCount();
	}

	public int createBasicLogBusiness(BasicLogBusiness bean) {
		return basicLogBusinessMapper.createBasicLogBusiness(bean);
	}

	public int createBasicLogBusinessWithOrg(BasicLogBusiness bean, HttpServletRequest request) {
		bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
		bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
		return basicLogBusinessMapper.createBasicLogBusiness(bean);
	}

	public int updateBasicLogBusiness(BasicLogBusiness bean) {
		return basicLogBusinessMapper.updateById(bean);
	}

	public int delBasicLogBusinessById(int id) {
		return basicLogBusinessMapper.delBasicLogBusinessById(id);
	}

	public BasicLogBusiness getBasicLogBusinessById(int id) {
		return basicLogBusinessMapper.getBasicLogBusinessById(id);
	}

	public List<BasicLogBusiness> getBasicLogBusinessDAC(Map<String, Object> params, HttpServletRequest request) {
		strategyCodeToParams(params, request);
		return basicLogBusinessMapper.getBasicLogBusinessDAC(params);
	}

	public Long getBasicLogBusinessDACCount(Map<String, Object> params, HttpServletRequest request) {

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

		return basicLogBusinessMapper.getBasicLogBusinessDACCount(params);
	}

}
