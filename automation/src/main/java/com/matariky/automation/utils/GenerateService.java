package com.matariky.automation.utils;

import java.util.HashMap;
import java.util.Map;

import com.matariky.automation.bean.DbBean;

public class GenerateService {

	public static StringBuffer SERVICE_TEMPLATE = null;

	static {
		SERVICE_TEMPLATE = new StringBuffer();
		SERVICE_TEMPLATE.append("package [package].service;\n\n");
		SERVICE_TEMPLATE.append("import java.util.List;\n");
		SERVICE_TEMPLATE.append("import java.util.Map;\n");
		SERVICE_TEMPLATE.append("import java.util.HashMap;\n");
		SERVICE_TEMPLATE.append("import org.springframework.beans.factory.annotation.Autowired;\n");
		SERVICE_TEMPLATE.append("import org.springframework.stereotype.Service;\n\n");
		SERVICE_TEMPLATE.append("import com.github.pagehelper.Page;\n");
		SERVICE_TEMPLATE.append("import com.matariky.iservice.BaseService;\n");
		SERVICE_TEMPLATE.append("import com.matariky.iservice.impl.BaseServiceImpl;\n");
		SERVICE_TEMPLATE.append("import [package].bean.[tablename];\n");
		SERVICE_TEMPLATE.append("import [package].mapper.[tablename]Mapper;\n\n");
		SERVICE_TEMPLATE.append("import java.util.ArrayList;\n\n");
		SERVICE_TEMPLATE.append(" import java.util.HashMap;\n\n");
		SERVICE_TEMPLATE.append(" import javax.servlet.http.HttpServletRequest;\n\n");
		SERVICE_TEMPLATE.append(" import com.github.pagehelper.util.StringUtil;\n\n");
		SERVICE_TEMPLATE.append(" import com.matariky.commonservice.accesslog.bean.CommonAccessLog;\n\n");
		SERVICE_TEMPLATE.append(" import com.matariky.commonservice.accesslog.mapper.CommonAccessLogMapper;\n\n");
		SERVICE_TEMPLATE.append(" import com.matariky.commonservice.datasharing.bean.CommonSharingPool;\n\n");
		SERVICE_TEMPLATE.append(" import com.matariky.commonservice.datasharing.service.CommonSharingPoolService;\n\n");
		SERVICE_TEMPLATE.append(" import com.matariky.constant.PermissionConstant;\n\n");
		SERVICE_TEMPLATE.append(" import com.matariky.utils.TokenUtils;\n\n");
		SERVICE_TEMPLATE.append(" import javax.servlet.http.HttpServletRequest;\n\n");
		SERVICE_TEMPLATE.append(" import com.matariky.commonservice.commondict.service.CommonDictService;\n\n");
		SERVICE_TEMPLATE.append(" import com.matariky.commonservice.commondict.service.CommonDictTypeService;\n\n");
		SERVICE_TEMPLATE.append(" import com.github.pagehelper.PageHelper;\n\n");
		SERVICE_TEMPLATE.append(" import com.matariky.commonservice.commondict.bean.CommonDict;\n\n");
		SERVICE_TEMPLATE.append(" import com.matariky.commonservice.commondict.bean.CommonDictType;\n\n");
		SERVICE_TEMPLATE.append("/**\n");
		SERVICE_TEMPLATE.append("*BUSINESS INTERFACE INTEGRATION\n");
		SERVICE_TEMPLATE.append("* @author AUTOMATION\n");
		SERVICE_TEMPLATE.append("*/\n");
		SERVICE_TEMPLATE.append("@Service\n");
		SERVICE_TEMPLATE.append(
				"public class [tablename]Service extends BaseServiceImpl<[tablename]Mapper,[tablename]> implements BaseService<[tablename]>{\n\n");
		SERVICE_TEMPLATE.append("\t@Autowired\n");
		SERVICE_TEMPLATE.append("\tprivate [tablename]Mapper [cameltablename]Mapper;\n\n");
		SERVICE_TEMPLATE.append("\t@Autowired\n\n");
		SERVICE_TEMPLATE.append("\tprivate HttpServletRequest request;\n\n");
		SERVICE_TEMPLATE.append("\t@Autowired\n\n");
		SERVICE_TEMPLATE.append("\t private CommonDictService commonDictService;\n\n");
		SERVICE_TEMPLATE.append("\t @Autowired\n\n");
		SERVICE_TEMPLATE.append("\t private CommonDictTypeService commonDictTypeService;\n\n");
		SERVICE_TEMPLATE.append("\t//GET ALL\n");
		SERVICE_TEMPLATE.append("\tpublic List<[tablename]> get[tablename]All([tablename] vo){\n");
		SERVICE_TEMPLATE.append("\t\tString hid = request.getHeader(\"id\");\n\n");
		SERVICE_TEMPLATE.append("\t\tString resourceIdDictKey = \"dp\" + hid.substring(0, hid.length() - 1);\n\n");
		SERVICE_TEMPLATE.append("\t\tString tenantId = TokenUtils.extractTenantIdFromHttpReqeust(request);\n\n");
		SERVICE_TEMPLATE.append(
				"\t\tCommonDictType commonDictType = commonDictTypeService.getDictTypeByKey(TokenUtils.extractTenantIdFromHttpReqeust(request), PermissionConstant.DATA_ACCESS_PERMISSION);\n\n");
		SERVICE_TEMPLATE.append(
				"\t\tCommonDict dict = commonDictService.getCommonDictByIdTenantIdAndDictType(resourceIdDictKey, tenantId, commonDictType.getId());\n\n");
		SERVICE_TEMPLATE.append("\t\t if (dict == null) {\n\n");
		SERVICE_TEMPLATE.append("\t\t    vo.setStrategyCode(PermissionConstant.COMMON_DATA_ACCESS_ALL);\n\n");
		SERVICE_TEMPLATE.append("\t\t } else {\n\n");
		SERVICE_TEMPLATE.append("\t\t     vo.setStrategyCode(dict.getDictValue());\n\n");
		SERVICE_TEMPLATE.append("\t\t}\n\n");
		SERVICE_TEMPLATE.append("\t\tvo.setSelfOrgCode(TokenUtils.extractSelfOrgCode(request));\n\n");
		SERVICE_TEMPLATE.append("\t\t vo.setTenantId(tenantId);\n\n");
		SERVICE_TEMPLATE.append(
				"\t\tPageHelper.startPage(Integer.parseInt(request.getParameter(\"index\")), Integer.parseInt(request.getParameter(\"perPage\")));\n\n");

		SERVICE_TEMPLATE.append("\t\treturn [cameltablename]Mapper.get[tablename]All(vo);\n");
		SERVICE_TEMPLATE.append("\t}\n\n");
		SERVICE_TEMPLATE.append("\t//QUERY COUNT ALL\n");
		SERVICE_TEMPLATE.append("\tpublic int get[tablename]AllCount(){\n");
		SERVICE_TEMPLATE.append("\t\treturn [cameltablename]Mapper.get[tablename]AllCount();\n");
		SERVICE_TEMPLATE.append("\t}\n\n");
		SERVICE_TEMPLATE.append("\t//NEW \n");
		SERVICE_TEMPLATE.append("\tpublic int create[tablename]([tablename] bean){\n");
		SERVICE_TEMPLATE.append("\t\treturn [cameltablename]Mapper.create[tablename](bean);\n");
		SERVICE_TEMPLATE.append("\t}\n\n");
		SERVICE_TEMPLATE.append("\t//UPDATE \n");

		SERVICE_TEMPLATE.append("\t//NEW\n");
		SERVICE_TEMPLATE
				.append("\tpublic int create[tablename]WithOrg([tablename] bean, HttpServletRequest request){\n");
		SERVICE_TEMPLATE.append("\tbean.setOperatorOrgCode(TokenUtils.extractOrgCode(request));\n");
		SERVICE_TEMPLATE.append("\t	bean.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));\n");
		SERVICE_TEMPLATE.append("\tbean.setCreateTime(System.currentTimeMillis());\n");
		SERVICE_TEMPLATE
				.append("\tbean.setCreateBy(Long.parseLong(TokenUtils.extractUserIdFromHttpReqeust(request)));\n");
		SERVICE_TEMPLATE.append("\tbean.setTenantId(TokenUtils.extractTenantIdFromHttpReqeust(request));\n");
		SERVICE_TEMPLATE.append("\tbean.setDeleteTime(0l);\n");
		SERVICE_TEMPLATE.append("\t	return [cameltablename]Mapper.create[tablename](bean);\n");
		SERVICE_TEMPLATE.append("\t}\n");

		SERVICE_TEMPLATE.append("\tpublic int update[tablename]([tablename] bean){\n");
		SERVICE_TEMPLATE.append("\t\treturn [cameltablename]Mapper.updateById(bean);\n");
		SERVICE_TEMPLATE.append("\t}\n\n");
		SERVICE_TEMPLATE.append("\t//DELETE\n");
		SERVICE_TEMPLATE.append("\tpublic int del[tablename]ById(int id){\n");
		SERVICE_TEMPLATE.append("\t\treturn [cameltablename]Mapper.del[tablename]ById(id);\n");
		SERVICE_TEMPLATE.append("\t}\n\n");
		SERVICE_TEMPLATE.append("\t//QUERY OBJECT BY ID\n");
		SERVICE_TEMPLATE.append("\tpublic [tablename] get[tablename]ById(int id){\n");
		SERVICE_TEMPLATE.append("\t\treturn [cameltablename]Mapper.get[tablename]ById(id);\n");
		SERVICE_TEMPLATE.append("\t}\n\n");

		SERVICE_TEMPLATE.append(
				"\tpublic List<[tablename]> get[tablename]DAC(Map<String, Object> params, HttpServletRequest request) {\n");

		SERVICE_TEMPLATE.append("\t\tstrategyCodeToParams(params, request);\n");

		SERVICE_TEMPLATE.append("\t\treturn [cameltablename]Mapper.get[tablename]DAC( params);\n");
		SERVICE_TEMPLATE.append("\t}\n\n");

		SERVICE_TEMPLATE.append(
				"\tpublic Long get[tablename]DACCount(Map<String, Object> params,HttpServletRequest request) {\n\n");

		SERVICE_TEMPLATE.append("\t\tString strategyCode =(String)params.get(\"strategyCode\");\n");
		SERVICE_TEMPLATE.append("\t\tif(StringUtil.isEmpty(strategyCode))\n");
		SERVICE_TEMPLATE
				.append("\t\tstrategyCode=PermissionConstant.COMMON_DATA_ACCESS_PRIVATE;//VISIBLE BY INDIVIDUAL\n");
		SERVICE_TEMPLATE.append("\t\tswitch (strategyCode) {\n");
		SERVICE_TEMPLATE.append(
				"\t\tcase PermissionConstant.COMMON_DATA_ACCESS_PRIVATE://VISIBLE BY INDIVIDUAL WITH SHARING RULES\n");
		SERVICE_TEMPLATE
				.append("\t\t\tMap<String, List<String>> sharingOrgCodes0=extractedSharingOrgCodes(request);\n");
		SERVICE_TEMPLATE.append("\t\t\tparams.put(\"selfOrgCode\", TokenUtils.extractSelfOrgCode(request));\n");
		SERVICE_TEMPLATE.append("\t\t\tparams.putAll(sharingOrgCodes0);\n");
		SERVICE_TEMPLATE.append("\t\t\tbreak;\n");
		SERVICE_TEMPLATE.append("\t\tcase PermissionConstant.COMMON_DATA_ACCESS_ALL://ALL VISIBLE TO ALL\n");
		SERVICE_TEMPLATE.append("\t\t\tbreak;\n");
		SERVICE_TEMPLATE.append("\t\tcase PermissionConstant.COMMON_DATA_ACCESS_ORG://VIEW ORG AND ALL SUBORG\n");
		SERVICE_TEMPLATE
				.append("\t\t\tMap<String, List<String>> sharingOrgCodes3=extractedSharingOrgCodes(request);\n");
		SERVICE_TEMPLATE.append("\t\t\tparams.put(\"orgCode\", TokenUtils.extractOrgCode(request));\n");
		SERVICE_TEMPLATE.append("\t\t\tparams.putAll(sharingOrgCodes3);\n");
		SERVICE_TEMPLATE.append("\t\t\tbreak;\n");

		SERVICE_TEMPLATE.append(
				"\t\tcase PermissionConstant.COMMON_DATA_ACCESS_LEVEL://ORG VISIBLE WITH SPECIAL SHARING RULE NO SUB ORGS\n");
		SERVICE_TEMPLATE
				.append("\t\t\tMap<String, List<String>> sharingOrgCodes2=extractedSharingOrgCodes(request);\n");
		SERVICE_TEMPLATE.append("\t\t\tparams.put(\"orgCode\", TokenUtils.extractOrgCode(request));\n");
		SERVICE_TEMPLATE.append("\t\t\tparams.putAll(sharingOrgCodes2);\n");
		SERVICE_TEMPLATE.append("\t\t\tbreak;\n");

		SERVICE_TEMPLATE.append("\t\tdefault:\n");
		SERVICE_TEMPLATE.append("\t\t\tbreak;\n");
		SERVICE_TEMPLATE.append("\t\t}\n\n");

		SERVICE_TEMPLATE.append("\t\treturn [cameltablename]Mapper.get[tablename]DACCount(params);\n");
		SERVICE_TEMPLATE.append("\t}\n\n");

		SERVICE_TEMPLATE.append("}\n");

	}

	public static String get_Service(DbBean model) {
		Map<String, String> data = new HashMap<String, String>();
		data.put("tablename", GenerateJava.captureName(model.getTablename()));
		data.put("cameltablename", GenerateJava.firstLetterLowerCase(data.get("tablename")));
		data.put("xtablename", model.getTablename().toLowerCase());
		data.put("package", model.getCompages());
		return StringTemplateUtils.render(SERVICE_TEMPLATE.toString(), data);
	}

}
