package com.matariky.userservice.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.google.code.kaptcha.impl.DefaultKaptcha;

@SpringBootTest
public class KaptchaConfigTest {

    @InjectMocks
    private KaptchaConfig kaptchaconfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProducer() {
        // Given
        // No specific setup required for this test

        // When
        DefaultKaptcha defaultKaptcha = kaptchaconfig.producer();

        // Then
        assertNotNull(defaultKaptcha);
        assertNotNull(defaultKaptcha.getConfig());
        assertEquals("no", defaultKaptcha.getConfig().getProperties().getProperty("kaptcha.border"));
        assertEquals("black",
                defaultKaptcha.getConfig().getProperties().getProperty("kaptcha.textproducer.font.color"));
        assertEquals("5", defaultKaptcha.getConfig().getProperties().getProperty("kaptcha.textproducer.char.space"));
    }

    // Add more test methods for other methods in KaptchaConfig if needed
}
