package com.matariky.orderservice.bean;


import java.lang.String;
import java.math.BigDecimal;
import java.lang.Long;
/**
* Automatically generated entity class
* @author AUTOMATION
*/
public class OrderSuiteConfig {

	private Long id;
	private String suiteConfigCode;
	private String suiteCode;
	private Long numberStart;
	private Long numberEnd;
	private BigDecimal yearPrice;
	private BigDecimal averagePrice;
	public Long getId(){
		return id;
	}

	public String getSuiteCode(){
		return suiteCode;
	}

	public Long getNumberStart(){
		return numberStart;
	}

	public Long getNumberEnd(){
		return numberEnd;
	}

	public BigDecimal getYearPrice(){
		return yearPrice;
	}

	public BigDecimal getAveragePrice(){
		return averagePrice;
	}

	public void setId(Long id){
		this.id = id;
	}

	public void setSuiteCode(String suiteCode){
		this.suiteCode = suiteCode;
	}

	public void setNumberStart(Long numberStart){
		this.numberStart = numberStart;
	}

	public void setNumberEnd(Long numberEnd){
		this.numberEnd = numberEnd;
	}

	public void setYearPrice(BigDecimal yearPrice){
		this.yearPrice = yearPrice;
	}

	public void setAveragePrice(BigDecimal averagePrice){
		this.averagePrice = averagePrice;
	}

	public String getSuiteConfigCode() {
		return suiteConfigCode;
	}

	public void setSuiteConfigCode(String suiteConfigCode) {
		this.suiteConfigCode = suiteConfigCode;
	}
}