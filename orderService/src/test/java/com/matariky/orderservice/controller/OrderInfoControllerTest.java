package com.matariky.orderservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.orderservice.bean.OrderInfo;
import com.matariky.orderservice.service.OrderInfoService;
import com.matariky.orderservice.vo.OrderInfoAddVo;
import com.matariky.orderservice.vo.OrderInfoEditVo;
import com.matariky.utils.AjaxResult;

@SpringBootTest
public class OrderInfoControllerTest {

    @InjectMocks
    private OrderInfoController orderInfoController;

    @Mock
    private OrderInfoService orderInfoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Arrange
        when(orderInfoService.getOrderInfoAll()).thenReturn(new ArrayList<>());

        // Act
        AjaxResult result = orderInfoController.list(null, null, "1", 1, 10, "jwt");

        // Assert
        assertEquals(200, result.get(AjaxResult.CODE_TAG));
        assertEquals("success", result.get(AjaxResult.MSG_TAG));
        verify(orderInfoService, times(1)).getOrderInfoAll();
    }

    @Test
    void testSave() {
        // Arrange
        OrderInfoAddVo orderInfoAddVo = new OrderInfoAddVo();

        // Act
        AjaxResult result = orderInfoController.save(orderInfoAddVo, "1", "jwt", null, null);

        // Assert
        assertEquals(200, result.get(AjaxResult.CODE_TAG));
        assertEquals("success", result.get(AjaxResult.MSG_TAG));
        verify(orderInfoService, times(1)).createOrderInfoWithOrg(eq(orderInfoAddVo), any(), eq("1"), eq("jwt"));
    }

    @Test
    void testUpdate() {
        // Arrange
        OrderInfoEditVo orderInfoEditVo = new OrderInfoEditVo();

        // Act
        AjaxResult result = orderInfoController.update(orderInfoEditVo, "1", "jwt", null, null);

        // Assert
        assertEquals(200, result.get(AjaxResult.CODE_TAG));
        assertEquals("success", result.get(AjaxResult.MSG_TAG));
        verify(orderInfoService, times(1)).updateOrderInfo(eq(orderInfoEditVo), any(), eq("1"), eq("jwt"));
    }

    @Test
    void testDelete() {
        // Act
        AjaxResult result = orderInfoController.del("1", null, null);

        // Assert
        assertEquals(200, result.get(AjaxResult.CODE_TAG));
        assertEquals("success", result.get(AjaxResult.MSG_TAG));
        verify(orderInfoService, times(1)).delOrderInfoById(1L);
    }

    @Test
    void testGetOne() {
        // Arrange
        OrderInfo orderInfo = new OrderInfo();
        when(orderInfoService.selectById(1L)).thenReturn(orderInfo);

        // Act
        AjaxResult result = orderInfoController.getOne(1L, null, null);

        // Assert
        assertEquals(200, result.get(AjaxResult.CODE_TAG));
        assertEquals("success", result.get(AjaxResult.MSG_TAG));
        assertEquals(orderInfo, result.get(AjaxResult.DATA_TAG));
        verify(orderInfoService, times(1)).selectById(1L);
    }
}
