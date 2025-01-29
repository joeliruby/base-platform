package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseDeviceRuleDetailAddVOTest {

    @InjectMocks
    private BasicBaseDeviceRuleDetailAddVO basicbasedeviceruledetailaddvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFilterConditions() {
        // Given
        String filterConditions = "Condition1";
        basicbasedeviceruledetailaddvo.setFilterConditions(filterConditions);

        // When
        String result = basicbasedeviceruledetailaddvo.getFilterConditions();

        // Then
        assertThat(result).isEqualTo(filterConditions);
    }

    @Test
    void testConditionSetting() {
        // Given
        String conditionSetting = "Setting1";
        basicbasedeviceruledetailaddvo.setConditionSetting(conditionSetting);

        // When
        String result = basicbasedeviceruledetailaddvo.getConditionSetting();

        // Then
        assertThat(result).isEqualTo(conditionSetting);
    }

    @Test
    void testSetValue() {
        // Given
        String setValue = "Value1";
        basicbasedeviceruledetailaddvo.setSetValue(setValue);

        // When
        String result = basicbasedeviceruledetailaddvo.getSetValue();

        // Then
        assertThat(result).isEqualTo(setValue);
    }

    @Test
    void testRuleId() {
        // Given
        Long ruleId = 123L;
        basicbasedeviceruledetailaddvo.setRuleId(ruleId);

        // When
        Long result = basicbasedeviceruledetailaddvo.getRuleId();

        // Then
        assertThat(result).isEqualTo(ruleId);
    }

    // Add more test methods for other methods in BasicBaseDeviceRuleDetailAddVO
}
