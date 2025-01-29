package com.matariky.commonservice.filters;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.utils.TokenUtils;

@SpringBootTest
public class AccessControllFilterTest {

    @InjectMocks
    private AccessControllFilter accesscontrollfilter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain chain;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoFilter_withValidPermId() throws IOException, ServletException {
        // Given
        when(request.getRequestURI()).thenReturn("/some/uri");
        when(request.getHeader("Id")).thenReturn("home123");
        when(request.getHeader("Authorization")).thenReturn("validToken");
        when(TokenUtils.extractPermissionIdsFromToken("validToken")).thenReturn(Arrays.asList("home123"));

        // When
        accesscontrollfilter.doFilter(request, response, chain);

        // Then
        verify(chain, times(1)).doFilter(request, response);
    }

    @Test
    void testDoFilter_withInvalidPermId() throws IOException, ServletException {
        // Given
        when(request.getRequestURI()).thenReturn("/some/uri");
        when(request.getHeader("Id")).thenReturn("invalidId");
        when(request.getHeader("Authorization")).thenReturn("validToken");
        when(TokenUtils.extractPermissionIdsFromToken("validToken")).thenReturn(Arrays.asList("home123"));

        // When
        accesscontrollfilter.doFilter(request, response, chain);

        // Then
        verify(response, times(1)).setStatus(HttpServletResponse.SC_FORBIDDEN);
        verify(chain, never()).doFilter(request, response);
    }

    @Test
    void testDoFilter_withEmptyPermId() throws IOException, ServletException {
        // Given
        when(request.getRequestURI()).thenReturn("/some/uri");
        when(request.getHeader("Id")).thenReturn("");
        when(request.getHeader("Authorization")).thenReturn("validToken");

        // When
        accesscontrollfilter.doFilter(request, response, chain);

        // Then
        verify(response, times(1)).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        verify(chain, never()).doFilter(request, response);
    }

    @Test
    void testDoFilter_withEmptyToken() throws IOException, ServletException {
        // Given
        when(request.getRequestURI()).thenReturn("/some/uri");
        when(request.getHeader("Id")).thenReturn("home123");
        when(request.getHeader("Authorization")).thenReturn("");

        // When
        accesscontrollfilter.doFilter(request, response, chain);

        // Then
        verify(response, times(1)).setStatus(HttpServletResponse.SC_FORBIDDEN);
        verify(chain, never()).doFilter(request, response);
    }

    @Test
    void testDoFilter_withExcludedUri() throws IOException, ServletException {
        // Given
        when(request.getRequestURI()).thenReturn("/api-docs");

        // When
        accesscontrollfilter.doFilter(request, response, chain);

        // Then
        verify(chain, times(1)).doFilter(request, response);
    }
}
