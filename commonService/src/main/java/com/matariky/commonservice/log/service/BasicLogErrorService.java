package com.matariky.commonservice.log.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.commonservice.log.bean.BasicLogError;
import com.matariky.commonservice.log.mapper.BasicLogErrorMapper;

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
public class BasicLogErrorService extends BaseServiceImpl<BasicLogErrorMapper, BasicLogError>
		implements BaseService<BasicLogError> {

	@Autowired
	private BasicLogErrorMapper basicLogErrorMapper;

	public List<BasicLogError> getBasicLogErrorAll() {
		return basicLogErrorMapper.getBasicLogErrorAll();
	}

	public int getBasicLogErrorAllCount() {
		return basicLogErrorMapper.getBasicLogErrorAllCount();
	}

	public int createBasicLogError(BasicLogError bean) {
		return basicLogErrorMapper.createBasicLogError(bean);
	}

	public int createBasicLogErrorWithOrg(BasicLogError bean, HttpServletRequest request) {
		bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
		bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
		return basicLogErrorMapper.createBasicLogError(bean);
	}

	public int updateBasicLogError(BasicLogError bean) {
		return basicLogErrorMapper.updateById(bean);
	}

	public int delBasicLogErrorById(int id) {
		return basicLogErrorMapper.delBasicLogErrorById(id);
	}

	public BasicLogError getBasicLogErrorById(int id) {
		return basicLogErrorMapper.getBasicLogErrorById(id);
	}

	public List<BasicLogError> getBasicLogErrorDAC(Map<String, Object> params, HttpServletRequest request) {
		strategyCodeToParams(params, request);
		return basicLogErrorMapper.getBasicLogErrorDAC(params);
	}

	public Long getBasicLogErrorDACCount(Map<String, Object> params, HttpServletRequest request) {

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

		return basicLogErrorMapper.getBasicLogErrorDACCount(params);
	}

}
