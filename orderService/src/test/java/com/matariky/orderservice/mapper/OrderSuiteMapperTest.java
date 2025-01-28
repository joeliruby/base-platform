package com.matariky.orderservice.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.orderservice.bean.OrderSuite;
import org.mockito.Mock;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class OrderSuiteMapperTest {

    @InjectMocks
    private OrderSuiteMapper ordersuitemapper;

    @Mock
    private OrderSuiteMapper mockOrderSuiteMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrderSuiteAll() {
        List<OrderSuite> mockList = Arrays.asList(new OrderSuite(), new OrderSuite());
        when(mockOrderSuiteMapper.getOrderSuiteAll()).thenReturn(mockList);

        List<OrderSuite> result = ordersuitemapper.getOrderSuiteAll();
        assertEquals(2, result.size());
    }

    @Test
    void testCreateOrderSuite() {
        OrderSuite orderSuite = new OrderSuite();
        when(mockOrderSuiteMapper.createOrderSuite(orderSuite)).thenReturn(1);

        int result = ordersuitemapper.createOrderSuite(orderSuite);
        assertEquals(1, result);
    }

    @Test
    void testUpdateOrderSuite() {
        OrderSuite orderSuite = new OrderSuite();
        when(mockOrderSuiteMapper.updateOrderSuite(orderSuite)).thenReturn(1);

        int result = ordersuitemapper.updateOrderSuite(orderSuite);
        assertEquals(1, result);
    }

    @Test
    void testDelOrderSuiteById() {
        when(mockOrderSuiteMapper.delOrderSuiteById(1)).thenReturn(1);

        int result = ordersuitemapper.delOrderSuiteById(1);
        assertEquals(1, result);
    }

    @Test
    void testGetOrderSuiteById() {
        OrderSuite orderSuite = new OrderSuite();
        when(mockOrderSuiteMapper.getOrderSuiteById(1L)).thenReturn(orderSuite);

        OrderSuite result = ordersuitemapper.getOrderSuiteById(1L);
        assertNotNull(result);
    }

    // Add more test methods for other methods in OrderSuiteMapper
}
