package com.matariky.commonservice.base.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasicBaseDeviceRuleDetailInfoTest {

    @InjectMocks
    private BasicBaseDeviceRuleDetailInfo basicbasedeviceruledetailinfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRuleId() {
        // Given
        Long expectedRuleId = 123L;
        basicbasedeviceruledetailinfo.setRuleId(expectedRuleId);

        // When
        Long actualRuleId = basicbasedeviceruledetailinfo.getRuleId();

        // Then
        assertEquals(expectedRuleId, actualRuleId);
    }

    @Test
    void testGetList() {
        // Given
        BasicBaseDeviceRuleDetailVO detailVO = new BasicBaseDeviceRuleDetailVO();
        List<BasicBaseDeviceRuleDetailVO> expectedList = Collections.singletonList(detailVO);
        basicbasedeviceruledetailinfo.setList(expectedList);

        // When
        List<BasicBaseDeviceRuleDetailVO> actualList = basicbasedeviceruledetailinfo.getList();

        // Then
        assertEquals(expectedList, actualList);
    }

    @Test
    void testIsRecordLog() {
        // Given
        Boolean expectedIsRecordLog = true;
        basicbasedeviceruledetailinfo.setIsRecordLog(expectedIsRecordLog);

        // When
        Boolean actualIsRecordLog = basicbasedeviceruledetailinfo.getIsRecordLog();

        // Then
        assertEquals(expectedIsRecordLog, actualIsRecordLog);
    }

    @Test
    void testSetRuleId() {
        // Given
        Long expectedRuleId = 456L;

        // When
        basicbasedeviceruledetailinfo.setRuleId(expectedRuleId);

        // Then
        assertEquals(expectedRuleId, basicbasedeviceruledetailinfo.getRuleId());
    }

    @Test
    void testSetList() {
        // Given
        BasicBaseDeviceRuleDetailVO detailVO = new BasicBaseDeviceRuleDetailVO();
        List<BasicBaseDeviceRuleDetailVO> expectedList = Collections.singletonList(detailVO);

        // When
        basicbasedeviceruledetailinfo.setList(expectedList);

        // Then
        assertEquals(expectedList, basicbasedeviceruledetailinfo.getList());
    }

    @Test
    void testSetIsRecordLog() {
        // Given
        Boolean expectedIsRecordLog = false;

        // When
        basicbasedeviceruledetailinfo.setIsRecordLog(expectedIsRecordLog);

        // Then
        assertEquals(expectedIsRecordLog, basicbasedeviceruledetailinfo.getIsRecordLog());
    }
}
