package com.matariky.commonservice.upload.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RenExceptionTest {

    @InjectMocks
    private RenException renexception;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConstructorWithMessage() {
        // Given
        String message = "Test message";

        // When
        RenException exception = new RenException(message);

        // Then
        assertEquals(message, exception.getMsg());
    }

    @Test
    void testConstructorWithMessageAndThrowable() {
        // Given
        String message = "Test message";
        Throwable cause = new Throwable("Cause");

        // When
        RenException exception = new RenException(message, cause);

        // Then
        assertEquals(message, exception.getMsg());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithMessageAndResource() {
        // Given
        String message = "Test message";
        String resource = " Resource";

        // When
        RenException exception = new RenException(message, resource);

        // Then
        assertEquals(message + resource, exception.getMsg());
    }

    @Test
    void testSetMsg() {
        // Given
        String message = "Test message";
        renexception = new RenException(message);

        // When
        String newMessage = "New message";
        renexception.setMsg(newMessage);

        // Then
        assertEquals(newMessage, renexception.getMsg());
    }

    @Test
    void testGetMsg() {
        // Given
        String message = "Test message";
        renexception = new RenException(message);

        // When
        String result = renexception.getMsg();

        // Then
        assertEquals(message, result);
    }
}
