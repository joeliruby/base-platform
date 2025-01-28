package com.matariky.orderservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.orderservice.bean.OrderInfoRecord;
import com.matariky.orderservice.service.OrderInfoRecordService;
import com.matariky.utils.AjaxResult;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@SpringBootTest
public class OrderInfoRecordControllerTest {

    @InjectMocks
    private OrderInfoRecordController orderinforecordcontroller;

    @Mock
    private OrderInfoRecordService orderInfoRecordService;

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
        when(orderInfoRecordService.getOrderInfoRecordAll()).thenReturn(Collections.emptyList());
        AjaxResult result = orderinforecordcontroller.list(new OrderInfoRecord(), 1, 10, "jwtToken");
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testGetList() {
        when(orderInfoRecordService.getOrderInfoRecordDAC(anyMap(), eq(request))).thenReturn(Collections.emptyList());
        AjaxResult result = orderinforecordcontroller.getList(request, Collections.emptyMap(), "tenantId", "jwtToken");
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testSave() {
        doNothing().when(orderInfoRecordService).createOrderInfoRecordWithOrg(any(OrderInfoRecord.class), eq(request));
        AjaxResult result = orderinforecordcontroller.save(new OrderInfoRecord(), request, response);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testUpdate() {
        doNothing().when(orderInfoRecordService).updateOrderInfoRecord(any(OrderInfoRecord.class));
        AjaxResult result = orderinforecordcontroller.update(new OrderInfoRecord(), request, response);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testDelete() {
        doNothing().when(orderInfoRecordService).deleteById(anyLong());
        AjaxResult result = orderinforecordcontroller.del("1", request, response);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
    }

    @Test
    void testGetOne() {
        OrderInfoRecord orderInfoRecord = new OrderInfoRecord();
        when(orderInfoRecordService.selectById(anyLong())).thenReturn(orderInfoRecord);
        AjaxResult result = orderinforecordcontroller.getOne(1L, request, response);
        assertEquals(HttpStatus.OK.value(), result.get(AjaxResult.CODE_TAG));
        assertEquals(AjaxResult.SUCCESS, result.get(AjaxResult.MSG_TAG));
        assertEquals(orderInfoRecord, result.get(AjaxResult.DATA_TAG));
    }
}
