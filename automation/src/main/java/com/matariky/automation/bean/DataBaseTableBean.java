package com.matariky.automation.bean;

public class DataBaseTableBean {

	private String name;
	private String type;
	private String isnull;
	private String length;
	private String bz;
	private String qtype;
	private long size;
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getIsnull() {
		return isnull;
	}

	public String getLength() {
		return length;
	}

	public String getBz() {
		return bz;
	}

	public String getQtype() {
		return qtype;
	}

	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public void setQtype(String qtype) {
		this.qtype = qtype;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public long getSize() {
		return size;
	}
}
