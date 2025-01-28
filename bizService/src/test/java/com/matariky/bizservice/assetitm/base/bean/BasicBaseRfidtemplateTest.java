package com.matariky.bizservice.assetitm.base.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;

@SpringBootTest
public class BasicBaseRfidtemplateTest {

    @InjectMocks
    private BasicBaseRfidtemplate basicbaserfidtemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetId() {
        // Given
        Long expectedId = 1L;

        // When
        basicbaserfidtemplate.setId(expectedId);

        // Then
        assertEquals(expectedId, basicbaserfidtemplate.getId());
    }

    @Test
    void testSetAndGetProjectName() {
        // Given
        String expectedProjectName = "Project A";

        // When
        basicbaserfidtemplate.setProjectName(expectedProjectName);

        // Then
        assertEquals(expectedProjectName, basicbaserfidtemplate.getProjectName());
    }

    @Test
    void testSetAndGetTemplateName() {
        // Given
        String expectedTemplateName = "Template A";

        // When
        basicbaserfidtemplate.setTemplateName(expectedTemplateName);

        // Then
        assertEquals(expectedTemplateName, basicbaserfidtemplate.getTemplateName());
    }

    @Test
    void testSetAndGetRfidLength() {
        // Given
        BigDecimal expectedRfidLength = new BigDecimal("10.5");

        // When
        basicbaserfidtemplate.setRfidLength(expectedRfidLength);

        // Then
        assertEquals(expectedRfidLength, basicbaserfidtemplate.getRfidLength());
    }

    @Test
    void testSetAndGetRfidWidth() {
        // Given
        BigDecimal expectedRfidWidth = new BigDecimal("5.5");

        // When
        basicbaserfidtemplate.setRfidWidth(expectedRfidWidth);

        // Then
        assertEquals(expectedRfidWidth, basicbaserfidtemplate.getRfidWidth());
    }

    // Add more test methods for other fields in BasicBaseRfidtemplate
}
