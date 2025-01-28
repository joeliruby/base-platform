package com.matariky.jobs.jobsService.assetitm.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TapeRfidCreateParameterTaskTest {

    @InjectMocks
    private TapeRfidCreateParameterTask taperfidcreateparametertask;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        taperfidcreateparametertask.setId(expectedId);

        // When
        Long actualId = taperfidcreateparametertask.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testSetId() {
        // Given
        Long expectedId = 1L;

        // When
        taperfidcreateparametertask.setId(expectedId);

        // Then
        assertThat(taperfidcreateparametertask.getId()).isEqualTo(expectedId);
    }

    @Test
    void testGetRfidfactoryId() {
        // Given
        Long expectedRfidfactoryId = 2L;
        taperfidcreateparametertask.setRfidfactoryId(expectedRfidfactoryId);

        // When
        Long actualRfidfactoryId = taperfidcreateparametertask.getRfidfactoryId();

        // Then
        assertThat(actualRfidfactoryId).isEqualTo(expectedRfidfactoryId);
    }

    @Test
    void testSetRfidfactoryId() {
        // Given
        Long expectedRfidfactoryId = 2L;

        // When
        taperfidcreateparametertask.setRfidfactoryId(expectedRfidfactoryId);

        // Then
        assertThat(taperfidcreateparametertask.getRfidfactoryId()).isEqualTo(expectedRfidfactoryId);
    }

    @Test
    void testGetType() {
        // Given
        String expectedType = "type1";
        taperfidcreateparametertask.setType(expectedType);

        // When
        String actualType = taperfidcreateparametertask.getType();

        // Then
        assertThat(actualType).isEqualTo(expectedType);
    }

    @Test
    void testSetType() {
        // Given
        String expectedType = "type1";

        // When
        taperfidcreateparametertask.setType(expectedType);

        // Then
        assertThat(taperfidcreateparametertask.getType()).isEqualTo(expectedType);
    }

    // Add more test methods for other getters and setters in
    // TapeRfidCreateParameterTask
}
