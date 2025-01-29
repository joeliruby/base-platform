package com.matariky.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import javax.servlet.http.HttpServletRequest;
import org.mockito.Mock;

@SpringBootTest
public class HttpUtilsTest {

    @InjectMocks
    private HttpUtils httputils;

    @Mock
    private HttpServletRequest httpServletRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsStatic_withStaticResource() {
        // Given
        when(httpServletRequest.getRequestURL()).thenReturn(new StringBuffer("http://example.com/image.png"));
        when(httpServletRequest.getRequestURI()).thenReturn("/image.png");

        // When
        boolean result = HttpUtils.isStatic(httpServletRequest);

        // Then
        assertTrue(result);
    }

    @Test
    void testIsStatic_withNonStaticResource() {
        // Given
        when(httpServletRequest.getRequestURL()).thenReturn(new StringBuffer("http://example.com/api/data"));
        when(httpServletRequest.getRequestURI()).thenReturn("/api/data");

        // When
        boolean result = HttpUtils.isStatic(httpServletRequest);

        // Then
        assertFalse(result);
    }

    @Test
    void testIsStatic_withFlowableModelJson() {
        // Given
        when(httpServletRequest.getRequestURL())
                .thenReturn(new StringBuffer("http://example.com/flowable-idm/app/rest/models/123/model-json"));
        when(httpServletRequest.getRequestURI()).thenReturn("/flowable-idm/app/rest/models/123/model-json");

        // When
        boolean result = HttpUtils.isStatic(httpServletRequest);

        // Then
        assertTrue(result);
    }

    @Test
    void testIsStatic_withVersionedResource() {
        // Given
        when(httpServletRequest.getRequestURL()).thenReturn(new StringBuffer("http://example.com/style.css?v=123"));
        when(httpServletRequest.getRequestURI()).thenReturn("/style.css");

        // When
        boolean result = HttpUtils.isStatic(httpServletRequest);

        // Then
        assertTrue(result);
    }

    // Add more test methods for other scenarios if needed
}
