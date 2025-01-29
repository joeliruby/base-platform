package com.matariky.commonservice.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseRfidBindingTest {

    @InjectMocks
    private BasicBaseRfidBinding basicBaseRfidBinding;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        basicBaseRfidBinding.setId(expectedId);

        // When
        Long actualId = basicBaseRfidBinding.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetRfidId() {
        // Given
        Long expectedRfidId = 2L;
        basicBaseRfidBinding.setRfidId(expectedRfidId);

        // When
        Long actualRfidId = basicBaseRfidBinding.getRfidId();

        // Then
        assertThat(actualRfidId).isEqualTo(expectedRfidId);
    }

    @Test
    void testGetGoodsId() {
        // Given
        Long expectedGoodsId = 3L;
        basicBaseRfidBinding.setGoodsId(expectedGoodsId);

        // When
        Long actualGoodsId = basicBaseRfidBinding.getGoodsId();

        // Then
        assertThat(actualGoodsId).isEqualTo(expectedGoodsId);
    }

    @Test
    void testGetDeviceId() {
        // Given
        Long expectedDeviceId = 4L;
        basicBaseRfidBinding.setDeviceId(expectedDeviceId);

        // When
        Long actualDeviceId = basicBaseRfidBinding.getDeviceId();

        // Then
        assertThat(actualDeviceId).isEqualTo(expectedDeviceId);
    }

    @Test
    void testGetEpc() {
        // Given
        String expectedEpc = "EPC123";
        basicBaseRfidBinding.setEpc(expectedEpc);

        // When
        String actualEpc = basicBaseRfidBinding.getEpc();

        // Then
        assertThat(actualEpc).isEqualTo(expectedEpc);
    }

    @Test
    void testGetTid() {
        // Given
        String expectedTid = "TID123";
        basicBaseRfidBinding.setTid(expectedTid);

        // When
        String actualTid = basicBaseRfidBinding.getTid();

        // Then
        assertThat(actualTid).isEqualTo(expectedTid);
    }

    @Test
    void testGetRemark() {
        // Given
        String expectedRemark = "Test Remark";
        basicBaseRfidBinding.setRemark(expectedRemark);

        // When
        String actualRemark = basicBaseRfidBinding.getRemark();

        // Then
        assertThat(actualRemark).isEqualTo(expectedRemark);
    }

    @Test
    void testGetCreateTime() {
        // Given
        Long expectedCreateTime = 123456789L;
        basicBaseRfidBinding.setCreateTime(expectedCreateTime);

        // When
        Long actualCreateTime = basicBaseRfidBinding.getCreateTime();

        // Then
        assertThat(actualCreateTime).isEqualTo(expectedCreateTime);
    }

    @Test
    void testGetUpdateTime() {
        // Given
        Long expectedUpdateTime = 987654321L;
        basicBaseRfidBinding.setUpdateTime(expectedUpdateTime);

        // When
        Long actualUpdateTime = basicBaseRfidBinding.getUpdateTime();

        // Then
        assertThat(actualUpdateTime).isEqualTo(expectedUpdateTime);
    }

    @Test
    void testGetDeleteTime() {
        // Given
        Long expectedDeleteTime = 1122334455L;
        basicBaseRfidBinding.setDeleteTime(expectedDeleteTime);

        // When
        Long actualDeleteTime = basicBaseRfidBinding.getDeleteTime();

        // Then
        assertThat(actualDeleteTime).isEqualTo(expectedDeleteTime);
    }

    @Test
    void testGetCreateBy() {
        // Given
        Long expectedCreateBy = 5L;
        basicBaseRfidBinding.setCreateBy(expectedCreateBy);

        // When
        Long actualCreateBy = basicBaseRfidBinding.getCreateBy();

        // Then
        assertThat(actualCreateBy).isEqualTo(expectedCreateBy);
    }

    @Test
    void testGetUpdateBy() {
        // Given
        Long expectedUpdateBy = 6L;
        basicBaseRfidBinding.setUpdateBy(expectedUpdateBy);

        // When
        Long actualUpdateBy = basicBaseRfidBinding.getUpdateBy();

        // Then
        assertThat(actualUpdateBy).isEqualTo(expectedUpdateBy);
    }

    @Test
    void testGetOperatorOrgCode() {
        // Given
        String expectedOperatorOrgCode = "ORG123";
        basicBaseRfidBinding.setOperatorOrgCode(expectedOperatorOrgCode);

        // When
        String actualOperatorOrgCode = basicBaseRfidBinding.getOperatorOrgCode();

        // Then
        assertThat(actualOperatorOrgCode).isEqualTo(expectedOperatorOrgCode);
    }

    @Test
    void testGetOperatorSelfOrgCode() {
        // Given
        String expectedOperatorSelfOrgCode = "SELFORG123";
        basicBaseRfidBinding.setOperatorSelfOrgCode(expectedOperatorSelfOrgCode);

        // When
        String actualOperatorSelfOrgCode = basicBaseRfidBinding.getOperatorSelfOrgCode();

        // Then
        assertThat(actualOperatorSelfOrgCode).isEqualTo(expectedOperatorSelfOrgCode);
    }

    @Test
    void testGetTenantId() {
        // Given
        String expectedTenantId = "TENANT123";
        basicBaseRfidBinding.setTenantId(expectedTenantId);

        // When
        String actualTenantId = basicBaseRfidBinding.getTenantId();

        // Then
        assertThat(actualTenantId).isEqualTo(expectedTenantId);
    }

    @Test
    void testGetTagCode() {
        // Given
        Long expectedTagCode = 7L;
        basicBaseRfidBinding.setTagCode(expectedTagCode);

        // When
        Long actualTagCode = basicBaseRfidBinding.getTagCode();

        // Then
        assertThat(actualTagCode).isEqualTo(expectedTagCode);
    }
}
