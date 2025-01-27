package com.matariky.automation.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@SpringBootTest
public class GenerateFileTest {

    @InjectMocks
    private GenerateFile generatefile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInitDir() throws IOException {
        // Given
        String URL = "testURL";
        String keyParam = "key:param";
        String mk = "mk";
        String name = "name";

        // When
        Map<String, String> result = GenerateFile.initDir(URL, keyParam, mk, name);

        // Then
        assertNotNull(result);
        assertEquals(result.get("bean"), "testURL\\key_param\\mk\\bean\\name.java");
        assertEquals(result.get("mapper"), "testURL\\key_param\\mk\\mapper\\nameMapper.java");
        assertEquals(result.get("mapperxml"), "testURL\\key_param\\mk\\mapper\\nameMapper.xml");
        assertEquals(result.get("service"), "testURL\\key_param\\mk\\service\\nameService.java");
        assertEquals(result.get("controller"), "testURL\\key_param\\mk\\controller\\nameController.java");
    }

    @Test
    void testCreateDir() throws IOException {
        // Given
        String url = "testDir/testFile.txt";

        // When
        GenerateFile.createDir(url);

        // Then
        File file = new File(url);
        assertTrue(file.exists());
        assertTrue(file.isFile());

        // Clean up
        file.delete();
        file.getParentFile().delete();
    }

    @Test
    void testWriteTxtFile() throws Exception {
        // Given
        String content = "Test content";
        File file = new File("testFile.txt");

        // When
        boolean result = GenerateFile.writeTxtFile(content, file);

        // Then
        assertTrue(result);
        assertTrue(file.exists());

        // Clean up
        file.delete();
    }
}
