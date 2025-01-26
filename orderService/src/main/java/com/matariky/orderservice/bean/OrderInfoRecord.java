package com.matariky.orderservice.bean;


import java.lang.String;
import java.math.BigDecimal;
import java.lang.Long;
import java.lang.Integer;
/**
* Automatically generated entity class
* @author AUTOMATION
*/
public class OrderInfoRecord {

	private Long id;
	private Long recordType;
	private String operatorOrgCode;
	private String operatorSelfOrgCode;
	private String orderCode;
	private String orderShowCode;
	private String contractCode;
	private String tenantId;
	private String orderTenantId;
	private String suiteCode;
	private String suiteConfigCode;
	private String contacts;
	private String contactsPhone;
	private Long expirationStartTime;
	private Long expirationEndTime;
	private Integer accountNumber;
	private String chargeMode;
	private BigDecimal price;
	private BigDecimal discountPrice;
	private Long paymentTime;
	private String paymentVoucherPath;
	private String orderStatus;
	private String remark;
	private Long createdBy;
	private Long createTime;
	private Long updateBy;
	private Long updateTime;
	private Long deleteTime;
	public Long getId(){
		return id;
	}

	public String getOperatorOrgCode(){
		return operatorOrgCode;
	}

	public String getOperatorSelfOrgCode(){
		return operatorSelfOrgCode;
	}

	public String getOrderCode(){
		return orderCode;
	}

	public String getOrderShowCode(){
		return orderShowCode;
	}

	public String getContractCode(){
		return contractCode;
	}

	public String getTenantId(){
		return tenantId;
	}

	public String getSuiteCode(){
		return suiteCode;
	}

	public String getContacts(){
		return contacts;
	}

	public String getContactsPhone(){
		return contactsPhone;
	}

	public Integer getAccountNumber(){
		return accountNumber;
	}

	public String getChargeMode(){
		return chargeMode;
	}

	public BigDecimal getPrice(){
		return price;
	}

	public BigDecimal getDiscountPrice(){
		return discountPrice;
	}

	public Long getPaymentTime(){
		return paymentTime;
	}

	public String getPaymentVoucherPath(){
		return paymentVoucherPath;
	}

	public String getOrderStatus(){
		return orderStatus;
	}

	public String getRemark(){
		return remark;
	}

	public Long getCreatedBy(){
		return createdBy;
	}

	public Long getCreateTime(){
		return createTime;
	}

	public Long getUpdateBy(){
		return updateBy;
	}

	public Long getUpdateTime(){
		return updateTime;
	}

	public Long getDeleteTime(){
		return deleteTime;
	}

	public void setId(Long id){
		this.id = id;
	}

	public void setOperatorOrgCode(String operatorOrgCode){
		this.operatorOrgCode = operatorOrgCode;
	}

	public void setOperatorSelfOrgCode(String operatorSelfOrgCode){
		this.operatorSelfOrgCode = operatorSelfOrgCode;
	}

	public void setOrderCode(String orderCode){
		this.orderCode = orderCode;
	}

	public void setOrderShowCode(String orderShowCode){
		this.orderShowCode = orderShowCode;
	}

	public void setContractCode(String contractCode){
		this.contractCode = contractCode;
	}

	public void setTenantId(String tenantId){
		this.tenantId = tenantId;
	}

	public void setSuiteCode(String suiteCode){
		this.suiteCode = suiteCode;
	}

	public void setContacts(String contacts){
		this.contacts = contacts;
	}

	public void setContactsPhone(String contactsPhone){
		this.contactsPhone = contactsPhone;
	}

	public void setAccountNumber(Integer accountNumber){
		this.accountNumber = accountNumber;
	}

	public void setChargeMode(String chargeMode){
		this.chargeMode = chargeMode;
	}

	public void setPrice(BigDecimal price){
		this.price = price;
	}

	public void setDiscountPrice(BigDecimal discountPrice){
		this.discountPrice = discountPrice;
	}

	public void setPaymentTime(Long paymentTime){
		this.paymentTime = paymentTime;
	}

	public void setPaymentVoucherPath(String paymentVoucherPath){
		this.paymentVoucherPath = paymentVoucherPath;
	}

	public void setOrderStatus(String orderStatus){
		this.orderStatus = orderStatus;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public void setCreatedBy(Long createdBy){
		this.createdBy = createdBy;
	}

	public void setCreateTime(Long createTime){
		this.createTime = createTime;
	}

	public void setUpdateBy(Long updateBy){
		this.updateBy = updateBy;
	}

	public void setUpdateTime(Long updateTime){
		this.updateTime = updateTime;
	}

	public void setDeleteTime(Long deleteTime){
		this.deleteTime = deleteTime;
	}

	public Long getRecordType() {
		return recordType;
	}

	public void setRecordType(Long recordType) {
		this.recordType = recordType;
	}

	public Long getExpirationStartTime() {
		return expirationStartTime;
	}

	public void setExpirationStartTime(Long expirationStartTime) {
		this.expirationStartTime = expirationStartTime;
	}

	public Long getExpirationEndTime() {
		return expirationEndTime;
	}

	public void setExpirationEndTime(Long expirationEndTime) {
		this.expirationEndTime = expirationEndTime;
	}

	public String getOrderTenantId() {
		return orderTenantId;
	}

	public void setOrderTenantId(String orderTenantId) {
		this.orderTenantId = orderTenantId;
	}

	public String getSuiteConfigCode() {
		return suiteConfigCode;
	}

	public void setSuiteConfigCode(String suiteConfigCode) {
		this.suiteConfigCode = suiteConfigCode;
	}
}