package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class IOTAuthenticationVOTest {

    @InjectMocks
    private IOTAuthenticationVO iotauthenticationvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetSignature() {
        // Given
        String signature = "testSignature";

        // When
        iotauthenticationvo.setSignature(signature);

        // Then
        assertThat(iotauthenticationvo.getSignature()).isEqualTo(signature);
    }

    @Test
    void testSetAndGetTagValidity() {
        // Given
        List<Map<String, String>> tagValidity = List.of(Map.of("key", "value"));

        // When
        iotauthenticationvo.setTagValidity(tagValidity);

        // Then
        assertThat(iotauthenticationvo.getTagValidity()).isEqualTo(tagValidity);
    }

    // Add more test methods for other methods in IOTAuthenticationVO if needed
}
