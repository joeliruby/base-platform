package com.matariky.commonservice.base.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.commonservice.base.bean.BasicBaseCreaterfidPrint;
import com.matariky.commonservice.base.mapper.BasicBaseCreaterfidPrintMapper;

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
public class BasicBaseCreaterfidPrintService
		extends BaseServiceImpl<BasicBaseCreaterfidPrintMapper, BasicBaseCreaterfidPrint> {

	@Autowired
	private BasicBaseCreaterfidPrintMapper basicBaseCreaterfidPrintMapper;

	public List<BasicBaseCreaterfidPrint> getBasicBaseCreaterfidPrintAll(Integer pageIndex, Integer perPage) {
		PageHelper.startPage(pageIndex, perPage);
		return basicBaseCreaterfidPrintMapper.getBasicBaseCreaterfidPrintAll();
	}

	public int getBasicBaseCreaterfidPrintAllCount() {
		return basicBaseCreaterfidPrintMapper.getBasicBaseCreaterfidPrintAllCount();
	}

	public int createBasicBaseCreaterfidPrint(BasicBaseCreaterfidPrint bean) {
		return basicBaseCreaterfidPrintMapper.createBasicBaseCreaterfidPrint(bean);
	}

	public int createBasicBaseCreaterfidPrintWithOrg(BasicBaseCreaterfidPrint bean, HttpServletRequest request) {
		bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
		bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
		return basicBaseCreaterfidPrintMapper.createBasicBaseCreaterfidPrint(bean);
	}

	public int updateBasicBaseCreaterfidPrint(BasicBaseCreaterfidPrint bean) {
		return basicBaseCreaterfidPrintMapper.updateById(bean);
	}

	public int delBasicBaseCreaterfidPrintById(int id) {
		return basicBaseCreaterfidPrintMapper.delBasicBaseCreaterfidPrintById(id);
	}

	public BasicBaseCreaterfidPrint getBasicBaseCreaterfidPrintById(int id) {
		return basicBaseCreaterfidPrintMapper.getBasicBaseCreaterfidPrintById(id);
	}

	public List<BasicBaseCreaterfidPrint> getBasicBaseCreaterfidPrintDAC(Map<String, Object> params,
			HttpServletRequest request) {
		strategyCodeToParams(params, request);
		return basicBaseCreaterfidPrintMapper.getBasicBaseCreaterfidPrintDAC(params);
	}

	public Long getBasicBaseCreaterfidPrintDACCount(Map<String, Object> params, HttpServletRequest request) {

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

		return basicBaseCreaterfidPrintMapper.getBasicBaseCreaterfidPrintDACCount(params);
	}

}
