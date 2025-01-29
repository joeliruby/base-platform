package com.matariky.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ImageUtilsTest {

    @InjectMocks
    private ImageUtils imageutils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsImageWithPngFile() {
        // Given
        String fileName = "test.png";

        // When
        boolean result = imageutils.isImage(fileName);

        // Then
        assertTrue(result);
    }

    @Test
    void testIsImageWithJpgFile() {
        // Given
        String fileName = "test.jpg";

        // When
        boolean result = imageutils.isImage(fileName);

        // Then
        assertTrue(result);
    }

    @Test
    void testIsImageWithNonImageFile() {
        // Given
        String fileName = "test.txt";

        // When
        boolean result = imageutils.isImage(fileName);

        // Then
        assertFalse(result);
    }

    @Test
    void testIsImageWithNullFileName() {
        // Given
        String fileName = null;

        // When
        boolean result = imageutils.isImage(fileName);

        // Then
        assertFalse(result);
    }

    @Test
    void testIsImageWithEmptyFileName() {
        // Given
        String fileName = "";

        // When
        boolean result = imageutils.isImage(fileName);

        // Then
        assertFalse(result);
    }
}
