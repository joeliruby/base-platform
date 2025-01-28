package com.matariky.orderservice.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.orderservice.bean.OrderInfo;
import org.mockito.Mock;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class OrderInfoMapperTest {

    @InjectMocks
    private OrderInfoMapper orderinfomapper;

    @Mock
    private OrderInfoMapper mockOrderInfoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrderInfoAll() {
        OrderInfo order1 = new OrderInfo();
        OrderInfo order2 = new OrderInfo();
        List<OrderInfo> orders = Arrays.asList(order1, order2);

        when(mockOrderInfoMapper.getOrderInfoAll()).thenReturn(orders);

        List<OrderInfo> result = orderinfomapper.getOrderInfoAll();
        assertEquals(2, result.size());
        verify(mockOrderInfoMapper, times(1)).getOrderInfoAll();
    }

    @Test
    void testCreateOrderInfo() {
        OrderInfo order = new OrderInfo();
        when(mockOrderInfoMapper.createOrderInfo(order)).thenReturn(1);

        int result = orderinfomapper.createOrderInfo(order);
        assertEquals(1, result);
        verify(mockOrderInfoMapper, times(1)).createOrderInfo(order);
    }

    @Test
    void testUpdateOrderInfo() {
        OrderInfo order = new OrderInfo();
        when(mockOrderInfoMapper.updateOrderInfo(order)).thenReturn(1);

        int result = orderinfomapper.updateOrderInfo(order);
        assertEquals(1, result);
        verify(mockOrderInfoMapper, times(1)).updateOrderInfo(order);
    }

    @Test
    void testDelOrderInfoById() {
        Long id = 1L;
        when(mockOrderInfoMapper.delOrderInfoById(id)).thenReturn(1);

        int result = orderinfomapper.delOrderInfoById(id);
        assertEquals(1, result);
        verify(mockOrderInfoMapper, times(1)).delOrderInfoById(id);
    }

    @Test
    void testGetOrderInfoById() {
        int id = 1;
        OrderInfo order = new OrderInfo();
        when(mockOrderInfoMapper.getOrderInfoById(id)).thenReturn(order);

        OrderInfo result = orderinfomapper.getOrderInfoById(id);
        assertNotNull(result);
        verify(mockOrderInfoMapper, times(1)).getOrderInfoById(id);
    }

    // Add more test methods for other methods in OrderInfoMapper
}
