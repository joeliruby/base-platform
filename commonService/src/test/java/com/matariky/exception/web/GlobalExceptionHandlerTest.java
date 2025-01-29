package com.matariky.exception.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.alibaba.fastjson.JSONObject;
import com.matariky.commonservice.commondict.constant.DictKey;
import com.matariky.commonservice.commondict.constant.DictTypeKey;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.exception.QslException;
import com.matariky.utils.TokenUtils;

@SpringBootTest
public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleServiceException() {
        // Given
        QslException qslException = new QslException("error.key", new Object[] { "arg1" });
        String locale = "en_US";
        String token = "Bearer token";
        JSONObject serviceMessage = new JSONObject();
        serviceMessage.put("message", "Error message with %s");

        when(request.getHeader("Authorization")).thenReturn(token);
        when(TokenUtils.extractLocaleFromToken(token)).thenReturn(locale);
        when(commonDictService.getServiceMessage(locale + DictTypeKey.SERVICE_MESSAGE, "error.key", Boolean.FALSE,
                TokenUtils.extractTenantIdFromHttpReqeust(request))).thenReturn(serviceMessage);

        // When
        ResponseEntity<JSONObject> response = globalExceptionHandler.handleServiceException(qslException, request);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getString("message")).isEqualTo("Error message with arg1");
    }

    @Test
    void testHandleException() {
        // Given
        Exception exception = new Exception("Unknown error");
        String locale = "en_US";
        String token = "Bearer token";
        JSONObject serviceMessage = new JSONObject();
        serviceMessage.put("message", "System error");

        when(request.getHeader("Authorization")).thenReturn(token);
        when(TokenUtils.extractLocaleFromToken(token)).thenReturn(locale);
        when(commonDictService.getServiceMessage(locale + DictTypeKey.SERVICE_MESSAGE, DictKey.SYSTEM_ERROR,
                Boolean.FALSE, TokenUtils.getTenantIdFromRequest(request))).thenReturn(serviceMessage);

        // When
        ResponseEntity<JSONObject> response = globalExceptionHandler.handleException(exception, request);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getString("message")).isEqualTo("System error");
    }

    @Test
    void testHandleMethodArgumentNotValidException() {
        // Given
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        String locale = "en_US";
        String token = "Bearer token";
        String errorMessage = "Validation error";
        JSONObject serviceMessage = new JSONObject();
        serviceMessage.put("message", errorMessage);

        when(request.getHeader("Authorization")).thenReturn(token);
        when(TokenUtils.extractLocaleFromToken(token)).thenReturn(locale);
        when(exception.getBindingResult().getFieldError().getDefaultMessage()).thenReturn(errorMessage);
        when(commonDictService.getServiceMessage(locale + DictTypeKey.SERVICE_MESSAGE, errorMessage, Boolean.FALSE,
                TokenUtils.getTenantIdFromRequest(request))).thenReturn(serviceMessage);

        // When
        ResponseEntity<JSONObject> response = (ResponseEntity<JSONObject>) globalExceptionHandler
                .handleMethodArgumentNotValidException(exception, request);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getString("message")).isEqualTo(errorMessage);
    }
}
