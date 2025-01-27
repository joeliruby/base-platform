package com.matariky.commonservice.sqlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.commonservice.sqlog.bean.CommonSqlLog;
import com.matariky.commonservice.sqlog.mapper.CommonSqlLogMapper;

/**
 * Business Inteface Implementation
 * 
 * @author AUTOMATION
 */
@Service
public class CommonSqlLogService extends BaseServiceImpl<CommonSqlLogMapper, CommonSqlLog> {

	@Autowired
	private CommonSqlLogMapper commonSqlLogMapper;

	public Page<CommonSqlLog> getCommonSqlLogAll() {
		return commonSqlLogMapper.getCommonSqlLogAll();
	}

	public int getCommonSqlLogAllCount() {
		return commonSqlLogMapper.getCommonSqlLogAllCount();
	}

	public int createCommonSqlLog(CommonSqlLog bean) {
		return commonSqlLogMapper.createCommonSqlLog(bean);
	}

	public int updateCommonSqlLog(CommonSqlLog bean) {
		return commonSqlLogMapper.updateCommonSqlLog(bean);
	}

	public int delCommonSqlLogById(int id) {
		return commonSqlLogMapper.delCommonSqlLogById(id);
	}

	public CommonSqlLog getCommonSqlLogById(int id) {
		return commonSqlLogMapper.getCommonSqlLogById(id);
	}

	public Page<CommonSqlLog> getCommonSQLLogDynamicCondition(CommonSqlLog bean) {
		return commonSqlLogMapper.getCommonSQLLogDynamicCondition(bean);
	}

}
