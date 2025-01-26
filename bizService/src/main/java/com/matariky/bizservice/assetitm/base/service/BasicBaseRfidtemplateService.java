package com.matariky.bizservice.assetitm.base.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidtemplate;
import com.matariky.bizservice.assetitm.base.mapper.BasicBaseRfidtemplateMapper;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.util.StringUtil;

import com.matariky.constant.PermissionConstant;

import com.matariky.utils.TokenUtils;

import com.matariky.commonservice.commondict.service.CommonDictService;

import com.matariky.commonservice.commondict.service.CommonDictTypeService;

import com.github.pagehelper.PageHelper;

import com.matariky.commonservice.commondict.bean.CommonDict;

import com.matariky.commonservice.commondict.bean.CommonDictType;

/**
 * Business Inteface Implementation
 * 
 * @author AUTOMATION
 */
@Service
public class BasicBaseRfidtemplateService extends BaseServiceImpl<BasicBaseRfidtemplateMapper, BasicBaseRfidtemplate>
		implements BaseService<BasicBaseRfidtemplate> {

	@Autowired
	private BasicBaseRfidtemplateMapper basicBaseRfidtemplateMapper;

	@Autowired

	private HttpServletRequest request;

	@Autowired

	private CommonDictService commonDictService;

	@Autowired

	private CommonDictTypeService commonDictTypeService;

	public List<BasicBaseRfidtemplate> getBasicBaseRfidtemplateAll(BasicBaseRfidtemplate vo) {
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

		PageHelper.startPage(Integer.parseInt(request.getParameter("index")),
				Integer.parseInt(request.getParameter("perPage")));

		return basicBaseRfidtemplateMapper.getBasicBaseRfidtemplateAll(vo);
	}

	public int getBasicBaseRfidtemplateAllCount() {
		return basicBaseRfidtemplateMapper.getBasicBaseRfidtemplateAllCount();
	}

	public int createBasicBaseRfidtemplate(BasicBaseRfidtemplate bean) {
		return basicBaseRfidtemplateMapper.createBasicBaseRfidtemplate(bean);
	}

	public int createBasicBaseRfidtemplateWithOrg(BasicBaseRfidtemplate bean, HttpServletRequest request) {
		bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
		bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
		bean.setCreateTime(System.currentTimeMillis());
		bean.setCreateBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request)));
		bean.setTenantId(TokenUtils.extractTenantIdFromHttpReqeust(request));
		bean.setDeleteTime(0l);
		return basicBaseRfidtemplateMapper.createBasicBaseRfidtemplate(bean);
	}

	public int updateBasicBaseRfidtemplate(BasicBaseRfidtemplate bean) {
		return basicBaseRfidtemplateMapper.updateById(bean);
	}

	public int delBasicBaseRfidtemplateById(int id) {
		return basicBaseRfidtemplateMapper.delBasicBaseRfidtemplateById(id);
	}

	public BasicBaseRfidtemplate getBasicBaseRfidtemplateById(int id) {
		return basicBaseRfidtemplateMapper.getBasicBaseRfidtemplateById(id);
	}

	public List<BasicBaseRfidtemplate> getBasicBaseRfidtemplateDAC(Map<String, Object> params,
			HttpServletRequest request) {
		strategyCodeToParams(params, request);
		return basicBaseRfidtemplateMapper.getBasicBaseRfidtemplateDAC(params);
	}

	public Long getBasicBaseRfidtemplateDACCount(Map<String, Object> params, HttpServletRequest request) {

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

		return basicBaseRfidtemplateMapper.getBasicBaseRfidtemplateDACCount(params);
	}

}
