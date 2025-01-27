package com.matariky.automation.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class WordUtilTest {

    @InjectMocks
    private WordUtil wordutil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateWord() throws Exception {
        // Given
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("key", "value");
        String templateName = "template.ftl";
        String filePath = "testPath";
        String fileName = "testFile.doc";

        // When
        wordutil.createWord(dataMap, templateName, filePath, fileName);

        // Then
        File outFile = new File(filePath + File.separator + fileName);
        assertTrue(outFile.exists());
        // Clean up
        outFile.delete();
    }

    @Test
    void testDownLoad() throws IOException {
        // Given
        String fileName = "testFile.doc";
        String path = "testPath" + File.separator + fileName;
        HttpServletResponse response = mock(HttpServletResponse.class);
        File file = new File(path);
        file.getParentFile().mkdirs();
        file.createNewFile();

        // When
        wordutil.downLoad(fileName, path, response);

        // Then
        verify(response).setHeader(eq("Content-Disposition"), anyString());
        verify(response).setContentLength((int) file.length());
        verify(response).setContentType("application/msword");

        // Clean up
        file.delete();
    }

    // Add more test methods for other methods in WordUtil
}
