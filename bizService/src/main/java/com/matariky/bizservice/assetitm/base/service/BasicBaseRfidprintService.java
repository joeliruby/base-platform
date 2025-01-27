package com.matariky.bizservice.assetitm.base.service;

import java.util.*;

import com.github.pagehelper.PageHelper;
import com.matariky.bizservice.job.JobApiService;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidPrint;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidprintDetail;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidprintParameter;
import com.matariky.bizservice.assetitm.base.mapper.BasicBaseRfidPrintMapper;
import com.matariky.bizservice.assetitm.base.mapper.BasicBaseRfidprintDetailMapper;
import com.matariky.bizservice.assetitm.base.mapper.BasicBaseRfidprintParameterMapper;
import com.matariky.bizservice.assetitm.base.vo.BasicBaseRfidprintAddVO;
import com.matariky.commonservice.base.bean.*;
import com.matariky.commonservice.base.mapper.*;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.exception.QslException;
import com.matariky.jobs.jobsService.bean.form.RfidPrintJobForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;

import java.util.stream.Collectors;

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
public class BasicBaseRfidprintService extends BaseServiceImpl<BasicBaseRfidPrintMapper, BasicBaseRfidPrint>
		implements BaseService<BasicBaseRfidPrint> {

	@Autowired
	private BasicBaseRfidPrintMapper basicBaseRfidprintMapper;

	@Autowired
	private BasicBaseRfidprintParameterMapper basicBaseRfidprintParameterMapper;

	@Autowired
	private BasicBaseRfidInfoMapper basicBaseRfidInfoMapper;

	@Autowired
	private BasicBaseCreaterfidPrintMapper basicBaseCreaterfidPrintMapper;

	@Autowired
	private CommonDictService commonDictService;

	@Autowired
	private CommonDictTypeService commonDictTypeService;

	@Autowired
	private BasicBaseRfidprintDetailMapper basicBaseRfidprintDetailMapper;

	@Autowired
	private BasicBaseDeviceMapper basicBaseDeviceMapper;

	@Autowired
	private JobApiService jobApiService;

	public List<BasicBaseRfidPrint> getBasicBaseRfidprintAll(BasicBaseRfidPrint bean, String tenantId,
			HttpServletRequest request, Integer pageIndex, Integer perPage) {
		String hid = request.getHeader("id");
		String resourceIdDictKey = "dp" + hid.substring(0, hid.length() - 1);
		CommonDictType commonDictType = commonDictTypeService.getDictTypeByKey(
				TokenUtils.extractTenantIdFromHttpReqeust(request), PermissionConstant.DATA_ACCESS_PERMISSION);
		CommonDict dict = commonDictService.getCommonDictByIdTenantIdAndDictType(resourceIdDictKey, tenantId,
				commonDictType.getId());
		if (dict == null) {
			bean.setStrategyCode(PermissionConstant.COMMON_DATA_ACCESS_ALL);
		} else {
			bean.setStrategyCode(dict.getDictValue());
		}
		bean.setSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
		bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
		bean.setTenantId(tenantId);
		bean.setOrgCode(TokenUtils.extractOrgCode(request));
		PageHelper.startPage(pageIndex, perPage);
		return basicBaseRfidprintMapper.getBasicBaseRfidprintAll(bean);
	}

	public List<BasicBaseRfidPrint> getBasicBaseRfidprintAppAll(BasicBaseRfidPrint bean, String deviceCode,
			String tenantId, HttpServletRequest request, Integer pageIndex, Integer perPage) {
		PageHelper.startPage(pageIndex, perPage);

		if (StringUtil.isNotEmpty(deviceCode)) {
			BasicBaseDevice basicBaseDevice = basicBaseDeviceMapper.selectPrintByCode(deviceCode);
			if (basicBaseDevice != null) {
				bean.setDeviceId(basicBaseDevice.getId());
			}
		}

		return basicBaseRfidprintMapper.getBasicBaseRfidprintAll(bean);
	}

	public int getBasicBaseRfidprintAllCount() {
		return basicBaseRfidprintMapper.getBasicBaseRfidprintAllCount();
	}

	public int createBasicBaseRfidprint(BasicBaseRfidPrint bean) {
		return basicBaseRfidprintMapper.createBasicBaseRfidprint(bean);
	}

	public int createBasicBaseRfidprintWithOrg(BasicBaseRfidprintAddVO bean, HttpServletRequest request) {
		bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
		bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
		bean.setTaskTime(Calendar.getInstance().getTimeInMillis());
		bean.setDeleteTime(0L);
		bean.setCreateTime(Calendar.getInstance().getTimeInMillis());
		bean.setUpdateTime(Calendar.getInstance().getTimeInMillis());

		BasicBaseRfidPrint basicBaseRfidprint = new BasicBaseRfidPrint();
		BeanUtils.copyProperties(bean, basicBaseRfidprint);
		basicBaseRfidprint.setUnprintNum(basicBaseRfidprint.getPrintNum());
		basicBaseRfidprint.setPrintedNum(0);
		basicBaseRfidprint.setPrintType("1");
		basicBaseRfidprint.setPrintStatus(0);

		if (basicBaseRfidprint.getDeviceId() != null) {
			basicBaseRfidprint.setIsLock(1);
		} else {
			basicBaseRfidprint.setIsLock(0);
		}

		int result = basicBaseRfidprintMapper.createBasicBaseRfidprint(basicBaseRfidprint);

		if (bean.getBaseRfidprintParameterAddVOS() != null && bean.getBaseRfidprintParameterAddVOS().size() > 0) {
			List<BasicBaseRfidprintParameter> basicBaseRfidprintParameterList = bean.getBaseRfidprintParameterAddVOS()
					.stream().map(basicBaseRfidprintParameterAddVO -> {
						BasicBaseRfidprintParameter baseRfidprintParameter = new BasicBaseRfidprintParameter();
						BeanUtils.copyProperties(basicBaseRfidprintParameterAddVO, baseRfidprintParameter);
						baseRfidprintParameter.setPrintId(basicBaseRfidprint.getId());
						baseRfidprintParameter.setCreateBy(basicBaseRfidprint.getCreateBy());
						baseRfidprintParameter.setUpdateBy(basicBaseRfidprint.getUpdateBy());
						baseRfidprintParameter.setDeleteTime(basicBaseRfidprint.getDeleteTime());
						baseRfidprintParameter.setCreateTime(basicBaseRfidprint.getCreateTime());
						baseRfidprintParameter.setUpdateTime(basicBaseRfidprint.getUpdateTime());
						return baseRfidprintParameter;
					}).collect(Collectors.toList());
			/** Insert Parameter Information **/
			for (BasicBaseRfidprintParameter basicBaseRfidprintParameter : basicBaseRfidprintParameterList) {
				basicBaseRfidprintParameterMapper.insert(basicBaseRfidprintParameter);
			}
		}

		RfidPrintJobForm jobForm = new RfidPrintJobForm();
		jobForm.setTaskId(basicBaseRfidprint.getId());
		jobForm.setTaskType(1);
		jobForm.setStartTime(Calendar.getInstance().getTimeInMillis());

		String sourceId = request.getHeader("id");
		String token = TokenUtils.getTokenFromRequest(request);
		/** Add Timer Task **/
		jobApiService.addRfidPrintJob(jobForm, basicBaseRfidprint.getTenantId(), sourceId, token);

		return result;
	}

	public int updateBasicBaseRfidprint(BasicBaseRfidPrint bean) {
		return basicBaseRfidprintMapper.updateById(bean);
	}

	public int delBasicBaseRfidprintById(int id) {
		return basicBaseRfidprintMapper.delBasicBaseRfidprintById(id);
	}

	public BasicBaseRfidPrint getBasicBaseRfidprintById(Long id) {
		return basicBaseRfidprintMapper.getBasicBaseRfidprintById(id);
	}

	public List<BasicBaseRfidPrint> getBasicBaseRfidprintDAC(Map<String, Object> params, HttpServletRequest request) {
		strategyCodeToParams(params, request);
		return basicBaseRfidprintMapper.getBasicBaseRfidprintDAC(params);
	}

	public Long getBasicBaseRfidprintDACCount(Map<String, Object> params, HttpServletRequest request) {

		String strategyCode = (String) params.get("strategyCode");
		if (StringUtil.isEmpty(strategyCode)) {
			strategyCode = PermissionConstant.COMMON_DATA_ACCESS_PRIVATE;// By default only visible by owner
		}
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

		return basicBaseRfidprintMapper.getBasicBaseRfidprintDACCount(params);
	}

	// 补打 Method
	@Transactional(rollbackFor = Exception.class)
	public int createBasicBaseRfidSuppprintWithOrg(Long id, String jwt) {
		BasicBaseRfidprintDetail basicBaseRfidprintDetail = basicBaseRfidprintDetailMapper
				.getBasicBaseRfidprintDetailById(id);
		BasicBaseRfidPrint basicBaseRfidprint = basicBaseRfidprintMapper
				.getBasicBaseRfidprintById(basicBaseRfidprintDetail.getPrintId());

		basicBaseRfidprint.setTaskName(basicBaseRfidprint.getTaskName() + "（补打）");
		basicBaseRfidprint.setPrintType("0");
		basicBaseRfidprint.setTaskTime(Calendar.getInstance().getTimeInMillis());
		basicBaseRfidprint.setDeleteTime(0L);
		basicBaseRfidprint.setCreateBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
		basicBaseRfidprint.setUpdateBy(Long.parseLong(TokenUtils.extractUserIdFromToken(jwt)));
		basicBaseRfidprint.setCreateTime(Calendar.getInstance().getTimeInMillis());
		basicBaseRfidprint.setUpdateTime(Calendar.getInstance().getTimeInMillis());
		basicBaseRfidprint.setPrintNum(1);
		basicBaseRfidprint.setUnprintNum(1);
		basicBaseRfidprint.setPrintedNum(0);
		basicBaseRfidprint.setId(null);
		basicBaseRfidprint.setPrintStatus(0);
		basicBaseRfidprint.setIsLock(0);

		int result = basicBaseRfidprintMapper.createBasicBaseRfidprint(basicBaseRfidprint);

		BasicBaseRfidInfo basicBaseRfidInfo = new BasicBaseRfidInfo();
		basicBaseRfidInfo = basicBaseRfidInfoMapper.selectById(basicBaseRfidprintDetail.getRfidId());

		BasicBaseRfidInfo baseRfidInfo = new BasicBaseRfidInfo();

		baseRfidInfo.setEpc(basicBaseRfidprintDetail.getEpc());
		baseRfidInfo.setPassword(basicBaseRfidInfo.getPassword());
		baseRfidInfo.setOdContent(basicBaseRfidInfo.getOdContent());
		baseRfidInfo.setQrContent(basicBaseRfidInfo.getQrContent());
		baseRfidInfo.setCreateBy(basicBaseRfidprint.getCreateBy());
		baseRfidInfo.setUpdateBy(basicBaseRfidprint.getUpdateBy());
		baseRfidInfo.setDeleteTime(basicBaseRfidprint.getDeleteTime());
		baseRfidInfo.setCreateTime(basicBaseRfidprint.getCreateTime());
		baseRfidInfo.setUpdateTime(basicBaseRfidprint.getUpdateTime());
		baseRfidInfo.setOperatorOrgCode(basicBaseRfidprint.getOperatorOrgCode());
		baseRfidInfo.setOperatorSelfOrgCode(basicBaseRfidprint.getOperatorSelfOrgCode());
		baseRfidInfo.setTenantId(basicBaseRfidprint.getTenantId());
		basicBaseRfidInfoMapper.createBasicBaseRfidInfo(baseRfidInfo);

		BasicBaseCreaterfidPrint basicBaseCreaterfidPrint = new BasicBaseCreaterfidPrint();
		basicBaseCreaterfidPrint.setPrintId(basicBaseRfidprint.getId());
		basicBaseCreaterfidPrint.setGoodsId(basicBaseRfidprint.getGoodsId());
		basicBaseCreaterfidPrint.setRfidId(baseRfidInfo.getId());
		basicBaseCreaterfidPrint.setCreateBy(basicBaseRfidprint.getCreateBy());
		basicBaseCreaterfidPrint.setUpdateBy(basicBaseRfidprint.getUpdateBy());
		basicBaseCreaterfidPrint.setDeleteTime(basicBaseRfidprint.getDeleteTime());
		basicBaseCreaterfidPrint.setCreateTime(basicBaseRfidprint.getCreateTime());
		basicBaseCreaterfidPrint.setUpdateTime(basicBaseRfidprint.getUpdateTime());
		basicBaseCreaterfidPrint.setOperatorOrgCode(basicBaseRfidprint.getOperatorOrgCode());
		basicBaseCreaterfidPrint.setOperatorSelfOrgCode(basicBaseRfidprint.getOperatorSelfOrgCode());
		basicBaseCreaterfidPrint.setTenantId(basicBaseRfidprint.getTenantId());
		basicBaseCreaterfidPrintMapper.createBasicBaseCreaterfidPrint(basicBaseCreaterfidPrint);

		BasicBaseRfidprintDetail baseRfidprintDetail = new BasicBaseRfidprintDetail();
		baseRfidprintDetail.setPrintId(basicBaseRfidprint.getId());
		baseRfidprintDetail.setEpc(basicBaseRfidprintDetail.getEpc());
		baseRfidprintDetail.setIsPrint(0);
		baseRfidprintDetail.setCreateBy(basicBaseRfidprint.getCreateBy());
		baseRfidprintDetail.setUpdateBy(basicBaseRfidprint.getUpdateBy());
		baseRfidprintDetail.setDeleteTime(basicBaseRfidprint.getDeleteTime());
		baseRfidprintDetail.setCreateTime(basicBaseRfidprint.getCreateTime());
		baseRfidprintDetail.setUpdateTime(basicBaseRfidprint.getUpdateTime());
		baseRfidprintDetail.setOperatorOrgCode(basicBaseRfidprint.getOperatorOrgCode());
		baseRfidprintDetail.setOperatorSelfOrgCode(basicBaseRfidprint.getOperatorSelfOrgCode());
		basicBaseRfidprintDetailMapper.createBasicBaseRfidprintDetail(baseRfidprintDetail);

		return result;
	}

	// Query Print Method

	public BasicBaseDevice selectPrint(String mac) {
		return basicBaseDeviceMapper.selectPrint(mac);
	}

	public int printLock(Long printId, String deviceCode) {
		BasicBaseDevice basicBaseDevice = basicBaseDeviceMapper.selectPrintByCode(deviceCode);
		if (basicBaseDevice == null) {
			throw new QslException(" Printer Error   ,请确认后重试！");
		}
		return basicBaseRfidprintMapper.printLock(printId, basicBaseDevice.getId());
	}

	public int printUnlock(Long printId) {
		return basicBaseRfidprintMapper.printUnlock(printId);
	}

	public int selectLock(Long printId) {
		return basicBaseRfidprintMapper.selectLock(printId);
	}

}
