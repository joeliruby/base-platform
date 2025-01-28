package com.matariky.orderservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.orderservice.bean.OrderSuite;
import com.matariky.orderservice.mapper.OrderSuiteMapper;
import org.mockito.Mock;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class OrderSuiteServiceTest {

    @InjectMocks
    private OrderSuiteService orderSuiteService;

    @Mock
    private OrderSuiteMapper orderSuiteMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrderSuiteAll() {
        OrderSuite orderSuite1 = new OrderSuite();
        OrderSuite orderSuite2 = new OrderSuite();
        List<OrderSuite> orderSuites = Arrays.asList(orderSuite1, orderSuite2);

        when(orderSuiteMapper.getOrderSuiteAll()).thenReturn(orderSuites);

        List<OrderSuite> result = orderSuiteService.getOrderSuiteAll();
        assertEquals(2, result.size());
        verify(orderSuiteMapper, times(1)).getOrderSuiteAll();
    }

    @Test
    void testGetOrderSuiteAllCount() {
        when(orderSuiteMapper.getOrderSuiteAllCount()).thenReturn(5);

        int result = orderSuiteService.getOrderSuiteAllCount();
        assertEquals(5, result);
        verify(orderSuiteMapper, times(1)).getOrderSuiteAllCount();
    }

    @Test
    void testCreateOrderSuite() {
        OrderSuite orderSuite = new OrderSuite();
        when(orderSuiteMapper.createOrderSuite(orderSuite)).thenReturn(1);

        int result = orderSuiteService.createOrderSuite(orderSuite);
        assertEquals(1, result);
        verify(orderSuiteMapper, times(1)).createOrderSuite(orderSuite);
    }

    @Test
    void testGetOrderSuiteById() {
        OrderSuite orderSuite = new OrderSuite();
        when(orderSuiteMapper.getOrderSuiteById(1L)).thenReturn(orderSuite);

        OrderSuite result = orderSuiteService.getOrderSuiteById(1L);
        assertEquals(orderSuite, result);
        verify(orderSuiteMapper, times(1)).getOrderSuiteById(1L);
    }

    @Test
    void testDelOrderSuiteById() {
        when(orderSuiteMapper.delOrderSuiteById(1)).thenReturn(1);

        int result = orderSuiteService.delOrderSuiteById(1);
        assertEquals(1, result);
        verify(orderSuiteMapper, times(1)).delOrderSuiteById(1);
    }

    // Add more test methods for other methods in OrderSuiteService
}
