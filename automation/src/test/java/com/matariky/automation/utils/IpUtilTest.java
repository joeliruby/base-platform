package com.matariky.automation.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IpUtilTest {

    @InjectMocks
    private IpUtil ipUtil;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetIpAddr_xForwardedFor() {
        // Given
        when(request.getHeader("x-forwarded-for")).thenReturn("192.168.1.1");

        // When
        String ipAddress = ipUtil.getIpAddr(request);

        // Then
        assertThat(ipAddress).isEqualTo("192.168.1.1");
    }

    @Test
    void testGetIpAddr_proxyClientIp() {
        // Given
        when(request.getHeader("x-forwarded-for")).thenReturn(null);
        when(request.getHeader("Proxy-Client-IP")).thenReturn("192.168.1.2");

        // When
        String ipAddress = ipUtil.getIpAddr(request);

        // Then
        assertThat(ipAddress).isEqualTo("192.168.1.2");
    }

    @Test
    void testGetIpAddr_wlProxyClientIp() {
        // Given
        when(request.getHeader("x-forwarded-for")).thenReturn(null);
        when(request.getHeader("Proxy-Client-IP")).thenReturn(null);
        when(request.getHeader("WL-Proxy-Client-IP")).thenReturn("192.168.1.3");

        // When
        String ipAddress = ipUtil.getIpAddr(request);

        // Then
        assertThat(ipAddress).isEqualTo("192.168.1.3");
    }

    @Test
    void testGetIpAddr_remoteAddr() {
        // Given
        when(request.getHeader("x-forwarded-for")).thenReturn(null);
        when(request.getHeader("Proxy-Client-IP")).thenReturn(null);
        when(request.getHeader("WL-Proxy-Client-IP")).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn("192.168.1.4");

        // When
        String ipAddress = ipUtil.getIpAddr(request);

        // Then
        assertThat(ipAddress).isEqualTo("192.168.1.4");
    }

    @Test
    void testGetIpAddr_localhost() throws Exception {
        // Given
        when(request.getHeader("x-forwarded-for")).thenReturn(null);
        when(request.getHeader("Proxy-Client-IP")).thenReturn(null);
        when(request.getHeader("WL-Proxy-Client-IP")).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");
        InetAddress inetAddress = mock(InetAddress.class);
        when(inetAddress.getHostAddress()).thenReturn("192.168.1.5");
        mockStatic(InetAddress.class);
        when(InetAddress.getLocalHost()).thenReturn(inetAddress);

        // When
        String ipAddress = ipUtil.getIpAddr(request);

        // Then
        assertThat(ipAddress).isEqualTo("192.168.1.5");
    }

    @Test
    void testGetIpAddr_multipleIps() {
        // Given
        when(request.getHeader("x-forwarded-for")).thenReturn("192.168.1.6, 192.168.1.7");

        // When
        String ipAddress = ipUtil.getIpAddr(request);

        // Then
        assertThat(ipAddress).isEqualTo("192.168.1.6");
    }
}
