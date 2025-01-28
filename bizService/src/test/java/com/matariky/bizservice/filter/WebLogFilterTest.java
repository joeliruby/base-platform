package com.matariky.bizservice.filter;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import javax.servlet.FilterChain;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mockito.Mock;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import com.matariky.commonservice.accesslog.service.CommonAccessLogService;
import com.matariky.redis.RedisUtils;
import com.matariky.userservice.service.PermissionService;

@SpringBootTest
public class WebLogFilterTest {

    @InjectMocks
    private WebLogFilter weblogfilter;

    @Mock
    private CommonAccessLogService calService;

    @Mock
    private PermissionService permissionService;

    @Mock
    private RedisUtils redisUtils;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoFilterInternal() throws ServletException, IOException {
        // Given
        ContentCachingRequestWrapper wrapperRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrapperResponse = new ContentCachingResponseWrapper(response);

        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost/test"));
        when(request.getMethod()).thenReturn("POST");

        // When
        weblogfilter.doFilterInternal(wrapperRequest, wrapperResponse, filterChain);

        // Then
        verify(filterChain, times(1)).doFilter(wrapperRequest, wrapperResponse);
        assertThat(wrapperResponse.getContentAsByteArray()).isNotEmpty();
    }

    @Test
    void testGetRequestBody() {
        // Given
        ContentCachingRequestWrapper wrapperRequest = new ContentCachingRequestWrapper(request);
        when(wrapperRequest.getContentAsByteArray()).thenReturn("test body".getBytes());

        // When
        String requestBody = weblogfilter.getRequestBody(wrapperRequest);

        // Then
        assertThat(requestBody).isEqualTo("test body");
    }

    @Test
    void testGetResponseBody() {
        // Given
        ContentCachingResponseWrapper wrapperResponse = new ContentCachingResponseWrapper(response);
        when(wrapperResponse.getContentAsByteArray()).thenReturn("test body".getBytes());

        // When
        String responseBody = weblogfilter.getResponseBody(wrapperResponse);

        // Then
        assertThat(responseBody).isEqualTo("test body");
    }

    @Test
    void testGetRequestParams() {
        // Given
        when(request.getParameterNames()).thenReturn(new Enumeration<String>() {
            private final String[] elements = { "param1", "param2" };
            private int index = 0;

            @Override
            public boolean hasMoreElements() {
                return index < elements.length;
            }

            @Override
            public String nextElement() {
                return elements[index++];
            }
        });
        when(request.getParameter("param1")).thenReturn("value1");
        when(request.getParameter("param2")).thenReturn("value2");

        // When
        String params = weblogfilter.getRequestParams(request);

        // Then
        assertThat(params).isEqualTo("param1=value1, param2=value2");
    }

    // Add more test methods for other methods in WebLogFilter
}
