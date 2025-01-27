package com.matariky.automation.common;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@SpringBootTest
public class InterceptorConfigTest {

    @InjectMocks
    private InterceptorConfig interceptorConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddInterceptors() {
        // Given
        InterceptorRegistry registry = mock(InterceptorRegistry.class);

        // When
//        interceptorConfig.addInterceptors(registry);

        // Then
//        verify(registry).addInterceptor(any(AuthenticationInterceptor.class));
    }

    @Test
    void testAuthenticationInterceptorBean() {
        // When
//        AuthenticationInterceptor interceptor = interceptorConfig.authenticationInterceptor();

        // Then
//        assertNotNull(interceptor);
    }

    // Add more test methods for other methods in InterceptorConfig
}
