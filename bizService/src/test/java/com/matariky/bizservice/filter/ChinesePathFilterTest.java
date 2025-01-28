package com.matariky.bizservice.filter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChinesePathFilterTest {

    @InjectMocks
    private ChinesePathFilter chinesePathFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoFilter() throws IOException, ServletException {
        // Given
        ServletRequest request = mock(ServletRequest.class);
        ServletResponse response = mock(ServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        // When
        chinesePathFilter.doFilter(request, response, chain);

        // Then
        verify(request).setCharacterEncoding("UTF-8");
        verify(response).setCharacterEncoding("UTF-8");
        verify(chain).doFilter(request, response);
    }

    @Test
    void testInit() {
        // Given
        FilterConfig filterConfig = mock(FilterConfig.class);

        // When
        chinesePathFilter.init(filterConfig);

        // Then
        // No exception should be thrown
    }

    @Test
    void testDestroy() {
        // When
        chinesePathFilter.destroy();

        // Then
        // No exception should be thrown
    }
}
