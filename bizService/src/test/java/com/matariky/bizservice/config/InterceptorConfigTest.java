package com.matariky.bizservice.config;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import com.matariky.bizservice.interceptor.AuthenticationInterceptor;

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
        AuthenticationInterceptor interceptor = mock(AuthenticationInterceptor.class);
        when(interceptorConfig.authenticationInterceptor()).thenReturn(interceptor);

        // When
        interceptorConfig.addInterceptors(registry);

        // Then
        verify(registry).addInterceptor(interceptor);
    }

    @Test
    void testAuthenticationInterceptor() {
        // When
        AuthenticationInterceptor interceptor = interceptorConfig.authenticationInterceptor();

        // Then
        assertThat(interceptor).isNotNull();
        assertThat(interceptor).isInstanceOf(AuthenticationInterceptor.class);
    }

    // Add more test methods for other methods in InterceptorConfig if needed
}
