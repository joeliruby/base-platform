package com.matariky.userservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.google.code.kaptcha.Producer;
import com.matariky.redis.RedisUtils;
import org.mockito.Mock;
import java.awt.image.BufferedImage;
import java.io.IOException;

@SpringBootTest
public class CaptchaServiceTest {

    @InjectMocks
    private CaptchaService captchaService;

    @Mock
    private Producer producer;

    @Mock
    private RedisUtils redisUtils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerate() throws IOException {
        // Given
        String resultCode = "1234";

        // When
        String base64Image = captchaService.generate(resultCode);

        // Then
        assertNotNull(base64Image);
        assertTrue(base64Image.startsWith("data:image/jpg;base64,"));
    }

    @Test
    void testCreate() {
        // Given
        String uuid = "test-uuid";
        String code = "abcd";
        BufferedImage image = new BufferedImage(90, 36, BufferedImage.TYPE_INT_RGB);

        when(producer.createText()).thenReturn(code);
        when(producer.createImage(code)).thenReturn(image);

        // When
        BufferedImage result = captchaService.create(uuid);

        // Then
        assertNotNull(result);
        verify(redisUtils).set("sys:captcha:" + uuid, code, 300);
    }

    @Test
    void testValidate() {
        // Given
        String uuid = "test-uuid";
        String code = "abcd";

        when(redisUtils.get("sys:captcha:" + uuid)).thenReturn(code);

        // When
        boolean isValid = captchaService.validate(uuid, code);

        // Then
        assertTrue(isValid);
        verify(redisUtils).delete("sys:captcha:" + uuid);
    }

    @Test
    void testValidateInvalidCode() {
        // Given
        String uuid = "test-uuid";
        String code = "abcd";
        String invalidCode = "efgh";

        when(redisUtils.get("sys:captcha:" + uuid)).thenReturn(code);

        // When
        boolean isValid = captchaService.validate(uuid, invalidCode);

        // Then
        assertFalse(isValid);
        verify(redisUtils, never()).delete("sys:captcha:" + uuid);
    }
}
