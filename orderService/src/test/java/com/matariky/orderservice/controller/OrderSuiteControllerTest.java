package com.matariky.orderservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.orderservice.bean.OrderSuite;
import com.matariky.orderservice.param.QueryTapeOrderSuiteParam;
import com.matariky.orderservice.service.OrderSuiteService;
import com.matariky.orderservice.vo.OrderSuiteAddVo;
import com.matariky.orderservice.vo.OrderSuiteEditVo;
import com.matariky.utils.AjaxResult;
import com.matariky.utils.TokenUtils;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@SpringBootTest
public class OrderSuiteControllerTest {

    @InjectMocks
    private OrderSuiteController ordersuitecontroller;

    @Mock
    private OrderSuiteService orderSuiteService;

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
        when(orderSuiteService.getOrderSuiteAll()).thenReturn(Collections.emptyList());

        AjaxResult result = ordersuitecontroller.list(request, new OrderSuite(), "tenantId", 1, 10, "jwt");

        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertNotNull(result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testDaclist() {
        QueryTapeOrderSuiteParam params = new QueryTapeOrderSuiteParam();
        when(orderSuiteService.getOrderSuiteDAC(params)).thenReturn(Collections.emptyList());
        when(orderSuiteService.getOrderSuiteDACCount(params)).thenReturn(0L);

        AjaxResult result = ordersuitecontroller.daclist(params);

        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertNotNull(result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testSave() {
        OrderSuiteAddVo bean = new OrderSuiteAddVo();
        when(TokenUtils.extractUserIdFromToken("jwt")).thenReturn("1");

        AjaxResult result = ordersuitecontroller.save(bean, "tenantId", "jwt", request, response);

        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(result.get(AjaxResult.MSG_TAG)));
        verify(orderSuiteService, times(1)).saveOrderSuite(bean, request);
    }

    @Test
    void testUpdate() {
        OrderSuiteEditVo bean = new OrderSuiteEditVo();
        when(TokenUtils.extractUserIdFromToken("jwt")).thenReturn("1");

        AjaxResult result = ordersuitecontroller.update(bean, "tenantId", "jwt", request, response);

        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        verify(orderSuiteService, times(1)).updateOrderSuite(bean, request);
    }

    @Test
    void testDel() {
        AjaxResult result = ordersuitecontroller.del("1", request, response);

        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        verify(orderSuiteService, times(1)).deleteById(1L);
    }

    @Test
    void testGetOne() {
        OrderSuite orderSuite = new OrderSuite();
        when(orderSuiteService.selectById(1L)).thenReturn(orderSuite);

        AjaxResult result = ordersuitecontroller.getOne(1L, request, response);

        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(orderSuite, result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testGetList() {
        when(orderSuiteService.getOrderSuiteList("tenantId", null)).thenReturn(Collections.emptyList());

        AjaxResult result = ordersuitecontroller.getList(request, Collections.emptyMap(), "tenantId", "jwt");

        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertNotNull(result.get(AjaxResult.DATA_TAG));
    }

    @Test
    void testUpdateOrderSuiteStatus() {
        OrderSuiteEditVo bean = new OrderSuiteEditVo();
        bean.setId(1L);
        bean.setSuiteStatus("status");
        when(TokenUtils.extractUserIdFromToken("jwt")).thenReturn("1");

        AjaxResult result = ordersuitecontroller.updateOrderSuiteStatus(bean, "tenantId", "jwt", request, response);

        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        verify(orderSuiteService, times(1)).updateOrderSuiteStatus(1L, "status");
    }
}
