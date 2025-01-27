package com.matariky.bizservice.assetitm.base.service;

import java.util.*;

import com.github.pagehelper.PageHelper;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidPrint;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidprintDetail;
import com.matariky.bizservice.assetitm.base.mapper.BasicBaseRfidPrintMapper;
import com.matariky.bizservice.assetitm.base.mapper.BasicBaseRfidprintDetailMapper;
import com.matariky.exception.QslException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matariky.iservice.impl.BaseServiceImpl;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.util.StringUtil;

import com.matariky.constant.PermissionConstant;

import com.matariky.utils.TokenUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * Business Inteface Implementation
 * 
 * @author AUTOMATION
 */
@Service
public class BasicBaseRfidprintDetailService
		extends BaseServiceImpl<BasicBaseRfidprintDetailMapper, BasicBaseRfidprintDetail> {

	@Autowired
	private BasicBaseRfidprintDetailMapper basicBaseRfidprintDetailMapper;

	@Autowired
	private BasicBaseRfidPrintMapper basicBaseRfidprintMapper;

	public List<BasicBaseRfidprintDetail> getBasicBaseRfidprintDetailAll(BasicBaseRfidprintDetail bean,
			Integer pageIndex, Integer perPage) {
		PageHelper.startPage(pageIndex, perPage);
		return basicBaseRfidprintDetailMapper.getBasicBaseRfidprintDetailAll(bean);
	}

	public int getBasicBaseRfidprintDetailAllCount() {
		return basicBaseRfidprintDetailMapper.getBasicBaseRfidprintDetailAllCount();
	}

	public int createBasicBaseRfidprintDetail(BasicBaseRfidprintDetail bean) {
		return basicBaseRfidprintDetailMapper.createBasicBaseRfidprintDetail(bean);
	}

	public int createBasicBaseRfidprintDetailWithOrg(BasicBaseRfidprintDetail bean, HttpServletRequest request) {
		bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
		bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
		return basicBaseRfidprintDetailMapper.createBasicBaseRfidprintDetail(bean);
	}

	@Transactional(rollbackFor = Exception.class)
	public int updateBasicBaseRfidprintDetail(BasicBaseRfidprintDetail bean) {

		if (bean == null || (bean != null && bean.getTid() == null)) {
			throw new QslException(" Submit  Data Failed！");
		}

		BasicBaseRfidPrint basicBaseRfidprint = new BasicBaseRfidPrint();
		basicBaseRfidprint = basicBaseRfidprintMapper.getBasicBaseRfidprintById(bean.getPrintId());
		basicBaseRfidprint.setPrintedNum(basicBaseRfidprint.getPrintedNum() + 1);
		basicBaseRfidprint.setUnprintNum(basicBaseRfidprint.getUnprintNum() - 1);
		if (basicBaseRfidprint.getUnprintNum() > 0) {
			basicBaseRfidprint.setPrintStatus(1);
		} else {
			basicBaseRfidprint.setPrintStatus(2);
		}
		basicBaseRfidprintMapper.updateBasicBaseRfidprintById(basicBaseRfidprint);

		bean.setPrintTime(Calendar.getInstance().getTimeInMillis());
		bean.setUpdateTime(Calendar.getInstance().getTimeInMillis());

		int result = basicBaseRfidprintDetailMapper.updateBasicBaseRfidprintDetail(bean);
		if (result == 0) {
			throw new QslException("Update Data Failed！");
		}

		return result;
	}

	public int delBasicBaseRfidprintDetailById(int id) {
		return basicBaseRfidprintDetailMapper.delBasicBaseRfidprintDetailById(id);
	}

	public BasicBaseRfidprintDetail getBasicBaseRfidprintDetailById(Long id) {
		return basicBaseRfidprintDetailMapper.getBasicBaseRfidprintDetailById(id);
	}

	public List<BasicBaseRfidprintDetail> getBasicBaseRfidprintDetailDAC(Map<String, Object> params,
			HttpServletRequest request) {
		strategyCodeToParams(params, request);
		return basicBaseRfidprintDetailMapper.getBasicBaseRfidprintDetailDAC(params);
	}

	public Long getBasicBaseRfidprintDetailDACCount(Map<String, Object> params, HttpServletRequest request) {

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

		return basicBaseRfidprintDetailMapper.getBasicBaseRfidprintDetailDACCount(params);
	}

	public List<BasicBaseRfidprintDetail> getBasicBaseRfidprintDetailAllById(Long id) {
		return basicBaseRfidprintDetailMapper.getBasicBaseRfidprintDetailAllById(id);
	}

	public List<BasicBaseRfidprintDetail> getBasicBaseRfidprintDetailUnprintById(Long id) {
		return basicBaseRfidprintDetailMapper.getBasicBaseRfidprintDetailUnprintById(id);
	}

}
