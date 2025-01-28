package com.matariky.bizservice.assetitm.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseRfidprintParameterTest {

    @InjectMocks
    private BasicBaseRfidprintParameter basicBaseRfidprintParameter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String type = "TypeA";
        String parameterName = "ParamName";
        String parameterContent = "ParamContent";
        String remark = "Remark";
        Long createTime = System.currentTimeMillis();
        Long updateTime = System.currentTimeMillis();
        Long deleteTime = System.currentTimeMillis();
        Long createBy = 2L;
        Long updateBy = 3L;
        String operatorOrgCode = "OrgCode";
        String operatorSelfOrgCode = "SelfOrgCode";

        // When
        basicBaseRfidprintParameter.setId(id);
        basicBaseRfidprintParameter.setType(type);
        basicBaseRfidprintParameter.setParameterName(parameterName);
        basicBaseRfidprintParameter.setParameterContent(parameterContent);
        basicBaseRfidprintParameter.setRemark(remark);
        basicBaseRfidprintParameter.setCreateTime(createTime);
        basicBaseRfidprintParameter.setUpdateTime(updateTime);
        basicBaseRfidprintParameter.setDeleteTime(deleteTime);
        basicBaseRfidprintParameter.setCreateBy(createBy);
        basicBaseRfidprintParameter.setUpdateBy(updateBy);
        basicBaseRfidprintParameter.setOperatorOrgCode(operatorOrgCode);
        basicBaseRfidprintParameter.setOperatorSelfOrgCode(operatorSelfOrgCode);

        // Then
        assertThat(basicBaseRfidprintParameter.getId()).isEqualTo(id);
        assertThat(basicBaseRfidprintParameter.getType()).isEqualTo(type);
        assertThat(basicBaseRfidprintParameter.getParameterName()).isEqualTo(parameterName);
        assertThat(basicBaseRfidprintParameter.getParameterContent()).isEqualTo(parameterContent);
        assertThat(basicBaseRfidprintParameter.getRemark()).isEqualTo(remark);
        assertThat(basicBaseRfidprintParameter.getCreateTime()).isEqualTo(createTime);
        assertThat(basicBaseRfidprintParameter.getUpdateTime()).isEqualTo(updateTime);
        assertThat(basicBaseRfidprintParameter.getDeleteTime()).isEqualTo(deleteTime);
        assertThat(basicBaseRfidprintParameter.getCreateBy()).isEqualTo(createBy);
        assertThat(basicBaseRfidprintParameter.getUpdateBy()).isEqualTo(updateBy);
        assertThat(basicBaseRfidprintParameter.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
        assertThat(basicBaseRfidprintParameter.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
    }

    @Test
    void testToString() {
        // Given
        basicBaseRfidprintParameter.setId(1L);
        basicBaseRfidprintParameter.setType("TypeA");

        // When
        String result = basicBaseRfidprintParameter.toString();

        // Then
        assertThat(result).contains("BasicBaseRfidprintParameter");
        assertThat(result).contains("id=1");
        assertThat(result).contains("type=TypeA");
    }

    // Add more test methods for other methods in BasicBaseRfidprintParameter
}
