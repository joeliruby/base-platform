package com.matariky.commonservice.commondict.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;

import com.matariky.commonservice.commondict.bean.CommonDict;
import com.matariky.commonservice.commondict.bean.CommonDictType;
import com.matariky.commonservice.commondict.mapper.CommonDictTypeMapper;
import com.matariky.iservice.impl.BaseServiceImpl;

/**
 * Business Inteface Implementation
 * 
 * @author AUTOMATION
 */
@Service
public class CommonDictTypeService extends BaseServiceImpl<CommonDictTypeMapper, CommonDictType> {

	@Autowired
	private CommonDictTypeMapper commonDictTypeMapper;

	public Page<CommonDictType> getCommonDictTypeAll(Map<String, Object> params) {
		return commonDictTypeMapper.getCommonDictTypeAll(params);
	}

	public int getCommonDictTypeAllCount(Map<String, Object> params) {
		return commonDictTypeMapper.getCommonDictTypeAllCount(params);
	}

	public int createCommonDictType(CommonDictType bean) {
		return commonDictTypeMapper.createCommonDictType(bean);
	}

	public int updateCommonDictType(CommonDictType bean) {
		return commonDictTypeMapper.updateCommonDictType(bean);
	}

	@CacheEvict(value = "dictTypes", key = "#id")
	public int delCommonDictTypeById(Long id) {
		return commonDictTypeMapper.delCommonDictTypeById(id);
	}

	@Cacheable(value = "dictTypes", key = "#id")
	public CommonDictType getCommonDictTypeById(String id) {
		return commonDictTypeMapper.getCommonDictTypeById(id);
	}

	// according to Dictionary group key Retrieve all Dictionary Detail
	@Cacheable(key = "#tenantId+#dictTypeKey", value = "dicts")
	public List<CommonDict> getDictsByDictTypeKey(String tenantId, String dictTypeKey) {
		return commonDictTypeMapper.getDictsByDictTypeKey(tenantId, dictTypeKey, 1L, 0L);
	}

	public int updateDeleteTimeById(String[] id) {

		return commonDictTypeMapper.updateDeleteTimeById(id);
	}

	public CommonDictType getDictTypeByKey(String tenantId, String dictTypeKey) {

		return commonDictTypeMapper.getDictTypeByKey(tenantId, dictTypeKey);
	}

}
