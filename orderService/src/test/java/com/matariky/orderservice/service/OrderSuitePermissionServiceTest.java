package com.matariky.orderservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.orderservice.bean.OrderSuitePermission;
import com.matariky.orderservice.mapper.OrderSuitePermissionMapper;
import org.mockito.Mock;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class OrderSuitePermissionServiceTest {

    @InjectMocks
    private OrderSuitePermissionService ordersuitepermissionservice;

    @Mock
    private OrderSuitePermissionMapper orderSuitePermissionMapper;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrderSuitePermissionAll() {
        List<OrderSuitePermission> expectedList = Collections.singletonList(new OrderSuitePermission());
        when(orderSuitePermissionMapper.getOrderSuitePermissionAll()).thenReturn(expectedList);

        List<OrderSuitePermission> result = ordersuitepermissionservice.getOrderSuitePermissionAll();

        assertEquals(expectedList, result);
        verify(orderSuitePermissionMapper).getOrderSuitePermissionAll();
    }

    @Test
    void testGetOrderSuitePermissionAllCount() {
        int expectedCount = 10;
        when(orderSuitePermissionMapper.getOrderSuitePermissionAllCount()).thenReturn(expectedCount);

        int result = ordersuitepermissionservice.getOrderSuitePermissionAllCount();

        assertEquals(expectedCount, result);
        verify(orderSuitePermissionMapper).getOrderSuitePermissionAllCount();
    }

    @Test
    void testCreateOrderSuitePermission() {
        OrderSuitePermission permission = new OrderSuitePermission();
        when(orderSuitePermissionMapper.createOrderSuitePermission(permission)).thenReturn(1);

        int result = ordersuitepermissionservice.createOrderSuitePermission(permission);

        assertEquals(1, result);
        verify(orderSuitePermissionMapper).createOrderSuitePermission(permission);
    }

    @Test
    void testUpdateOrderSuitePermission() {
        OrderSuitePermission permission = new OrderSuitePermission();
        when(orderSuitePermissionMapper.updateById(permission)).thenReturn(1);

        int result = ordersuitepermissionservice.updateOrderSuitePermission(permission);

        assertEquals(1, result);
        verify(orderSuitePermissionMapper).updateById(permission);
    }

    @Test
    void testDelOrderSuitePermissionById() {
        int id = 1;
        when(orderSuitePermissionMapper.delOrderSuitePermissionById(id)).thenReturn(1);

        int result = ordersuitepermissionservice.delOrderSuitePermissionById(id);

        assertEquals(1, result);
        verify(orderSuitePermissionMapper).delOrderSuitePermissionById(id);
    }

    @Test
    void testGetOrderSuitePermissionById() {
        int id = 1;
        OrderSuitePermission expectedPermission = new OrderSuitePermission();
        when(orderSuitePermissionMapper.getOrderSuitePermissionById(id)).thenReturn(expectedPermission);

        OrderSuitePermission result = ordersuitepermissionservice.getOrderSuitePermissionById(id);

        assertEquals(expectedPermission, result);
        verify(orderSuitePermissionMapper).getOrderSuitePermissionById(id);
    }

    @Test
    void testGetOrderSuitePermissions() {
        OrderSuitePermission permission = new OrderSuitePermission();
        List<OrderSuitePermission> expectedList = Collections.singletonList(new OrderSuitePermission());
        when(orderSuitePermissionMapper.selectList(any())).thenReturn(expectedList);

        List<OrderSuitePermission> result = ordersuitepermissionservice.getOrderSuitePermissions(permission);

        assertEquals(expectedList, result);
        verify(orderSuitePermissionMapper).selectList(any());
    }
}
