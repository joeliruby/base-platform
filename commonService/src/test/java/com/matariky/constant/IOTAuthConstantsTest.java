package com.matariky.constant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class IOTAuthConstantsTest {

    @InjectMocks
    private IOTAuthConstants iotauthconstants;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNoTidConstant() {
        // Given
        Integer expectedValue = 4;

        // When
        Integer actualValue = IOTAuthConstants.NO_TID;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testAllPassConstant() {
        // Given
        Integer expectedValue = 1;

        // When
        Integer actualValue = IOTAuthConstants.ALL_PASS;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testAllFailConstant() {
        // Given
        Integer expectedValue = 3;

        // When
        Integer actualValue = IOTAuthConstants.ALL_FAIL;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testPartialPassConstant() {
        // Given
        Integer expectedValue = 2;

        // When
        Integer actualValue = IOTAuthConstants.PARTIAL_PASS;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }
}
