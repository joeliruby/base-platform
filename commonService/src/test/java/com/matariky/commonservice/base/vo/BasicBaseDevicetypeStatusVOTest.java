package com.matariky.commonservice.base.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicBaseDevicetypeStatusVOTest {

    @InjectMocks
    private BasicBaseDevicetypeStatusVO basicbasedevicetypestatusvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        basicbasedevicetypestatusvo.setId(expectedId);

        // When
        Long actualId = basicbasedevicetypestatusvo.getId();

        // Then
        assertEquals(expectedId, actualId);
    }

    @Test
    void testGetStatus() {
        // Given
        Integer expectedStatus = 1;
        basicbasedevicetypestatusvo.setStatus(expectedStatus);

        // When
        Integer actualStatus = basicbasedevicetypestatusvo.getStatus();

        // Then
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    void testSetId() {
        // Given
        Long expectedId = 2L;

        // When
        basicbasedevicetypestatusvo.setId(expectedId);

        // Then
        assertEquals(expectedId, basicbasedevicetypestatusvo.getId());
    }

    @Test
    void testSetStatus() {
        // Given
        Integer expectedStatus = 2;

        // When
        basicbasedevicetypestatusvo.setStatus(expectedStatus);

        // Then
        assertEquals(expectedStatus, basicbasedevicetypestatusvo.getStatus());
    }

    // Add more test methods for other methods in BasicBaseDevicetypeStatusVO if
    // needed
}
