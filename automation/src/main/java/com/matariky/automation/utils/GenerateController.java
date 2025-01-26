package com.matariky.automation.utils;

import java.util.HashMap;
import java.util.Map;

import com.matariky.automation.bean.DbBean;

public class GenerateController {

	public static StringBuffer CONTROLLER_TEMPLATE = null;

	static {
		CONTROLLER_TEMPLATE = new StringBuffer();
		CONTROLLER_TEMPLATE.append("package [package].controller;\n\n");
		CONTROLLER_TEMPLATE.append("import java.util.List;\n");
		CONTROLLER_TEMPLATE.append("import java.util.Map;\n");
		CONTROLLER_TEMPLATE.append("import java.util.HashMap;\n");
		CONTROLLER_TEMPLATE.append("import javax.servlet.http.HttpServletRequest;\n");
		CONTROLLER_TEMPLATE.append("import javax.servlet.http.HttpServletResponse;\n");
		CONTROLLER_TEMPLATE.append("import org.springframework.beans.factory.annotation.Autowired;\n");
		CONTROLLER_TEMPLATE.append("import org.springframework.http.HttpStatus;\n");
		CONTROLLER_TEMPLATE.append("import org.springframework.http.MediaType;\n");
		CONTROLLER_TEMPLATE.append("import org.springframework.http.ResponseEntity;\n");
		CONTROLLER_TEMPLATE.append("import org.springframework.web.bind.annotation.RestController;\n");
		CONTROLLER_TEMPLATE.append("import org.springframework.web.bind.annotation.RequestBody;\n");
		CONTROLLER_TEMPLATE.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
		CONTROLLER_TEMPLATE.append("import org.springframework.web.bind.annotation.RequestMethod;\n");
		CONTROLLER_TEMPLATE.append("import org.springframework.web.bind.annotation.ResponseBody;\n");
		CONTROLLER_TEMPLATE.append("import org.springframework.web.servlet.ModelAndView;\n");
		CONTROLLER_TEMPLATE.append("import com.github.pagehelper.Page;\n");
		CONTROLLER_TEMPLATE.append("import com.github.pagehelper.PageInfo;\n");
		CONTROLLER_TEMPLATE.append("import com.github.pagehelper.PageHelper;\n");
		CONTROLLER_TEMPLATE.append("import [package].bean.[tablename];\n");
		CONTROLLER_TEMPLATE.append("import [package].service.[tablename]Service;\n\n");
		CONTROLLER_TEMPLATE.append("import io.swagger.annotations.ApiParam;\n\n");
		CONTROLLER_TEMPLATE.append("import org.springframework.web.bind.annotation.PathVariable;\n\n");
		CONTROLLER_TEMPLATE.append("import org.springframework.web.bind.annotation.RequestHeader;\n\n");
		CONTROLLER_TEMPLATE.append("import org.springframework.web.bind.annotation.RequestParam;\n\n");
		CONTROLLER_TEMPLATE.append("import org.springframework.beans.factory.annotation.Value;\n\n");
		CONTROLLER_TEMPLATE.append("import com.matariky.commonservice.commondict.service.CommonDictService;\n\n");
		CONTROLLER_TEMPLATE.append("import org.springframework.web.bind.annotation.RequestBody;\n\n");
		CONTROLLER_TEMPLATE.append("import com.matariky.commonservice.commondict.bean.CommonDict;\n\n");
		CONTROLLER_TEMPLATE.append("import com.matariky.commonservice.commondict.bean.CommonDictType;\n\n");
		CONTROLLER_TEMPLATE.append("import com.matariky.commonservice.commondict.service.CommonDictService;\n\n");
		CONTROLLER_TEMPLATE.append("import com.matariky.commonservice.commondict.service.CommonDictTypeService;\n\n");
		CONTROLLER_TEMPLATE.append("import com.matariky.constant.PermissionConstant;\n\n");
		CONTROLLER_TEMPLATE.append("import com.matariky.utils.TokenUtils;\n\n");
		CONTROLLER_TEMPLATE.append("import springfox.documentation.annotations.ApiIgnore;\n\n");
		CONTROLLER_TEMPLATE.append("import com.matariky.utils.AjaxResult;\n\n");

		CONTROLLER_TEMPLATE.append("import org.springframework.beans.factory.annotation.Value;\n\n");
		CONTROLLER_TEMPLATE.append("import com.alibaba.fastjson.JSONObject;\n\n");
		CONTROLLER_TEMPLATE.append("import com.matariky.exception.QslException;\n\n");
		CONTROLLER_TEMPLATE.append("import com.matariky.commonservice.upload.constant.MessageKey;\n\n");
		CONTROLLER_TEMPLATE.append("import com.matariky.constant.PermissionConstant;\n");
		CONTROLLER_TEMPLATE.append("import com.matariky.exception.QslException;\n");
		CONTROLLER_TEMPLATE.append("/**\n");
		CONTROLLER_TEMPLATE.append("*CONTROLLER\n");
		CONTROLLER_TEMPLATE.append("* @author AUTOMATION\n");
		CONTROLLER_TEMPLATE.append("*/\n");
		CONTROLLER_TEMPLATE.append("@RestController\n");
		CONTROLLER_TEMPLATE.append("\t@RequestMapping(\"/api/v1/tenant/{tenantId}\")\n");
		CONTROLLER_TEMPLATE.append("public class [tablename]Controller {\n\n");
		CONTROLLER_TEMPLATE.append("\t@Value(\"${message.locale}\")");
		CONTROLLER_TEMPLATE.append("\tString locale;\n");
		CONTROLLER_TEMPLATE.append("\t@Autowired\n");
		CONTROLLER_TEMPLATE.append("\tprivate [tablename]Service [xtablename]Service;\n\n");
		CONTROLLER_TEMPLATE.append("\t@Autowired\n");
		CONTROLLER_TEMPLATE.append("\tprivate CommonDictService commonDictService;\n\n");
		CONTROLLER_TEMPLATE.append("\t@Autowired\n");
		CONTROLLER_TEMPLATE.append("\tprivate CommonDictTypeService commonDictTypeService;\n\n");
		CONTROLLER_TEMPLATE.append("\t//PAGINATION\n");
		CONTROLLER_TEMPLATE.append("\t@RequestMapping(\"/[xtablename]/list\")\n");
		CONTROLLER_TEMPLATE.append(
				"\tpublic Object list(HttpServletRequest request,[tablename] bean, @ApiParam(value = \"TENANTID\", required = true) @PathVariable(\"tenantId\") String tenantId, @ApiParam(value = \"PAGE INDEX\", required = true) @RequestParam(\"index\") int pageIndex,@ApiParam(value = \"PAGE SIZE\", required = true) @RequestParam(\"perPage\") int perPage, @ApiParam(value = \"JWT Token\", required = true) @RequestHeader(\"Authorization\") String jwt) {\n");
		CONTROLLER_TEMPLATE.append("\t\tPageHelper.startPage(pageIndex, perPage);\n");
		CONTROLLER_TEMPLATE.append(
				"\t\tPageInfo<[tablename]> page = new PageInfo( [xtablename]Service.get[tablename]All(bean));\n");
		CONTROLLER_TEMPLATE.append("\t\treturn new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,page);\n");
		CONTROLLER_TEMPLATE.append("\t}\n\n");

		CONTROLLER_TEMPLATE.append("\t@RequestMapping(\"/[xtablename]/daclist\")\n");
		CONTROLLER_TEMPLATE.append(
				"\tpublic Object daclist(HttpServletRequest request,@ApiIgnore @RequestParam Map<String, Object> params,	@ApiParam(value = \"TENANT ID\", required = true) @PathVariable(\"tenantId\") String tenantId,	@ApiParam(value = \"JWT Token\", required = true) @RequestHeader(\"Authorization\") String jwt) {\n");
		CONTROLLER_TEMPLATE.append("\t\t\t String hid=request.getHeader(\"id\");\n");
		CONTROLLER_TEMPLATE.append("\t\t\tString resourceIdDictKey=\"dp\"+hid.substring(0, hid.length()-1);\n");
		CONTROLLER_TEMPLATE.append(
				"\t\tCommonDictType commonDictType=commonDictTypeService.getDictTypeByKey(TokenUtils.extractTenantIdFromHttpReqeust(request),PermissionConstant.DATA_ACCESS_PERMISSION);\n");
		CONTROLLER_TEMPLATE.append(
				"\t\tCommonDict dict =commonDictService.getCommonDictByIdTenantIdAndDictType(resourceIdDictKey,tenantId,commonDictType.getId());\n");

		CONTROLLER_TEMPLATE.append("\t if (dict == null) {\n");
		CONTROLLER_TEMPLATE.append("\t	params.put(\"strategyCode\", PermissionConstant.COMMON_DATA_ACCESS_ALL);\n");
		CONTROLLER_TEMPLATE.append("\t}\n");
		CONTROLLER_TEMPLATE.append("\t else {\n");
		CONTROLLER_TEMPLATE.append("\t    params.put(\"strategyCode\", dict.getDictValue());\n");
		CONTROLLER_TEMPLATE.append("\t} \n");

		CONTROLLER_TEMPLATE.append("\t\t\tint pageIndex=Integer.parseInt(params.get(\"index\").toString());\n");
		CONTROLLER_TEMPLATE.append("\t\t\tint perPage=Integer.parseInt(params.get(\"perPage\").toString());\n");

		CONTROLLER_TEMPLATE.append("\t\tparams.put(\"tenantId\", tenantId);\n");

		CONTROLLER_TEMPLATE.append("\t\tparams.put(\"pageStart\", (pageIndex-1)*perPage);\n");
		CONTROLLER_TEMPLATE.append("\t\tparams.put(\"pageSize\", perPage);\n");
		CONTROLLER_TEMPLATE.append(
				"\t\tList<[tablename]> commonDictList =[xtablename]Service.get[tablename]DAC(params, request);\n");
		CONTROLLER_TEMPLATE.append("\t\tLong count=[xtablename]Service.get[tablename]DACCount(params, request);\n");
		CONTROLLER_TEMPLATE.append("\t\tPageInfo<[tablename]> page= new PageInfo<[tablename]>(commonDictList);\n");
		CONTROLLER_TEMPLATE.append("\t\tpage.setTotal(count);\n");
		CONTROLLER_TEMPLATE.append("\t\tpage.setPageSize(perPage);\n");
		CONTROLLER_TEMPLATE.append("\t\tpage.setPageNum(pageIndex);\n");
		CONTROLLER_TEMPLATE.append(
				"\t\tpage.setPages(Integer.parseInt(new Long(count%new Long(perPage) ==0 ? count%new Long(perPage) :count%new Long(perPage) +1).toString()));");
		CONTROLLER_TEMPLATE.append("\t\treturn new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,page);\n");
		CONTROLLER_TEMPLATE.append("\t}\n\n");

		CONTROLLER_TEMPLATE.append("\t//SAVE\n");
		CONTROLLER_TEMPLATE.append("\t@RequestMapping(value = \"/[xtablename]\",method = RequestMethod.POST)\n");
		CONTROLLER_TEMPLATE.append(
				"\tpublic Object save(@RequestBody [tablename] bean,HttpServletRequest request, HttpServletResponse response) {\n");
		CONTROLLER_TEMPLATE.append("\t\ttry{\n");
		CONTROLLER_TEMPLATE
				.append("\t\t\tint success = [xtablename]Service.create[tablename]WithOrg(bean, request);\n");
		CONTROLLER_TEMPLATE.append("\t\t\t\tif(success > 0){\n");
		CONTROLLER_TEMPLATE.append("\t\t\t\t\treturn new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,null);\n");
		CONTROLLER_TEMPLATE.append("\t\t\t\t}else{\n");
		CONTROLLER_TEMPLATE.append("\t\t\t\t\t throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);\n");
		CONTROLLER_TEMPLATE.append("\t\t\t\t}\n");
		CONTROLLER_TEMPLATE.append("\t\t}catch (Exception e){\n");
		CONTROLLER_TEMPLATE.append("\t\t\te.printStackTrace();\n");
		CONTROLLER_TEMPLATE.append("\t\t\t throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);\n");
		CONTROLLER_TEMPLATE.append("\t\t}\n");
		CONTROLLER_TEMPLATE.append("\t}\n\n");
		CONTROLLER_TEMPLATE.append("\t//UPDATE\n");
		CONTROLLER_TEMPLATE.append("\t@RequestMapping(value = \"/[xtablename]\",method = RequestMethod.PUT)\n");
		CONTROLLER_TEMPLATE.append(
				"\tpublic Object update(@RequestBody [tablename] bean,HttpServletRequest request, HttpServletResponse response) {\n");
		CONTROLLER_TEMPLATE.append("\t\ttry{\n");
		CONTROLLER_TEMPLATE.append("\t\t\tint success = [xtablename]Service.update[tablename](bean);\n");
		CONTROLLER_TEMPLATE.append("\t\t\t\tif(success > 0){\n");
		CONTROLLER_TEMPLATE
				.append("\t\t\t\t\treturn new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS, null);\n");
		CONTROLLER_TEMPLATE.append("\t\t\t\t}else{\n");
		CONTROLLER_TEMPLATE.append("\t\t\t\t\t throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);\n");
		CONTROLLER_TEMPLATE.append("\t\t\t\t}\n");
		CONTROLLER_TEMPLATE.append("\t\t}catch (Exception e){\n");
		CONTROLLER_TEMPLATE.append("\t\t\te.printStackTrace();\n");
		CONTROLLER_TEMPLATE.append("\t\t\t throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);\n");
		CONTROLLER_TEMPLATE.append("\t\t}\n");
		CONTROLLER_TEMPLATE.append("\t}\n\n");
		CONTROLLER_TEMPLATE.append("\t//DELETE\n");
		CONTROLLER_TEMPLATE.append("\t@RequestMapping(value = \"/[xtablename]\",method = RequestMethod.DELETE)\n");
		CONTROLLER_TEMPLATE
				.append("\tpublic Object del(String id,HttpServletRequest request, HttpServletResponse response) {\n");
		CONTROLLER_TEMPLATE.append("\t\ttry{\n");
		CONTROLLER_TEMPLATE.append("\t\t\tBoolean success = [xtablename]Service.deleteById(Long.parseLong(id));\n");
		CONTROLLER_TEMPLATE.append("\t\t\t\tif(success ){\n");
		CONTROLLER_TEMPLATE.append("\t\t\t\t\treturn new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,null);\n");
		CONTROLLER_TEMPLATE.append("\t\t\t\t}else{\n");
		CONTROLLER_TEMPLATE.append("\t\t\t\t\t throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);\n");
		CONTROLLER_TEMPLATE.append("\t\t\t\t}\n");
		CONTROLLER_TEMPLATE.append("\t\t}catch (Exception e){\n");
		CONTROLLER_TEMPLATE.append("\t\t\te.printStackTrace();\n");
		CONTROLLER_TEMPLATE.append("\t\t\t throw new QslException(MessageKey.ERROR_INSERTING_DATABASE);\n");
		CONTROLLER_TEMPLATE.append("\t\t}\n");
		CONTROLLER_TEMPLATE.append("\t}\n\n");

		CONTROLLER_TEMPLATE.append("\t//QUERY BY ID\n");
		CONTROLLER_TEMPLATE
				.append("\t@RequestMapping(value = \"/[xtablename]/{[xtablename]Id}\",method = RequestMethod.GET)\n");
		CONTROLLER_TEMPLATE.append(
				"\tpublic Object getOne(@PathVariable(\"/[xtablename]Id\") Long id, HttpServletRequest request, HttpServletResponse response) {\n");
		CONTROLLER_TEMPLATE.append(
				"\t\t\t return new AjaxResult(HttpStatus.OK.value(),AjaxResult.SUCCESS,[xtablename]Service.selectById(id));\n");
		CONTROLLER_TEMPLATE.append("}\n\n\n");
		CONTROLLER_TEMPLATE.append("}");
	}

	public static String get_controller(DbBean model) {
		Map<String, String> data = new HashMap<String, String>();
		String className = GenerateJava.captureName(model.getTablename());
		data.put("tablename", className);
		data.put("xtablename", firstLetterLowerCase(className));
		data.put("dbtablename", model.getTablename().toLowerCase());
		data.put("package", model.getCompages());
		return StringTemplateUtils.render(CONTROLLER_TEMPLATE.toString(), data);
	}

	private static String firstLetterLowerCase(String className) {
		return className.substring(0, 1).toLowerCase() + className.substring(1);
	}
}
