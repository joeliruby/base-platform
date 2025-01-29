package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseDeviceRuleDetailVOTest {

    @InjectMocks
    private BasicBaseDeviceRuleDetailVO basicbasedeviceruledetailvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        basicbasedeviceruledetailvo.setId(expectedId);

        // When
        Long actualId = basicbasedeviceruledetailvo.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetRuleId() {
        // Given
        Long expectedRuleId = 2L;
        basicbasedeviceruledetailvo.setRuleId(expectedRuleId);

        // When
        Long actualRuleId = basicbasedeviceruledetailvo.getRuleId();

        // Then
        assertThat(actualRuleId).isEqualTo(expectedRuleId);
    }

    @Test
    void testGetFilterConditions() {
        // Given
        String expectedFilterConditions = "condition1";
        basicbasedeviceruledetailvo.setFilterConditions(expectedFilterConditions);

        // When
        String actualFilterConditions = basicbasedeviceruledetailvo.getFilterConditions();

        // Then
        assertThat(actualFilterConditions).isEqualTo(expectedFilterConditions);
    }

    @Test
    void testGetConditionSetting() {
        // Given
        String expectedConditionSetting = "setting1";
        basicbasedeviceruledetailvo.setConditionSetting(expectedConditionSetting);

        // When
        String actualConditionSetting = basicbasedeviceruledetailvo.getConditionSetting();

        // Then
        assertThat(actualConditionSetting).isEqualTo(expectedConditionSetting);
    }

    @Test
    void testGetSetValue() {
        // Given
        String expectedSetValue = "value1";
        basicbasedeviceruledetailvo.setSetValue(expectedSetValue);

        // When
        String actualSetValue = basicbasedeviceruledetailvo.getSetValue();

        // Then
        assertThat(actualSetValue).isEqualTo(expectedSetValue);
    }

    @Test
    void testGetSetValueName() {
        // Given
        String expectedSetValueName = "valueName1";
        basicbasedeviceruledetailvo.setSetValueName(expectedSetValueName);

        // When
        String actualSetValueName = basicbasedeviceruledetailvo.getSetValueName();

        // Then
        assertThat(actualSetValueName).isEqualTo(expectedSetValueName);
    }

    @Test
    void testGetRemark() {
        // Given
        String expectedRemark = "remark1";
        basicbasedeviceruledetailvo.setRemark(expectedRemark);

        // When
        String actualRemark = basicbasedeviceruledetailvo.getRemark();

        // Then
        assertThat(actualRemark).isEqualTo(expectedRemark);
    }

    @Test
    void testGetCreateTime() {
        // Given
        Long expectedCreateTime = 123456789L;
        basicbasedeviceruledetailvo.setCreateTime(expectedCreateTime);

        // When
        Long actualCreateTime = basicbasedeviceruledetailvo.getCreateTime();

        // Then
        assertThat(actualCreateTime).isEqualTo(expectedCreateTime);
    }

    @Test
    void testGetUpdateTime() {
        // Given
        Long expectedUpdateTime = 987654321L;
        basicbasedeviceruledetailvo.setUpdateTime(expectedUpdateTime);

        // When
        Long actualUpdateTime = basicbasedeviceruledetailvo.getUpdateTime();

        // Then
        assertThat(actualUpdateTime).isEqualTo(expectedUpdateTime);
    }

    @Test
    void testGetDeleteTime() {
        // Given
        Long expectedDeleteTime = 1122334455L;
        basicbasedeviceruledetailvo.setDeleteTime(expectedDeleteTime);

        // When
        Long actualDeleteTime = basicbasedeviceruledetailvo.getDeleteTime();

        // Then
        assertThat(actualDeleteTime).isEqualTo(expectedDeleteTime);
    }

    @Test
    void testGetCreateBy() {
        // Given
        Long expectedCreateBy = 1L;
        basicbasedeviceruledetailvo.setCreateBy(expectedCreateBy);

        // When
        Long actualCreateBy = basicbasedeviceruledetailvo.getCreateBy();

        // Then
        assertThat(actualCreateBy).isEqualTo(expectedCreateBy);
    }

    @Test
    void testGetUpdateBy() {
        // Given
        Long expectedUpdateBy = 2L;
        basicbasedeviceruledetailvo.setUpdateBy(expectedUpdateBy);

        // When
        Long actualUpdateBy = basicbasedeviceruledetailvo.getUpdateBy();

        // Then
        assertThat(actualUpdateBy).isEqualTo(expectedUpdateBy);
    }

    @Test
    void testGetOperatorOrgCode() {
        // Given
        String expectedOperatorOrgCode = "orgCode1";
        basicbasedeviceruledetailvo.setOperatorOrgCode(expectedOperatorOrgCode);

        // When
        String actualOperatorOrgCode = basicbasedeviceruledetailvo.getOperatorOrgCode();

        // Then
        assertThat(actualOperatorOrgCode).isEqualTo(expectedOperatorOrgCode);
    }

    @Test
    void testGetOperatorSelfOrgCode() {
        // Given
        String expectedOperatorSelfOrgCode = "selfOrgCode1";
        basicbasedeviceruledetailvo.setOperatorSelfOrgCode(expectedOperatorSelfOrgCode);

        // When
        String actualOperatorSelfOrgCode = basicbasedeviceruledetailvo.getOperatorSelfOrgCode();

        // Then
        assertThat(actualOperatorSelfOrgCode).isEqualTo(expectedOperatorSelfOrgCode);
    }

    @Test
    void testGetRealName() {
        // Given
        String expectedRealName = "realName1";
        basicbasedeviceruledetailvo.setRealName(expectedRealName);

        // When
        String actualRealName = basicbasedeviceruledetailvo.getRealName();

        // Then
        assertThat(actualRealName).isEqualTo(expectedRealName);
    }
}
