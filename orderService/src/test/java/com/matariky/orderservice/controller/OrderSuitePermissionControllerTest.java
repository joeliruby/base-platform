package com.matariky.orderservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.orderservice.bean.OrderSuitePermission;
import com.matariky.orderservice.service.OrderSuitePermissionService;
import com.matariky.utils.AjaxResult;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderSuitePermissionControllerTest {

    @InjectMocks
    private OrderSuitePermissionController ordersuitepermissioncontroller;

    @Mock
    private OrderSuitePermissionService orderSuitePermissionService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        when(orderSuitePermissionService.getOrderSuitePermissionAll()).thenReturn(Collections.emptyList());

        AjaxResult result = ordersuitepermissioncontroller.list(request, new OrderSuitePermission(), "tenantId", 1, 10,
                "jwt");

        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        verify(orderSuitePermissionService, times(1)).getOrderSuitePermissionAll();
    }

    @Test
    void testSave() {
        OrderSuitePermission orderSuitePermission = new OrderSuitePermission();

        AjaxResult result = ordersuitepermissioncontroller.save(orderSuitePermission, request, response);

        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        verify(orderSuitePermissionService, times(1)).createOrderSuitePermissionWithOrg(orderSuitePermission, request);
    }

    @Test
    void testUpdate() {
        OrderSuitePermission orderSuitePermission = new OrderSuitePermission();

        AjaxResult result = ordersuitepermissioncontroller.update(orderSuitePermission, request, response);

        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        verify(orderSuitePermissionService, times(1)).updateOrderSuitePermission(orderSuitePermission);
    }

    @Test
    void testDelete() {
        String id = "1";

        AjaxResult result = ordersuitepermissioncontroller.del(id, request, response);

        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        verify(orderSuitePermissionService, times(1)).deleteById(Long.parseLong(id));
    }

    @Test
    void testGetOne() {
        Long id = 1L;
        OrderSuitePermission orderSuitePermission = new OrderSuitePermission();
        when(orderSuitePermissionService.selectById(id)).thenReturn(orderSuitePermission);

        AjaxResult result = ordersuitepermissioncontroller.getOne(id, request, response);

        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(orderSuitePermission, result.get(AjaxResult.DATA_TAG));
        verify(orderSuitePermissionService, times(1)).selectById(id);
    }

    @Test
    void testGetList() {
        Map<String, Object> params = mock(Map.class, withSettings().defaultAnswer(RETURNS_DEEP_STUBS));
        when(params.get("suiteCode")).thenReturn("suiteCode");
        when(orderSuitePermissionService.getOrderSuitePermissions(any(OrderSuitePermission.class)))
                .thenReturn(Collections.emptyList());

        AjaxResult result = ordersuitepermissioncontroller.getList(request, params, "tenantId", "jwt");

        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        verify(orderSuitePermissionService, times(1)).getOrderSuitePermissions(any(OrderSuitePermission.class));
    }
}
