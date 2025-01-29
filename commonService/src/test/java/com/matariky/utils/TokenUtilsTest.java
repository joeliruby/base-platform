package com.matariky.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenUtilsTest {

    @InjectMocks
    private TokenUtils tokenutils;

    @Mock
    private HttpServletRequest httpServletRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTokenFromRequest() {
        // Given
        when(httpServletRequest.getHeader("Authorization")).thenReturn("Bearer token123");

        // When
        String token = TokenUtils.getTokenFromRequest(httpServletRequest);

        // Then
        assertEquals("token123", token);
    }

    @Test
    void testGetTenantIdFromRequest() {
        // Given
        when(httpServletRequest.getHeader("tenantId")).thenReturn("tenant123");

        // When
        String tenantId = TokenUtils.getTenantIdFromRequest(httpServletRequest);

        // Then
        assertEquals("tenant123", tenantId);
    }

    @Test
    void testExtractTenantIdFromToken() {
        // Given
        String token = "Bearer token123";
        // Mock the JWT.decode method and its behavior

        // When
        String tenantId = TokenUtils.extractTenantIdFromToken(token);

        // Then
        // Assert the expected tenantId
        assertEquals("tenant123", tenantId);
    }

    @Test
    void testExtractLocaleFromToken() {
        // Given
        String token = "Bearer token123";
        // Mock the JWT.decode method and its behavior

        // When
        String locale = TokenUtils.extractLocaleFromToken(token);

        // Then
        // Assert the expected locale
        assertEquals("en_US", locale);
    }

    @Test
    void testExtractRealNameFromToken() {
        // Given
        String token = "Bearer token123";
        // Mock the JWT.decode method and its behavior

        // When
        String realName = TokenUtils.extractRealNameFromToken(token);

        // Then
        // Assert the expected realName
        assertEquals("John Doe", realName);
    }

    @Test
    void testExtractGroupIdsFromToken() {
        // Given
        String token = "Bearer token123";
        // Mock the JWT.decode method and its behavior

        // When
        List<String> groupIds = TokenUtils.extractGroupIdsFromToken(token);

        // Then
        // Assert the expected groupIds
        assertEquals(2, groupIds.size());
    }

    @Test
    void testCheckTokenOutTime() {
        // Given
        String token = "Bearer token123";
        // Mock the JWT.decode method and its behavior

        // When
        boolean isOutTime = TokenUtils.checkTokenOutTime(token);

        // Then
        // Assert the expected result
        assertEquals(false, isOutTime);
    }

    // Add more test methods for other methods in TokenUtils
}
