package com.matariky.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HexUtilTest {

    @InjectMocks
    private HexUtil hexutil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsHexNumber() {
        // Given
        String hexValue = "0x1A";
        String nonHexValue = "123G";

        // When
        boolean isHex = hexutil.isHexNumber(hexValue);
        boolean isNotHex = hexutil.isHexNumber(nonHexValue);

        // Then
        assertThat(isHex).isTrue();
        assertThat(isNotHex).isFalse();
    }

    @Test
    void testEncodeHex() {
        // Given
        byte[] data = { 0x1, 0x2F };

        // When
        char[] hexChars = hexutil.encodeHex(data);

        // Then
        assertThat(hexChars).isEqualTo(new char[] { '0', '1', '2', 'f' });
    }

    @Test
    void testEncodeHexStr() {
        // Given
        byte[] data = { 0x1, 0x2F };

        // When
        String hexString = hexutil.encodeHexStr(data);

        // Then
        assertThat(hexString).isEqualTo("012f");
    }

    @Test
    void testDecodeHex() {
        // Given
        String hexStr = "012f";

        // When
        byte[] bytes = hexutil.decodeHex(hexStr);

        // Then
        assertThat(bytes).isEqualTo(new byte[] { 0x1, 0x2F });
    }

    @Test
    void testToUnicodeHex() {
        // Given
        char ch = '我';

        // When
        String unicodeHex = hexutil.toUnicodeHex(ch);

        // Then
        assertThat(unicodeHex).isEqualTo("\\u6211");
    }

    // Add more test methods for other methods in HexUtil
}
