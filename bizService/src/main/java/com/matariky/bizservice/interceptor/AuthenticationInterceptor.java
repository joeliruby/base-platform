package com.matariky.bizservice.interceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson2.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.matariky.annotation.PassToken;
import com.matariky.annotation.UserLoginToken;
import com.matariky.constant.RedisKey;
import com.matariky.redis.RedisUtils;
import com.matariky.userservice.bean.UserApplication;
import com.matariky.userservice.service.UserApplicationService;
import com.matariky.utils.TokenUtils;


public class AuthenticationInterceptor implements HandlerInterceptor {
	@Autowired UserApplicationService applicationService;
	@Autowired RedisUtils redisUtils;
	
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("Authorization");
        
        if (token!=null) {
        	if(token.contains("Basic")) {
        		return true;//pulling iot user from keycloak
        	}
        	Long expireTime = TokenUtils.extractExpireTimeFromToken(token);
        	
	        Long applicationId=TokenUtils.extractApplicatoinIdFromToken(token);
	        UserApplication application=applicationService.selectById(applicationId);
	        Long activityTimeout= application.getActivityTimeout()*1000;
	        String userId=TokenUtils.extractUserIdFromToken(token);
	        Long lastActiveTime=redisUtils.get(RedisKey.USER_LAST_ACTIVE_TIME_PREFIX+"_"+userId);
	       
	        Long currentTime=System.currentTimeMillis();
	        lastActiveTime= (lastActiveTime==null) ? currentTime : lastActiveTime;
	        if(lastActiveTime!=null && expireTime< currentTime && lastActiveTime +activityTimeout<currentTime) {
	        	Map<String, Object> timeoutMap= new  HashMap<String, Object>();
	        	timeoutMap.put("code", 401);
	        	timeoutMap.put("message", "登录超时，请重新登录");
	        	httpServletResponse.sendError(200, JSON.toJSONString(timeoutMap));
	        }
	        token =token.substring(7);
        }
        
        
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                if (token == null) {
                	httpServletResponse.sendError(401, "没有token");
                }
                try {
                    JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                	httpServletResponse.sendError(401, "token无法解析");
                }
                
                
                	}
            		return false;
                }
        return true;
    }

	@Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
