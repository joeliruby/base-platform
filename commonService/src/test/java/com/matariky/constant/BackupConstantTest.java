package com.matariky.constant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BackupConstantTest {

    @InjectMocks
    private BackupConstant backupconstant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFileSuffix() {
        // Given
        String expectedSuffix = ".sql";

        // When
        String actualSuffix = BackupConstant.FILE_SUFFIX;

        // Then
        assertThat(actualSuffix).isEqualTo(expectedSuffix);
    }

    @Test
    void testDatabaseName() {
        // Given
        String expectedDatabaseName = "qslsaas";

        // When
        String actualDatabaseName = BackupConstant.DATA_BASE_NAME;

        // Then
        assertThat(actualDatabaseName).isEqualTo(expectedDatabaseName);
    }

    @Test
    void testIsSystemWindows() {
        // Given
        String osName = "windows";

        // When
        boolean isWindows = BackupConstant.isSystem(osName);

        // Then
        assertThat(isWindows).isTrue();
    }

    @Test
    void testIsSystemLinux() {
        // Given
        String osName = "linux";

        // When
        boolean isLinux = BackupConstant.isSystem(osName);

        // Then
        assertThat(isLinux).isFalse();
    }

    // Add more test methods for other scenarios if needed
}
