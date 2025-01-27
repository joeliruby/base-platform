package com.matariky.automation.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.File;
import java.io.IOException;

@SpringBootTest
public class DelFileTest {

    @InjectMocks
    private DelFile delfile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDelFolder() throws IOException {
        // Given
        File folder = new File("testFolder");
        folder.mkdir();
        File file = new File(folder, "testFile.txt");
        file.createNewFile();

        // When
        delfile.delFolder("testFolder");

        // Then
        assertFalse(folder.exists());
    }

    @Test
    void testDelAllFile() throws IOException {
        // Given
        File folder = new File("testFolder");
        folder.mkdir();
        File file1 = new File(folder, "testFile1.txt");
        file1.createNewFile();
        File file2 = new File(folder, "testFile2.txt");
        file2.createNewFile();

        // When
        boolean result = delfile.delAllFile("testFolder");

        // Then
        assertTrue(result);
        assertEquals(0, folder.list().length);
    }

    @Test
    void testDelAllFileWithNonExistentPath() {
        // Given
        String nonExistentPath = "nonExistentFolder";

        // When
        boolean result = delfile.delAllFile(nonExistentPath);

        // Then
        assertFalse(result);
    }

    @Test
    void testDelAllFileWithFileInsteadOfDirectory() throws IOException {
        // Given
        File file = new File("testFile.txt");
        file.createNewFile();

        // When
        boolean result = delfile.delAllFile("testFile.txt");

        // Then
        assertFalse(result);
        file.delete();
    }
}
