package com.matariky.orderservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.orderservice.bean.OrderSuiteConfig;
import com.matariky.orderservice.mapper.OrderSuiteConfigMapper;
import org.mockito.Mock;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class OrderSuiteConfigServiceTest {

    @InjectMocks
    private OrderSuiteConfigService ordersuiteconfigservice;

    @Mock
    private OrderSuiteConfigMapper orderSuiteConfigMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrderSuiteConfigAll() {
        // Given
        OrderSuiteConfig config = new OrderSuiteConfig();
        when(orderSuiteConfigMapper.getOrderSuiteConfigAll()).thenReturn(Collections.singletonList(config));

        // When
        List<OrderSuiteConfig> result = ordersuiteconfigservice.getOrderSuiteConfigAll();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(orderSuiteConfigMapper, times(1)).getOrderSuiteConfigAll();
    }

    @Test
    void testCreateOrderSuiteConfig() {
        // Given
        OrderSuiteConfig config = new OrderSuiteConfig();
        when(orderSuiteConfigMapper.createOrderSuiteConfig(config)).thenReturn(1);

        // When
        int result = ordersuiteconfigservice.createOrderSuiteConfig(config);

        // Then
        assertEquals(1, result);
        verify(orderSuiteConfigMapper, times(1)).createOrderSuiteConfig(config);
    }

    @Test
    void testUpdateOrderSuiteConfig() {
        // Given
        OrderSuiteConfig config = new OrderSuiteConfig();
        when(orderSuiteConfigMapper.updateById(config)).thenReturn(1);

        // When
        int result = ordersuiteconfigservice.updateOrderSuiteConfig(config);

        // Then
        assertEquals(1, result);
        verify(orderSuiteConfigMapper, times(1)).updateById(config);
    }

    @Test
    void testDelOrderSuiteConfigById() {
        // Given
        int id = 1;
        when(orderSuiteConfigMapper.delOrderSuiteConfigById(id)).thenReturn(1);

        // When
        int result = ordersuiteconfigservice.delOrderSuiteConfigById(id);

        // Then
        assertEquals(1, result);
        verify(orderSuiteConfigMapper, times(1)).delOrderSuiteConfigById(id);
    }

    @Test
    void testGetOrderSuiteConfigById() {
        // Given
        int id = 1;
        OrderSuiteConfig config = new OrderSuiteConfig();
        when(orderSuiteConfigMapper.getOrderSuiteConfigById(id)).thenReturn(config);

        // When
        OrderSuiteConfig result = ordersuiteconfigservice.getOrderSuiteConfigById(id);

        // Then
        assertNotNull(result);
        verify(orderSuiteConfigMapper, times(1)).getOrderSuiteConfigById(id);
    }
}
