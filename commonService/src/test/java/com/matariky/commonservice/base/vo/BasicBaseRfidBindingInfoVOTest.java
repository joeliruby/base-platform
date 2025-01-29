package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBaseRfidBindingInfoVOTest {

    @InjectMocks
    private BasicBaseRfidBindingInfoVO basicBaseRfidBindingInfoVO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        Long rfidId = 2L;
        String epc = "epcValue";
        String tid = "tidValue";
        String remark = "remarkValue";
        Long createTime = 123456789L;
        Long updateTime = 987654321L;
        Long deleteTime = 111111111L;
        Long createBy = 3L;
        Long updateBy = 4L;
        String operatorOrgCode = "orgCode";
        String operatorSelfOrgCode = "selfOrgCode";
        String tenantId = "tenantId";
        String goodsName = "goodsName";
        String goodsCode = "goodsCode";
        Long typeId = 5L;
        String deviceCode = "deviceCode";
        String typeCode = "typeCode";
        String typeName = "typeName";
        String deviceFactory = "deviceFactory";
        String deviceModel = "deviceModel";
        Long tagCode = 6L;

        // When
        basicBaseRfidBindingInfoVO.setId(id);
        basicBaseRfidBindingInfoVO.setRfidId(rfidId);
        basicBaseRfidBindingInfoVO.setEpc(epc);
        basicBaseRfidBindingInfoVO.setTid(tid);
        basicBaseRfidBindingInfoVO.setRemark(remark);
        basicBaseRfidBindingInfoVO.setCreateTime(createTime);
        basicBaseRfidBindingInfoVO.setUpdateTime(updateTime);
        basicBaseRfidBindingInfoVO.setDeleteTime(deleteTime);
        basicBaseRfidBindingInfoVO.setCreateBy(createBy);
        basicBaseRfidBindingInfoVO.setUpdateBy(updateBy);
        basicBaseRfidBindingInfoVO.setOperatorOrgCode(operatorOrgCode);
        basicBaseRfidBindingInfoVO.setOperatorSelfOrgCode(operatorSelfOrgCode);
        basicBaseRfidBindingInfoVO.setTenantId(tenantId);
        basicBaseRfidBindingInfoVO.setGoodsName(goodsName);
        basicBaseRfidBindingInfoVO.setGoodsCode(goodsCode);
        basicBaseRfidBindingInfoVO.setTypeId(typeId);
        basicBaseRfidBindingInfoVO.setDeviceCode(deviceCode);
        basicBaseRfidBindingInfoVO.setTypeCode(typeCode);
        basicBaseRfidBindingInfoVO.setTypeName(typeName);
        basicBaseRfidBindingInfoVO.setDeviceFactory(deviceFactory);
        basicBaseRfidBindingInfoVO.setDeviceModel(deviceModel);
        basicBaseRfidBindingInfoVO.setTagCode(tagCode);

        // Then
        assertThat(basicBaseRfidBindingInfoVO.getId()).isEqualTo(id);
        assertThat(basicBaseRfidBindingInfoVO.getRfidId()).isEqualTo(rfidId);
        assertThat(basicBaseRfidBindingInfoVO.getEpc()).isEqualTo(epc);
        assertThat(basicBaseRfidBindingInfoVO.getTid()).isEqualTo(tid);
        assertThat(basicBaseRfidBindingInfoVO.getRemark()).isEqualTo(remark);
        assertThat(basicBaseRfidBindingInfoVO.getCreateTime()).isEqualTo(createTime);
        assertThat(basicBaseRfidBindingInfoVO.getUpdateTime()).isEqualTo(updateTime);
        assertThat(basicBaseRfidBindingInfoVO.getDeleteTime()).isEqualTo(deleteTime);
        assertThat(basicBaseRfidBindingInfoVO.getCreateBy()).isEqualTo(createBy);
        assertThat(basicBaseRfidBindingInfoVO.getUpdateBy()).isEqualTo(updateBy);
        assertThat(basicBaseRfidBindingInfoVO.getOperatorOrgCode()).isEqualTo(operatorOrgCode);
        assertThat(basicBaseRfidBindingInfoVO.getOperatorSelfOrgCode()).isEqualTo(operatorSelfOrgCode);
        assertThat(basicBaseRfidBindingInfoVO.getTenantId()).isEqualTo(tenantId);
        assertThat(basicBaseRfidBindingInfoVO.getGoodsName()).isEqualTo(goodsName);
        assertThat(basicBaseRfidBindingInfoVO.getGoodsCode()).isEqualTo(goodsCode);
        assertThat(basicBaseRfidBindingInfoVO.getTypeId()).isEqualTo(typeId);
        assertThat(basicBaseRfidBindingInfoVO.getDeviceCode()).isEqualTo(deviceCode);
        assertThat(basicBaseRfidBindingInfoVO.getTypeCode()).isEqualTo(typeCode);
        assertThat(basicBaseRfidBindingInfoVO.getTypeName()).isEqualTo(typeName);
        assertThat(basicBaseRfidBindingInfoVO.getDeviceFactory()).isEqualTo(deviceFactory);
        assertThat(basicBaseRfidBindingInfoVO.getDeviceModel()).isEqualTo(deviceModel);
        assertThat(basicBaseRfidBindingInfoVO.getTagCode()).isEqualTo(tagCode);
    }

    // Add more test methods for other methods in BasicBaseRfidBindingInfoVO
}
