package com.matariky.orderservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.matariky.orderservice.bean.OrderSuiteConfig;
import com.matariky.orderservice.service.OrderSuiteConfigService;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class OrderSuiteConfigControllerTest {

    @InjectMocks
    private OrderSuiteConfigController ordersuiteconfigcontroller;

    @Mock
    private OrderSuiteConfigService orderSuiteConfigService;

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
        when(orderSuiteConfigService.getOrderSuiteConfigAll()).thenReturn(Collections.emptyList());
        AjaxResult result = ordersuiteconfigcontroller.list(request, new OrderSuiteConfig(), "tenantId", 1, 10, "jwt");
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testSave() {
        OrderSuiteConfig config = new OrderSuiteConfig();
        doNothing().when(orderSuiteConfigService).createOrderSuiteConfigWithOrg(config, request);
        AjaxResult result = ordersuiteconfigcontroller.save(config, request, response);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testUpdate() {
        OrderSuiteConfig config = new OrderSuiteConfig();
        doNothing().when(orderSuiteConfigService).updateOrderSuiteConfig(config);
        AjaxResult result = ordersuiteconfigcontroller.update(config, request, response);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testDelete() {
        doNothing().when(orderSuiteConfigService).deleteById(1L);
        AjaxResult result = ordersuiteconfigcontroller.del("1", request, response);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testGetOne() {
        OrderSuiteConfig config = new OrderSuiteConfig();
        when(orderSuiteConfigService.selectById(1L)).thenReturn(config);
        AjaxResult result = ordersuiteconfigcontroller.getOne(1L, request, response);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(config, result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testGetList() {
        when(orderSuiteConfigService.getOrderSuiteConfigs(any(OrderSuiteConfig.class)))
                .thenReturn(Collections.emptyList());
        AjaxResult result = ordersuiteconfigcontroller.getList(request, Map.of("suiteCode", "code"), "tenantId", "jwt");
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testGetPriceConfig() {
        when(orderSuiteConfigService.getOrderSuiteConfigByParams(anyMap())).thenReturn("result");
        AjaxResult result = ordersuiteconfigcontroller.getPriceConfig(request, Map.of(), "tenantId", "jwt");
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals("result", result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testGetOrderSuiteConfigByCode() {
        OrderSuiteConfig config = new OrderSuiteConfig();
        when(orderSuiteConfigService.getOrderSuiteConfigByCode(anyMap())).thenReturn(config);
        AjaxResult result = ordersuiteconfigcontroller.getOrderSuiteConfigByCode(request, Map.of(), "tenantId", "jwt");
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(config, result.get(AjaxResult.DATA_TAG));
    }
}
