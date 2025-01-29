package com.matariky.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MD5UtilTest {

    @InjectMocks
    private MD5Util md5util;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testByteArrayToHexString() {
        // Given
        byte[] byteArray = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
        String expectedHexString = "000102030405060708090a0b0c0d0e0f";

        // When
        String result = MD5Util.byteArrayToHexString(byteArray);

        // Then
        assertEquals(expectedHexString, result);
    }

    @Test
    void testMD5EncodeWithCharset() {
        // Given
        String input = "testString";
        String charset = "UTF-8";
        String expectedMD5 = "6f8db599de986fab7a21625b7916589c";

        // When
        String result = MD5Util.MD5Encode(input, charset);

        // Then
        assertEquals(expectedMD5, result);
    }

    @Test
    void testMD5EncodeWithoutCharset() {
        // Given
        String input = "testString";
        String expectedMD5 = "6f8db599de986fab7a21625b7916589c";

        // When
        String result = MD5Util.MD5Encode(input, null);

        // Then
        assertEquals(expectedMD5, result);
    }

    // Add more test methods for other methods in MD5Util if needed
}
