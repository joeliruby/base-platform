package com.matariky.automation.common;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootTest
public class WebAppConfigTest {

    @InjectMocks
    private WebAppConfig webappconfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddInterceptors() {
        // Given
        InterceptorRegistry registry = mock(InterceptorRegistry.class);

        // When
        webappconfig.addInterceptors(registry);

        // Then
        verify(registry).addInterceptor(any(InterceptorConfig.class));
    }

    @Test
    void testAddArgumentResolvers() {
        // Given
        List<HandlerMethodArgumentResolver> argumentResolvers = mock(List.class);

        // When
        webappconfig.addArgumentResolvers(argumentResolvers);

        // Then
        verify(argumentResolvers).add(any(PageableHandlerMethodArgumentResolver.class));
    }

    @Test
    void testAddResourceHandlers() {
        // Given
        ResourceHandlerRegistry registry = mock(ResourceHandlerRegistry.class);
        when(registry.hasMappingForPattern("/**")).thenReturn(false);

        // When
        webappconfig.addResourceHandlers(registry);

        // Then
        verify(registry).addResourceHandler("/**");
    }

    // Add more test methods for other methods in WebAppConfig
}
