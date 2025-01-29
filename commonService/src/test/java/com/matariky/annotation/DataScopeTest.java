package com.matariky.annotation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class DataScopeTest {

    @InjectMocks
    private DataScope datascope;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDefaultValue() {
        // Given
        String expectedValue = "t";

        // When
        String actualValue = datascope.value();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testDefaultAlias() {
        // Given
        String expectedAlias = "t";

        // When
        String actualAlias = datascope.alias();

        // Then
        assertThat(actualAlias).isEqualTo(expectedAlias);
    }

    @Test
    void testDefaultParamName() {
        // Given
        String expectedParamName = "params";

        // When
        String actualParamName = datascope.paramName();

        // Then
        assertThat(actualParamName).isEqualTo(expectedParamName);
    }

    // Add more test methods for other methods in DataScope
}
