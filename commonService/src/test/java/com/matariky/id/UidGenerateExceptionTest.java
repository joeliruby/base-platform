package com.matariky.id;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UidGenerateExceptionTest {

    @InjectMocks
    private UidGenerateException uidGenerateException;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDefaultConstructor() {
        // Given
        UidGenerateException exception = new UidGenerateException();

        // Then
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testConstructorWithMessage() {
        // Given
        String message = "Error message";

        // When
        UidGenerateException exception = new UidGenerateException(message);

        // Then
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // Given
        String message = "Error message";
        Throwable cause = new Throwable("Cause");

        // When
        UidGenerateException exception = new UidGenerateException(message, cause);

        // Then
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void testConstructorWithCause() {
        // Given
        Throwable cause = new Throwable("Cause");

        // When
        UidGenerateException exception = new UidGenerateException(cause);

        // Then
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo("java.lang.Throwable: Cause");
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void testConstructorWithMessageFormat() {
        // Given
        String msgFormat = "Error %s";
        String arg = "message";

        // When
        UidGenerateException exception = new UidGenerateException(msgFormat, arg);

        // Then
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo("Error message");
        assertThat(exception.getCause()).isNull();
    }
}
