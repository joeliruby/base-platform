package com.matariky.constant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TokenConstantTest {

    @InjectMocks
    private TokenConstant tokenconstant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLastAccessTimeConstant() {
        // Given
        String expectedValue = "LAST_ACCESS_TIME";

        // When
        String actualValue = TokenConstant.LAST_ACCESS_TIME;

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    // Add more test methods for other constants in TokenConstant
}
