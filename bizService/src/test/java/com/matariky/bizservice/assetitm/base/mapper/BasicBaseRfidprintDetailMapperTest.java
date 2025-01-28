package com.matariky.bizservice.assetitm.base.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidprintDetail;

@SpringBootTest
public class BasicBaseRfidprintDetailMapperTest {

    @InjectMocks
    private BasicBaseRfidprintDetailMapper basicBaseRfidprintDetailMapper;

    @Mock
    private BasicBaseRfidprintDetailMapper mockMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseRfidprintDetailAll() {
        // Given
        BasicBaseRfidprintDetail detail = new BasicBaseRfidprintDetail();
        when(mockMapper.getBasicBaseRfidprintDetailAll(any())).thenReturn(Collections.singletonList(detail));

        // When
        List<BasicBaseRfidprintDetail> result = basicBaseRfidprintDetailMapper.getBasicBaseRfidprintDetailAll(detail);

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(detail, result.get(0));
    }

    @Test
    void testCreateBasicBaseRfidprintDetail() {
        // Given
        BasicBaseRfidprintDetail detail = new BasicBaseRfidprintDetail();
        when(mockMapper.createBasicBaseRfidprintDetail(any())).thenReturn(1);

        // When
        int result = basicBaseRfidprintDetailMapper.createBasicBaseRfidprintDetail(detail);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateBasicBaseRfidprintDetail() {
        // Given
        BasicBaseRfidprintDetail detail = new BasicBaseRfidprintDetail();
        when(mockMapper.updateBasicBaseRfidprintDetail(any())).thenReturn(1);

        // When
        int result = basicBaseRfidprintDetailMapper.updateBasicBaseRfidprintDetail(detail);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelBasicBaseRfidprintDetailById() {
        // Given
        int id = 1;
        when(mockMapper.delBasicBaseRfidprintDetailById(id)).thenReturn(1);

        // When
        int result = basicBaseRfidprintDetailMapper.delBasicBaseRfidprintDetailById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseRfidprintDetailById() {
        // Given
        Long id = 1L;
        BasicBaseRfidprintDetail detail = new BasicBaseRfidprintDetail();
        when(mockMapper.getBasicBaseRfidprintDetailById(id)).thenReturn(detail);

        // When
        BasicBaseRfidprintDetail result = basicBaseRfidprintDetailMapper.getBasicBaseRfidprintDetailById(id);

        // Then
        assertNotNull(result);
        assertEquals(detail, result);
    }

    // Add more test methods for other methods in BasicBaseRfidprintDetailMapper
}
