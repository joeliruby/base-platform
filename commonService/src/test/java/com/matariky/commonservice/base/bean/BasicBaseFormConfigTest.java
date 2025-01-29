package com.matariky.commonservice.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseFormConfigTest {

    @InjectMocks
    private BasicBaseFormConfig basicbaseformconfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String name = "Test Name";
        String fieldName = "Test Field Name";
        String fieldMap = "Test Field Map";
        String fieldType = "Test Field Type";
        String remark = "Test Remark";
        Long createTime = System.currentTimeMillis();
        Long updateTime = System.currentTimeMillis();
        Long deleteTime = System.currentTimeMillis();
        Long createBy = 1L;
        Long updateBy = 1L;
        String operatorOrgCode = "Test Org Code";
        String operatorSelfOrgCode = "Test Self Org Code";
        String tenantId = "Test Tenant Id";
        Boolean isRequired = true;

        // When
        basicbaseformconfig.setId(id);
        basicbaseformconfig.setName(name);
        basicbaseformconfig.setFieldName(fieldName);
        basicbaseformconfig.setFieldMap(fieldMap);
        basicbaseformconfig.setFieldType(fieldType);
        basicbaseformconfig.setRemark(remark);
        basicbaseformconfig.setCreateTime(createTime);
        basicbaseformconfig.setUpdateTime(updateTime);
        basicbaseformconfig.setDeleteTime(deleteTime);
        basicbaseformconfig.setCreateBy(createBy);
        basicbaseformconfig.setUpdateBy(updateBy);
        basicbaseformconfig.setOperatorOrgCode(operatorOrgCode);
        basicbaseformconfig.setOperatorSelfOrgCode(operatorSelfOrgCode);
        basicbaseformconfig.setTenantId(tenantId);
        basicbaseformconfig.setIsRequired(isRequired);

        // Then
        assertThat(basicbaseformconfig.getId()).isEqualTo(id);
        assertThat(basicbaseformconfig.getName()).isEqualTo(name);
        assertThat(basicbaseformconfig.getFieldName()).isEqualTo(fieldName);
        assertThat(basicbaseformconfig.getFieldMap()).isEqualTo(fieldMap);
        assertThat(basicbaseformconfig.getFieldType()).isEqualTo(fieldType);
        assertThat(basicbaseformconfig.getRemark()).isEqualTo(remark);
        assertThat(basicbaseformconfig.getCreateTime()).isEqualTo(createTime);
        assertThat(basicbaseformconfig.getUpdateTime()).isEqualTo(updateTime);
        assertThat(basicbaseformconfig.getDeleteTime()).isEqualTo(deleteTime);
        assertThat(basicbaseformconfig.getCreateBy()).isEqualTo(createBy);
        assertThat(basicbaseformconfig.getUpdateBy()).isEqualTo(updateBy);
        assertThat(basicbaseformconfig.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
        assertThat(basicbaseformconfig.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
        assertThat(basicbaseformconfig.getTenantId()).isEqualTo(tenantId);
        assertThat(basicbaseformconfig.getIsRequired()).isEqualTo(isRequired);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        BasicBaseFormConfig anotherConfig = new BasicBaseFormConfig();
        anotherConfig.setId(1L);
        anotherConfig.setName("Test Name");

        basicbaseformconfig.setId(1L);
        basicbaseformconfig.setName("Test Name");

        // When & Then
        assertThat(basicbaseformconfig).isEqualTo(anotherConfig);
        assertThat(basicbaseformconfig.hashCode()).isEqualTo(anotherConfig.hashCode());
    }

    @Test
    void testToString() {
        // Given
        basicbaseformconfig.setId(1L);
        basicbaseformconfig.setName("Test Name");

        // When
        String toString = basicbaseformconfig.toString();

        // Then
        assertThat(toString).contains("id=1");
        assertThat(toString).contains("name=Test Name");
    }
}
