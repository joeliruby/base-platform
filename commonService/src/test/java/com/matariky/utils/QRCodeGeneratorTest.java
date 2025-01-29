package com.matariky.utils;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.zxing.WriterException;

@SpringBootTest
public class QRCodeGeneratorTest {

    @InjectMocks
    private QRCodeGenerator qrcodegenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateQRCodeImage() throws WriterException, IOException {
        // Given
        String text = "Hello World";
        int width = 200;
        int height = 200;
        String filePath = "src/test/resources/testQRCode.png";

        // When
        QRCodeGenerator.generateQRCodeImage(text, width, height, filePath);

        // Then
        Path path = FileSystems.getDefault().getPath(filePath);
        assertTrue(Files.exists(path));
        // Clean up
        Files.deleteIfExists(path);
    }

    @Test
    void testGenerateQRCodeImageThrowsWriterException() {
        // Given
        String text = "Hello World";
        int width = -1; // Invalid width to cause WriterException
        int height = 200;
        String filePath = "src/test/resources/testQRCode.png";

        // When & Then
        assertThrows(WriterException.class, () -> {
            QRCodeGenerator.generateQRCodeImage(text, width, height, filePath);
        });
    }

    @Test
    void testGenerateQRCodeImageThrowsIOException() {
        // Given
        String text = "Hello World";
        int width = 200;
        int height = 200;
        String filePath = "invalid_path/testQRCode.png"; // Invalid path to cause IOException

        // When & Then
        assertThrows(IOException.class, () -> {
            QRCodeGenerator.generateQRCodeImage(text, width, height, filePath);
        });
    }
}
