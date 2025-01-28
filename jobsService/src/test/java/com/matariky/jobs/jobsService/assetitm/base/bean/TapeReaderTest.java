package com.matariky.jobs.jobsService.assetitm.base.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TapeReaderTest {

    @InjectMocks
    private TapeReader tapereader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        tapereader.setId(expectedId);

        // When
        Long actualId = tapereader.getId();

        // Then
        assertEquals(expectedId, actualId);
    }

    @Test
    void testSetOperatorOrgCode() {
        // Given
        String expectedCode = "ORG123";
        tapereader.setOperatorOrgCode(expectedCode);

        // When
        String actualCode = tapereader.getOperatorOrgCode();

        // Then
        assertEquals(expectedCode, actualCode);
    }

    @Test
    void testGetStatus() {
        // Given
        Integer expectedStatus = 1;
        tapereader.setStatus(expectedStatus);

        // When
        Integer actualStatus = tapereader.getStatus();

        // Then
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    void testSetBrandModel() {
        // Given
        String expectedBrandModel = "BrandX";
        tapereader.setBrandModel(expectedBrandModel);

        // When
        String actualBrandModel = tapereader.getBrandModel();

        // Then
        assertEquals(expectedBrandModel, actualBrandModel);
    }

    @Test
    void testGetAntennasNum() {
        // Given
        Integer expectedAntennasNum = 4;
        tapereader.setAntennasNum(expectedAntennasNum);

        // When
        Integer actualAntennasNum = tapereader.getAntennasNum();

        // Then
        assertEquals(expectedAntennasNum, actualAntennasNum);
    }

    // Add more test methods for other getters and setters in TapeReader
}
