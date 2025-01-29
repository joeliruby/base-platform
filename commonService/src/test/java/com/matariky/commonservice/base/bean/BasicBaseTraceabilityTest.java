package com.matariky.commonservice.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseTraceabilityTest {

    @InjectMocks
    private BasicBaseTraceability basicBaseTraceability;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String traceabilityCode = "code123";
        String traceabilityName = "name123";
        Integer status = 1;
        String remark = "remark";
        Long createTime = System.currentTimeMillis();
        Long updateTime = System.currentTimeMillis();
        Long deleteTime = System.currentTimeMillis();
        Long createBy = 1L;
        Long updateBy = 2L;
        String operatorOrgCode = "orgCode";
        String operatorSelfOrgCode = "selfOrgCode";
        String tenantId = "tenantId";

        // When
        basicBaseTraceability.setId(id);
        basicBaseTraceability.setTraceabilityCode(traceabilityCode);
        basicBaseTraceability.setTraceabilityName(traceabilityName);
        basicBaseTraceability.setStatus(status);
        basicBaseTraceability.setRemark(remark);
        basicBaseTraceability.setCreateTime(createTime);
        basicBaseTraceability.setUpdateTime(updateTime);
        basicBaseTraceability.setDeleteTime(deleteTime);
        basicBaseTraceability.setCreateBy(createBy);
        basicBaseTraceability.setUpdateBy(updateBy);
        basicBaseTraceability.setOperatorOrgCode(operatorOrgCode);
        basicBaseTraceability.setOperatorSelfOrgCode(operatorSelfOrgCode);
        basicBaseTraceability.setTenantId(tenantId);

        // Then
        assertThat(basicBaseTraceability.getId()).isEqualTo(id);
        assertThat(basicBaseTraceability.getTraceabilityCode()).isEqualTo(traceabilityCode);
        assertThat(basicBaseTraceability.getTraceabilityName()).isEqualTo(traceabilityName);
        assertThat(basicBaseTraceability.getStatus()).isEqualTo(status);
        assertThat(basicBaseTraceability.getRemark()).isEqualTo(remark);
        assertThat(basicBaseTraceability.getCreateTime()).isEqualTo(createTime);
        assertThat(basicBaseTraceability.getUpdateTime()).isEqualTo(updateTime);
        assertThat(basicBaseTraceability.getDeleteTime()).isEqualTo(deleteTime);
        assertThat(basicBaseTraceability.getCreateBy()).isEqualTo(createBy);
        assertThat(basicBaseTraceability.getUpdateBy()).isEqualTo(updateBy);
        assertThat(basicBaseTraceability.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
        assertThat(basicBaseTraceability.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
        assertThat(basicBaseTraceability.getTenantId()).isEqualTo(tenantId);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        BasicBaseTraceability anotherInstance = new BasicBaseTraceability();
        anotherInstance.setId(1L);
        anotherInstance.setTraceabilityCode("code123");
        anotherInstance.setTraceabilityName("name123");
        anotherInstance.setStatus(1);
        anotherInstance.setRemark("remark");
        anotherInstance.setCreateTime(System.currentTimeMillis());
        anotherInstance.setUpdateTime(System.currentTimeMillis());
        anotherInstance.setDeleteTime(System.currentTimeMillis());
        anotherInstance.setCreateBy(1L);
        anotherInstance.setUpdateBy(2L);
        anotherInstance.setOperatorOrgCode("orgCode");
        anotherInstance.setOperatorSelfOrgCode("selfOrgCode");
        anotherInstance.setTenantId("tenantId");

        // When
        basicBaseTraceability.setId(1L);
        basicBaseTraceability.setTraceabilityCode("code123");
        basicBaseTraceability.setTraceabilityName("name123");
        basicBaseTraceability.setStatus(1);
        basicBaseTraceability.setRemark("remark");
        basicBaseTraceability.setCreateTime(System.currentTimeMillis());
        basicBaseTraceability.setUpdateTime(System.currentTimeMillis());
        basicBaseTraceability.setDeleteTime(System.currentTimeMillis());
        basicBaseTraceability.setCreateBy(1L);
        basicBaseTraceability.setUpdateBy(2L);
        basicBaseTraceability.setOperatorOrgCode("orgCode");
        basicBaseTraceability.setOperatorSelfOrgCode("selfOrgCode");
        basicBaseTraceability.setTenantId("tenantId");

        // Then
        assertThat(basicBaseTraceability).isEqualTo(anotherInstance);
        assertThat(basicBaseTraceability.hashCode()).isEqualTo(anotherInstance.hashCode());
    }
}
