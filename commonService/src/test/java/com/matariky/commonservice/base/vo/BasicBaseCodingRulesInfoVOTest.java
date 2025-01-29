package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseCodingRulesInfoVOTest {

    @InjectMocks
    private BasicBaseCodingRulesInfoVO basicbasecodingrulesinfovo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String rulesName = "Rule Name";
        Integer codingLength = 10;
        String uniqueCode = "E1E2";
        String remark = "Remark";
        Long createTime = System.currentTimeMillis();
        Long updateTime = System.currentTimeMillis();
        Long deleteTime = System.currentTimeMillis();
        Long createBy = 1L;
        Long updateBy = 2L;
        String operatorOrgCode = "OrgCode";
        String operatorSelfOrgCode = "SelfOrgCode";
        String tenantId = "TenantId";
        String realName = "Real Name";
        Integer status = 1;
        Integer rulesType = 2;

        // When
        basicbasecodingrulesinfovo.setId(id);
        basicbasecodingrulesinfovo.setRulesName(rulesName);
        basicbasecodingrulesinfovo.setCodingLength(codingLength);
        basicbasecodingrulesinfovo.setUniqueCode(uniqueCode);
        basicbasecodingrulesinfovo.setRemark(remark);
        basicbasecodingrulesinfovo.setCreateTime(createTime);
        basicbasecodingrulesinfovo.setUpdateTime(updateTime);
        basicbasecodingrulesinfovo.setDeleteTime(deleteTime);
        basicbasecodingrulesinfovo.setCreateBy(createBy);
        basicbasecodingrulesinfovo.setUpdateBy(updateBy);
        basicbasecodingrulesinfovo.setOperatorOrgCode(operatorOrgCode);
        basicbasecodingrulesinfovo.setOperatorSelfOrgCode(operatorSelfOrgCode);
        basicbasecodingrulesinfovo.setTenantId(tenantId);
        basicbasecodingrulesinfovo.setRealName(realName);
        basicbasecodingrulesinfovo.setStatus(status);
        basicbasecodingrulesinfovo.setRulesType(rulesType);

        // Then
        assertThat(basicbasecodingrulesinfovo.getId()).isEqualTo(id);
        assertThat(basicbasecodingrulesinfovo.getRulesName()).isEqualTo(rulesName);
        assertThat(basicbasecodingrulesinfovo.getCodingLength()).isEqualTo(codingLength);
        assertThat(basicbasecodingrulesinfovo.getUniqueCode()).isEqualTo(uniqueCode);
        assertThat(basicbasecodingrulesinfovo.getRemark()).isEqualTo(remark);
        assertThat(basicbasecodingrulesinfovo.getCreateTime()).isEqualTo(createTime);
        assertThat(basicbasecodingrulesinfovo.getUpdateTime()).isEqualTo(updateTime);
        assertThat(basicbasecodingrulesinfovo.getDeleteTime()).isEqualTo(deleteTime);
        assertThat(basicbasecodingrulesinfovo.getCreateBy()).isEqualTo(createBy);
        assertThat(basicbasecodingrulesinfovo.getUpdateBy()).isEqualTo(updateBy);
        assertThat(basicbasecodingrulesinfovo.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
        assertThat(basicbasecodingrulesinfovo.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
        assertThat(basicbasecodingrulesinfovo.getTenantId()).isEqualTo(tenantId);
        assertThat(basicbasecodingrulesinfovo.getRealName()).isEqualTo(realName);
        assertThat(basicbasecodingrulesinfovo.getStatus()).isEqualTo(status);
        assertThat(basicbasecodingrulesinfovo.getRulesType()).isEqualTo(rulesType);
    }

    @Test
    void testToString() {
        // Given
        basicbasecodingrulesinfovo.setId(1L);
        basicbasecodingrulesinfovo.setRulesName("Rule Name");

        // When
        String result = basicbasecodingrulesinfovo.toString();

        // Then
        assertThat(result).contains("BasicBaseCodingRulesInfoVO");
        assertThat(result).contains("id=1");
        assertThat(result).contains("rulesName=Rule Name");
    }

    // Add more test methods for other methods in BasicBaseCodingRulesInfoVO
}
