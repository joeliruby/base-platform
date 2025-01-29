package com.matariky.utils;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import javax.servlet.http.HttpServletRequest;

@SpringBootTest
public class IpUtilsTest {

    @InjectMocks
    private IpUtils iputils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetIpAddr() {
        // Given
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("x-forwarded-for")).thenReturn("192.168.1.1");

        // When
        String ip = IpUtils.getIpAddr(request);

        // Then
        assertThat(ip).isEqualTo("192.168.1.1");
    }

    @Test
    void testGetIpAddrWithNullRequest() {
        // Given
        HttpServletRequest request = null;

        // When
        String ip = IpUtils.getIpAddr(request);

        // Then
        assertThat(ip).isEqualTo("unknown");
    }

    @Test
    void testInternalIp() {
        // Given
        String ip = "192.168.1.1";

        // When
        boolean result = IpUtils.internalIp(ip);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void testInternalIpWithExternalIp() {
        // Given
        String ip = "8.8.8.8";

        // When
        boolean result = IpUtils.internalIp(ip);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    void testGetHostIp() {
        // When
        String ip = IpUtils.getHostIp();

        // Then
        assertThat(ip).isNotBlank();
    }

    @Test
    void testGetHostName() {
        // When
        String hostName = IpUtils.getHostName();

        // Then
        assertThat(hostName).isNotBlank();
    }

    @Test
    void testGetMultistageReverseProxyIp() {
        // Given
        String ip = "192.168.1.1, 10.0.0.1";

        // When
        String result = IpUtils.getMultistageReverseProxyIp(ip);

        // Then
        assertThat(result).isEqualTo("192.168.1.1");
    }

    @Test
    void testIsUnknown() {
        // Given
        String checkString = "unknown";

        // When
        boolean result = IpUtils.isUnknown(checkString);

        // Then
        assertThat(result).isTrue();
    }
}
