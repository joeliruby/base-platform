package com.matariky.jobs.jobsService.dbbackup;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.matariky.commonservice.backup.service.SystemMysqlBackupsService;
import com.matariky.jobs.jobsService.job.base.BaseJob;

public class DBBackupJob implements BaseJob{
	
	@Autowired 
	private SystemMysqlBackupsService systemMysqlBackupsService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		systemMysqlBackupsService.mysqlBackups();
	}

}
