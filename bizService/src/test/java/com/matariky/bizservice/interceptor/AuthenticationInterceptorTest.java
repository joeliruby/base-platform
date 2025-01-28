package com.matariky.bizservice.interceptor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.method.HandlerMethod;

import com.matariky.annotation.PassToken;
import com.matariky.annotation.UserLoginToken;
import com.matariky.redis.RedisUtils;
import com.matariky.userservice.service.UserApplicationService;

@SpringBootTest
public class AuthenticationInterceptorTest {

    @InjectMocks
    private AuthenticationInterceptor authenticationInterceptor;

    @Mock
    private UserApplicationService applicationService;

    @Mock
    private RedisUtils redisUtils;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPreHandleWithValidToken() throws Exception {
        // Given
        String token = "Bearer validToken";
        when(httpServletRequest.getHeader("Authorization")).thenReturn(token);
        // Mock other dependencies and their behaviors

        // When
        boolean result = authenticationInterceptor.preHandle(httpServletRequest, httpServletResponse, new Object());

        // Then
        assertTrue(result);
    }

    @Test
    void testPreHandleWithExpiredToken() throws Exception {
        // Given
        String token = "Bearer expiredToken";
        when(httpServletRequest.getHeader("Authorization")).thenReturn(token);
        // Mock other dependencies and their behaviors

        // When
        boolean result = authenticationInterceptor.preHandle(httpServletRequest, httpServletResponse, new Object());

        // Then
        assertFalse(result);
    }

    @Test
    void testPreHandleWithNoToken() throws Exception {
        // Given
        when(httpServletRequest.getHeader("Authorization")).thenReturn(null);

        // When
        boolean result = authenticationInterceptor.preHandle(httpServletRequest, httpServletResponse, new Object());

        // Then
        assertTrue(result);
    }

    @Test
    void testPreHandleWithPassTokenAnnotation() throws Exception {
        // Given
        HandlerMethod handlerMethod = mock(HandlerMethod.class);
        Method method = AuthenticationInterceptorTest.class.getMethod("testPreHandleWithPassTokenAnnotation");
        when(handlerMethod.getMethod()).thenReturn(method);
        when(method.isAnnotationPresent(PassToken.class)).thenReturn(true);
        PassToken passToken = mock(PassToken.class);
        when(method.getAnnotation(PassToken.class)).thenReturn(passToken);
        when(passToken.required()).thenReturn(true);

        // When
        boolean result = authenticationInterceptor.preHandle(httpServletRequest, httpServletResponse, handlerMethod);

        // Then
        assertTrue(result);
    }

    @Test
    void testPreHandleWithUserLoginTokenAnnotation() throws Exception {
        // Given
        HandlerMethod handlerMethod = mock(HandlerMethod.class);
        Method method = AuthenticationInterceptorTest.class.getMethod("testPreHandleWithUserLoginTokenAnnotation");
        when(handlerMethod.getMethod()).thenReturn(method);
        when(method.isAnnotationPresent(UserLoginToken.class)).thenReturn(true);
        UserLoginToken userLoginToken = mock(UserLoginToken.class);
        when(method.getAnnotation(UserLoginToken.class)).thenReturn(userLoginToken);
        when(userLoginToken.required()).thenReturn(true);
        when(httpServletRequest.getHeader("Authorization")).thenReturn("Bearer validToken");

        // When
        boolean result = authenticationInterceptor.preHandle(httpServletRequest, httpServletResponse, handlerMethod);

        // Then
        assertFalse(result);
    }

    // Add more test methods for other scenarios in AuthenticationInterceptor
}
