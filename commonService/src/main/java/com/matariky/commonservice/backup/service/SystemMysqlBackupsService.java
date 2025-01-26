package com.matariky.commonservice.backup.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.Page;
import com.matariky.commonservice.backup.bean.SystemMysqlBackups;

public interface SystemMysqlBackupsService extends IService<SystemMysqlBackups> {
	
	

	/**
	 * Query all backup data
	 */
	Page<SystemMysqlBackups> selectBackupsList();

	/**
	 * MySQL backup interface
	 */
	Object mysqlBackups();

	/**
	 * Query by ID
	 */
	SystemMysqlBackups selectListId(Long id);

	/**
	 * Restore Database
	 *
	 * @param smb      Restore object
	 * @param userName Database username
	 * @param password Database password
	 * @return
	 */
	Object rollback(SystemMysqlBackups smb);

}

