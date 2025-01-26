package com.matariky.automation.utils;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTemplateUtils {

	public static final String DEF_REGEX = "\\[(.+?)\\]";

	public static String render(String template, Map<String, String> data) {
		return render(template, data, DEF_REGEX);
	}

	public static String render(String template, Map<String, String> data, String regex) {
		if (Objects.isNull(template)) {
			return "";
		}
		if (Objects.isNull(regex)) {
			return template;
		}
		if (data == null || data.size() == 0) {
			return template;
		}
		try {
			StringBuffer sb = new StringBuffer();
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(template);
			while (matcher.find()) {
				String name = matcher.group(1);
				String value = data.get(name);
				if (value == null) {
					value = "";
				}
				matcher.appendReplacement(sb, value);
			}
			matcher.appendTail(sb);
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return template;

	}

}
