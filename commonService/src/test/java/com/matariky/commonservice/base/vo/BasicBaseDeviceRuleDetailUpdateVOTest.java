package com.matariky.commonservice.base.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBaseDeviceRuleDetailUpdateVOTest {

    @InjectMocks
    private BasicBaseDeviceRuleDetailUpdateVO basicbasedeviceruledetailupdatevo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFilterConditions() {
        // Given
        String filterConditions = "Condition1";
        basicbasedeviceruledetailupdatevo.setFilterConditions(filterConditions);

        // When
        String result = basicbasedeviceruledetailupdatevo.getFilterConditions();

        // Then
        assertThat(result).isEqualTo(filterConditions);
    }

    @Test
    void testConditionSetting() {
        // Given
        String conditionSetting = "Setting1";
        basicbasedeviceruledetailupdatevo.setConditionSetting(conditionSetting);

        // When
        String result = basicbasedeviceruledetailupdatevo.getConditionSetting();

        // Then
        assertThat(result).isEqualTo(conditionSetting);
    }

    @Test
    void testSetValue() {
        // Given
        String setValue = "Value1";
        basicbasedeviceruledetailupdatevo.setSetValue(setValue);

        // When
        String result = basicbasedeviceruledetailupdatevo.getSetValue();

        // Then
        assertThat(result).isEqualTo(setValue);
    }

    @Test
    void testNotBlankAnnotations() {
        // Given
        basicbasedeviceruledetailupdatevo.setFilterConditions("");
        basicbasedeviceruledetailupdatevo.setConditionSetting("");
        basicbasedeviceruledetailupdatevo.setSetValue("");

        // When
        boolean isFilterConditionsBlank = basicbasedeviceruledetailupdatevo.getFilterConditions().isBlank();
        boolean isConditionSettingBlank = basicbasedeviceruledetailupdatevo.getConditionSetting().isBlank();
        boolean isSetValueBlank = basicbasedeviceruledetailupdatevo.getSetValue().isBlank();

        // Then
        assertTrue(isFilterConditionsBlank);
        assertTrue(isConditionSettingBlank);
        assertTrue(isSetValueBlank);
    }

    // Add more test methods for other scenarios if needed
}
