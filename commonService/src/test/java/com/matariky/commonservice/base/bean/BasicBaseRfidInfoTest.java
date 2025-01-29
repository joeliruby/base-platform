package com.matariky.commonservice.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseRfidInfoTest {

    @InjectMocks
    private BasicBaseRfidInfo basicBaseRfidInfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String tagCode = "TAG123";
        String epc = "EPC123";
        String tid = "TID123";
        String odContent = "ODContent";
        String qrContent = "QRContent";
        String password = "password";
        String remark = "remark";
        Long createTime = System.currentTimeMillis();
        Long updateTime = System.currentTimeMillis();
        Long deleteTime = System.currentTimeMillis();
        Long createBy = 1L;
        Long updateBy = 2L;
        String operatorOrgCode = "ORG123";
        String operatorSelfOrgCode = "SELFORG123";
        String tenantId = "TENANT123";

        // When
        basicBaseRfidInfo.setId(id);
        basicBaseRfidInfo.setTagCode(tagCode);
        basicBaseRfidInfo.setEpc(epc);
        basicBaseRfidInfo.setTid(tid);
        basicBaseRfidInfo.setOdContent(odContent);
        basicBaseRfidInfo.setQrContent(qrContent);
        basicBaseRfidInfo.setPassword(password);
        basicBaseRfidInfo.setRemark(remark);
        basicBaseRfidInfo.setCreateTime(createTime);
        basicBaseRfidInfo.setUpdateTime(updateTime);
        basicBaseRfidInfo.setDeleteTime(deleteTime);
        basicBaseRfidInfo.setCreateBy(createBy);
        basicBaseRfidInfo.setUpdateBy(updateBy);
        basicBaseRfidInfo.setOperatorOrgCode(operatorOrgCode);
        basicBaseRfidInfo.setOperatorSelfOrgCode(operatorSelfOrgCode);
        basicBaseRfidInfo.setTenantId(tenantId);

        // Then
        assertThat(basicBaseRfidInfo.getId()).isEqualTo(id);
        assertThat(basicBaseRfidInfo.getTagCode()).isEqualTo(tagCode);
        assertThat(basicBaseRfidInfo.getEpc()).isEqualTo(epc);
        assertThat(basicBaseRfidInfo.getTid()).isEqualTo(tid);
        assertThat(basicBaseRfidInfo.getOdContent()).isEqualTo(odContent);
        assertThat(basicBaseRfidInfo.getQrContent()).isEqualTo(qrContent);
        assertThat(basicBaseRfidInfo.getPassword()).isEqualTo(password);
        assertThat(basicBaseRfidInfo.getRemark()).isEqualTo(remark);
        assertThat(basicBaseRfidInfo.getCreateTime()).isEqualTo(createTime);
        assertThat(basicBaseRfidInfo.getUpdateTime()).isEqualTo(updateTime);
        assertThat(basicBaseRfidInfo.getDeleteTime()).isEqualTo(deleteTime);
        assertThat(basicBaseRfidInfo.getCreateBy()).isEqualTo(createBy);
        assertThat(basicBaseRfidInfo.getUpdateBy()).isEqualTo(updateBy);
        assertThat(basicBaseRfidInfo.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
        assertThat(basicBaseRfidInfo.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
        assertThat(basicBaseRfidInfo.getTenantId()).isEqualTo(tenantId);
    }

    @Test
    void testToString() {
        // Given
        basicBaseRfidInfo.setId(1L);
        basicBaseRfidInfo.setTagCode("TAG123");

        // When
        String result = basicBaseRfidInfo.toString();

        // Then
        assertThat(result).contains("id=1", "tagCode=TAG123");
    }

    // Add more test methods for other methods in BasicBaseRfidInfo
}
