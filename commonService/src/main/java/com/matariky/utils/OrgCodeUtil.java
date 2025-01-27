package com.matariky.utils;

public class OrgCodeUtil {
	// Organization category constants
	public static final Integer ORG_TYPE_INDIVIDUAL = 4; // Individual
	public static final Integer ORG_TYPE_GROUP = 3; // Group
	public static final Integer ORG_TYPE_DEPARTMENT = 2; // Department
	public static final Integer ORG_TYPE_ORGANIZATION = 1; // Organization

	// Individual organization code rule: ind_<departmentOrgCode>_<userId>
	public static String generateSelfOrganizationCode(String departmentOrgCode, Long userId) {
		return "ind_" + departmentOrgCode + "_" + userId;
	}

	// Department organization code rule: org_<parentId>_<id> if under tenant level
	// 0: org_<tenantId>_<id>
	public static String generateDeparmentOrganzationCode(Long parentId, Long id) {
		return "org_" + parentId + "_" + id;
	}

	public static String generateDeparmentOrganzationCodeByParentOrgCode(String parentCode, Long orgId) {
		return parentCode + "_" + orgId;
	}

	public static String generateOrgCodeFromOrgBean(String parentOrgCode, Long orgId, Integer orgType) {
		String codeString = null;
		if (orgType.equals(OrgCodeUtil.ORG_TYPE_INDIVIDUAL)) { // Temporarily hardcoded, will read from dictionary in
																// the future
			codeString = OrgCodeUtil.generateSelfOrganizationCode(parentOrgCode, orgId);
		} else {
			codeString = OrgCodeUtil.generateDeparmentOrganzationCodeByParentOrgCode(parentOrgCode, orgId);
		}
		return codeString;
	}
}
