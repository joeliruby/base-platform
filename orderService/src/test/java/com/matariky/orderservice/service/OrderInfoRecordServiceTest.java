package com.matariky.orderservice.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import com.matariky.orderservice.bean.OrderInfoRecord;
import com.matariky.orderservice.mapper.OrderInfoRecordMapper;
import com.matariky.utils.TokenUtils;
import org.mockito.Mock;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class OrderInfoRecordServiceTest {

    @InjectMocks
    private OrderInfoRecordService orderInfoRecordService;

    @Mock
    private OrderInfoRecordMapper orderInfoRecordMapper;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrderInfoRecordAll() {
        // Given
        List<OrderInfoRecord> expectedRecords = Collections.singletonList(new OrderInfoRecord());
        when(orderInfoRecordMapper.getOrderInfoRecordAll()).thenReturn(expectedRecords);

        // When
        List<OrderInfoRecord> actualRecords = orderInfoRecordService.getOrderInfoRecordAll();

        // Then
        assertThat(actualRecords).isEqualTo(expectedRecords);
    }

    @Test
    void testGetOrderInfoRecordAllCount() {
        // Given
        int expectedCount = 10;
        when(orderInfoRecordMapper.getOrderInfoRecordAllCount()).thenReturn(expectedCount);

        // When
        int actualCount = orderInfoRecordService.getOrderInfoRecordAllCount();

        // Then
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    void testCreateOrderInfoRecord() {
        // Given
        OrderInfoRecord record = new OrderInfoRecord();
        when(orderInfoRecordMapper.createOrderInfoRecord(record)).thenReturn(1);

        // When
        int result = orderInfoRecordService.createOrderInfoRecord(record);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testCreateOrderInfoRecordWithOrg() {
        // Given
        OrderInfoRecord record = new OrderInfoRecord();
        when(TokenUtils.extractOrgCode(request)).thenReturn("orgCode");
        when(TokenUtils.extractSelfOrgCode(request)).thenReturn("selfOrgCode");
        when(orderInfoRecordMapper.createOrderInfoRecord(record)).thenReturn(1);

        // When
        int result = orderInfoRecordService.createOrderInfoRecordWithOrg(record, request);

        // Then
        assertThat(result).isEqualTo(1);
        assertThat(record.getOperatorOrgCode()).isEqualTo("orgCode");
        assertThat(record.getOperatorSelfOrgCode()).isEqualTo("selfOrgCode");
    }

    @Test
    void testUpdateOrderInfoRecord() {
        // Given
        OrderInfoRecord record = new OrderInfoRecord();
        when(orderInfoRecordMapper.updateById(record)).thenReturn(1);

        // When
        int result = orderInfoRecordService.updateOrderInfoRecord(record);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testDelOrderInfoRecordById() {
        // Given
        int id = 1;
        when(orderInfoRecordMapper.delOrderInfoRecordById(id)).thenReturn(1);

        // When
        int result = orderInfoRecordService.delOrderInfoRecordById(id);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testGetOrderInfoRecordById() {
        // Given
        int id = 1;
        OrderInfoRecord expectedRecord = new OrderInfoRecord();
        when(orderInfoRecordMapper.getOrderInfoRecordById(id)).thenReturn(expectedRecord);

        // When
        OrderInfoRecord actualRecord = orderInfoRecordService.getOrderInfoRecordById(id);

        // Then
        assertThat(actualRecord).isEqualTo(expectedRecord);
    }

    @Test
    void testGetOrderInfoRecordDAC() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        List<OrderInfoRecord> expectedRecords = Collections.singletonList(new OrderInfoRecord());
        when(orderInfoRecordMapper.getOrderInfoRecordDAC(params)).thenReturn(expectedRecords);

        // When
        List<OrderInfoRecord> actualRecords = orderInfoRecordService.getOrderInfoRecordDAC(params, request);

        // Then
        assertThat(actualRecords).isEqualTo(expectedRecords);
    }

    @Test
    void testGetOrderInfoRecordDACCount() {
        // Given
        Map<String, Object> params = Collections.emptyMap();
        long expectedCount = 10L;
        when(orderInfoRecordMapper.getOrderInfoRecordDACCount(params)).thenReturn(expectedCount);

        // When
        long actualCount = orderInfoRecordService.getOrderInfoRecordDACCount(params, request);

        // Then
        assertThat(actualCount).isEqualTo(expectedCount);
    }
}
