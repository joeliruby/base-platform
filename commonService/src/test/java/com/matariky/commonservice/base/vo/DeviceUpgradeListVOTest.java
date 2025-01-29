package com.matariky.commonservice.base.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeviceUpgradeListVOTest {

    @InjectMocks
    private DeviceUpgradeListVO deviceUpgradeListVO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetIdList() {
        // Given
        deviceUpgradeListVO.setIdList(Arrays.asList(1L, 2L, 3L));

        // When
        List<Long> idList = deviceUpgradeListVO.getIdList();

        // Then
        assertNotNull(idList);
        assertEquals(3, idList.size());
        assertTrue(idList.contains(1L));
        assertTrue(idList.contains(2L));
        assertTrue(idList.contains(3L));
    }

    @Test
    void testSetIdList() {
        // Given
        List<Long> newIdList = Arrays.asList(4L, 5L, 6L);

        // When
        deviceUpgradeListVO.setIdList(newIdList);

        // Then
        assertNotNull(deviceUpgradeListVO.getIdList());
        assertEquals(3, deviceUpgradeListVO.getIdList().size());
        assertTrue(deviceUpgradeListVO.getIdList().contains(4L));
        assertTrue(deviceUpgradeListVO.getIdList().contains(5L));
        assertTrue(deviceUpgradeListVO.getIdList().contains(6L));
    }

    // Add more test methods for other methods in DeviceUpgradeListVO if needed
}
