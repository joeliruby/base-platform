package com.matariky.automation.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class InterceptorConfig implements HandlerInterceptor {
	private static final Logger log = LoggerFactory.getLogger(InterceptorConfig.class);

	@Override
	public boolean preHandle(@NonNull HttpServletRequest httpServletRequest,
			@NonNull HttpServletResponse httpServletResponse, Object o)
			throws Exception {
		log.info("---------------------Start entering the request address interception----------------------------");
		return true;
	}

	@Override
	public void postHandle(@NonNull HttpServletRequest httpServletRequest,
			@NonNull HttpServletResponse httpServletResponse, Object o,
			@Nullable ModelAndView modelAndView) throws Exception {
		log.info(
				"--------------After processing the request is completed, the processing operation before the view rendering---------------");
	}

	@Override
	public void afterCompletion(@NonNull HttpServletRequest httpServletRequest,
			@NonNull HttpServletResponse httpServletResponse,
			@NonNull Object o, @Nullable Exception e) throws Exception {
		log.info("---------------Operation after view rendering-------------------------");
	}
}
