package com.matariky.automation.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import org.mockito.Mock;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SpringBootTest
public class CheckedSessionTest {

    @InjectMocks
    private CheckedSession checkedSession;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCheckWhenSessionAttributeIsNull() {
        // Given
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("KEY")).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        // When
        String result = CheckedSession.check(request);

        // Then
        assertNotNull(result);
        verify(session).setAttribute(eq("KEY"), anyString());
    }

    @Test
    void testCheckWhenSessionAttributeIsNotNull() {
        // Given
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("KEY")).thenReturn("existingKey");

        // When
        String result = CheckedSession.check(request);

        // Then
        assertThat(result).isEqualTo("existingKey");
        verify(session, never()).setAttribute(eq("KEY"), anyString());
    }

    // Add more test methods for other scenarios if needed
}
