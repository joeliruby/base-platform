package com.matariky.commonservice.backup.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.matariky.commonservice.backup.bean.SystemMysqlBackups;
import com.matariky.commonservice.backup.service.SystemMysqlBackupsService;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.BeanUtils;



/**
 * ClassName:SystemMysqlBackupsController
 * Classification： MySQL Data Backup interface
 */
@RestController
@RequestMapping(value = "/api/v1/system/baskups")
public class SystemMysqlBackupsController {

  

    @Autowired
    private SystemMysqlBackupsService systemMysqlBackupsService;

    @RequestMapping("/backupsList")
    public Object backupsList(@RequestParam("index") int pageIndex, @RequestParam("perPage") int perPage, @RequestHeader("Authorization") String jwt) {
    	PageHelper.startPage(pageIndex, perPage);
        Page<SystemMysqlBackups> systemMysqlBackups = systemMysqlBackupsService.selectBackupsList();
        PageInfo<SystemMysqlBackups> pi=new PageInfo<SystemMysqlBackups>();
        BeanUtils.copyProperties(systemMysqlBackups, pi);
        pi.setList(systemMysqlBackups.getResult());
        return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,pi);
    }

    @PostMapping("/mysqlBackups")
    public Object mysqlBackups() {
        Object systemMysqlBackups = systemMysqlBackupsService.mysqlBackups();
        return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,systemMysqlBackups);
    }

    @PutMapping("/rollback")
    public Object rollback( @RequestBody Map<String, Object> map) {
    	String idS=(String)map.get("id");
    	 if (StringUtils.isEmpty(idS) ) {
         	throw new QslException(MessageKey.INVALID_DATA_ID);
         }
        Long id = Long.valueOf(idS.toString());
       
        SystemMysqlBackups smb = systemMysqlBackupsService.selectListId(id);
	     // Restore Database
	    Object rollback = systemMysqlBackupsService.rollback(smb);
	     // Update operation count
        systemMysqlBackupsService.updateById(smb);
        return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,rollback);
    }
}

