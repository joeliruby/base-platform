package com.matariky.utils;


public class OrgCodeUtil {
	//机构类别常量
	public static final Integer ORG_TYPE_INDIVIDUAL=4;//个人
	public static final Integer ORG_TYPE_GROUP=3;//小组
	public static final Integer ORG_TYPE_DEPARTMENT=2;//部门
	public static final Integer ORG_TYPE_ORGANIZATION=1;//机构
	
	//个人组织机构编码 Rule ：ind_所在部门机构编码_个人用户Id
	public static String generateSelfOrganizationCode(String departmentOrgCode, Long userId) {
		return "ind_"+departmentOrgCode + "_" + userId;
	}

	//部门机构编码 Rule  org_父机构ID_本机构id 如果是 Tenant 下0级 org_ Tenant ID_本机构id
	public static String generateDeparmentOrganzationCode(Long parentId, Long id) {
		
		return "org_"+parentId+"_"+id;
	}

	public static String generateDeparmentOrganzationCodeByParentOrgCode(String parentCode, Long orgId) {
		return parentCode+"_"+orgId;
	}

	public static String generateOrgCodeFromOrgBean(String parentOrgCode, Long orgId, Integer orgType) {
		String codeString=null;
		if(orgType.equals(OrgCodeUtil.ORG_TYPE_INDIVIDUAL)){//暂时写死以后从字典里读
			 codeString=OrgCodeUtil.generateSelfOrganizationCode(parentOrgCode,orgId);
		}
		else
			codeString=OrgCodeUtil.generateDeparmentOrganzationCodeByParentOrgCode(parentOrgCode,orgId);
		return codeString;
	}
}
