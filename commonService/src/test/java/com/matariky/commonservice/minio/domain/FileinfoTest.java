package com.matariky.commonservice.minio.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class FileinfoTest {

    @InjectMocks
    private Fileinfo fileinfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFilename() {
        // Given
        String expectedFilename = "testfile.txt";
        fileinfo.setFilename(expectedFilename);

        // When
        String actualFilename = fileinfo.getFilename();

        // Then
        assertThat(actualFilename).isEqualTo(expectedFilename);
    }

    @Test
    void testSetFilename() {
        // Given
        String expectedFilename = "testfile.txt";

        // When
        fileinfo.setFilename(expectedFilename);

        // Then
        assertThat(fileinfo.getFilename()).isEqualTo(expectedFilename);
    }

    @Test
    void testGetDirectory() {
        // Given
        Boolean expectedDirectory = true;
        fileinfo.setDirectory(expectedDirectory);

        // When
        Boolean actualDirectory = fileinfo.getDirectory();

        // Then
        assertThat(actualDirectory).isEqualTo(expectedDirectory);
    }

    @Test
    void testSetDirectory() {
        // Given
        Boolean expectedDirectory = true;

        // When
        fileinfo.setDirectory(expectedDirectory);

        // Then
        assertThat(fileinfo.getDirectory()).isEqualTo(expectedDirectory);
    }

    // Add more test methods for other methods in Fileinfo if needed
}
