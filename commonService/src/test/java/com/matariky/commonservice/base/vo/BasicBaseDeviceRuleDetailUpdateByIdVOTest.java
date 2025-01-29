package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseDeviceRuleDetailUpdateByIdVOTest {

    @InjectMocks
    private BasicBaseDeviceRuleDetailUpdateByIdVO basicbasedeviceruledetailupdatebyidvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFilterConditions() {
        // Given
        String expectedFilterConditions = "filterCondition";
        basicbasedeviceruledetailupdatebyidvo.setFilterConditions(expectedFilterConditions);

        // When
        String actualFilterConditions = basicbasedeviceruledetailupdatebyidvo.getFilterConditions();

        // Then
        assertThat(actualFilterConditions).isEqualTo(expectedFilterConditions);
    }

    @Test
    void testGetConditionSetting() {
        // Given
        String expectedConditionSetting = "conditionSetting";
        basicbasedeviceruledetailupdatebyidvo.setConditionSetting(expectedConditionSetting);

        // When
        String actualConditionSetting = basicbasedeviceruledetailupdatebyidvo.getConditionSetting();

        // Then
        assertThat(actualConditionSetting).isEqualTo(expectedConditionSetting);
    }

    @Test
    void testGetSetValue() {
        // Given
        String expectedSetValue = "setValue";
        basicbasedeviceruledetailupdatebyidvo.setSetValue(expectedSetValue);

        // When
        String actualSetValue = basicbasedeviceruledetailupdatebyidvo.getSetValue();

        // Then
        assertThat(actualSetValue).isEqualTo(expectedSetValue);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        basicbasedeviceruledetailupdatebyidvo.setId(expectedId);

        // When
        Long actualId = basicbasedeviceruledetailupdatebyidvo.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    // Add more test methods for other methods in
    // BasicBaseDeviceRuleDetailUpdateByIdVO
}
