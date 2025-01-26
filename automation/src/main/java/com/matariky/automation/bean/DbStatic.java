package com.matariky.automation.bean;

import java.util.HashMap;
import java.util.Map;

public class DbStatic {

	public final static Map<String, String> DB_DRIVE = new HashMap<String, String>();

	public final static Map<String, String> DB_BEAN_ODBC = new HashMap<String, String>();

	static {
		DB_DRIVE.put("MYSQL", "com.mysql.jdbc.Driver");
		DB_BEAN_ODBC.put("VARCHAR", "java.lang.String");
		DB_BEAN_ODBC.put("CHAR", "java.lang.String");
		DB_BEAN_ODBC.put("BLOB", "java.lang.byte[]");
		DB_BEAN_ODBC.put("TEXT", "java.lang.String");
		DB_BEAN_ODBC.put("INTEGER", "java.lang.Long");
		DB_BEAN_ODBC.put("TINYINT", "java.lang.Integer");
		DB_BEAN_ODBC.put("SMALLINT", "java.lang.Integer");
		DB_BEAN_ODBC.put("MEDIUMINT", "java.lang.Integer");
		DB_BEAN_ODBC.put("BIT", "java.lang.Boolean");
		DB_BEAN_ODBC.put("BIGINT", "java.lang.Long");
		DB_BEAN_ODBC.put("FLOAT", "java.lang.Float");
		DB_BEAN_ODBC.put("DOUBLE", "java.lang.Double");
		DB_BEAN_ODBC.put("DECIMAL", "java.math.BigDecimal");
		DB_BEAN_ODBC.put("DATE", "java.util.Date");
		DB_BEAN_ODBC.put("TIME", "java.util.Date");
		DB_BEAN_ODBC.put("DATETIME", "java.util.Date");
		DB_BEAN_ODBC.put("TIMESTAMP", "java.util.Date");
		DB_BEAN_ODBC.put("YEAR", "java.util.Date");
		DB_BEAN_ODBC.put("INT", "java.lang.Integer");
		DB_BEAN_ODBC.put("VARCHAR2", "java.lang.String");
		DB_BEAN_ODBC.put("CHAR", "java.lang.String");
		DB_BEAN_ODBC.put("JSON", "java.lang.String");
	}
}
