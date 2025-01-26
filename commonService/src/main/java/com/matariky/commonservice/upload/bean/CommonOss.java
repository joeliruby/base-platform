package com.matariky.commonservice.upload.bean;

import java.lang.String;
import java.lang.Integer;
/**
* Automatically generated entity class
* @author AUTOMATION
*/
public class CommonOss {

	private Long id;
	private String url;
	private String tenant_id;
	private String domain_name;
	private String old_name;
	private String file_name;
	private String extension;
	private Integer size;
	private String content_type;
	private String service_provider;
	private Long create_time;
	private Long update_time;
	private Long created_by;
	private Long updated_by;
	
	public String getService_provider() {
		return service_provider;
	}

	public void setService_provider(String service_provider) {
		this.service_provider = service_provider;
	}

	public Long getId(){
		return id;
	}

	public String getUrl(){
		return url;
	}

	public String getTenantId(){
		return tenant_id;
	}

	public String getDomainName(){
		return domain_name;
	}

	public String getOldName(){
		return old_name;
	}

	public String getFileName(){
		return file_name;
	}

	public String getExtension(){
		return extension;
	}

	public Integer getSize(){
		return size;
	}

	public String getContentType(){
		return content_type;
	}

	public Long getCreateTime(){
		return create_time;
	}

	public Long getUpdateTime(){
		return update_time;
	}

	public Long getCreatedBy(){
		return created_by;
	}

	public Long getUpdatedBy(){
		return updated_by;
	}

	public void setId(Long id){
		this.id = id;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public void setTenantId(String tenant_id){
		this.tenant_id = tenant_id;
	}

	public void setDomainName(String domain_name){
		this.domain_name = domain_name;
	}

	public void setOldName(String old_name){
		this.old_name = old_name;
	}

	public void setFileName(String file_name){
		this.file_name = file_name;
	}

	public void setExtension(String extension){
		this.extension = extension;
	}

	public void setSize(Integer size){
		this.size = size;
	}

	public void setContentType(String content_type){
		this.content_type = content_type;
	}



	public void setCreateTime(Long create_time){
		this.create_time = create_time;
	}

	public void setUpdateTime(Long update_time){
		this.update_time = update_time;
	}

	public void setCreatedBy(Long created_by){
		this.created_by = created_by;
	}

	public void setUpdatedBy(Long updated_by){
		this.updated_by = updated_by;
	}


}