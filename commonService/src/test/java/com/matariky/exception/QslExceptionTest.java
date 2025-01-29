package com.matariky.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class QslExceptionTest {

    @InjectMocks
    private QslException qslexception;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConstructorWithHttpStatus() {
        // Given
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String msgKey = "error.bad.request";
        Object[] args = new Object[] { "param1", "param2" };

        // When
        QslException exception = new QslException(status, msgKey, args);

        // Then
        assertThat(exception.getHttpStatus()).isEqualTo(status);
        assertThat(exception.getMsgKey()).isEqualTo(msgKey);
        assertThat(exception.getArgs()).isEqualTo(args);
    }

    @Test
    void testConstructorWithMsgKey() {
        // Given
        String msgKey = "error.internal.server";

        // When
        QslException exception = new QslException(msgKey);

        // Then
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getMsgKey()).isEqualTo(msgKey);
        assertThat(exception.getArgs()).isEmpty();
    }

    @Test
    void testConstructorWithMsgKeyAndArgs() {
        // Given
        String msgKey = "error.internal.server";
        Object[] args = new Object[] { "param1", "param2" };

        // When
        QslException exception = new QslException(msgKey, args);

        // Then
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getMsgKey()).isEqualTo(msgKey);
        assertThat(exception.getArgs()).isEqualTo(args);
    }
}
