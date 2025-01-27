package com.matariky.user.customer.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class EncryptionUtilsTest {

    @InjectMocks
    private EncryptionUtils encryptionutils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHashWithMD5() {
        // Given
        String source = "testString";
        String hashType = "MD5";
        String expectedHash = "6f8db599de986fab7a21625b7916589c";

        // When
        String actualHash = encryptionutils.getHash(source, hashType);

        // Then
        assertThat(actualHash).isEqualTo(expectedHash);
    }

    @Test
    void testGetHashWithSHA1() {
        // Given
        String source = "testString";
        String hashType = "SHA-1";
        String expectedHash = "661295c9cbf9d6b2f6428414504a8deed3020641";

        // When
        String actualHash = encryptionutils.getHash(source, hashType);

        // Then
        assertThat(actualHash).isEqualTo(expectedHash);
    }

    @Test
    void testGetHash2WithMD5() {
        // Given
        String source = "testString";
        String hashType = "MD5";
        String expectedHash = "6F8DB599DE986FAB7A21625B7916589C";

        // When
        String actualHash = encryptionutils.getHash2(source, hashType);

        // Then
        assertThat(actualHash).isEqualTo(expectedHash);
    }

    @Test
    void testGetHash2WithSHA1() {
        // Given
        String source = "testString";
        String hashType = "SHA-1";
        String expectedHash = "661295C9CBF9D6B2F6428414504A8DEED3020641";

        // When
        String actualHash = encryptionutils.getHash2(source, hashType);

        // Then
        assertThat(actualHash).isEqualTo(expectedHash);
    }

    @Test
    void testGetHash3WithMD5() {
        // Given
        String source = "testString";
        String hashType = "MD5";
        String expectedHash = "6f8db599de986fab7a21625b7916589c";

        // When
        String actualHash = encryptionutils.getHash3(source, hashType);

        // Then
        assertThat(actualHash).isEqualTo(expectedHash);
    }

    @Test
    void testGetHash3WithSHA1() {
        // Given
        String source = "testString";
        String hashType = "SHA-1";
        String expectedHash = "661295c9cbf9d6b2f6428414504a8deed3020641";

        // When
        String actualHash = encryptionutils.getHash3(source, hashType);

        // Then
        assertThat(actualHash).isEqualTo(expectedHash);
    }

    // Add more test methods for other scenarios and edge cases
}
