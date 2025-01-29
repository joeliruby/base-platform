package com.matariky.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
public class FileUtilTest {

    @InjectMocks
    private FileUtil fileUtil;

    @Mock
    private MultipartFile multipartFile;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvertMultipartFileToFile() throws IOException {
        // Given
        File tempFile = File.createTempFile("test", null);
        when(multipartFile.getOriginalFilename()).thenReturn("testFile.txt");
        doAnswer(invocation -> {
            File file = invocation.getArgument(0);
            FileUtils.copyFile(tempFile, file);
            return null;
        });

        // When
        File result = FileUtil.convertMultipartFileToFile(multipartFile);

        // Then
        assertNotNull(result);
        assertTrue(result.exists());
        assertEquals("testFile.txt", result.getName());
    }

    @Test
    void testDownloadFile() throws IOException {
        // Given
        String fileUrl = "https://example.com/testFile.txt";

        // When
        File result = FileUtil.downloadFile(fileUrl);

        // Then
        assertNotNull(result);
        assertTrue(result.exists());
    }

    @Test
    void testGetRealPath() {
        // Given
        when(request.getSession()).thenReturn(mock(HttpSession.class));
        when(request.getSession().getServletContext()).thenReturn(mock(ServletContext.class));
        when(request.getSession().getServletContext().getRealPath("/upload")).thenReturn("C:/upload");

        // When
        String realPath = FileUtil.getRealPath(request);

        // Then
        assertEquals("C:/upload", realPath);
    }

    @Test
    void testSaveFile() throws IOException {
        // Given
        String savePath = "C:/upload/";
        String fileFullName = "testFile.txt";
        byte[] fileContent = "Test content".getBytes();
        when(multipartFile.getBytes()).thenReturn(fileContent);

        // When
        boolean result = FileUtil.saveFile(savePath, fileFullName, multipartFile);

        // Then
        assertTrue(result);
        File savedFile = new File(savePath + fileFullName);
        assertTrue(savedFile.exists());
        assertEquals(fileContent.length, savedFile.length());
    }
}
