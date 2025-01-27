package com.matariky.commonservice.filters;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.alibaba.druid.util.StringUtils;
import com.matariky.utils.TokenUtils;

import cn.hutool.http.HttpStatus;

@WebFilter(urlPatterns = { "/*" })
@Component
public class AccessControllFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (httpRequest.getRequestURI().endsWith("api-docs")
				|| httpRequest.getRequestURI().contains("swagger-resources")
				|| httpRequest.getRequestURI().contains("swagger-ui")
				|| httpRequest.getRequestURI().endsWith("downloadFile")
				|| httpRequest.getRequestURI().contains("userApplications/user")
				|| httpRequest.getRequestURI().contains("randomImage")
				|| httpRequest.getRequestURI().contains("login")
				|| httpRequest.getRequestURI().contains("readerFile")
				|| httpRequest.getRequestURI().contains("api/v1/rfid/stock/inout/insert")
				|| httpRequest.getRequestURI().contains("logout")
				|| httpRequest.getRequestURI().contains("IOTUserInfo")) {
			chain.doFilter(request, response);
			return;
		}

		String permId = httpRequest.getHeader("Id");
		if (StringUtils.isEmpty(permId)) {
			((HttpServletResponse) response).setStatus(HttpStatus.HTTP_UNAUTHORIZED);
			return;
		}

		if ("login4".equals(permId) || (permId != null && (permId.startsWith("home") || (permId.startsWith("Home"))))) {
			chain.doFilter(request, response);
			return;
		}
		String token = httpRequest.getHeader("Authorization");
		if (StringUtils.isEmpty(token)) {
			((HttpServletResponse) response).setStatus(HttpStatus.HTTP_FORBIDDEN);
			return;
		}
		if (!StringUtils.isEmpty(permId) && permId != null)
			permId = permId.substring(0, permId.length() - 1);// The current logic is that if there is resources, the
																// owner can add deletion, deletion, change and
																// investigation, and no longer segmented

		List<String> permList = TokenUtils.extractPermissionIdsFromToken(token);
		if (!permList.contains(permId)) {
			((HttpServletResponse) response).setStatus(HttpStatus.HTTP_FORBIDDEN);
			return;
		}
		chain.doFilter(request, response);
	}

}
