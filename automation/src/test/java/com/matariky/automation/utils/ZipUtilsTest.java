package com.matariky.automation.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ZipUtilsTest {

    @InjectMocks
    private ZipUtils ziputils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToZipWithEmptyDirectory() {
        // Given
        String srcDir = "src/test/resources/emptyDir";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        boolean keepDirStructure = true;

        // When
        ziputils.toZip(srcDir, out, keepDirStructure);

        // Then
        assertThat(out.size()).isGreaterThan(0);
    }

    @Test
    void testToZipWithFiles() {
        // Given
        String srcDir = "src/test/resources/filesDir";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        boolean keepDirStructure = true;

        // When
        ziputils.toZip(srcDir, out, keepDirStructure);

        // Then
        assertThat(out.size()).isGreaterThan(0);
    }

    @Test
    void testToZipWithoutKeepingDirStructure() {
        // Given
        String srcDir = "src/test/resources/filesDir";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        boolean keepDirStructure = false;

        // When
        ziputils.toZip(srcDir, out, keepDirStructure);

        // Then
        assertThat(out.size()).isGreaterThan(0);
    }

    @Test
    void testDownLoadZip() throws Exception {
        // Given
        String fileName = "test.zip";
        String path = "src/test/resources/test.zip";
        HttpServletResponse response = mock(HttpServletResponse.class);
        ServletOutputStream out = mock(ServletOutputStream.class);
        when(response.getOutputStream()).thenReturn(out);

        // When
        ziputils.downLoadZip(fileName, path, response);

        // Then
        verify(response).setCharacterEncoding("UTF-8");
        verify(response).setHeader("Content-Disposition", "attachment; filename=" + fileName);
        verify(response).setContentLength((int) new File(path).length());
        verify(response).setContentType("application/zip");
        assertThat(out.toString().length()).isGreaterThan(0);
    }

    // Add more test methods for other methods in ZipUtils
}
