package com.matariky.bizservice.assetitm.base.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicBaseRfidprintAddVOTest {

    @InjectMocks
    private BasicBaseRfidprintAddVO basicbaserfidprintaddvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String taskName = "Task 1";
        Long taskTime = 123456789L;
        Long deviceId = 2L;
        Long goodsId = 3L;
        Integer printNum = 100;
        Integer printedNum = 50;
        Integer unprintNum = 50;
        String epcRule = "EPC123";
        Integer isLockEpc = 1;
        String epcPassword = "password";
        Integer isOdcode = 1;
        String odFixedContent = "OD123";
        Integer isQrcode = 1;
        String qrFixedContent = "QR123";
        String rfidImg = "image.png";
        String remark = "Remark";
        Integer printStatus = 1;
        Integer isLock = 1;
        String operatorOrgCode = "ORG123";
        String operatorSelfOrgCode = "SELFORG123";
        String tenantId = "TENANT123";
        Long createBy = 1L;
        Long updateBy = 2L;
        Long createTime = 123456789L;
        Long updateTime = 987654321L;
        Long deleteTime = 111111111L;

        // When
        basicbaserfidprintaddvo.setId(id);
        basicbaserfidprintaddvo.setTaskName(taskName);
        basicbaserfidprintaddvo.setTaskTime(taskTime);
        basicbaserfidprintaddvo.setDeviceId(deviceId);
        basicbaserfidprintaddvo.setGoodsId(goodsId);
        basicbaserfidprintaddvo.setPrintNum(printNum);
        basicbaserfidprintaddvo.setPrintedNum(printedNum);
        basicbaserfidprintaddvo.setUnprintNum(unprintNum);
        basicbaserfidprintaddvo.setEpcRule(epcRule);
        basicbaserfidprintaddvo.setIsLockEpc(isLockEpc);
        basicbaserfidprintaddvo.setEpcPassword(epcPassword);
        basicbaserfidprintaddvo.setIsOdcode(isOdcode);
        basicbaserfidprintaddvo.setOdFixedContent(odFixedContent);
        basicbaserfidprintaddvo.setIsQrcode(isQrcode);
        basicbaserfidprintaddvo.setQrFixedContent(qrFixedContent);
        basicbaserfidprintaddvo.setRfidImg(rfidImg);
        basicbaserfidprintaddvo.setRemark(remark);
        basicbaserfidprintaddvo.setPrintStatus(printStatus);
        basicbaserfidprintaddvo.setIsLock(isLock);
        basicbaserfidprintaddvo.setOperatorOrgCode(operatorOrgCode);
        basicbaserfidprintaddvo.setOperatorSelfOrgCode(operatorSelfOrgCode);
        basicbaserfidprintaddvo.setTenantId(tenantId);
        basicbaserfidprintaddvo.setCreateBy(createBy);
        basicbaserfidprintaddvo.setUpdateBy(updateBy);
        basicbaserfidprintaddvo.setCreateTime(createTime);
        basicbaserfidprintaddvo.setUpdateTime(updateTime);
        basicbaserfidprintaddvo.setDeleteTime(deleteTime);

        // Then
        assertEquals(id, basicbaserfidprintaddvo.getId());
        assertEquals(taskName, basicbaserfidprintaddvo.getTaskName());
        assertEquals(taskTime, basicbaserfidprintaddvo.getTaskTime());
        assertEquals(deviceId, basicbaserfidprintaddvo.getDeviceId());
        assertEquals(goodsId, basicbaserfidprintaddvo.getGoodsId());
        assertEquals(printNum, basicbaserfidprintaddvo.getPrintNum());
        assertEquals(printedNum, basicbaserfidprintaddvo.getPrintedNum());
        assertEquals(unprintNum, basicbaserfidprintaddvo.getUnprintNum());
        assertEquals(epcRule, basicbaserfidprintaddvo.getEpcRule());
        assertEquals(isLockEpc, basicbaserfidprintaddvo.getIsLockEpc());
        assertEquals(epcPassword, basicbaserfidprintaddvo.getEpcPassword());
        assertEquals(isOdcode, basicbaserfidprintaddvo.getIsOdcode());
        assertEquals(odFixedContent, basicbaserfidprintaddvo.getOdFixedContent());
        assertEquals(isQrcode, basicbaserfidprintaddvo.getIsQrcode());
        assertEquals(qrFixedContent, basicbaserfidprintaddvo.getQrFixedContent());
        assertEquals(rfidImg, basicbaserfidprintaddvo.getRfidImg());
        assertEquals(remark, basicbaserfidprintaddvo.getRemark());
        assertEquals(printStatus, basicbaserfidprintaddvo.getPrintStatus());
        assertEquals(isLock, basicbaserfidprintaddvo.getIsLock());
        assertEquals(operatorOrgCode, basicbaserfidprintaddvo.getOperatorOrgCode());
        assertEquals(operatorSelfOrgCode, basicbaserfidprintaddvo.getOperatorSelfOrgCode());
        assertEquals(tenantId, basicbaserfidprintaddvo.getTenantId());
        assertEquals(createBy, basicbaserfidprintaddvo.getCreateBy());
        assertEquals(updateBy, basicbaserfidprintaddvo.getUpdateBy());
        assertEquals(createTime, basicbaserfidprintaddvo.getCreateTime());
        assertEquals(updateTime, basicbaserfidprintaddvo.getUpdateTime());
        assertEquals(deleteTime, basicbaserfidprintaddvo.getDeleteTime());
    }

    @Test
    void testDefaultValues() {
        // Given
        BasicBaseRfidprintAddVO vo = new BasicBaseRfidprintAddVO();

        // Then
        assertNull(vo.getId());
        assertNull(vo.getTaskName());
        assertNull(vo.getTaskTime());
        assertNull(vo.getDeviceId());
        assertNull(vo.getGoodsId());
        assertNull(vo.getPrintNum());
        assertNull(vo.getPrintedNum());
        assertNull(vo.getUnprintNum());
        assertNull(vo.getEpcRule());
        assertNull(vo.getIsLockEpc());
        assertNull(vo.getEpcPassword());
        assertNull(vo.getIsOdcode());
        assertNull(vo.getOdFixedContent());
        assertNull(vo.getIsQrcode());
        assertNull(vo.getQrFixedContent());
        assertNull(vo.getRfidImg());
        assertNull(vo.getRemark());
        assertNull(vo.getPrintStatus());
        assertNull(vo.getIsLock());
        assertNull(vo.getOperatorOrgCode());
        assertNull(vo.getOperatorSelfOrgCode());
        assertNull(vo.getTenantId());
        assertNull(vo.getCreateBy());
        assertNull(vo.getUpdateBy());
        assertNull(vo.getCreateTime());
        assertNull(vo.getUpdateTime());
        assertNull(vo.getDeleteTime());
    }
}
