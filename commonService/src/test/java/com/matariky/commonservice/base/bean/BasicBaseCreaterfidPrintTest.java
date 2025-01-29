package com.matariky.commonservice.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBaseCreaterfidPrintTest {

    @InjectMocks
    private BasicBaseCreaterfidPrint basicbasecreaterfidprint;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetId() {
        // Given
        Long id = 1L;

        // When
        basicbasecreaterfidprint.setId(id);

        // Then
        assertThat(basicbasecreaterfidprint.getId()).isEqualTo(id);
    }

    @Test
    void testSetAndGetPrintId() {
        // Given
        Long printId = 2L;

        // When
        basicbasecreaterfidprint.setPrintId(printId);

        // Then
        assertThat(basicbasecreaterfidprint.getPrintId()).isEqualTo(printId);
    }

    @Test
    void testSetAndGetGoodsId() {
        // Given
        Long goodsId = 3L;

        // When
        basicbasecreaterfidprint.setGoodsId(goodsId);

        // Then
        assertThat(basicbasecreaterfidprint.getGoodsId()).isEqualTo(goodsId);
    }

    @Test
    void testSetAndGetDeviceId() {
        // Given
        Long deviceId = 4L;

        // When
        basicbasecreaterfidprint.setDeviceId(deviceId);

        // Then
        assertThat(basicbasecreaterfidprint.getDeviceId()).isEqualTo(deviceId);
    }

    @Test
    void testSetAndGetRfidId() {
        // Given
        Long rfidId = 5L;

        // When
        basicbasecreaterfidprint.setRfidId(rfidId);

        // Then
        assertThat(basicbasecreaterfidprint.getRfidId()).isEqualTo(rfidId);
    }

    @Test
    void testSetAndGetRemark() {
        // Given
        String remark = "Test Remark";

        // When
        basicbasecreaterfidprint.setRemark(remark);

        // Then
        assertThat(basicbasecreaterfidprint.getRemark()).isEqualTo(remark);
    }

    @Test
    void testSetAndGetCreateTime() {
        // Given
        Long createTime = 6L;

        // When
        basicbasecreaterfidprint.setCreateTime(createTime);

        // Then
        assertThat(basicbasecreaterfidprint.getCreateTime()).isEqualTo(createTime);
    }

    @Test
    void testSetAndGetUpdateTime() {
        // Given
        Long updateTime = 7L;

        // When
        basicbasecreaterfidprint.setUpdateTime(updateTime);

        // Then
        assertThat(basicbasecreaterfidprint.getUpdateTime()).isEqualTo(updateTime);
    }

    @Test
    void testSetAndGetDeleteTime() {
        // Given
        Long deleteTime = 8L;

        // When
        basicbasecreaterfidprint.setDeleteTime(deleteTime);

        // Then
        assertThat(basicbasecreaterfidprint.getDeleteTime()).isEqualTo(deleteTime);
    }

    @Test
    void testSetAndGetCreateBy() {
        // Given
        Long createBy = 9L;

        // When
        basicbasecreaterfidprint.setCreateBy(createBy);

        // Then
        assertThat(basicbasecreaterfidprint.getCreateBy()).isEqualTo(createBy);
    }

    @Test
    void testSetAndGetUpdateBy() {
        // Given
        Long updateBy = 10L;

        // When
        basicbasecreaterfidprint.setUpdateBy(updateBy);

        // Then
        assertThat(basicbasecreaterfidprint.getUpdateBy()).isEqualTo(updateBy);
    }

    @Test
    void testSetAndGetOperatorOrgCode() {
        // Given
        String operatorOrgCode = "OrgCode1";

        // When
        basicbasecreaterfidprint.setOperatorOrgCode(operatorOrgCode);

        // Then
        assertThat(basicbasecreaterfidprint.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
    }

    @Test
    void testSetAndGetOperatorSelfOrgCode() {
        // Given
        String operatorSelfOrgCode = "SelfOrgCode1";

        // When
        basicbasecreaterfidprint.setOperatorSelfOrgCode(operatorSelfOrgCode);

        // Then
        assertThat(basicbasecreaterfidprint.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
    }

    @Test
    void testSetAndGetTenantId() {
        // Given
        String tenantId = "Tenant1";

        // When
        basicbasecreaterfidprint.setTenantId(tenantId);

        // Then
        assertThat(basicbasecreaterfidprint.getTenantId()).isEqualTo(tenantId);
    }
}
