package com.matariky.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerMapping;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.pagehelper.util.StringUtil;
import com.matariky.commonservice.upload.constant.MessageKey;
import com.matariky.exception.QslException;

import io.jsonwebtoken.lang.Collections;


public class TokenUtils {

//    @Value("${token.expire.time}")
//    private Long EXPIRE_TIME;


    public static String getTokenFromRequest(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");// 从 http 请求头中取出 token
        if (token != null) {
            token = token.substring(7);
            return token;
        } else {
            token = httpServletRequest.getHeader("token");
        }
        return token;
    }


    public static String getTenantIdFromRequest(HttpServletRequest httpServletRequest) {

        String tenantIdFromHeader = httpServletRequest.getHeader("tenantId");
        if (tenantIdFromHeader != null) {
            return tenantIdFromHeader;
        }


        Map<String, String> pathVariables = (Map<String, String>) httpServletRequest
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        if (!Collections.isEmpty(pathVariables)) {
            String tenantIdFromURL = (String) pathVariables.get("tenantId");
            if (tenantIdFromURL != null) {
                return tenantIdFromURL;
            }
        }


        String tenantIdFromQueryString = httpServletRequest.getParameter("tenantId");
        return tenantIdFromQueryString;
    }


    public static String extractTenantIdFromToken(String token) {
        String stripBearer = null;
        if (token != null) {
            stripBearer = token.substring(7);
            DecodedJWT decodeJWT = JWT.decode(stripBearer);
            String tenantId = decodeJWT.getClaim("tenantId").asString();
            return tenantId;
        }
        return null;

    }

    /**
     * @return java.lang.String
     * @Description: 获取国际化标识
     * @Author: bo.chen
     * @Date: 2023/9/7 15:29
     **/
    public static String extractLocaleForRequest(HttpServletRequest httpServletRequest) {
        return extractLocaleFromToken(httpServletRequest.getHeader("Authorization"));
    }

    /**
     * @param token
     * @return java.lang.String
     * @Description: 获取国际化标识
     * @Author: bo.chen
     * @Date: 2023/9/7 15:27
     **/
    public static String extractLocaleFromToken(String token) {
        if (!StringUtils.isEmpty(token) && token.startsWith("Basic"))
            return ("en");
        if (StringUtils.isNotEmpty(token)) {
            String stripBearer = token.substring(7);
            DecodedJWT decodeJWT = JWT.decode(stripBearer);
            if (Objects.nonNull(decodeJWT)) {
                return decodeJWT.getClaim("locale").asString();
            }
        }
        return null;
    }

    /**
     * @param httpServletRequest
     * @return java.lang.String
     * @Description: 获取application
     * @Author: bo.chen
     * @Date: 2023/9/7 15:27
     **/
    public static Integer extractApplicationFromToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if (StringUtils.isNotEmpty(token)) {
            String stripBearer = token.substring(7);
            DecodedJWT decodeJWT = JWT.decode(stripBearer);
            if (Objects.nonNull(decodeJWT)) {
                return decodeJWT.getClaim("applicationType").asInt();
            }
        }
        return NumberUtils.INTEGER_ONE;
    }

    public static String extractRealNameFromToken(String token) {
        String stripBearer = null;
        if (token != null) {
            stripBearer = token.substring(7);
        }
        DecodedJWT decodeJWT = JWT.decode(stripBearer);
        String tenantId = decodeJWT.getClaim("realName").asString();
        return tenantId;
    }

    public static String extractTenantNameFromToken(String token) {
        String stripBearer = null;
        if (token != null) {
            stripBearer = token.substring(7);
        }
        DecodedJWT decodeJWT = JWT.decode(stripBearer);
        String tenantId = decodeJWT.getClaim("tenantName").asString();
        return tenantId;
    }


    public static List<String> extractGroupIdsFromToken(String token) {
        String stripBearer = null;
        if (token != null) {
            stripBearer = token.substring(7);
        }
        DecodedJWT decodeJWT = JWT.decode(stripBearer);
        List<String> groupIdLIST = new ArrayList<String>();
        if (decodeJWT.getClaim("groups") != null && !(decodeJWT.getClaim("groups") instanceof com.auth0.jwt.impl.NullClaim))
            groupIdLIST.addAll(Arrays.asList(decodeJWT.getClaim("groups").asString().split(",")));
        return groupIdLIST;
    }

    public static List<String> extractPermissionIdsFromToken(String token) {
        String stripBearer = null;
        if (token != null) {
            stripBearer = token.substring(7);
        }
        DecodedJWT decodeJWT = JWT.decode(stripBearer);
        List<String> permissionIdLIST = new ArrayList<String>();


        if (decodeJWT.getClaim("permissions") != null)
            permissionIdLIST.addAll(Arrays.asList(decodeJWT.getClaim("permissions").asString().split(",")));
        return permissionIdLIST;
    }

    // 检查token Wether 过期
    public static boolean checkTokenOutTime(String token) {
        String stripBearer = null;
        if (token != null) {
            stripBearer = token.substring(7);
        }
        DecodedJWT decodeJWT = JWT.decode(stripBearer);
        Date expiresDate = decodeJWT.getExpiresAt();
        Date currenDate = new Date();
        if (currenDate.after(expiresDate)) {
            return true;
        } else {
            // 没有过期，自动延长过期 Time 
            String loginName = decodeJWT.getClaim("loginName").asString();
            String organizationCode = decodeJWT.getClaim("organizationCode").asString();
            String selfOrganizationCode = decodeJWT.getClaim("selfOrganizationCode").asString();
            String organizationId = decodeJWT.getClaim("organizationId").asString();
            Integer applicationId = decodeJWT.getClaim("applicationId").asInt();
            String realName = decodeJWT.getClaim("realName").asString();
            String groups = decodeJWT.getClaim("groups").asString();
            String tenantName = decodeJWT.getClaim("tenantName").asString();
            Integer isAdmin = decodeJWT.getClaim("isAdmin").asInt();
            String theme = decodeJWT.getClaim("theme").asString();
            String locale = decodeJWT.getClaim("locale").asString();
            String logo = decodeJWT.getClaim("logo").asString();
            String tenantId = decodeJWT.getClaim("tenantId").asString();
            Integer applicationType = decodeJWT.getClaim("applicationType").asInt();
            Date expirationDate = new Date(decodeJWT.getExpiresAt().getTime() + 3 * 60 * 1000);
            String userId = extractUserIdFromToken(token);
            List<String> permissionIds = extractPermissionIdsFromToken(token);
            String permissions = permissionIds.stream().collect(Collectors.joining(","));
            token = JWT.create().withAudience(userId).withSubject(loginName).withClaim("loginName", loginName)
                    .withClaim("organizationId", organizationId)
                    .withClaim("organizationCode", organizationCode)
                    .withClaim("selfOrganizationCode", selfOrganizationCode)
                    .withClaim("tenantId", tenantId)
                    .withClaim("applicationId", applicationId)
                    .withClaim("applicationType", applicationType)
                    .withClaim("permissions", permissions)
                    .withClaim("realName", realName)
                    .withClaim("groups", groups)
                    .withClaim("tenantName", tenantName)
                    .withClaim("isAdmin", isAdmin)
                    .withClaim("theme", theme)
                    .withClaim("locale", locale)
                    .withClaim("logo", logo)
                    .withExpiresAt(expirationDate)
                    .sign(Algorithm.HMAC256(tenantId));
            return false;
        }
    }

    public static String extractUserIdFromToken(String token) {
        String stripBearer = null;
        if (token != null) {
            stripBearer = token.substring(7);
        }
        DecodedJWT decodeJWT = JWT.decode(stripBearer);
        return decodeJWT.getAudience().get(0);
    }


    public static String extractUserIdFromHttpReqeust(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if (StringUtil.isEmpty(token))
            token = httpServletRequest.getHeader("token") == null ? null : "Bearer " + httpServletRequest.getHeader("token");
        if (token == null || token.startsWith("Basic"))
            return null;
        return extractUserIdFromToken(token);
    }


    public static String extractRealNameFromHttpRequest(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if (StringUtil.isEmpty(token))
            token = httpServletRequest.getHeader("token") == null ? null : "Bearer " + httpServletRequest.getHeader("token");
        if (token == null)
            return null;
        return extractRealNameFromToken(token);
    }


    public static String extractTenantIdFromHttpReqeust(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if (StringUtil.isEmpty(token))
            token = httpServletRequest.getHeader("token") == null ? null : "Bearer " + httpServletRequest.getHeader("token");
        if (token == null)
            return null;
        return extractTenantIdFromToken(token);
    }


    public static String extractTenantNameFromHttpRequest(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if (StringUtil.isEmpty(token))
            token = httpServletRequest.getHeader("token") == null ? null : "Bearer " + httpServletRequest.getHeader("token");
        if (token == null)
            return null;
        return extractTenantNameFromToken(token);
    }


    public static Long extractApplicatoinIdFromToken(String token) {
        String stripBearer = null;
        if (token != null) {
            stripBearer = token.substring(7);
        }
        DecodedJWT decodeJWT = JWT.decode(stripBearer);
        Long applicationId = decodeJWT.getClaim("applicationId").asLong();
        return applicationId;
    }

    public static Long extractExpireTimeFromToken(String token) {
    	String stripBearer = null;
    	 if (token != null) {
             stripBearer = token.substring(7);
         }
    	DecodedJWT decodeJWT = JWT.decode(stripBearer);
    	Long expireAt=decodeJWT.getExpiresAt().getTime();
		return expireAt;
	}

    public static String extractLoginNameFromHttpRequest(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if (StringUtil.isEmpty(token))
            token = httpServletRequest.getHeader("token") == null ? null : "Bearer " + httpServletRequest.getHeader("token");
        if (token == null)
            return null;
        return extractLoginNameFromToken(token);
    }


    private static String extractLoginNameFromToken(String token) {
        String stripBearer = null;
        if (token != null) {
            stripBearer = token.substring(7);
        }
        DecodedJWT decodeJWT = JWT.decode(stripBearer);
        return decodeJWT.getClaim("loginName").asString();
    }




    public static String extractTenantIdFromHttpRequest(HttpServletRequest httpServletRequest) {
        String stripBearer = null;
        if (httpServletRequest == null) {
            return null;
        }
        String token = httpServletRequest.getHeader("Authorization");
        if (token == null)
            token = httpServletRequest.getHeader("token") == null ? null : "Bearer " + httpServletRequest.getHeader("token");
        if (token != null) {
            stripBearer = token.substring(7);
        }
        if (StringUtils.isNotEmpty(stripBearer)) {
            DecodedJWT decodeJWT = JWT.decode(stripBearer);
            if (Objects.nonNull(decodeJWT)) {
                return decodeJWT.getClaim("tenantId").asString();
            }
        }
        return null;
    }


    public static String extractOrgCode(HttpServletRequest request) {
        String stripBearer = null;
        String token = request.getHeader("Authorization");
        if (token == null)
            token = request.getHeader("token") == null ? null : "Bearer " + request.getHeader("token");
        if (token != null) {
            stripBearer = token.substring(7);
        }
        if(stripBearer==null)
        	return null;
        DecodedJWT decodeJWT = JWT.decode(stripBearer);
        String tenantId = decodeJWT.getClaim("organizationCode").asString();
        return tenantId;
    }


    public static String extractSelfOrgCode(HttpServletRequest request) {
        String stripBearer = null;
        String token = request.getHeader("Authorization");
        if (token == null)
            token = request.getHeader("token") == null ? null : "Bearer " + request.getHeader("token");
        if (token != null) {
            stripBearer = token.substring(7);
        }
        if(StringUtils.isEmpty(stripBearer)) {
        	return null;
        }
        DecodedJWT decodeJWT = JWT.decode(stripBearer);
        
        String orgCode = decodeJWT.getClaim("selfOrganizationCode").asString();
        return orgCode;
    }


	public static Long longify(String tenantId) {
		if(StringUtils.isNotBlank(tenantId)) {
			if(tenantId.contains("_")){
                tenantId=tenantId.split("_")[1];
            }
			return Long.parseLong(tenantId);
		}
		else {
			return null;
		}
	}




}

