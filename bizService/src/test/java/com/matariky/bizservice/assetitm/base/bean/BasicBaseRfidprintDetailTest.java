package com.matariky.bizservice.assetitm.base.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicBaseRfidprintDetailTest {

    @InjectMocks
    private BasicBaseRfidprintDetail basicBaseRfidprintDetail;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGettersAndSetters() {
        // Given
        Long id = 1L;
        String epc = "EPC123";
        String tid = "TID123";

        // When
        basicBaseRfidprintDetail.setId(id);
        basicBaseRfidprintDetail.setEpc(epc);
        basicBaseRfidprintDetail.setTid(tid);

        // Then
        assertEquals(id, basicBaseRfidprintDetail.getId());
        assertEquals(epc, basicBaseRfidprintDetail.getEpc());
        assertEquals(tid, basicBaseRfidprintDetail.getTid());
    }

    @Test
    void testIsPrint() {
        // Given
        Integer isPrint = 1;

        // When
        basicBaseRfidprintDetail.setIsPrint(isPrint);

        // Then
        assertEquals(isPrint, basicBaseRfidprintDetail.getIsPrint());
    }

    @Test
    void testPrintTime() {
        // Given
        Long printTime = System.currentTimeMillis();

        // When
        basicBaseRfidprintDetail.setPrintTime(printTime);

        // Then
        assertEquals(printTime, basicBaseRfidprintDetail.getPrintTime());
    }

    @Test
    void testRemark() {
        // Given
        String remark = "Test Remark";

        // When
        basicBaseRfidprintDetail.setRemark(remark);

        // Then
        assertEquals(remark, basicBaseRfidprintDetail.getRemark());
    }

    @Test
    void testCreateTime() {
        // Given
        Long createTime = System.currentTimeMillis();

        // When
        basicBaseRfidprintDetail.setCreateTime(createTime);

        // Then
        assertEquals(createTime, basicBaseRfidprintDetail.getCreateTime());
    }

    @Test
    void testUpdateTime() {
        // Given
        Long updateTime = System.currentTimeMillis();

        // When
        basicBaseRfidprintDetail.setUpdateTime(updateTime);

        // Then
        assertEquals(updateTime, basicBaseRfidprintDetail.getUpdateTime());
    }

    @Test
    void testDeleteTime() {
        // Given
        Long deleteTime = System.currentTimeMillis();

        // When
        basicBaseRfidprintDetail.setDeleteTime(deleteTime);

        // Then
        assertEquals(deleteTime, basicBaseRfidprintDetail.getDeleteTime());
    }

    @Test
    void testOperatorOrgCode() {
        // Given
        String operatorOrgCode = "ORG123";

        // When
        basicBaseRfidprintDetail.setOperatorOrgCode(operatorOrgCode);

        // Then
        assertEquals(operatorOrgCode, basicBaseRfidprintDetail.getOperatorOrgCode());
    }

    @Test
    void testOperatorSelfOrgCode() {
        // Given
        String operatorSelfOrgCode = "SELFORG123";

        // When
        basicBaseRfidprintDetail.setOperatorSelfOrgCode(operatorSelfOrgCode);

        // Then
        assertEquals(operatorSelfOrgCode, basicBaseRfidprintDetail.getOperatorSelfOrgCode());
    }

    @Test
    void testOdContent() {
        // Given
        String odContent = "OD Content";

        // When
        basicBaseRfidprintDetail.setOdContent(odContent);

        // Then
        assertEquals(odContent, basicBaseRfidprintDetail.getOdContent());
    }

    @Test
    void testQrContent() {
        // Given
        String qrContent = "QR Content";

        // When
        basicBaseRfidprintDetail.setQrContent(qrContent);

        // Then
        assertEquals(qrContent, basicBaseRfidprintDetail.getQrContent());
    }

    @Test
    void testPassword() {
        // Given
        String password = "password123";

        // When
        basicBaseRfidprintDetail.setPassword(password);

        // Then
        assertEquals(password, basicBaseRfidprintDetail.getPassword());
    }

    @Test
    void testRfidImg() {
        // Given
        String rfidImg = "RFID Image";

        // When
        basicBaseRfidprintDetail.setRfidImg(rfidImg);

        // Then
        assertEquals(rfidImg, basicBaseRfidprintDetail.getRfidImg());
    }
}
