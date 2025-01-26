package com.matariky.commonservice.loginlog.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.matariky.iservice.BaseService;
import com.matariky.iservice.impl.BaseServiceImpl;
import com.matariky.commonservice.loginlog.bean.CommonLoginLog;
import com.matariky.commonservice.loginlog.mapper.CommonLoginLogMapper;

/**
* Business Inteface Implementation
* @author AUTOMATION
*/
@Service
public class CommonLoginLogService extends BaseServiceImpl<CommonLoginLogMapper,CommonLoginLog> implements ICommonLoginLogService, BaseService<CommonLoginLog>{

	@Autowired
	private CommonLoginLogMapper commonLoginLogMapper;

	 
	public Page<CommonLoginLog> getCommonLoginLogAll(){
		return commonLoginLogMapper.getCommonLoginLogAll();
	}

	 
	public int getCommonLoginLogAllCount(){
		return commonLoginLogMapper.getCommonLoginLogAllCount();
	}

	 
	public int createCommonLoginLog(CommonLoginLog bean){
		return commonLoginLogMapper.createCommonLoginLog(bean);
	}

	 
	public int updateCommonLoginLog(CommonLoginLog bean){
		return commonLoginLogMapper.updateCommonLoginLog(bean);
	}

	 
	public int delCommonLoginLogById(int id){
		return commonLoginLogMapper.delCommonLoginLogById(id);
	}

	 
	public CommonLoginLog getCommonLoginLogById(int id){
		return commonLoginLogMapper.getCommonLoginLogById(id);
	}
	
	public List<CommonLoginLog> getCommonLoginLogDynamicCondition(Map<String, Object> params){
		return commonLoginLogMapper.getCommonLoginLogDynamicCondition(params);
	}

	public List<CommonLoginLog> getCommonLoginLogByIds(String[] split) {
		return commonLoginLogMapper.getCommonLoginLogByIds(split);
	}

}
