package com.matariky.exception.web;

import com.alibaba.fastjson.JSONObject;
import com.matariky.commonservice.commondict.constant.DictKey;
import com.matariky.commonservice.commondict.constant.DictTypeKey;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.exception.QslException;
import com.matariky.utils.NumberUtils;
import com.matariky.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @description: Global Exception Processor
 * @author: bo.chen
 * @create: 2023/9/6 17:32
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Value("${message.locale}")
    String defaultLocale;

    @Autowired
    private CommonDictService commonDictService;

    /**
     * @Description:
     * @Author: bo.chen
     * @Date: 2023/9/6 17:39
     * @param e
     * @param request
     * @return java.lang.Object
     **/
    @ExceptionHandler(QslException.class)
    public ResponseEntity handleServiceException(QslException e, HttpServletRequest request) {
        log.error("QslException：", e);
    	String locale = TokenUtils.extractLocaleFromToken(request.getHeader("Authorization"));
    	if (locale==null) {
            locale = defaultLocale;
        }
        JSONObject data = commonDictService.getServiceMessage(locale + DictTypeKey.SERVICE_MESSAGE, e.getMsgKey(), Boolean.FALSE, TokenUtils.extractTenantIdFromHttpReqeust(request));
        if (Objects.nonNull(e.getArgs()) && e.getArgs().length > NumberUtils.INTEGER_ZERO) {
            data.put("message", String.format(data.getString("message"), e.getArgs()));
        }
        return new ResponseEntity(data, HttpStatus.OK);
    }

    /**
     * @Description: Unknown exception handling
     * @Author: bo.chen
     * @Date: 2023/9/6 17:40
     * @param e
     * @param request
     * @return java.lang.Object
     **/
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e, HttpServletRequest request) {
        log.error("Unknown exception：", e);
        String locale=TokenUtils.extractLocaleFromToken(request.getHeader("Authorization"));
        return new ResponseEntity(commonDictService.getServiceMessage(locale + DictTypeKey.SERVICE_MESSAGE, DictKey.SYSTEM_ERROR, Boolean.FALSE, TokenUtils.getTenantIdFromRequest(request)), HttpStatus.OK);
    }

    /**
     * @Description: springboot  Parameter 校验异常处理
     * @Author: bo.chen
     * @Date: 2023/9/13 17:43
     * @param e
     * @param request
     * @return java.lang.Object
     **/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String locale=TokenUtils.extractLocaleFromToken(request.getHeader("Authorization"));
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return new ResponseEntity(commonDictService.getServiceMessage(locale + DictTypeKey.SERVICE_MESSAGE, message, Boolean.FALSE, TokenUtils.getTenantIdFromRequest(request)), HttpStatus.OK);
    }

}
