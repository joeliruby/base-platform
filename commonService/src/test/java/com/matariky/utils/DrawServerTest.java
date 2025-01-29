package com.matariky.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;

@SpringBootTest
public class DrawServerTest {

    @InjectMocks
    private DrawServer drawserver;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetServerSuccess() throws IOException {
        // Given
        int port = 8080;
        String expectedData = "Test data";
        java.net.ServerSocket serverSocket = mock(java.net.ServerSocket.class);
        java.net.Socket clientSocket = mock(java.net.Socket.class);
        java.io.InputStream inputStream = mock(java.io.InputStream.class);
        java.io.OutputStream outputStream = mock(java.io.OutputStream.class);

        when(serverSocket.accept()).thenReturn(clientSocket);
        when(clientSocket.getInputStream()).thenReturn(inputStream);
        when(clientSocket.getOutputStream()).thenReturn(outputStream);
        when(inputStream.read(any(byte[].class))).thenReturn(expectedData.length()).thenReturn(-1);

        // When
        String result = drawserver.setServer(port);

        // Then
        assertEquals(expectedData, result);
        verify(outputStream).write("Label data received".getBytes());
        verify(clientSocket).close();
        verify(serverSocket).close();
    }

    @Test
    void testSetServerIOException() {
        // Given
        int port = 8080;

        // When
        String result = drawserver.setServer(port);

        // Then
        assertEquals("", result);
    }

    // Add more test methods for other scenarios in DrawServer
}
