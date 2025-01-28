package com.matariky.bizservice.assetitm.base.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidPrint;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidprintDetail;
import com.matariky.bizservice.assetitm.base.mapper.BasicBaseRfidPrintMapper;
import com.matariky.bizservice.assetitm.base.mapper.BasicBaseRfidprintDetailMapper;
import com.matariky.exception.QslException;

@SpringBootTest
public class BasicBaseRfidprintDetailServiceTest {

    @InjectMocks
    private BasicBaseRfidprintDetailService basicBaseRfidprintDetailService;

    @Mock
    private BasicBaseRfidprintDetailMapper basicBaseRfidprintDetailMapper;

    @Mock
    private BasicBaseRfidPrintMapper basicBaseRfidprintMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseRfidprintDetailAll() {
        // Given
        BasicBaseRfidprintDetail bean = new BasicBaseRfidprintDetail();
        List<BasicBaseRfidprintDetail> expectedList = new ArrayList<>();
        when(basicBaseRfidprintDetailMapper.getBasicBaseRfidprintDetailAll(bean)).thenReturn(expectedList);

        // When
        List<BasicBaseRfidprintDetail> result = basicBaseRfidprintDetailService.getBasicBaseRfidprintDetailAll(bean, 1,
                10);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicBaseRfidprintDetail() {
        // Given
        BasicBaseRfidprintDetail bean = new BasicBaseRfidprintDetail();
        when(basicBaseRfidprintDetailMapper.createBasicBaseRfidprintDetail(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidprintDetailService.createBasicBaseRfidprintDetail(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseRfidprintDetail() {
        // Given
        BasicBaseRfidprintDetail bean = new BasicBaseRfidprintDetail();
        bean.setTid("1L");
        bean.setPrintId(1L);
        when(basicBaseRfidprintDetailMapper.updateBasicBaseRfidprintDetail(bean)).thenReturn(1);
        when(basicBaseRfidprintMapper.getBasicBaseRfidprintById(bean.getPrintId()))
                .thenReturn(new BasicBaseRfidPrint());

        // When
        int result = basicBaseRfidprintDetailService.updateBasicBaseRfidprintDetail(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseRfidprintDetailThrowsException() {
        // Given
        BasicBaseRfidprintDetail bean = new BasicBaseRfidprintDetail();

        // When & Then
        assertThrows(QslException.class, () -> basicBaseRfidprintDetailService.updateBasicBaseRfidprintDetail(bean));
    }

    @Test
    void testDelBasicBaseRfidprintDetailById() {
        // Given
        int id = 1;
        when(basicBaseRfidprintDetailMapper.delBasicBaseRfidprintDetailById(id)).thenReturn(1);

        // When
        int result = basicBaseRfidprintDetailService.delBasicBaseRfidprintDetailById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseRfidprintDetailById() {
        // Given
        Long id = 1L;
        BasicBaseRfidprintDetail expectedDetail = new BasicBaseRfidprintDetail();
        when(basicBaseRfidprintDetailMapper.getBasicBaseRfidprintDetailById(id)).thenReturn(expectedDetail);

        // When
        BasicBaseRfidprintDetail result = basicBaseRfidprintDetailService.getBasicBaseRfidprintDetailById(id);

        // Then
        assertEquals(expectedDetail, result);
    }

    // Add more test methods for other methods in BasicBaseRfidprintDetailService
}
