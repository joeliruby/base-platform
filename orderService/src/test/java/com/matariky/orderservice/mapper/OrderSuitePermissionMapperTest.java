package com.matariky.orderservice.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.orderservice.bean.OrderSuitePermission;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class OrderSuitePermissionMapperTest {

    @InjectMocks
    private OrderSuitePermissionMapper ordersuitepermissionmapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrderSuitePermissionAll() {
        // Given
        List<OrderSuitePermission> expectedList = Collections.emptyList();
        when(ordersuitepermissionmapper.getOrderSuitePermissionAll()).thenReturn(expectedList);

        // When
        List<OrderSuitePermission> result = ordersuitepermissionmapper.getOrderSuitePermissionAll();

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateOrderSuitePermission() {
        // Given
        OrderSuitePermission permission = new OrderSuitePermission();
        when(ordersuitepermissionmapper.createOrderSuitePermission(permission)).thenReturn(1);

        // When
        int result = ordersuitepermissionmapper.createOrderSuitePermission(permission);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateOrderSuitePermission() {
        // Given
        OrderSuitePermission permission = new OrderSuitePermission();
        when(ordersuitepermissionmapper.updateOrderSuitePermission(permission)).thenReturn(1);

        // When
        int result = ordersuitepermissionmapper.updateOrderSuitePermission(permission);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelOrderSuitePermissionById() {
        // Given
        int id = 1;
        when(ordersuitepermissionmapper.delOrderSuitePermissionById(id)).thenReturn(1);

        // When
        int result = ordersuitepermissionmapper.delOrderSuitePermissionById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetOrderSuitePermissionById() {
        // Given
        int id = 1;
        OrderSuitePermission expectedPermission = new OrderSuitePermission();
        when(ordersuitepermissionmapper.getOrderSuitePermissionById(id)).thenReturn(expectedPermission);

        // When
        OrderSuitePermission result = ordersuitepermissionmapper.getOrderSuitePermissionById(id);

        // Then
        assertEquals(expectedPermission, result);
    }

    // Add more test methods for other methods in OrderSuitePermissionMapper
}
