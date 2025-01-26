package com.matariky.automation.utils;

import java.util.HashMap;
import java.util.Map;

import com.matariky.automation.bean.DbBean;
import com.matariky.automation.bean.DbStatic;

public class GenerateJava {

	public static StringBuffer BEAN_TOP_TEMPLATE = null;
	public static StringBuffer BEAN_ATTRIBUTE_TEMPLATE = null;
	public static StringBuffer BEAN_GET_TEMPLATE = null;
	public static StringBuffer BEAN_SET_TEMPLATE = null;

	static {
		BEAN_TOP_TEMPLATE = new StringBuffer();
		BEAN_ATTRIBUTE_TEMPLATE = new StringBuffer();
		BEAN_GET_TEMPLATE = new StringBuffer();
		BEAN_SET_TEMPLATE = new StringBuffer();
	}

	public static String GenerateJavaCode(DbBean model) {
		StringBuffer str = new StringBuffer();
		str.append(get_filetop(model));
		str.append(get_fileattribute(model));
		return str.toString() + "\n}";
	}

	public static String get_fileSetMethon(DbBean model) {
		BEAN_SET_TEMPLATE.setLength(0);
		String[] obj = model.getZdname().split(",");
		for (String value : obj) {
			String[] svalue = value.split("--");
			String key = DbStatic.DB_BEAN_ODBC.get(svalue[1].toUpperCase()).toString();
			key = key.substring(key.indexOf(".", key.indexOf(".") + 1) + 1, key.length());
			String moth = captureName(svalue[0].toLowerCase());
			String fllMoth = firstLetterLowerCase(moth);
			BEAN_SET_TEMPLATE.append("\tpublic void set" + moth + "(" + key + " " + fllMoth + "){\n");
			BEAN_SET_TEMPLATE.append("\t\tthis." + fllMoth + " = " + fllMoth + ";\n");
			BEAN_SET_TEMPLATE.append("\t}\n\n");
		}
		return BEAN_SET_TEMPLATE.toString();
	}

	public static String get_fileGetMethon(DbBean model) {
		BEAN_GET_TEMPLATE.setLength(0);
		String[] obj = model.getZdname().split(",");
		for (String value : obj) {
			String[] svalue = value.split("--");
			String key = DbStatic.DB_BEAN_ODBC.get(svalue[1].toUpperCase()).toString();
			key = key.substring(key.indexOf(".", key.indexOf(".") + 1) + 1, key.length());
			String moth = captureName(svalue[0].toLowerCase());
			BEAN_GET_TEMPLATE.append("\tpublic " + key + " get" + moth + "(){\n");
			BEAN_GET_TEMPLATE.append("\t\treturn " + firstLetterLowerCase(moth) + ";\n");
			BEAN_GET_TEMPLATE.append("\t}\n\n");
		}
		return BEAN_GET_TEMPLATE.toString();
	}

	public static String get_fileattribute(DbBean model) {
		BEAN_ATTRIBUTE_TEMPLATE.setLength(0);
		String[] obj = model.getZdname().split(",");
		for (String value : obj) {
			String[] svalue = value.split("--");
			String key = DbStatic.DB_BEAN_ODBC.get(svalue[1].toUpperCase()).toString();
			key = key.substring(key.indexOf(".", key.indexOf(".") + 1) + 1, key.length());
			BEAN_ATTRIBUTE_TEMPLATE.append(
					"\tprivate " + key + " " + firstLetterLowerCase(captureName(svalue[0].toLowerCase())) + ";\n");
		}
		return BEAN_ATTRIBUTE_TEMPLATE.toString();
	}

	public static String get_filetop(DbBean model) {
		BEAN_TOP_TEMPLATE.setLength(0);
		BEAN_TOP_TEMPLATE.append("package " + model.getCompages() + ".bean;\n\n");
		String[] obj = model.getZdname().split(",");
		Map<String, String> map = new HashMap<String, String>();
		for (String value : obj) {
			String[] svalue = value.split("--");
			map.put(svalue[1].toUpperCase(), svalue[0]);
		}

		for (String key : map.keySet()) {
			BEAN_TOP_TEMPLATE.append("import " + DbStatic.DB_BEAN_ODBC.get(key) + ";\n");
			BEAN_TOP_TEMPLATE.append("import com.matariky.model.QueryDataIsolation;\n");
			BEAN_TOP_TEMPLATE.append("import lombok.Data;\n");
		}
		BEAN_TOP_TEMPLATE.append("/**\n");
		BEAN_TOP_TEMPLATE.append("*AUTO GEN ENTITY\n");
		BEAN_TOP_TEMPLATE.append("* @author AUTOMATION\n");
		BEAN_TOP_TEMPLATE.append("*/\n");
		BEAN_TOP_TEMPLATE.append("@Data\n");
		BEAN_TOP_TEMPLATE
				.append("public class " + captureName(model.getTablename()) + " extends  QueryDataIsolation {\n\n");
		return BEAN_TOP_TEMPLATE.toString();
	}

	public static String captureName(String name) {
		String returnName = "";
		for (String subName : name.split("_"))
			returnName = returnName + firstLetterUpCase(subName);
		return returnName;

	}

	private static String firstLetterUpCase(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	public static String firstLetterLowerCase(String name) {
		return name.substring(0, 1).toLowerCase() + name.substring(1);
	}
}
