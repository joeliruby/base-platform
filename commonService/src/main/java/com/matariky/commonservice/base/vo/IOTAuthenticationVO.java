package com.matariky.commonservice.base.vo;

import java.util.List;
import java.util.Map;

public class IOTAuthenticationVO {
	private String signature;
	List<Map<String,String>> tagValidity;
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public List<Map<String, String>> getTagValidity() {
		return tagValidity;
	}
	public void setTagValidity(List<Map<String, String>> tagValidity) {
		this.tagValidity = tagValidity;
	}
}
