package com.matariky.bizservice.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@SpringBootTest
public class BaseUrlFilterTest {

    @InjectMocks
    private BaseUrlFilter baseUrlFilter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private ServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private FilterConfig filterConfig;

    @Mock
    private RequestAttributes requestAttributes;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBaseUrl() {
        // Given
        when(RequestContextHolder.currentRequestAttributes()).thenReturn(requestAttributes);
        when(requestAttributes.getAttribute("baseUrl", 0)).thenReturn("http://mockedurl.com/");

        // When
        String baseUrl = BaseUrlFilter.getBaseUrl();

        // Then
        assertThat(baseUrl).isEqualTo("http://mockedurl.com/");
    }

    @Test
    void testDoFilter() throws IOException, ServletException {
        // Given
        when(request.getScheme()).thenReturn("http");
        when(request.getServerName()).thenReturn("localhost");
        when(request.getServerPort()).thenReturn(8080);
        when(request.getContextPath()).thenReturn("/context");

        // When
        baseUrlFilter.doFilter(request, response, filterChain);

        // Then
        verify(request).setAttribute(eq("baseUrl"), anyString());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void testInit() {
        // When
        baseUrlFilter.init(filterConfig);

        // Then
        // No exception should be thrown
    }

    @Test
    void testDestroy() {
        // When
        baseUrlFilter.destroy();

        // Then
        // No exception should be thrown
    }
}
