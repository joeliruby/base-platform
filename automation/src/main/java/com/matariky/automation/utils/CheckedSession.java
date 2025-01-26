package com.matariky.automation.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class CheckedSession {

	public static String check(HttpServletRequest request) {
		String key = "";
		long date = new Date().getTime();
		HttpSession session = request.getSession();
		if (session.getAttribute("KEY") == null) {
			key = date + IpUtil.getIpAddr(request);
			session.setAttribute("KEY", key);
		} else {
			key = session.getAttribute("KEY").toString();
		}
		return key;

	}

}
