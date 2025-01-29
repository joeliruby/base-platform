package com.matariky.commonservice.minio.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.matariky.commonservice.minio.utils.MinioUtil;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class FileControllerTest {

    @InjectMocks
    private FileController filecontroller;

    @Mock
    private MinioUtil minioUtil;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFileUpload() throws Exception {
        // Given
        MultipartFile uploadfile = new MockMultipartFile("file", "test.txt", "text/plain", "test content".getBytes());
        String bucket = "test-bucket";
        String objectName = "test-object";

        // When
        AjaxResult result = (AjaxResult) filecontroller.fileupload(uploadfile, bucket, objectName);

        // Then
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testDownloadFile() throws Exception {
        // Given
        String bucket = "test-bucket";
        String objectName = "test-object/test.txt";
        InputStream inputStream = new ByteArrayInputStream("test content".getBytes());
        ServletOutputStream outputStream = mock(ServletOutputStream.class);

        // When
        when(minioUtil.download(bucket, objectName)).thenReturn(inputStream);
        when(response.getOutputStream()).thenReturn(outputStream);
        filecontroller.downloadFile(bucket, objectName, response);

        // Then
        verify(response).setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("test.txt", "UTF-8"));
        verify(response).setContentType("application/octet-stream");
        verify(response).setCharacterEncoding("UTF-8");
        verify(outputStream).write("test content".getBytes(), 0, "test content".length());
    }

    // Add more test methods for other methods in FileController
}
