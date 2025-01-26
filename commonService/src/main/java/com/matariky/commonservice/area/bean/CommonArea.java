package com.matariky.commonservice.area.bean;

import java.lang.String;
import java.lang.Long;
import java.lang.Integer;
/**
* Automatically generated entity class
* @author AUTOMATION
*/
public class CommonArea {

	private Long id;
	private String areaCode;
	private String areaName;
	private Integer level;
	private String cityCode;
	private String center;
	private Long parentId;
	
	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getAreaCode() {
		return areaCode;
	}



	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}



	public String getAreaName() {
		return areaName;
	}



	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}



	public Integer getLevel() {
		return level;
	}



	public void setLevel(Integer level) {
		this.level = level;
	}



	public String getCityCode() {
		return cityCode;
	}



	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}



	public String getCenter() {
		return center;
	}



	public void setCenter(String center) {
		this.center = center;
	}



	public Long getParentId() {
		return parentId;
	}



	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	private String remark;


}