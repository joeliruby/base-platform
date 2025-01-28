package com.matariky.bizservice.assetitm.base.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidPrint;
import com.matariky.bizservice.assetitm.base.mapper.BasicBaseRfidPrintMapper;
import com.matariky.bizservice.assetitm.base.mapper.BasicBaseRfidprintDetailMapper;
import com.matariky.bizservice.assetitm.base.mapper.BasicBaseRfidprintParameterMapper;
import com.matariky.bizservice.job.JobApiService;
import com.matariky.commonservice.base.bean.BasicBaseDevice;
import com.matariky.commonservice.base.mapper.BasicBaseDeviceMapper;
import com.matariky.commonservice.commondict.service.CommonDictService;
import com.matariky.commonservice.commondict.service.CommonDictTypeService;

@SpringBootTest
public class BasicBaseRfidprintServiceTest {

    @InjectMocks
    private BasicBaseRfidprintService basicBaseRfidprintService;

    @Mock
    private BasicBaseRfidPrintMapper basicBaseRfidprintMapper;

    @Mock
    private BasicBaseRfidprintParameterMapper basicBaseRfidprintParameterMapper;

    @Mock
    private CommonDictService commonDictService;

    @Mock
    private CommonDictTypeService commonDictTypeService;

    @Mock
    private BasicBaseRfidprintDetailMapper basicBaseRfidprintDetailMapper;

    @Mock
    private JobApiService jobApiService;

    @Mock
    private BasicBaseDeviceMapper basicBaseDeviceMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseRfidprintAll() {
        // Given
        BasicBaseRfidPrint bean = new BasicBaseRfidPrint();
        String tenantId = "tenantId";
        HttpServletRequest request = mock(HttpServletRequest.class);
        Integer pageIndex = 1;
        Integer perPage = 10;
        List<BasicBaseRfidPrint> expectedList = new ArrayList<>();
        when(basicBaseRfidprintMapper.getBasicBaseRfidprintAll(bean)).thenReturn(expectedList);

        // When
        List<BasicBaseRfidPrint> result = basicBaseRfidprintService.getBasicBaseRfidprintAll(bean, tenantId, request,
                pageIndex, perPage);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicBaseRfidprint() {
        // Given
        BasicBaseRfidPrint bean = new BasicBaseRfidPrint();
        when(basicBaseRfidprintMapper.createBasicBaseRfidprint(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidprintService.createBasicBaseRfidprint(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseRfidprint() {
        // Given
        BasicBaseRfidPrint bean = new BasicBaseRfidPrint();
        when(basicBaseRfidprintMapper.updateById(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidprintService.updateBasicBaseRfidprint(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseRfidprintById() {
        // Given
        int id = 1;
        when(basicBaseRfidprintMapper.delBasicBaseRfidprintById(id)).thenReturn(1);

        // When
        int result = basicBaseRfidprintService.delBasicBaseRfidprintById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseRfidprintById() {
        // Given
        Long id = 1L;
        BasicBaseRfidPrint expected = new BasicBaseRfidPrint();
        when(basicBaseRfidprintMapper.getBasicBaseRfidprintById(id)).thenReturn(expected);

        // When
        BasicBaseRfidPrint result = basicBaseRfidprintService.getBasicBaseRfidprintById(id);

        // Then
        assertEquals(expected, result);
    }

    @Test
    void testPrintLock() {
        // Given
        Long printId = 1L;
        String deviceCode = "deviceCode";
        BasicBaseDevice basicBaseDevice = new BasicBaseDevice();
        basicBaseDevice.setId(1L);
        when(basicBaseDeviceMapper.selectPrintByCode(deviceCode)).thenReturn(basicBaseDevice);
        when(basicBaseRfidprintMapper.printLock(printId, basicBaseDevice.getId())).thenReturn(1);

        // When
        int result = basicBaseRfidprintService.printLock(printId, deviceCode);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testPrintUnlock() {
        // Given
        Long printId = 1L;
        when(basicBaseRfidprintMapper.printUnlock(printId)).thenReturn(1);

        // When
        int result = basicBaseRfidprintService.printUnlock(printId);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testSelectLock() {
        // Given
        Long printId = 1L;
        when(basicBaseRfidprintMapper.selectLock(printId)).thenReturn(1);

        // When
        int result = basicBaseRfidprintService.selectLock(printId);

        // Then
        assertEquals(1, result);
    }
}
