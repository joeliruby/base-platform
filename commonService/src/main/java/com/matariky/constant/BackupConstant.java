package com.matariky.constant;

public class BackupConstant {
	
	/**
	 * 文件后缀
	 */
	public static final String FILE_SUFFIX = ".sql";
	
	public static final String DATA_BASE_NAME="qslsaas";

	/**
	 * 判断操作系统Type 、Linux|Windows
	*/
	public static boolean isSystem(String osName) {
	    Boolean flag = null;
	    if (osName.startsWith("windows")) {
	        flag = true;
	    } else if (osName.startsWith("linux")) {
	        flag = false;
	    }
	     return flag;
	}


}
