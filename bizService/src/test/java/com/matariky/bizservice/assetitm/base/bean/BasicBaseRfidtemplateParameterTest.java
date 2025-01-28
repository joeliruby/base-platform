package com.matariky.bizservice.assetitm.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseRfidtemplateParameterTest {

    @InjectMocks
    private BasicBaseRfidtemplateParameter basicBaseRfidtemplateParameter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        Long templateId = 2L;
        String type = "type";
        String parameterName = "parameterName";
        String parameterContent = "parameterContent";
        String remark = "remark";
        Long createTime = 123456789L;
        Long updateTime = 987654321L;
        Long deleteTime = 111111111L;
        Long createBy = 222222222L;
        Long updateBy = 333333333L;
        String operatorOrgCode = "orgCode";
        String operatorSelfOrgCode = "selfOrgCode";

        // When
        basicBaseRfidtemplateParameter.setId(id);
        basicBaseRfidtemplateParameter.setTemplateId(templateId);
        basicBaseRfidtemplateParameter.setType(type);
        basicBaseRfidtemplateParameter.setParameterName(parameterName);
        basicBaseRfidtemplateParameter.setParameterContent(parameterContent);
        basicBaseRfidtemplateParameter.setRemark(remark);
        basicBaseRfidtemplateParameter.setCreateTime(createTime);
        basicBaseRfidtemplateParameter.setUpdateTime(updateTime);
        basicBaseRfidtemplateParameter.setDeleteTime(deleteTime);
        basicBaseRfidtemplateParameter.setCreateBy(createBy);
        basicBaseRfidtemplateParameter.setUpdateBy(updateBy);
        basicBaseRfidtemplateParameter.setOperatorOrgCode(operatorOrgCode);
        basicBaseRfidtemplateParameter.setOperatorSelfOrgCode(operatorSelfOrgCode);

        // Then
        assertThat(basicBaseRfidtemplateParameter.getId()).isEqualTo(id);
        assertThat(basicBaseRfidtemplateParameter.getTemplateId()).isEqualTo(templateId);
        assertThat(basicBaseRfidtemplateParameter.getType()).isEqualTo(type);
        assertThat(basicBaseRfidtemplateParameter.getParameterName()).isEqualTo(parameterName);
        assertThat(basicBaseRfidtemplateParameter.getParameterContent()).isEqualTo(parameterContent);
        assertThat(basicBaseRfidtemplateParameter.getRemark()).isEqualTo(remark);
        assertThat(basicBaseRfidtemplateParameter.getCreateTime()).isEqualTo(createTime);
        assertThat(basicBaseRfidtemplateParameter.getUpdateTime()).isEqualTo(updateTime);
        assertThat(basicBaseRfidtemplateParameter.getDeleteTime()).isEqualTo(deleteTime);
        assertThat(basicBaseRfidtemplateParameter.getCreateBy()).isEqualTo(createBy);
        assertThat(basicBaseRfidtemplateParameter.getUpdateBy()).isEqualTo(updateBy);
        assertThat(basicBaseRfidtemplateParameter.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
        assertThat(basicBaseRfidtemplateParameter.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        BasicBaseRfidtemplateParameter param1 = new BasicBaseRfidtemplateParameter();
        param1.setId(1L);
        BasicBaseRfidtemplateParameter param2 = new BasicBaseRfidtemplateParameter();
        param2.setId(1L);
        BasicBaseRfidtemplateParameter param3 = new BasicBaseRfidtemplateParameter();
        param3.setId(2L);

        // Then
        assertThat(param1).isEqualTo(param2);
        assertThat(param1).isNotEqualTo(param3);
        assertThat(param1.hashCode()).isEqualTo(param2.hashCode());
        assertThat(param1.hashCode()).isNotEqualTo(param3.hashCode());
    }

    @Test
    void testToString() {
        // Given
        basicBaseRfidtemplateParameter.setId(1L);
        basicBaseRfidtemplateParameter.setTemplateId(2L);
        basicBaseRfidtemplateParameter.setType("type");
        basicBaseRfidtemplateParameter.setParameterName("parameterName");
        basicBaseRfidtemplateParameter.setParameterContent("parameterContent");
        basicBaseRfidtemplateParameter.setRemark("remark");
        basicBaseRfidtemplateParameter.setCreateTime(123456789L);
        basicBaseRfidtemplateParameter.setUpdateTime(987654321L);
        basicBaseRfidtemplateParameter.setDeleteTime(111111111L);
        basicBaseRfidtemplateParameter.setCreateBy(222222222L);
        basicBaseRfidtemplateParameter.setUpdateBy(333333333L);
        basicBaseRfidtemplateParameter.setOperatorOrgCode("orgCode");
        basicBaseRfidtemplateParameter.setOperatorSelfOrgCode("selfOrgCode");

        // When
        String toString = basicBaseRfidtemplateParameter.toString();

        // Then
        assertThat(toString).contains("id=1");
        assertThat(toString).contains("templateId=2");
        assertThat(toString).contains("type=type");
        assertThat(toString).contains("parameterName=parameterName");
        assertThat(toString).contains("parameterContent=parameterContent");
        assertThat(toString).contains("remark=remark");
        assertThat(toString).contains("createTime=123456789");
        assertThat(toString).contains("updateTime=987654321");
        assertThat(toString).contains("deleteTime=111111111");
        assertThat(toString).contains("createBy=222222222");
        assertThat(toString).contains("updateBy=333333333");
        assertThat(toString).contains("operatorOrgCode=orgCode");
        assertThat(toString).contains("operatorSelfOrgCode=selfOrgCode");
    }
}
