package com.matariky.commonservice.sqlog.bean;

import java.lang.Long;
import java.lang.String;
/**
* Automatically generated entity class
* @author AUTOMATION
*/
public class CommonSqlLog {

	private String id;
	private String sqlStatemant;
	private Long createdBy;
	private Long createTime;
	private Long executionTime;
	private Long startTime;
	private Long endTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSqlStatemant() {
		return sqlStatemant;
	}
	public void setSqlStatemant(String sqlStatemant) {
		this.sqlStatemant = sqlStatemant;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Long getExecutionTime() {
		return executionTime;
	}
	public void setExecutionTime(Long executionTime) {
		this.executionTime = executionTime;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public Long getExecutionDuration() {
		return executionDuration;
	}
	public void setExecutionDuration(Long executionDuration) {
		this.executionDuration = executionDuration;
	}
	private Long executionDuration;


}