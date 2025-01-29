package com.matariky.commonservice.base.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.commonservice.minio.utils.MinioUtil;

@SpringBootTest
public class ReaderFileControllerTest {

    @InjectMocks
    private ReaderFileController readerFileController;

    @Mock
    private MinioUtil minioUtil;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDownloadFileSuccess() throws Exception {
        // Given
        InputStream inputStream = new ByteArrayInputStream("file content".getBytes());
        when(minioUtil.download("device-upload-package", "reader.tar.gz")).thenReturn(inputStream);
        ServletOutputStream outputStreamMock = new ServletOutputStream() {
            private StringBuilder stringBuilder = new StringBuilder();

            @Override
            public void write(int b) throws IOException {
                stringBuilder.append((char) b);
            }

            @Override
            public void setWriteListener(javax.servlet.WriteListener writeListener) {
                // No-op for testing
            }

            @Override
            public boolean isReady() {
                return true;
            }

        };
        when(response.getOutputStream()).thenReturn(outputStreamMock);

        // When
        readerFileController.test(response);

        // Then
        verify(response).setContentType("application/octet-stream");
        verify(response).setHeader("Content-Disposition", "attachment; filename=reader.tar.gz");
        assertEquals("file content", outputStreamMock.toString());
    }

    @Test
    void testDownloadFileFailure() throws Exception {
        // Given
        when(minioUtil.download("device-upload-package", "reader.tar.gz"))
                .thenThrow(new RuntimeException("File not found"));

        // When & Then
        assertThrows(RuntimeException.class, () -> readerFileController.test(response));
    }

    // Add more test methods for other methods in ReaderFileController
}
