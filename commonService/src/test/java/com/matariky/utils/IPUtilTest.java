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
public class IPUtilTest {

    @InjectMocks
    private IPUtil iputil;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCityInfo_ValidIp() {
        // Given
        String ip = "8.8.8.8";
        String expectedRegion = "United States";

        // When
        String actualRegion = IPUtil.getCityInfo(ip);

        // Then
        assertEquals(expectedRegion, actualRegion);
    }

    @Test
    void testGetCityInfo_InvalidIp() {
        // Given
        String ip = "999.999.999.999";

        // When
        String actualRegion = IPUtil.getCityInfo(ip);

        // Then
        assertNull(actualRegion);
    }

    @Test
    void testGetIPAddress_XForwardedFor() {
        // Given
        String expectedIp = "192.168.1.1";
        when(request.getHeader("X-Forwarded-For")).thenReturn(expectedIp);

        // When
        String actualIp = IPUtil.getIPAddress(request);

        // Then
        assertEquals(expectedIp, actualIp);
    }

    @Test
    void testGetIPAddress_ProxyClientIP() {
        // Given
        String expectedIp = "192.168.1.2";
        when(request.getHeader("X-Forwarded-For")).thenReturn(null);
        when(request.getHeader("Proxy-Client-IP")).thenReturn(expectedIp);

        // When
        String actualIp = IPUtil.getIPAddress(request);

        // Then
        assertEquals(expectedIp, actualIp);
    }

    @Test
    void testGetIPAddress_WLProxyClientIP() {
        // Given
        String expectedIp = "192.168.1.3";
        when(request.getHeader("X-Forwarded-For")).thenReturn(null);
        when(request.getHeader("Proxy-Client-IP")).thenReturn(null);
        when(request.getHeader("WL-Proxy-Client-IP")).thenReturn(expectedIp);

        // When
        String actualIp = IPUtil.getIPAddress(request);

        // Then
        assertEquals(expectedIp, actualIp);
    }

    @Test
    void testGetIPAddress_HTTPClientIP() {
        // Given
        String expectedIp = "192.168.1.4";
        when(request.getHeader("X-Forwarded-For")).thenReturn(null);
        when(request.getHeader("Proxy-Client-IP")).thenReturn(null);
        when(request.getHeader("WL-Proxy-Client-IP")).thenReturn(null);
        when(request.getHeader("HTTP_CLIENT_IP")).thenReturn(expectedIp);

        // When
        String actualIp = IPUtil.getIPAddress(request);

        // Then
        assertEquals(expectedIp, actualIp);
    }

    @Test
    void testGetIPAddress_XRealIP() {
        // Given
        String expectedIp = "192.168.1.5";
        when(request.getHeader("X-Forwarded-For")).thenReturn(null);
        when(request.getHeader("Proxy-Client-IP")).thenReturn(null);
        when(request.getHeader("WL-Proxy-Client-IP")).thenReturn(null);
        when(request.getHeader("HTTP_CLIENT_IP")).thenReturn(null);
        when(request.getHeader("X-Real-IP")).thenReturn(expectedIp);

        // When
        String actualIp = IPUtil.getIPAddress(request);

        // Then
        assertEquals(expectedIp, actualIp);
    }

    @Test
    void testGetIPAddress_RemoteAddr() {
        // Given
        String expectedIp = "192.168.1.6";
        when(request.getHeader("X-Forwarded-For")).thenReturn(null);
        when(request.getHeader("Proxy-Client-IP")).thenReturn(null);
        when(request.getHeader("WL-Proxy-Client-IP")).thenReturn(null);
        when(request.getHeader("HTTP_CLIENT_IP")).thenReturn(null);
        when(request.getHeader("X-Real-IP")).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn(expectedIp);

        // When
        String actualIp = IPUtil.getIPAddress(request);

        // Then
        assertEquals(expectedIp, actualIp);
    }
}
