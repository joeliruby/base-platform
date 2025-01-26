package com.matariky.utils;

import javax.servlet.http.HttpServletRequest;

public class HttpUtils {
	
    public static boolean isStatic(HttpServletRequest httpServletRequest) {
		String requestURL = httpServletRequest.getRequestURL().toString();
		if(httpServletRequest.getRequestURI().startsWith("/flowable-idm/app/rest/models")&&httpServletRequest.getRequestURI().contains("model-json"))
		return true;
		return requestURL.endsWith(".png") || requestURL.endsWith(".jpg") || requestURL.endsWith(".gif")
				|| requestURL.endsWith(".ico") || requestURL.endsWith(".js") || requestURL.endsWith(".css")||requestURL.endsWith("/flowable-idm/error")
				|| requestURL.contains("?v=") || requestURL.endsWith(".json") || requestURL.endsWith(".html")
				|| requestURL.endsWith(".woff") || requestURL.endsWith(".ttf")|| requestURL.endsWith(".map")||requestURL.contains("bpmn20")
				|| requestURL.contains("thumbnail?version=")|| requestURL.endsWith(".xml")||requestURL.contains("thumbnail");
	}

}
