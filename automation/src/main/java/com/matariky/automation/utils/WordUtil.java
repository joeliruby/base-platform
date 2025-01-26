package com.matariky.automation.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

public class WordUtil {

	public static void createWord(Map<?, ?> dataMap, String templateName, String filePath, String fileName)
			throws Exception {

		Configuration configuration = new Configuration();

		configuration.setDefaultEncoding("UTF-8");

		configuration.setClassForTemplateLoading(WordUtil.class, "/");

		Template template = configuration.getTemplate(templateName);

		File outFile = new File(filePath + File.separator + fileName);

		if (!outFile.getParentFile().exists()) {
			outFile.getParentFile().mkdirs();
		}

		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));

		template.process(dataMap, out);

		out.flush();
		out.close();

	}

	public static void downLoad(String fileName, String path, HttpServletResponse response) {
		File file = new File(path);
		FileInputStream fis = null;
		BufferedInputStream buff = null;
		response.setCharacterEncoding("UTF-8");
		try {
			response.setHeader("Content-Disposition",
					"attachment; filename=" + new String(fileName.getBytes("ISO8859-1"), "UTF-8"));
			response.setContentLength((int) file.length());
			response.setContentType("application/msword");
			fis = new FileInputStream(file);
			buff = new BufferedInputStream(fis);
			byte[] b = new byte[1024];
			long k = 0;
			OutputStream myout = response.getOutputStream();

			while (k < file.length()) {
				int j = buff.read(b, 0, 1024);
				k += j;
				myout.write(b, 0, j);
			}
			myout.flush();
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (buff != null)
				try {
					buff.close();
				} catch (IOException ie) {
					ie.printStackTrace();
				}
			if (fis != null)
				try {
					fis.close();
				} catch (IOException ie) {
					ie.printStackTrace();
				}

		} finally {
			if (buff != null)
				try {
					buff.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}
}
