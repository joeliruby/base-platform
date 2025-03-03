package com.matariky.commonservice.datasharing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matariky.commonservice.datasharing.bean.CommonSharingPool;
import com.matariky.commonservice.datasharing.mapper.CommonSharingPoolMapper;
import com.matariky.iservice.impl.BaseServiceImpl;

/**
 * Business Inteface Implementation
 * 
 * @author AUTOMATION
 */
@Service
public class CommonSharingPoolService extends BaseServiceImpl<CommonSharingPoolMapper, CommonSharingPool> {

	@Autowired
	private CommonSharingPoolMapper commonSharingPoolMapper;

	public List<CommonSharingPool> getCommonSharingPoolAll() {
		return commonSharingPoolMapper.getCommonSharingPoolAll();
	}

	public int getCommonSharingPoolAllCount() {
		return commonSharingPoolMapper.getCommonSharingPoolAllCount();
	}

	public int createCommonSharingPool(CommonSharingPool bean) {
		return commonSharingPoolMapper.createCommonSharingPool(bean);
	}

	public int updateCommonSharingPool(CommonSharingPool bean) {
		return commonSharingPoolMapper.updateCommonSharingPool(bean);
	}

	public int delCommonSharingPoolById(long l) {
		return commonSharingPoolMapper.delCommonSharingPoolById(l);
	}

	public CommonSharingPool getCommonSharingPoolById(int id) {
		return commonSharingPoolMapper.getCommonSharingPoolById(id);
	}

	public List<CommonSharingPool> selectByOrgCode(String organizationCode) {
		return commonSharingPoolMapper.selectByOrgCode(organizationCode);
	}

	public List<CommonSharingPool> getCommonSharingPoolByResourceId(Long resourceId, String tenantId) {
		return commonSharingPoolMapper.getCommonSharingPoolByResourceId(resourceId, tenantId);
	}

	public List<String> getOriginalOwnerOrgCodeList(String orgCode, String selfOrgCode) {
		return commonSharingPoolMapper.selectOriginalOwnerOrgCodes(orgCode, selfOrgCode);
	}

}
