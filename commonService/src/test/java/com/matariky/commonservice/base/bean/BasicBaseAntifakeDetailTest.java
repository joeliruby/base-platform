package com.matariky.commonservice.base.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicBaseAntifakeDetailTest {

    @InjectMocks
    private BasicBaseAntifakeDetail basicbaseantifakedetail;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String deviceCode = "device123";
        String validateResult = "valid";

        // When
        basicbaseantifakedetail.setId(id);
        basicbaseantifakedetail.setDeviceCode(deviceCode);
        basicbaseantifakedetail.setValidateResult(validateResult);

        // Then
        assertEquals(id, basicbaseantifakedetail.getId());
        assertEquals(deviceCode, basicbaseantifakedetail.getDeviceCode());
        assertEquals(validateResult, basicbaseantifakedetail.getValidateResult());
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        BasicBaseAntifakeDetail detail1 = new BasicBaseAntifakeDetail();
        detail1.setId(1L);
        BasicBaseAntifakeDetail detail2 = new BasicBaseAntifakeDetail();
        detail2.setId(1L);

        // When & Then
        assertEquals(detail1, detail2);
        assertEquals(detail1.hashCode(), detail2.hashCode());
    }

    @Test
    void testToString() {
        // Given
        basicbaseantifakedetail.setId(1L);
        basicbaseantifakedetail.setDeviceCode("device123");

        // When
        String result = basicbaseantifakedetail.toString();

        // Then
        assertTrue(result.contains("id=1"));
        assertTrue(result.contains("deviceCode=device123"));
    }

    // Add more test methods for other methods in BasicBaseAntifakeDetail
}
