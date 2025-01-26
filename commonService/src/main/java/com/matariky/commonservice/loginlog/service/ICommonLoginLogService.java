package com.matariky.commonservice.loginlog.service;

import org.springframework.stereotype.Service;

import com.matariky.commonservice.loginlog.bean.CommonLoginLog;

@Service
public interface ICommonLoginLogService {
	public int createCommonLoginLog(CommonLoginLog bean);
}
