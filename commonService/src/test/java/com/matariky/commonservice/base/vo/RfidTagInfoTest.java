package com.matariky.commonservice.base.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RfidTagInfoTest {

    @InjectMocks
    private RfidTagInfo rfidtaginfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetAndGetRfidCode() {
        // Given
        String rfidCode = "123456";

        // When
        rfidtaginfo.setRfidCode(rfidCode);

        // Then
        assertEquals(rfidCode, rfidtaginfo.getRfidCode());
    }

    @Test
    void testSetAndGetEpc() {
        // Given
        String epc = "EPC123";

        // When
        rfidtaginfo.setEpc(epc);

        // Then
        assertEquals(epc, rfidtaginfo.getEpc());
    }

    @Test
    void testSetAndGetTagId() {
        // Given
        String tagId = "TAG123";

        // When
        rfidtaginfo.setTagId(tagId);

        // Then
        assertEquals(tagId, rfidtaginfo.getTagId());
    }

    @Test
    void testSetAndGetTagType() {
        // Given
        Integer tagType = 1;

        // When
        rfidtaginfo.setTagType(tagType);

        // Then
        assertEquals(tagType, rfidtaginfo.getTagType());
    }

    @Test
    void testSetAndGetTagCategory() {
        // Given
        Integer tagCategory = 0;

        // When
        rfidtaginfo.setTagCategory(tagCategory);

        // Then
        assertEquals(tagCategory, rfidtaginfo.getTagCategory());
    }

    @Test
    void testSetAndGetEnable() {
        // Given
        Boolean enable = true;

        // When
        rfidtaginfo.setEnable(enable);

        // Then
        assertEquals(enable, rfidtaginfo.getEnable());
    }

    @Test
    void testSetAndGetRemark() {
        // Given
        String remark = "Test Remark";

        // When
        rfidtaginfo.setRemark(remark);

        // Then
        assertEquals(remark, rfidtaginfo.getRemark());
    }

    @Test
    void testSetAndGetOccupantId() {
        // Given
        Long occupantId = 123L;

        // When
        rfidtaginfo.setOccupantId(occupantId);

        // Then
        assertEquals(occupantId, rfidtaginfo.getOccupantId());
    }

    @Test
    void testSetAndGetId() {
        // Given
        Long id = 1L;

        // When
        rfidtaginfo.setId(id);

        // Then
        assertEquals(id, rfidtaginfo.getId());
    }

    @Test
    void testSetAndGetCreateTime() {
        // Given
        Date createTime = new Date();

        // When
        rfidtaginfo.setCreateTime(createTime);

        // Then
        assertEquals(createTime, rfidtaginfo.getCreateTime());
    }

    @Test
    void testSetAndGetCreateBy() {
        // Given
        String createBy = "creator";

        // When
        rfidtaginfo.setCreateBy(createBy);

        // Then
        assertEquals(createBy, rfidtaginfo.getCreateBy());
    }

    @Test
    void testSetAndGetUpdateTime() {
        // Given
        Date updateTime = new Date();

        // When
        rfidtaginfo.setUpdateTime(updateTime);

        // Then
        assertEquals(updateTime, rfidtaginfo.getUpdateTime());
    }

    @Test
    void testSetAndGetUpdateBy() {
        // Given
        String updateBy = "updater";

        // When
        rfidtaginfo.setUpdateBy(updateBy);

        // Then
        assertEquals(updateBy, rfidtaginfo.getUpdateBy());
    }

    @Test
    void testSetAndGetEpcListStr() {
        // Given
        String epcListStr = "EPC1,EPC2";

        // When
        rfidtaginfo.setEpcListStr(epcListStr);

        // Then
        assertEquals(epcListStr, rfidtaginfo.getEpcListStr());
    }

    @Test
    void testSetAndGetEpcAndTidList() {
        // Given
        List<EPCAndTIDVO> epcAndTidList = List.of(new EPCAndTIDVO());

        // When
        rfidtaginfo.setEpcAndTidList(epcAndTidList);

        // Then
        assertEquals(epcAndTidList, rfidtaginfo.getEpcAndTidList());
    }
}
