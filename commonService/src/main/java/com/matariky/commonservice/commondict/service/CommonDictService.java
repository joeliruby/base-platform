package com.matariky.commonservice.commondict.service;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.constant.DictKey;
import com.matariky.commonservice.commondict.constant.DictTypeKey;
import com.matariky.commonservice.commondict.mapper.CommonDictMapper;
import com.matariky.commonservice.commondict.mapper.CommonDictTypeMapper;
import com.matariky.iservice.impl.BaseServiceImpl;

/**
 * Business Inteface Implementation
 * 
 * @author AUTOMATION
 */
@Service
public class CommonDictService extends BaseServiceImpl<CommonDictMapper, CommonDict> {

	@Autowired
	private CommonDictMapper commonDictMapper;

	@Autowired
	private CommonDictTypeMapper commonDictTypeMapper;

	@Value("${default.tenantId}")
	private String defaultTenantId;

	public List<CommonDict> getCommonDictAll(Map<String, Object> params) {
		return commonDictMapper.getCommonDictAll(params);
	}

	public int getCommonDictAllCount(Map<String, Object> params) {
		return commonDictMapper.getCommonDictAllCount(params);
	}

	public int createCommonDict(CommonDict bean) {
		return commonDictMapper.createCommonDict(bean);
	}

	@CacheEvict(value = "dicts", key = "#bean.getId()")
	public int updateCommonDict(CommonDict bean) {
		return commonDictMapper.updateCommonDict(bean);
	}

	@CacheEvict(value = "dicts", key = "#id")
	public int delCommonDictById(Long id) {
		return commonDictMapper.delCommonDictById(id);
	}

	@Cacheable(value = "dicts", key = "#id")
	public CommonDict getCommonDictById(Long id) {
		return commonDictMapper.getCommonDictById(id);
	}

	// According to Dictionary key and Tenant id Query Data Updatedict_value
	// Configuration json
	public void updateValueByKeyAndTenantId(String dictTypekey, String dictKey, String dictValue, String tenantId) {

		Map<String, Object> columnMap = new HashMap<>();
		String dictTypeId = null;
		columnMap.put("id", dictTypekey);
		List<CommonDictType> dictTypes = commonDictTypeMapper.selectByMap(columnMap);
		if (dictTypes != null && dictTypes.size() > 0) {
			CommonDictType dictType = dictTypes.get(0);
			dictTypeId = dictType.getId().toString();
		}
		CommonDict dict = commonDictMapper.getDictionaryItemById(dictTypeId, dictKey, tenantId);
		dict.setDictValue(dictValue);
		updateCommonDict(dict);
	}

	@Cacheable(value = "message", key = "{#dictKey, #tenantCode}")
	public JSONObject getServiceMessage(String dictTypeKey, String dictKey, Boolean success, String tenantCode) {
		if (tenantCode == null) {
			tenantCode = defaultTenantId;
		}
		CommonDict dict = commonDictMapper.getDictionaryItemByTypeAndKey(dictTypeKey, dictKey, tenantCode);
		JSONObject jo = new JSONObject();
		if (Objects.nonNull(dict)) {
			jo.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			jo.put("message", dict.getDictValue());
			jo.put("success", success);
		} else {
			jo.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			jo.put("message", dictKey);
			jo.put("success", success);
		}
		return jo;
	}

	public String getServiceErrorText(String dictTypeKey, String dictKey, Boolean success, String tenantCode) {
		CommonDict dict = commonDictMapper.getDictionaryItemByTypeAndKey(dictTypeKey, dictKey, tenantCode);
		return dict.getDictValue();
	}

	public List<Map<String, Object>> initialList(String tenantId, String locale) {
		return commonDictMapper.initialList(tenantId, locale);
	}

	public int updateDeleteTimeById(String[] id) {
		return commonDictMapper.updateDeleteTimeById(id);
	}

	public String getDictValueByTenantIdAndKey(String extractTenantIdFromHttpReqeust, String storageDictType) {
		return commonDictMapper.getDictValueByTenantIdAndKey(extractTenantIdFromHttpReqeust, storageDictType);
	}

	public String getDictValueByTenantIdAndKeyAndDictTypeId(String tenantId, String permissionId, String dictTypeId) {
		return commonDictMapper.getDictValueByTenantIdAndKeyAndDictTypeId(tenantId, permissionId, dictTypeId);
	}

	public CommonDict getCommonDictByIdTenantIdAndDictType(String permissionId, String tenantId, Long id) {
		return commonDictMapper.getCommonDictByIdTenantIdAndDictType(permissionId, tenantId, id);
	}

	public List<CommonDict> getDictListByTypeKey(String tenantId, String ditTypeKey, Long isActive, Long deleteTime) {
		if (StringUtils.isNotEmpty(tenantId) && StringUtils.isNotEmpty(ditTypeKey)) {
			List<CommonDict> commonDictList = commonDictTypeMapper.getDictsByDictTypeKey(tenantId, ditTypeKey, isActive,
					deleteTime);
			return commonDictList;
		}
		return Collections.emptyList();
	}

	public int getReaderMaxTimes(String tenantId) {
		/** Query Dictionary Configuration item Reader maximum reading Time **/
		CommonDict commonDict = commonDictMapper.getDictionaryItemByTypeAndKey(DictTypeKey.READER_CONFIG,
				DictKey.READER_MAX_TIMES, tenantId);
		int readerMaxTimes;
		if (Objects.isNull(commonDict) || Objects.isNull(commonDict.getDictValue())) {
			/** Default 20 seconds **/
			readerMaxTimes = 120;
		} else {
			readerMaxTimes = Integer.parseInt(commonDict.getDictValue());
		}
		return readerMaxTimes;
	}

}
