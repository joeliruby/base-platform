package com.matariky.bizservice.assetitm.base.service;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.matariky.bizservice.job.JobApiService;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidfactory;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidfactoryParameter;
import com.matariky.bizservice.assetitm.base.bean.excel.TapeRfidCreateCNExeclListener;
import com.matariky.commonservice.base.vo.TapeRfidCreateCNExeclReqVo;
import com.matariky.bizservice.assetitm.base.mapper.BasicBaseRfidfactoryMapper;
import com.matariky.bizservice.assetitm.base.mapper.BasicBaseRfidfactoryParameterMapper;
import com.matariky.bizservice.assetitm.base.vo.BasicBaseRfidfactoryAddVO;
import com.matariky.commonservice.base.bean.BasicBaseRfidInfo;
import com.matariky.commonservice.base.mapper.BasicBaseRfidInfoMapper;
import com.matariky.commonservice.base.service.CommonService;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.constant.PermissionConstant;
import com.matariky.exception.QslException;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.jobs.jobsService.bean.form.RfidCreateJobForm;
import com.matariky.utils.CodeUtils;
import com.matariky.utils.StringUtils;
import com.matariky.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.collections4.CollectionUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Business Inteface Implementation
 * 
 * @author AUTOMATION
 */
@Service
@Component
public class BasicBaseRfidfactoryService extends BaseServiceImpl<BasicBaseRfidfactoryMapper, BasicBaseRfidfactory>
		implements BaseService<BasicBaseRfidfactory> {

	@Autowired
	private BasicBaseRfidfactoryMapper basicBaseRfidfactoryMapper;
	@Autowired
	private BasicBaseRfidfactoryParameterMapper basicBaseRfidfactoryParameterMapper;

	@Autowired
	private BasicBaseRfidInfoMapper basicBaseRfidInfoMapper;

	@Autowired
	private JobApiService jobApiService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private CommonDictService commonDictService;

	@Autowired
	private CommonDictTypeService commonDictTypeService;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpServletRequest request;

	public List<BasicBaseRfidfactory> getBasicBaseRfidfactoryAll(BasicBaseRfidfactory bean, String tenantId,
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
		return basicBaseRfidfactoryMapper.getBasicBaseRfidfactoryAll(bean);
	}

	public int getBasicBaseRfidfactoryAllCount() {
		return basicBaseRfidfactoryMapper.getBasicBaseRfidfactoryAllCount();
	}

	public int createBasicBaseRfidfactory(BasicBaseRfidfactory bean) {
		return basicBaseRfidfactoryMapper.createBasicBaseRfidfactory(bean);
	}

	public int createBasicBaseRfidfactoryWithOrg(BasicBaseRfidfactoryAddVO bean, HttpServletRequest request) {
		bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
		bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
		bean.setTaskBatchCode(CodeUtils.CreateBatchCode());
		bean.setDeleteTime(0L);
		bean.setCreateTime(Calendar.getInstance().getTimeInMillis());
		bean.setUpdateTime(Calendar.getInstance().getTimeInMillis());

		BasicBaseRfidfactory basicBaseRfidfactory = new BasicBaseRfidfactory();
		BeanUtils.copyProperties(bean, basicBaseRfidfactory);
		basicBaseRfidfactory.setUploadNum(0);
		basicBaseRfidfactory.setIsFileCreate(0);
		basicBaseRfidfactory.setDownloadNum(0);
		basicBaseRfidfactory.setTaskStatus(0);
		int result = basicBaseRfidfactoryMapper.createBasicBaseRfidfactory(basicBaseRfidfactory);

		if (bean.getRfidfactoryParameterAddVOS() != null && bean.getRfidfactoryParameterAddVOS().size() > 0) {
			List<BasicBaseRfidfactoryParameter> basicBaseRfidfactoryParameterList = bean.getRfidfactoryParameterAddVOS()
					.stream().map(baseRfidfactoryParameterAddVO -> {
						BasicBaseRfidfactoryParameter baseRfidfactoryParameter = new BasicBaseRfidfactoryParameter();
						BeanUtils.copyProperties(baseRfidfactoryParameterAddVO, baseRfidfactoryParameter);
						baseRfidfactoryParameter.setRfidfactoryId(basicBaseRfidfactory.getId());
						baseRfidfactoryParameter.setCreateBy(basicBaseRfidfactory.getCreateBy());
						baseRfidfactoryParameter.setUpdateBy(basicBaseRfidfactory.getUpdateBy());
						baseRfidfactoryParameter.setDeleteTime(basicBaseRfidfactory.getDeleteTime());
						baseRfidfactoryParameter.setCreateTime(basicBaseRfidfactory.getCreateTime());
						baseRfidfactoryParameter.setUpdateTime(basicBaseRfidfactory.getUpdateTime());
						return baseRfidfactoryParameter;
					}).collect(Collectors.toList());
			for (BasicBaseRfidfactoryParameter baseRfidfactoryParameter : basicBaseRfidfactoryParameterList) {
				basicBaseRfidfactoryParameterMapper.insert(baseRfidfactoryParameter);
			}
		}

		Long rulesId = 0L;
		if (StringUtil.isNotEmpty(basicBaseRfidfactory.getEpcRule())) {
			rulesId = Long.parseLong(basicBaseRfidfactory.getEpcRule());
		}
		if (rulesId > 0) {

			RfidCreateJobForm jobForm = new RfidCreateJobForm();
			jobForm.setTaskId(basicBaseRfidfactory.getId());
			jobForm.setTaskType(1);
			jobForm.setStartTime(Calendar.getInstance().getTimeInMillis());

			String sourceId = request.getHeader("id");
			String token = TokenUtils.getTokenFromRequest(request);
			jobApiService.addRfidJob(jobForm, basicBaseRfidfactory.getTenantId(), sourceId, token);

		}

		return result;
	}

	@Transactional(rollbackFor = Exception.class)
	public int updateBasicBaseRfidfactory(BasicBaseRfidfactoryAddVO bean, HttpServletRequest request) {
		bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
		bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));

		bean.setUpdateTime(Calendar.getInstance().getTimeInMillis());

		BasicBaseRfidfactory basicBaseRfidfactory = basicBaseRfidfactoryMapper
				.getBasicBaseRfidfactoryById(bean.getId());
		bean.setCreateBy(basicBaseRfidfactory.getCreateBy());
		bean.setCreateTime(basicBaseRfidfactory.getCreateTime());
		bean.setDeleteTime(basicBaseRfidfactory.getDeleteTime());
		bean.setTaskBatchCode(basicBaseRfidfactory.getTaskBatchCode());
		if (basicBaseRfidfactory != null) {
			BeanUtils.copyProperties(bean, basicBaseRfidfactory);
			basicBaseRfidfactoryParameterMapper.delBasicBaseRfidfactoryParameterByFactoryId(bean.getId());
			if (bean.getRfidfactoryParameterAddVOS() != null && bean.getRfidfactoryParameterAddVOS().size() > 0) {
				List<BasicBaseRfidfactoryParameter> basicBaseRfidfactoryParameterList = bean
						.getRfidfactoryParameterAddVOS().stream().map(baseRfidfactoryParameterAddVO -> {
							BasicBaseRfidfactoryParameter baseRfidfactoryParameter = new BasicBaseRfidfactoryParameter();
							BeanUtils.copyProperties(baseRfidfactoryParameterAddVO, baseRfidfactoryParameter);
							baseRfidfactoryParameter.setRfidfactoryId(basicBaseRfidfactory.getId());
							baseRfidfactoryParameter.setCreateBy(basicBaseRfidfactory.getCreateBy());
							baseRfidfactoryParameter.setUpdateBy(basicBaseRfidfactory.getUpdateBy());
							baseRfidfactoryParameter.setDeleteTime(basicBaseRfidfactory.getDeleteTime());
							baseRfidfactoryParameter.setCreateTime(basicBaseRfidfactory.getCreateTime());
							baseRfidfactoryParameter.setUpdateTime(basicBaseRfidfactory.getUpdateTime());
							return baseRfidfactoryParameter;
						}).collect(Collectors.toList());
				for (BasicBaseRfidfactoryParameter baseRfidfactoryParameter : basicBaseRfidfactoryParameterList) {
					basicBaseRfidfactoryParameterMapper.insert(baseRfidfactoryParameter);
				}
			}

		}
		return basicBaseRfidfactoryMapper.updateById(basicBaseRfidfactory);
	}

	// Delete Method
	public int delBasicBaseRfidfactoryById(Long id) {
		return basicBaseRfidfactoryMapper.delBasicBaseRfidfactoryById(id);
	}

	public BasicBaseRfidfactory getBasicBaseRfidfactoryById(Long id) {
		return basicBaseRfidfactoryMapper.getBasicBaseRfidfactoryById(id);
	}

	public List<BasicBaseRfidfactory> getBasicBaseRfidfactoryDAC(Map<String, Object> params,
			HttpServletRequest request) {
		strategyCodeToParams(params, request);
		return basicBaseRfidfactoryMapper.getBasicBaseRfidfactoryDAC(params);
	}

	public Long getBasicBaseRfidfactoryDACCount(Map<String, Object> params, HttpServletRequest request) {

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

		return basicBaseRfidfactoryMapper.getBasicBaseRfidfactoryDACCount(params);
	}

	/***
	 * Export Data
	 * 
	 * @param response
	 * @return
	 */
	public BasicBaseRfidfactory download(HttpServletResponse response, Long id) {

		BasicBaseRfidfactory basicBaseRfidfactory = basicBaseRfidfactoryMapper.getBasicBaseRfidfactoryById(id);

		if (basicBaseRfidfactory.getDownloadNum() == null) {
			basicBaseRfidfactory.setDownloadNum(0);
		}
		basicBaseRfidfactory.setDownloadNum(basicBaseRfidfactory.getDownloadNum() + 1);
		basicBaseRfidfactory.setDownloadTime(basicBaseRfidfactory.getCreateTime());
		basicBaseRfidfactoryMapper.updateById(basicBaseRfidfactory);
		return basicBaseRfidfactory;
	}

	/**
	 * 默认excel File Name 和单元sheet Name 一样的 Excel文件 Export
	 *
	 * @param workBookName:  excel File Name
	 * @param sheetName:工作簿
	 * @param list：Import表格的 Data
	 * @param pojoClass      ：工作表映射实体类
	 * @param response       ：Client 输出 Data 流
	 * @return void
	 * @author hanyulin
	 * @date 2021/10/21 16:33
	 */
	public static void exportExcel(String workBookName, String sheetName, List<?> list, Class<?> pojoClass,
			HttpServletResponse response) {
		try {
			response.setContentType("application/vnd.ms-excel; charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(workBookName + System.currentTimeMillis() + ".xlsx", "utf-8"));
			EasyExcel.write(response.getOutputStream(), pojoClass).sheet(sheetName).doWrite(list);
		} catch (IOException e) {
			throw new RuntimeException("Download Data 异常");
		}
	}

	/****
	 * Import Label Information
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean upload(MultipartFile file, String jwt, String tenantId, Long Id) throws IOException {
		// InputStream inputStream = file.getInputStream();
		// EasyExcel.read(inputStream, TapeRfidCreateCNExeclReqVo.class, new
		// TapeRfidCreateCNExeclListener(basicBaseRfidInfoMapper, jwt, request,
		// tenantId))
		// .sheet().doRead();

		List<TapeRfidCreateCNExeclReqVo> tapeRfidCreateCNExeclReqVos = new ArrayList<>();
		// Record Error 的 Information
		StringBuffer errorMessage = new StringBuffer();
		// Create 监听器

		// Long userId=Long.parseLong(TokenUtils.extractUserIdFromToken(jwt));
		TapeRfidCreateCNExeclListener listener = new TapeRfidCreateCNExeclListener(tapeRfidCreateCNExeclReqVos,
				errorMessage, basicBaseRfidInfoMapper, jwt, request, tenantId);

		try {
			EasyExcel.read(file.getInputStream(), TapeRfidCreateCNExeclReqVo.class, listener).sheet().doRead();
		} catch (Exception ex) {
			throw new QslException(MessageKey.TAPE_LABEL_UPLOAD_FILE_ERROR);
		}
		// 判断Excel Information Wether 有 Error
		if (StringUtils.isNotEmpty(errorMessage.toString())) {
			throw new QslException(errorMessage.toString());
		}

		final Integer[] size = { 0 };

		BasicBaseRfidfactory basicBaseRfidfactory = basicBaseRfidfactoryMapper.getBasicBaseRfidfactoryById(Id);
		int create_num = 0;
		int upload_num = 0;
		if (basicBaseRfidfactory != null && basicBaseRfidfactory.getCreateNum() != null) {
			create_num = basicBaseRfidfactory.getCreateNum();
		}
		if (basicBaseRfidfactory != null && basicBaseRfidfactory.getUploadNum() != null) {
			upload_num = basicBaseRfidfactory.getUploadNum();
		}

		// List<BasicBaseRfidInfo>
		// basicBaseRfidInfos=basicBaseRfidInfoMapper.getBasicBaseRfidInfoByFactoryId(Id);
		if (CollectionUtils.isNotEmpty(tapeRfidCreateCNExeclReqVos)) {
			if ((create_num - upload_num) < tapeRfidCreateCNExeclReqVos.size()
					|| tapeRfidCreateCNExeclReqVos.size() > 2000) {
				throw new QslException("Import Label  Data 有误  ,请确认后重试！");
			}

			tapeRfidCreateCNExeclReqVos.stream().forEach(item -> {
				BasicBaseRfidInfo baseRfidInfo = new BasicBaseRfidInfo();
				if (StringUtils.isNotEmpty(item.getEpc())) {
					baseRfidInfo = basicBaseRfidInfoMapper.getBasicBaseRfidInfoByEpc(item.getEpc());
				} else {
					item.setId(Id);
					baseRfidInfo = basicBaseRfidInfoMapper.getBasicBaseRfidInfoByOdqr(item);
				}

				if (baseRfidInfo != null) {
					baseRfidInfo.setTid(item.getTid());
					// baseRfidInfo.setUpdateBy(userId);
					baseRfidInfo.setUpdateTime(Calendar.getInstance().getTimeInMillis());
					basicBaseRfidInfoMapper.updateBasicBaseRfidInfo(baseRfidInfo);
					size[0] = size[0] + 1;
				} else {
					throw new QslException("Import Label  Data 有误  ,请确认后重试！");
				}
			});
			if (size[0] > 0) {
				basicBaseRfidfactory.setUploadNum(upload_num + tapeRfidCreateCNExeclReqVos.size());
				basicBaseRfidfactoryMapper.updateById(basicBaseRfidfactory);
			}

		} else {
			throw new QslException("Import Label  Data 有误  ,请确认后重试！");
		}

		return true;
	}

	public int overBasicBaseRfidfactory(BasicBaseRfidfactoryAddVO bean, HttpServletRequest request) {
		bean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
		bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));

		bean.setUpdateTime(Calendar.getInstance().getTimeInMillis());

		BasicBaseRfidfactory basicBaseRfidfactory = basicBaseRfidfactoryMapper
				.getBasicBaseRfidfactoryById(bean.getId());
		bean.setCreateBy(basicBaseRfidfactory.getCreateBy());
		bean.setCreateTime(basicBaseRfidfactory.getCreateTime());
		bean.setDeleteTime(basicBaseRfidfactory.getDeleteTime());
		bean.setTaskBatchCode(basicBaseRfidfactory.getTaskBatchCode());
		if (basicBaseRfidfactory != null) {
			BeanUtils.copyProperties(bean, basicBaseRfidfactory);
			basicBaseRfidfactory.setTaskStatus(1);
		}
		return basicBaseRfidfactoryMapper.updateById(basicBaseRfidfactory);
	}

	/***
	 * DownloadImport Template
	 */
	public void downLoadTemplate() {
		commonService.createTemplate(response, TapeRfidCreateCNExeclReqVo.class, "rfidfactory",
				" Label  Generation Import Template ");
	}
}
