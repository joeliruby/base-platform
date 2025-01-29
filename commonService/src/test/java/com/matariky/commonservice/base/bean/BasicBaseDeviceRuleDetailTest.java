package com.matariky.commonservice.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseDeviceRuleDetailTest {

    @InjectMocks
    private BasicBaseDeviceRuleDetail basicbasedeviceruledetail;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        basicbasedeviceruledetail.setId(1L);

        // When
        Long id = basicbasedeviceruledetail.getId();

        // Then
        assertThat(id).isEqualTo(1L);
    }

    @Test
    void testSetId() {
        // Given
        Long id = 2L;

        // When
        basicbasedeviceruledetail.setId(id);

        // Then
        assertThat(basicbasedeviceruledetail.getId()).isEqualTo(id);
    }

    @Test
    void testGetRuleId() {
        // Given
        basicbasedeviceruledetail.setRuleId(10L);

        // When
        Long ruleId = basicbasedeviceruledetail.getRuleId();

        // Then
        assertThat(ruleId).isEqualTo(10L);
    }

    @Test
    void testSetRuleId() {
        // Given
        Long ruleId = 20L;

        // When
        basicbasedeviceruledetail.setRuleId(ruleId);

        // Then
        assertThat(basicbasedeviceruledetail.getRuleId()).isEqualTo(ruleId);
    }

    @Test
    void testGetFilterConditions() {
        // Given
        basicbasedeviceruledetail.setFilterConditions("condition1");

        // When
        String filterConditions = basicbasedeviceruledetail.getFilterConditions();

        // Then
        assertThat(filterConditions).isEqualTo("condition1");
    }

    @Test
    void testSetFilterConditions() {
        // Given
        String filterConditions = "condition2";

        // When
        basicbasedeviceruledetail.setFilterConditions(filterConditions);

        // Then
        assertThat(basicbasedeviceruledetail.getFilterConditions()).isEqualTo(filterConditions);
    }

    @Test
    void testGetConditionSetting() {
        // Given
        basicbasedeviceruledetail.setConditionSetting("setting1");

        // When
        String conditionSetting = basicbasedeviceruledetail.getConditionSetting();

        // Then
        assertThat(conditionSetting).isEqualTo("setting1");
    }

    @Test
    void testSetConditionSetting() {
        // Given
        String conditionSetting = "setting2";

        // When
        basicbasedeviceruledetail.setConditionSetting(conditionSetting);

        // Then
        assertThat(basicbasedeviceruledetail.getConditionSetting()).isEqualTo(conditionSetting);
    }

    @Test
    void testGetSetValue() {
        // Given
        basicbasedeviceruledetail.setSetValue("value1");

        // When
        String setValue = basicbasedeviceruledetail.getSetValue();

        // Then
        assertThat(setValue).isEqualTo("value1");
    }

    @Test
    void testSetSetValue() {
        // Given
        String setValue = "value2";

        // When
        basicbasedeviceruledetail.setSetValue(setValue);

        // Then
        assertThat(basicbasedeviceruledetail.getSetValue()).isEqualTo(setValue);
    }

    @Test
    void testGetRemark() {
        // Given
        basicbasedeviceruledetail.setRemark("remark1");

        // When
        String remark = basicbasedeviceruledetail.getRemark();

        // Then
        assertThat(remark).isEqualTo("remark1");
    }

    @Test
    void testSetRemark() {
        // Given
        String remark = "remark2";

        // When
        basicbasedeviceruledetail.setRemark(remark);

        // Then
        assertThat(basicbasedeviceruledetail.getRemark()).isEqualTo(remark);
    }

    @Test
    void testGetCreateTime() {
        // Given
        basicbasedeviceruledetail.setCreateTime(1000L);

        // When
        Long createTime = basicbasedeviceruledetail.getCreateTime();

        // Then
        assertThat(createTime).isEqualTo(1000L);
    }

    @Test
    void testSetCreateTime() {
        // Given
        Long createTime = 2000L;

        // When
        basicbasedeviceruledetail.setCreateTime(createTime);

        // Then
        assertThat(basicbasedeviceruledetail.getCreateTime()).isEqualTo(createTime);
    }

    @Test
    void testGetUpdateTime() {
        // Given
        basicbasedeviceruledetail.setUpdateTime(3000L);

        // When
        Long updateTime = basicbasedeviceruledetail.getUpdateTime();

        // Then
        assertThat(updateTime).isEqualTo(3000L);
    }

    @Test
    void testSetUpdateTime() {
        // Given
        Long updateTime = 4000L;

        // When
        basicbasedeviceruledetail.setUpdateTime(updateTime);

        // Then
        assertThat(basicbasedeviceruledetail.getUpdateTime()).isEqualTo(updateTime);
    }

    @Test
    void testGetDeleteTime() {
        // Given
        basicbasedeviceruledetail.setDeleteTime(5000L);

        // When
        Long deleteTime = basicbasedeviceruledetail.getDeleteTime();

        // Then
        assertThat(deleteTime).isEqualTo(5000L);
    }

    @Test
    void testSetDeleteTime() {
        // Given
        Long deleteTime = 6000L;

        // When
        basicbasedeviceruledetail.setDeleteTime(deleteTime);

        // Then
        assertThat(basicbasedeviceruledetail.getDeleteTime()).isEqualTo(deleteTime);
    }

    @Test
    void testGetCreateBy() {
        // Given
        basicbasedeviceruledetail.setCreateBy(1L);

        // When
        Long createBy = basicbasedeviceruledetail.getCreateBy();

        // Then
        assertThat(createBy).isEqualTo(1L);
    }

    @Test
    void testSetCreateBy() {
        // Given
        Long createBy = 2L;

        // When
        basicbasedeviceruledetail.setCreateBy(createBy);

        // Then
        assertThat(basicbasedeviceruledetail.getCreateBy()).isEqualTo(createBy);
    }

    @Test
    void testGetUpdateBy() {
        // Given
        basicbasedeviceruledetail.setUpdateBy(3L);

        // When
        Long updateBy = basicbasedeviceruledetail.getUpdateBy();

        // Then
        assertThat(updateBy).isEqualTo(3L);
    }

    @Test
    void testSetUpdateBy() {
        // Given
        Long updateBy = 4L;

        // When
        basicbasedeviceruledetail.setUpdateBy(updateBy);

        // Then
        assertThat(basicbasedeviceruledetail.getUpdateBy()).isEqualTo(updateBy);
    }

    @Test
    void testGetOperatorOrgCode() {
        // Given
        basicbasedeviceruledetail.setOperatorOrgCode("orgCode1");

        // When
        String operatorOrgCode = basicbasedeviceruledetail.getOperatorOrgCode();

        // Then
        assertThat(operatorOrgCode).isEqualTo("orgCode1");
    }

    @Test
    void testSetOperatorOrgCode() {
        // Given
        String operatorOrgCode = "orgCode2";

        // When
        basicbasedeviceruledetail.setOperatorOrgCode(operatorOrgCode);

        // Then
        assertThat(basicbasedeviceruledetail.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
    }

    @Test
    void testGetOperatorSelfOrgCode() {
        // Given
        basicbasedeviceruledetail.setOperatorSelfOrgCode("selfOrgCode1");

        // When
        String operatorSelfOrgCode = basicbasedeviceruledetail.getOperatorSelfOrgCode();

        // Then
        assertThat(operatorSelfOrgCode).isEqualTo("selfOrgCode1");
    }

    @Test
    void testSetOperatorSelfOrgCode() {
        // Given
        String operatorSelfOrgCode = "selfOrgCode2";

        // When
        basicbasedeviceruledetail.setOperatorSelfOrgCode(operatorSelfOrgCode);

        // Then
        assertThat(basicbasedeviceruledetail.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
    }
}
