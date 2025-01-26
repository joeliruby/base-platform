package com.matariky.bizservice.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import com.alibaba.druid.util.StringUtils;
import com.matariky.commonservice.accesslog.bean.CommonAccessLog;
import com.matariky.commonservice.accesslog.service.CommonAccessLogService;
import com.matariky.constant.RedisKey;
import com.matariky.redis.RedisUtils;
import com.matariky.userservice.bean.Permission;
import com.matariky.userservice.service.PermissionService;
import com.matariky.utils.HttpUtils;
import com.matariky.utils.IPUtil;
import com.matariky.utils.NumberUtils;
import com.matariky.utils.TokenUtils;

import cn.hutool.core.lang.UUID;

@WebFilter(filterName = "LogFilter", urlPatterns = "/*")
@Component
public class WebLogFilter extends OncePerRequestFilter implements Ordered {

    // put filter at the end of all other filters to make sure we are processing after all others
    private int order = Ordered.LOWEST_PRECEDENCE - 8;

    public static final String SPLIT_STRING_M = "=";

    public static final String SPLIT_STRING_DOT = ", ";

    private
    @Autowired
    CommonAccessLogService calService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper wrapperRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrapperResponse = new ContentCachingResponseWrapper(response);
        filterChain.doFilter(wrapperRequest, wrapperResponse);
        if(!request.getRequestURL().toString().contains("randomImage")
                &&!request.getRequestURL().toString().endsWith("/login")
                &&!HttpUtils.isStatic(request)
                &&!request.getRequestURL().toString().endsWith("/readerFile/reader.tar.gz")
                &&!request.getRequestURL().toString().endsWith("/api/v1/rfid/stock/inout/insert")
                &&!request.getRequestURL().toString().contains("swagger-resources")&&!request.getRequestURL().toString().contains("api-docs")
        		&&!request.getRequestURL().toString().contains("commonAccessLog")
        		&&!request.getRequestURL().toString().contains("commonLoginLog")
        		&&!request.getRequestURL().toString().contains("commonSqlLog")
        		&&!request.getRequestURL().toString().contains("fileUpload")
        		&&!request.getRequestURL().toString().contains("api/image")
        		&&!request.getRequestURL().toString().contains("index")
        		&&!request.getRequestURL().toString().contains("viewImage")
                &&!request.getRequestURL().toString().endsWith("downloadFile")
        		&&!request.getRequestURL().toString().contains("pdf.svg")
        		&&!request.getRequestURL().toString().contains("listFiles")
        		&&!request.getRequestURL().toString().contains("userApplications/user")
        		&&!request.getRequestURL().toString().contains("IOTUserInfo")
        		) {
            CommonAccessLog cal = new CommonAccessLog();
            cal.setAccessTime(System.currentTimeMillis());
            cal.setAccount(TokenUtils.extractUserIdFromHttpReqeust(request));
            cal.setRequestUrl(request.getRequestURL().toString());
            cal.setClient(request.getHeader("User-Agent"));
            cal.setTenantName(TokenUtils.extractTenantNameFromHttpRequest(request));
            cal.setTenantId(TokenUtils.extractTenantIdFromHttpReqeust(request));
            cal.setOperatorSelfOrgCode(TokenUtils.extractSelfOrgCode(request));
            cal.setOperatorOrgCode(TokenUtils.extractOrgCode(request));
            cal.setRealName(TokenUtils.extractRealNameFromHttpRequest(request));
            cal.setRequestMethod(request.getMethod());
            cal.setId(UUID.randomUUID().toString());
            cal.setClientIp(IPUtil.getIPAddress(request));
            if (!RequestMethod.GET.name().equals(request.getMethod())) {
                String requestBodyStr = getRequestBody(wrapperRequest);
                cal.setRequestBody(requestBodyStr);
                String responseBodyStr = getResponseBody(wrapperResponse);
                cal.setResponseBody(responseBodyStr);
                cal.setClientIp(IPUtil.getIPAddress(request));
            }
            try {
                String rId = request.getHeader("Id");
                if (!StringUtils.isEmpty(rId) && rId.length() > NumberUtils.INTEGER_TWO && StringUtils.isNumber(rId)) {
                    Permission permission = permissionService.selectById(rId.substring(NumberUtils.INTEGER_ZERO, rId.length() - NumberUtils.INTEGER_ONE));
                    if (Objects.nonNull(permission)) {
                        cal.setOperationName(permission.getOrigId());
                        cal.setOperationNameString(permission.getPermissionName());
                    } else {
                        cal.setOperationName(NumberUtils.LONG_MINUS_ONE);
                    }
                    calService.insert(cal);
                }
                redisUtils.set(RedisKey.USER_LAST_ACTIVE_TIME_PREFIX+"_"+TokenUtils.extractUserIdFromHttpReqeust(request), System.currentTimeMillis(), RedisUtils.HOUR_SIX_EXPIRE);
            } catch(Exception e) {
                LoggerFactory.getLogger(WebLogFilter.class).error("Logging Exception！", e);
            }
        }
        wrapperResponse.copyBodyToResponse();
    }

    private String getRequestBody(ContentCachingRequestWrapper request) {
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if(wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if(buf.length > 0) {
                String payload;
                try {
                    payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException e) {
                    payload = "[unknown]";
                }
                return payload.replaceAll("\\n","");
            }
        }
        return "";
    }

    private String getResponseBody(ContentCachingResponseWrapper response) {
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if(wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if(buf.length > 0) {
                String payload;
                try {
                    payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException e) {
                    payload = "[unknown]";
                }
                return payload;
            }
        }
        return "";
    }

    public static String getRequestParams(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            sb.append(name + SPLIT_STRING_M).append(request.getParameter(name));
            if(enu.hasMoreElements()) {
                sb.append(SPLIT_STRING_DOT);
            }
        }
        return sb.toString();
    }
}
