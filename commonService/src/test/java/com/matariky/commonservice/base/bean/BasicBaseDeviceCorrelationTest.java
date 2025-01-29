package com.matariky.commonservice.base.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicBaseDeviceCorrelationTest {

    @InjectMocks
    private BasicBaseDeviceCorrelation basicbasedevicecorrelation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        Long mainDeviceId = 2L;
        Long subDeviceId = 3L;
        String remark = "Test Remark";
        Long createTime = System.currentTimeMillis();
        Long updateTime = System.currentTimeMillis();
        Long deleteTime = System.currentTimeMillis();
        Long createBy = 4L;
        Long updateBy = 5L;
        String operatorOrgCode = "OrgCode";
        String operatorSelfOrgCode = "SelfOrgCode";
        String tenantId = "TenantId";

        // When
        basicbasedevicecorrelation.setId(id);
        basicbasedevicecorrelation.setMainDeviceId(mainDeviceId);
        basicbasedevicecorrelation.setSubDeviceId(subDeviceId);
        basicbasedevicecorrelation.setRemark(remark);
        basicbasedevicecorrelation.setCreateTime(createTime);
        basicbasedevicecorrelation.setUpdateTime(updateTime);
        basicbasedevicecorrelation.setDeleteTime(deleteTime);
        basicbasedevicecorrelation.setCreateBy(createBy);
        basicbasedevicecorrelation.setUpdateBy(updateBy);
        basicbasedevicecorrelation.setOperatorOrgCode(operatorOrgCode);
        basicbasedevicecorrelation.setOperatorSelfOrgCode(operatorSelfOrgCode);
        basicbasedevicecorrelation.setTenantId(tenantId);

        // Then
        assertEquals(id, basicbasedevicecorrelation.getId());
        assertEquals(mainDeviceId, basicbasedevicecorrelation.getMainDeviceId());
        assertEquals(subDeviceId, basicbasedevicecorrelation.getSubDeviceId());
        assertEquals(remark, basicbasedevicecorrelation.getRemark());
        assertEquals(createTime, basicbasedevicecorrelation.getCreateTime());
        assertEquals(updateTime, basicbasedevicecorrelation.getUpdateTime());
        assertEquals(deleteTime, basicbasedevicecorrelation.getDeleteTime());
        assertEquals(createBy, basicbasedevicecorrelation.getCreateBy());
        assertEquals(updateBy, basicbasedevicecorrelation.getUpdateBy());
        assertEquals(operatorOrgCode, basicbasedevicecorrelation.getOperatorOrgCode());
        assertEquals(operatorSelfOrgCode, basicbasedevicecorrelation.getOperatorSelfOrgCode());
        assertEquals(tenantId, basicbasedevicecorrelation.getTenantId());
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        BasicBaseDeviceCorrelation anotherInstance = new BasicBaseDeviceCorrelation();
        anotherInstance.setId(1L);
        anotherInstance.setMainDeviceId(2L);
        anotherInstance.setSubDeviceId(3L);
        anotherInstance.setRemark("Test Remark");
        anotherInstance.setCreateTime(System.currentTimeMillis());
        anotherInstance.setUpdateTime(System.currentTimeMillis());
        anotherInstance.setDeleteTime(System.currentTimeMillis());
        anotherInstance.setCreateBy(4L);
        anotherInstance.setUpdateBy(5L);
        anotherInstance.setOperatorOrgCode("OrgCode");
        anotherInstance.setOperatorSelfOrgCode("SelfOrgCode");
        anotherInstance.setTenantId("TenantId");

        basicbasedevicecorrelation.setId(1L);
        basicbasedevicecorrelation.setMainDeviceId(2L);
        basicbasedevicecorrelation.setSubDeviceId(3L);
        basicbasedevicecorrelation.setRemark("Test Remark");
        basicbasedevicecorrelation.setCreateTime(System.currentTimeMillis());
        basicbasedevicecorrelation.setUpdateTime(System.currentTimeMillis());
        basicbasedevicecorrelation.setDeleteTime(System.currentTimeMillis());
        basicbasedevicecorrelation.setCreateBy(4L);
        basicbasedevicecorrelation.setUpdateBy(5L);
        basicbasedevicecorrelation.setOperatorOrgCode("OrgCode");
        basicbasedevicecorrelation.setOperatorSelfOrgCode("SelfOrgCode");
        basicbasedevicecorrelation.setTenantId("TenantId");

        // Then
        assertEquals(basicbasedevicecorrelation, anotherInstance);
        assertEquals(basicbasedevicecorrelation.hashCode(), anotherInstance.hashCode());
    }
}
