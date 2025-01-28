package com.matariky.bizservice.assetitm.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseRfidfactoryParameterTest {

    @InjectMocks
    private BasicBaseRfidfactoryParameter basicBaseRfidfactoryParameter;

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
        basicBaseRfidfactoryParameter.setId(id);
        basicBaseRfidfactoryParameter.setType(type);
        basicBaseRfidfactoryParameter.setParameterName(parameterName);
        basicBaseRfidfactoryParameter.setParameterContent(parameterContent);
        basicBaseRfidfactoryParameter.setRemark(remark);
        basicBaseRfidfactoryParameter.setCreateTime(createTime);
        basicBaseRfidfactoryParameter.setUpdateTime(updateTime);
        basicBaseRfidfactoryParameter.setDeleteTime(deleteTime);
        basicBaseRfidfactoryParameter.setCreateBy(createBy);
        basicBaseRfidfactoryParameter.setUpdateBy(updateBy);
        basicBaseRfidfactoryParameter.setOperatorOrgCode(operatorOrgCode);
        basicBaseRfidfactoryParameter.setOperatorSelfOrgCode(operatorSelfOrgCode);

        // Then
        assertThat(basicBaseRfidfactoryParameter.getId()).isEqualTo(id);
        assertThat(basicBaseRfidfactoryParameter.getType()).isEqualTo(type);
        assertThat(basicBaseRfidfactoryParameter.getParameterName()).isEqualTo(parameterName);
        assertThat(basicBaseRfidfactoryParameter.getParameterContent()).isEqualTo(parameterContent);
        assertThat(basicBaseRfidfactoryParameter.getRemark()).isEqualTo(remark);
        assertThat(basicBaseRfidfactoryParameter.getCreateTime()).isEqualTo(createTime);
        assertThat(basicBaseRfidfactoryParameter.getUpdateTime()).isEqualTo(updateTime);
        assertThat(basicBaseRfidfactoryParameter.getDeleteTime()).isEqualTo(deleteTime);
        assertThat(basicBaseRfidfactoryParameter.getCreateBy()).isEqualTo(createBy);
        assertThat(basicBaseRfidfactoryParameter.getUpdateBy()).isEqualTo(updateBy);
        assertThat(basicBaseRfidfactoryParameter.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
        assertThat(basicBaseRfidfactoryParameter.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
    }

    @Test
    void testToString() {
        // Given
        basicBaseRfidfactoryParameter.setId(1L);
        basicBaseRfidfactoryParameter.setType("TypeA");
        basicBaseRfidfactoryParameter.setParameterName("ParamName");
        basicBaseRfidfactoryParameter.setParameterContent("ParamContent");
        basicBaseRfidfactoryParameter.setRemark("Remark");

        // When
        String result = basicBaseRfidfactoryParameter.toString();

        // Then
        assertThat(result).contains("1", "TypeA", "ParamName", "ParamContent", "Remark");
    }

    // Add more test methods for other methods in BasicBaseRfidfactoryParameter
}
