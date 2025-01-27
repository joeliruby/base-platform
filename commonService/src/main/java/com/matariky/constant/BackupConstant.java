package com.matariky.constant;

public class BackupConstant {

	/**
	 * File suffix
	 */
	public static final String FILE_SUFFIX = ".sql";

	public static final String DATA_BASE_NAME = "qslsaas";

	/**
	 * Judgment Operation SystemType 、Linux|Windows
	 */
	public static boolean isSystem(String osName) {
		Boolean flag = false;
		if (osName.startsWith("windows")) {
			flag = true;
		} else if (osName.startsWith("linux")) {
			flag = false;
		}
		return flag;
	}

}
