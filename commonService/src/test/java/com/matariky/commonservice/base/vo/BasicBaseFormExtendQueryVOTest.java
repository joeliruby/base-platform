package com.matariky.commonservice.base.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicBaseFormExtendQueryVOTest {

    @InjectMocks
    private BasicBaseFormExtendQueryVO basicbaseformextendqueryvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetId() {
        // Given
        Long expectedId = 1L;

        // When
        basicbaseformextendqueryvo.setId(expectedId);

        // Then
        assertEquals(expectedId, basicbaseformextendqueryvo.getId());
    }

    @Test
    void testSetAndGetBusinessId() {
        // Given
        Long expectedBusinessId = 2L;

        // When
        basicbaseformextendqueryvo.setBusinessId(expectedBusinessId);

        // Then
        assertEquals(expectedBusinessId, basicbaseformextendqueryvo.getBusinessId());
    }

    @Test
    void testSetAndGetField0() {
        // Given
        String expectedField0 = "testField0";

        // When
        basicbaseformextendqueryvo.setField0(expectedField0);

        // Then
        assertEquals(expectedField0, basicbaseformextendqueryvo.getField0());
    }

    @Test
    void testSetAndGetBusinessIds() {
        // Given
        List<Long> expectedBusinessIds = Arrays.asList(1L, 2L, 3L);

        // When
        basicbaseformextendqueryvo.setBusinessIds(expectedBusinessIds);

        // Then
        assertEquals(expectedBusinessIds, basicbaseformextendqueryvo.getBusinessIds());
    }

    // Add more test methods for other fields in BasicBaseFormExtendQueryVO
}
