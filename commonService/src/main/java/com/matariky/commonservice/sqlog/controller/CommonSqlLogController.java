package com.matariky.commonservice.sqlog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.sqlog.bean.CommonSqlLog;
import com.matariky.commonservice.sqlog.service.CommonSqlLogService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;

/**
*Controller
* @author AUTOMATION
*/
@RestController
	@RequestMapping("/api/v1/tenant/{tenantId}")
public class CommonSqlLogController {

	@Autowired
	private CommonSqlLogService commonSqlLogService;

	 
	@RequestMapping("/commonSqlLog/list")
	public PageInfo<CommonSqlLog> list(HttpServletRequest request,CommonSqlLog bean, @PathVariable("tenantId") String tenantId,  @RequestParam("index") int pageIndex, @RequestParam("perPage") int perPage,  @RequestHeader("Authorization") String jwt) {
		PageHelper.startPage(pageIndex, perPage);
		if(bean.getStartTime()!=null&&bean.getStartTime().equals(bean.getEndTime())&&bean.getStartTime().longValue()!=0l) {//同一天
			bean.setEndTime(bean.getEndTime()+24*60*60*1000);
		}
		Page<CommonSqlLog> page = commonSqlLogService.getCommonSQLLogDynamicCondition(bean);
		PageInfo<CommonSqlLog> pageInfo= new PageInfo<CommonSqlLog>(page.getResult());
		return pageInfo;
	}


	 
	@RequestMapping(value = "/commonSqlLog",method = RequestMethod.POST)
	public ResponseEntity<String> save(CommonSqlLog bean,HttpServletRequest request, HttpServletResponse response) {
		try{
			int success = commonSqlLogService.createCommonSqlLog(bean);
				if(success > 0){
					return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
				}else{
					throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
				}
		}catch (Exception e){
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	 
	@RequestMapping(value = "/commonSqlLog",method = RequestMethod.PUT)
	public ResponseEntity<String> update(CommonSqlLog bean,HttpServletRequest request, HttpServletResponse response) {
		try{
			int success = commonSqlLogService.updateCommonSqlLog(bean);
				if(success > 0){
					return new ResponseEntity<String>("SUCCESS",  HttpStatus.OK);
				}else{
					throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
				}
		}catch (Exception e){
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

	 
	@RequestMapping(value = "/commonSqlLog",method = RequestMethod.DELETE)
	public ResponseEntity<String> del(String id,HttpServletRequest request, HttpServletResponse response) {
		try{
			int success = commonSqlLogService.delCommonSqlLogById(Integer.parseInt(id));
				if(success > 0){
					return new ResponseEntity<String>("SUCCESS",  HttpStatus.OK);
				}else{
					throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
				}
		}catch (Exception e){
			e.printStackTrace();
			throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);
		}
	}

}