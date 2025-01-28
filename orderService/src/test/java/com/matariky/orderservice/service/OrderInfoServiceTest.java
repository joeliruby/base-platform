package com.matariky.orderservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.orderservice.bean.OrderInfo;
import com.matariky.orderservice.mapper.OrderInfoMapper;
import org.mockito.Mock;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class OrderInfoServiceTest {

    @InjectMocks
    private OrderInfoService orderInfoService;

    @Mock
    private OrderInfoMapper orderInfoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrderInfoAll() {
        // Given
        OrderInfo orderInfo = new OrderInfo();
        when(orderInfoMapper.getOrderInfoAll()).thenReturn(Collections.singletonList(orderInfo));

        // When
        List<OrderInfo> result = orderInfoService.getOrderInfoAll();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(orderInfoMapper, times(1)).getOrderInfoAll();
    }

    @Test
    void testGetOrderInfoAllCount() {
        // Given
        when(orderInfoMapper.getOrderInfoAllCount()).thenReturn(5);

        // When
        int result = orderInfoService.getOrderInfoAllCount();

        // Then
        assertEquals(5, result);
        verify(orderInfoMapper, times(1)).getOrderInfoAllCount();
    }

    @Test
    void testCreateOrderInfo() {
        // Given
        OrderInfo orderInfo = new OrderInfo();
        when(orderInfoMapper.createOrderInfo(orderInfo)).thenReturn(1);

        // When
        int result = orderInfoService.createOrderInfo(orderInfo);

        // Then
        assertEquals(1, result);
        verify(orderInfoMapper, times(1)).createOrderInfo(orderInfo);
    }

    @Test
    void testGetOrderInfoById() {
        // Given
        OrderInfo orderInfo = new OrderInfo();
        when(orderInfoMapper.getOrderInfoById(1)).thenReturn(orderInfo);

        // When
        OrderInfo result = orderInfoService.getOrderInfoById(1);

        // Then
        assertNotNull(result);
        verify(orderInfoMapper, times(1)).getOrderInfoById(1);
    }

    // Add more test methods for other methods in OrderInfoService
}
