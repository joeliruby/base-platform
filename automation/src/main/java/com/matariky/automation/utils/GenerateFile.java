package com.matariky.automation.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GenerateFile {

	public static Map<String, String> initDir(String URL, String keyParam, String mk, String name) throws IOException {
		Map<String, String> map = new HashMap<>();
		String key = keyParam.replace(":", "_");
		String one = URL + "/zip" + key + "/version.txt";
		String bean = URL + "\\" + key + "\\" + mk + "\\bean\\" + name + ".java";
		String mapper = URL + "\\" + key + "\\" + mk + "\\mapper\\" + name + "Mapper.java";
		String mapperxml = URL + "\\" + key + "\\" + mk + "\\mapper\\" + name + "Mapper.xml";
		String service = URL + "\\" + key + "\\" + mk + "\\service\\" + name + "Service.java";
		String controller = URL + "\\" + key + "\\" + mk + "\\controller\\" + name + "Controller.java";
		createDir(one);
		createDir(bean);
		createDir(mapper);
		createDir(mapperxml);
		createDir(service);
		createDir(controller);
		map.put("bean", bean);
		map.put("mapper", mapper);
		map.put("mapperxml", mapperxml);
		map.put("service", service);
		map.put("controller", controller);
		return map;
	}

	public static void createDir(String url) throws IOException {
		File file = new File(url);
		File fileParent = file.getParentFile();
		if (!fileParent.exists()) {
			fileParent.mkdirs();
		}
		file.createNewFile();

	}

	public static boolean writeTxtFile(String content, File fileName) throws Exception {

		boolean flag = false;
		FileOutputStream o = null;
		try {
			o = new FileOutputStream(fileName);
			o.write(content.getBytes("UTF-8"));
			o.close();
			flag = true;
		} catch (Exception e) {
			o.close();
			e.printStackTrace();
		} finally {
			o.close();
		}
		return flag;
	}

}
