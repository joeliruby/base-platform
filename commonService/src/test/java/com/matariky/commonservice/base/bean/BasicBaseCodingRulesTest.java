package com.matariky.commonservice.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBaseCodingRulesTest {

    @InjectMocks
    private BasicBaseCodingRules basicBaseCodingRules;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        basicBaseCodingRules.setId(expectedId);

        // When
        Long actualId = basicBaseCodingRules.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetRulesName() {
        // Given
        String expectedRulesName = "Test Rule";
        basicBaseCodingRules.setRulesName(expectedRulesName);

        // When
        String actualRulesName = basicBaseCodingRules.getRulesName();

        // Then
        assertThat(actualRulesName).isEqualTo(expectedRulesName);
    }

    @Test
    void testGetCodingLength() {
        // Given
        Integer expectedCodingLength = 10;
        basicBaseCodingRules.setCodingLength(expectedCodingLength);

        // When
        Integer actualCodingLength = basicBaseCodingRules.getCodingLength();

        // Then
        assertThat(actualCodingLength).isEqualTo(expectedCodingLength);
    }

    @Test
    void testGetUniqueCode() {
        // Given
        String expectedUniqueCode = "E1";
        basicBaseCodingRules.setUniqueCode(expectedUniqueCode);

        // When
        String actualUniqueCode = basicBaseCodingRules.getUniqueCode();

        // Then
        assertThat(actualUniqueCode).isEqualTo(expectedUniqueCode);
    }

    @Test
    void testGetRemark() {
        // Given
        String expectedRemark = "This is a remark";
        basicBaseCodingRules.setRemark(expectedRemark);

        // When
        String actualRemark = basicBaseCodingRules.getRemark();

        // Then
        assertThat(actualRemark).isEqualTo(expectedRemark);
    }

    // Add more test methods for other getters and setters in BasicBaseCodingRules
}
