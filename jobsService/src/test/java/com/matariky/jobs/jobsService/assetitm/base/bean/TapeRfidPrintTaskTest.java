package com.matariky.jobs.jobsService.assetitm.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TapeRfidPrintTaskTest {

    @InjectMocks
    private TapeRfidPrintTask taperfidprinttask;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        taperfidprinttask.setId(1L);

        // When
        Long id = taperfidprinttask.getId();

        // Then
        assertThat(id).isEqualTo(1L);
    }

    @Test
    void testGetTaskName() {
        // Given
        taperfidprinttask.setTaskName("Task 1");

        // When
        String taskName = taperfidprinttask.getTaskName();

        // Then
        assertThat(taskName).isEqualTo("Task 1");
    }

    @Test
    void testGetPrintType() {
        // Given
        taperfidprinttask.setPrintType("1");

        // When
        String printType = taperfidprinttask.getPrintType();

        // Then
        assertThat(printType).isEqualTo("1");
    }

    @Test
    void testGetTaskTime() {
        // Given
        taperfidprinttask.setTaskTime(123456789L);

        // When
        Long taskTime = taperfidprinttask.getTaskTime();

        // Then
        assertThat(taskTime).isEqualTo(123456789L);
    }

    @Test
    void testGetDeviceId() {
        // Given
        taperfidprinttask.setDeviceId(2L);

        // When
        Long deviceId = taperfidprinttask.getDeviceId();

        // Then
        assertThat(deviceId).isEqualTo(2L);
    }

    @Test
    void testGetPrintCode() {
        // Given
        taperfidprinttask.setPrintCode("P123");

        // When
        String printCode = taperfidprinttask.getPrintCode();

        // Then
        assertThat(printCode).isEqualTo("P123");
    }

    @Test
    void testGetPrintName() {
        // Given
        taperfidprinttask.setPrintName("Printer 1");

        // When
        String printName = taperfidprinttask.getPrintName();

        // Then
        assertThat(printName).isEqualTo("Printer 1");
    }

    @Test
    void testGetGoodsId() {
        // Given
        taperfidprinttask.setGoodsId(3L);

        // When
        Long goodsId = taperfidprinttask.getGoodsId();

        // Then
        assertThat(goodsId).isEqualTo(3L);
    }

    @Test
    void testGetGoodsCode() {
        // Given
        taperfidprinttask.setGoodsCode("G123");

        // When
        String goodsCode = taperfidprinttask.getGoodsCode();

        // Then
        assertThat(goodsCode).isEqualTo("G123");
    }

    @Test
    void testGetGoodsName() {
        // Given
        taperfidprinttask.setGoodsName("Item 1");

        // When
        String goodsName = taperfidprinttask.getGoodsName();

        // Then
        assertThat(goodsName).isEqualTo("Item 1");
    }

    @Test
    void testGetPrintNum() {
        // Given
        taperfidprinttask.setPrintNum(10);

        // When
        Integer printNum = taperfidprinttask.getPrintNum();

        // Then
        assertThat(printNum).isEqualTo(10);
    }

    @Test
    void testGetPrintedNum() {
        // Given
        taperfidprinttask.setPrintedNum(5);

        // When
        Integer printedNum = taperfidprinttask.getPrintedNum();

        // Then
        assertThat(printedNum).isEqualTo(5);
    }

    @Test
    void testGetUnprintNum() {
        // Given
        taperfidprinttask.setUnprintNum(5);

        // When
        Integer unprintNum = taperfidprinttask.getUnprintNum();

        // Then
        assertThat(unprintNum).isEqualTo(5);
    }

    @Test
    void testGetEpcRule() {
        // Given
        taperfidprinttask.setEpcRule("EPC123");

        // When
        String epcRule = taperfidprinttask.getEpcRule();

        // Then
        assertThat(epcRule).isEqualTo("EPC123");
    }

    @Test
    void testGetIsLockEpc() {
        // Given
        taperfidprinttask.setIsLockEpc(1);

        // When
        Integer isLockEpc = taperfidprinttask.getIsLockEpc();

        // Then
        assertThat(isLockEpc).isEqualTo(1);
    }

    @Test
    void testGetEpcPassword() {
        // Given
        taperfidprinttask.setEpcPassword("password");

        // When
        String epcPassword = taperfidprinttask.getEpcPassword();

        // Then
        assertThat(epcPassword).isEqualTo("password");
    }

    @Test
    void testGetIsOdcode() {
        // Given
        taperfidprinttask.setIsOdcode(1);

        // When
        Integer isOdcode = taperfidprinttask.getIsOdcode();

        // Then
        assertThat(isOdcode).isEqualTo(1);
    }

    @Test
    void testGetOdFixedContent() {
        // Given
        taperfidprinttask.setOdFixedContent("fixed content");

        // When
        String odFixedContent = taperfidprinttask.getOdFixedContent();

        // Then
        assertThat(odFixedContent).isEqualTo("fixed content");
    }

    @Test
    void testGetIsQrcode() {
        // Given
        taperfidprinttask.setIsQrcode(1);

        // When
        Integer isQrcode = taperfidprinttask.getIsQrcode();

        // Then
        assertThat(isQrcode).isEqualTo(1);
    }

    @Test
    void testGetQrFixedContent() {
        // Given
        taperfidprinttask.setQrFixedContent("QR content");

        // When
        String qrFixedContent = taperfidprinttask.getQrFixedContent();

        // Then
        assertThat(qrFixedContent).isEqualTo("QR content");
    }

    @Test
    void testGetRfidImg() {
        // Given
        taperfidprinttask.setRfidImg("image.png");

        // When
        String rfidImg = taperfidprinttask.getRfidImg();

        // Then
        assertThat(rfidImg).isEqualTo("image.png");
    }

    @Test
    void testGetPrintStatus() {
        // Given
        taperfidprinttask.setPrintStatus(1);

        // When
        Integer printStatus = taperfidprinttask.getPrintStatus();

        // Then
        assertThat(printStatus).isEqualTo(1);
    }

    @Test
    void testGetIsLock() {
        // Given
        taperfidprinttask.setIsLock(1);

        // When
        Integer isLock = taperfidprinttask.getIsLock();

        // Then
        assertThat(isLock).isEqualTo(1);
    }

    @Test
    void testGetRemark() {
        // Given
        taperfidprinttask.setRemark("remark");

        // When
        String remark = taperfidprinttask.getRemark();

        // Then
        assertThat(remark).isEqualTo("remark");
    }

    @Test
    void testGetRealName() {
        // Given
        taperfidprinttask.setRealName("John Doe");

        // When
        String realName = taperfidprinttask.getRealName();

        // Then
        assertThat(realName).isEqualTo("John Doe");
    }

    @Test
    void testGetCreateTime() {
        // Given
        taperfidprinttask.setCreateTime(123456789L);

        // When
        Long createTime = taperfidprinttask.getCreateTime();

        // Then
        assertThat(createTime).isEqualTo(123456789L);
    }

    @Test
    void testGetUpdateTime() {
        // Given
        taperfidprinttask.setUpdateTime(987654321L);

        // When
        Long updateTime = taperfidprinttask.getUpdateTime();

        // Then
        assertThat(updateTime).isEqualTo(987654321L);
    }

    @Test
    void testGetDeleteTime() {
        // Given
        taperfidprinttask.setDeleteTime(192837465L);

        // When
        Long deleteTime = taperfidprinttask.getDeleteTime();

        // Then
        assertThat(deleteTime).isEqualTo(192837465L);
    }

    @Test
    void testGetCreateBy() {
        // Given
        taperfidprinttask.setCreateBy(4L);

        // When
        Long createBy = taperfidprinttask.getCreateBy();

        // Then
        assertThat(createBy).isEqualTo(4L);
    }

    @Test
    void testGetUpdateBy() {
        // Given
        taperfidprinttask.setUpdateBy(5L);

        // When
        Long updateBy = taperfidprinttask.getUpdateBy();

        // Then
        assertThat(updateBy).isEqualTo(5L);
    }

    @Test
    void testGetOperatorOrgCode() {
        // Given
        taperfidprinttask.setOperatorOrgCode("ORG123");

        // When
        String operatorOrgCode = taperfidprinttask.getOperatorOrgCode();

        // Then
        assertThat(operatorOrgCode).isEqualTo("ORG123");
    }

    @Test
    void testGetOperatorSelfOrgCode() {
        // Given
        taperfidprinttask.setOperatorSelfOrgCode("SELF123");

        // When
        String operatorSelfOrgCode = taperfidprinttask.getOperatorSelfOrgCode();

        // Then
        assertThat(operatorSelfOrgCode).isEqualTo("SELF123");
    }

    @Test
    void testGetTenantId() {
        // Given
        taperfidprinttask.setTenantId("TENANT123");

        // When
        String tenantId = taperfidprinttask.getTenantId();

        // Then
        assertThat(tenantId).isEqualTo("TENANT123");
    }
}
