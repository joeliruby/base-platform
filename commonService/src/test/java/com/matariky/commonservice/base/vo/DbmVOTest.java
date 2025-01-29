package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class DbmVOTest {

    @InjectMocks
    private DbmVO dbmvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetValue() {
        // Given
        String expectedValue = "testValue";
        dbmvo.setValue(expectedValue);

        // When
        String actualValue = dbmvo.getValue();

        // Then
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @Test
    void testGetLabel() {
        // Given
        String expectedLabel = "testLabel";
        dbmvo.setLabel(expectedLabel);

        // When
        String actualLabel = dbmvo.getLabel();

        // Then
        assertThat(actualLabel).isEqualTo(expectedLabel);
    }

    @Test
    void testSetValue() {
        // Given
        String expectedValue = "newValue";

        // When
        dbmvo.setValue(expectedValue);

        // Then
        assertThat(dbmvo.getValue()).isEqualTo(expectedValue);
    }

    @Test
    void testSetLabel() {
        // Given
        String expectedLabel = "newLabel";

        // When
        dbmvo.setLabel(expectedLabel);

        // Then
        assertThat(dbmvo.getLabel()).isEqualTo(expectedLabel);
    }
}
