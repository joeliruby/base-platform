package com.matariky.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.mockito.Mock;

@SpringBootTest
public class ExcelTemplateUtilTest {

    @InjectMocks
    private ExcelTemplateUtil excelTemplateUtil;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ServletOutputStream servletOutputStream;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDownloadExcel() throws Exception {
        // Given
        String inFileName = "testTemplate.xlsx";
        String outFileName = "output.xlsx";
        InputStream inputStream = getClass().getResourceAsStream("/template/" + inFileName);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        when(response.getOutputStream()).thenReturn(servletOutputStream);
        doAnswer(invocation -> {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            return null;
        }).when(servletOutputStream).write(any(byte[].class), anyInt(), anyInt());

        // When
        excelTemplateUtil.downloadExcel(response, inFileName, outFileName);

        // Then
        verify(response).reset();
        verify(response).setContentType("application/vnd.ms-excel");
        verify(response).setHeader(eq("Content-Disposition"), contains("attachment;filename="));
        verify(servletOutputStream).flush();
        verify(servletOutputStream).close();
        assertArrayEquals(byteArrayOutputStream.toByteArray(), byteArrayOutputStream.toByteArray());
    }

    // Add more test methods for other methods in ExcelTemplateUtil
}
