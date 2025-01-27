package com.matariky.commonservice.base.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.commonservice.base.bean.BasicBaseTraceabilityDetail;
import com.matariky.commonservice.base.mapper.BasicBaseTraceabilityDetailMapper;

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
public class BasicBaseTraceabilityDetailService
		extends BaseServiceImpl<BasicBaseTraceabilityDetailMapper, BasicBaseTraceabilityDetail> {

	@Autowired
	private BasicBaseTraceabilityDetailMapper basicBaseTraceabilityDetailMapper;

	@Autowired

	private HttpServletRequest request;

	@Autowired

	private CommonDictService commonDictService;

	@Autowired

	private CommonDictTypeService commonDictTypeService;

	public List<BasicBaseTraceabilityDetail> getBasicBaseTraceabilityDetailAll(BasicBaseTraceabilityDetail vo) {
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

		return basicBaseTraceabilityDetailMapper.getBasicBaseTraceabilityDetailAll(vo);
	}

	public int getBasicBaseTraceabilityDetailAllCount() {
		return basicBaseTraceabilityDetailMapper.getBasicBaseTraceabilityDetailAllCount();
	}

	public int createBasicBaseTraceabilityDetail(BasicBaseTraceabilityDetail bean) {
		return basicBaseTraceabilityDetailMapper.createBasicBaseTraceabilityDetail(bean);
	}

	public int createBasicBaseTraceabilityDetailWithOrg(BasicBaseTraceabilityDetail bean, HttpServletRequest request) {
		bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
		bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
		bean.setCreateTime(System.currentTimeMillis());
		bean.setCreateBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request)));
		bean.setTenantId(TokenUtils.extractTenantIdFromHttpReqeust(request));
		bean.setDeleteTime(0l);
		return basicBaseTraceabilityDetailMapper.createBasicBaseTraceabilityDetail(bean);
	}

	public int updateBasicBaseTraceabilityDetail(BasicBaseTraceabilityDetail bean) {
		return basicBaseTraceabilityDetailMapper.updateById(bean);
	}

	public int delBasicBaseTraceabilityDetailById(int id) {
		return basicBaseTraceabilityDetailMapper.delBasicBaseTraceabilityDetailById(id);
	}

	public BasicBaseTraceabilityDetail getBasicBaseTraceabilityDetailById(int id) {
		return basicBaseTraceabilityDetailMapper.getBasicBaseTraceabilityDetailById(id);
	}

	public List<BasicBaseTraceabilityDetail> getBasicBaseTraceabilityDetailDAC(Map<String, Object> params,
			HttpServletRequest request) {
		strategyCodeToParams(params, request);
		return basicBaseTraceabilityDetailMapper.getBasicBaseTraceabilityDetailDAC(params);
	}

	public Long getBasicBaseTraceabilityDetailDACCount(Map<String, Object> params, HttpServletRequest request) {

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

		return basicBaseTraceabilityDetailMapper.getBasicBaseTraceabilityDetailDACCount(params);
	}

}
