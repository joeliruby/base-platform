package com.matariky.orderservice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.github.pagehelper.Page;
import com.matariky.orderservice.bean.OrderInfoRecord;

@SpringBootTest
public class OrderInfoRecordMapperTest {

    @InjectMocks
    private OrderInfoRecordMapper orderInfoRecordMapper;

    @Mock
    private OrderInfoRecordMapper mockOrderInfoRecordMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrderInfoRecordAll() {
        List<OrderInfoRecord> expectedRecords = Collections.singletonList(new OrderInfoRecord());
        when(mockOrderInfoRecordMapper.getOrderInfoRecordAll()).thenReturn(expectedRecords);

        List<OrderInfoRecord> actualRecords = orderInfoRecordMapper.getOrderInfoRecordAll();
        assertEquals(expectedRecords, actualRecords);
    }

    @Test
    void testGetOrderInfoRecordAllCount() {
        int expectedCount = 10;
        when(mockOrderInfoRecordMapper.getOrderInfoRecordAllCount()).thenReturn(expectedCount);

        int actualCount = orderInfoRecordMapper.getOrderInfoRecordAllCount();
        assertEquals(expectedCount, actualCount);
    }

    @Test
    void testCreateOrderInfoRecord() {
        OrderInfoRecord record = new OrderInfoRecord();
        when(mockOrderInfoRecordMapper.createOrderInfoRecord(record)).thenReturn(1);

        int result = orderInfoRecordMapper.createOrderInfoRecord(record);
        assertEquals(1, result);
    }

    @Test
    void testUpdateOrderInfoRecord() {
        OrderInfoRecord record = new OrderInfoRecord();
        when(mockOrderInfoRecordMapper.updateOrderInfoRecord(record)).thenReturn(1);

        int result = orderInfoRecordMapper.updateOrderInfoRecord(record);
        assertEquals(1, result);
    }

    @Test
    void testDelOrderInfoRecordById() {
        int id = 1;
        when(mockOrderInfoRecordMapper.delOrderInfoRecordById(id)).thenReturn(1);

        int result = orderInfoRecordMapper.delOrderInfoRecordById(id);
        assertEquals(1, result);
    }

    @Test
    void testGetOrderInfoRecordById() {
        int id = 1;
        OrderInfoRecord expectedRecord = new OrderInfoRecord();
        when(mockOrderInfoRecordMapper.getOrderInfoRecordById(id)).thenReturn(expectedRecord);

        OrderInfoRecord actualRecord = orderInfoRecordMapper.getOrderInfoRecordById(id);
        assertEquals(expectedRecord, actualRecord);
    }

    @Test
    void testInsert() {
        OrderInfoRecord record = new OrderInfoRecord();
        when(mockOrderInfoRecordMapper.insert(record)).thenReturn(1);

        int result = orderInfoRecordMapper.insert(record);
        assertEquals(1, result);
    }

    @Test
    void testDeleteById() {
        Serializable id = 1;
        when(mockOrderInfoRecordMapper.deleteById(id)).thenReturn(1);

        int result = orderInfoRecordMapper.deleteById(id);
        assertEquals(1, result);
    }

    @Test
    void testDeleteByMap() {
        Map<String, Object> columnMap = Collections.singletonMap("key", "value");
        when(mockOrderInfoRecordMapper.deleteByMap(columnMap)).thenReturn(1);

        int result = orderInfoRecordMapper.deleteByMap(columnMap);
        assertEquals(1, result);
    }

    @Test
    void testDelete() {
        Wrapper<OrderInfoRecord> queryWrapper = mock(Wrapper.class);
        when(mockOrderInfoRecordMapper.delete(queryWrapper)).thenReturn(1);

        int result = orderInfoRecordMapper.delete(queryWrapper);
        assertEquals(1, result);
    }

    @Test
    void testDeleteBatchIds() {
        Collection<? extends Serializable> idList = Collections.singletonList(1);
        when(mockOrderInfoRecordMapper.deleteBatchIds(idList)).thenReturn(1);

        int result = orderInfoRecordMapper.deleteBatchIds(idList);
        assertEquals(1, result);
    }

    @Test
    void testUpdateById() {
        OrderInfoRecord entity = new OrderInfoRecord();
        when(mockOrderInfoRecordMapper.updateById(entity)).thenReturn(1);

        int result = orderInfoRecordMapper.updateById(entity);
        assertEquals(1, result);
    }

    @Test
    void testUpdate() {
        OrderInfoRecord entity = new OrderInfoRecord();
        Wrapper<OrderInfoRecord> updateWrapper = mock(Wrapper.class);
        when(mockOrderInfoRecordMapper.update(entity, updateWrapper)).thenReturn(1);

        int result = orderInfoRecordMapper.update(entity, updateWrapper);
        assertEquals(1, result);
    }

    @Test
    void testSelectById() {
        Serializable id = 1;
        OrderInfoRecord expectedRecord = new OrderInfoRecord();
        when(mockOrderInfoRecordMapper.selectById(id)).thenReturn(expectedRecord);

        OrderInfoRecord actualRecord = orderInfoRecordMapper.selectById(id);
        assertEquals(expectedRecord, actualRecord);
    }

    @Test
    void testSelectBatchIds() {
        Collection<? extends Serializable> idList = Collections.singletonList(1);
        List<OrderInfoRecord> expectedRecords = Collections.singletonList(new OrderInfoRecord());
        when(mockOrderInfoRecordMapper.selectBatchIds(idList)).thenReturn(expectedRecords);

        List<OrderInfoRecord> actualRecords = orderInfoRecordMapper.selectBatchIds(idList);
        assertEquals(expectedRecords, actualRecords);
    }

    @Test
    void testSelectByMap() {
        Map<String, Object> columnMap = Collections.singletonMap("key", "value");
        List<OrderInfoRecord> expectedRecords = Collections.singletonList(new OrderInfoRecord());
        when(mockOrderInfoRecordMapper.selectByMap(columnMap)).thenReturn(expectedRecords);

        List<OrderInfoRecord> actualRecords = orderInfoRecordMapper.selectByMap(columnMap);
        assertEquals(expectedRecords, actualRecords);
    }

    @Test
    void testSelectOne() {
        Wrapper<OrderInfoRecord> queryWrapper = mock(Wrapper.class);
        OrderInfoRecord expectedRecord = new OrderInfoRecord();
        when(mockOrderInfoRecordMapper.selectOne(queryWrapper)).thenReturn(expectedRecord);

        OrderInfoRecord actualRecord = orderInfoRecordMapper.selectOne(queryWrapper);
        assertEquals(expectedRecord, actualRecord);
    }

    @Test
    void testSelectCount() {
        Wrapper<OrderInfoRecord> queryWrapper = mock(Wrapper.class);
        Long expectedCount = 10L;
        when(mockOrderInfoRecordMapper.selectCount(queryWrapper)).thenReturn(expectedCount);

        Long actualCount = orderInfoRecordMapper.selectCount(queryWrapper);
        assertEquals(expectedCount, actualCount);
    }

    @Test
    void testSelectList() {
        Wrapper<OrderInfoRecord> queryWrapper = mock(Wrapper.class);
        List<OrderInfoRecord> expectedRecords = Collections.singletonList(new OrderInfoRecord());
        when(mockOrderInfoRecordMapper.selectList(queryWrapper)).thenReturn(expectedRecords);

        List<OrderInfoRecord> actualRecords = orderInfoRecordMapper.selectList(queryWrapper);
        assertEquals(expectedRecords, actualRecords);
    }

    @Test
    void testSelectMaps() {
        Wrapper<OrderInfoRecord> queryWrapper = mock(Wrapper.class);
        List<Map<String, Object>> expectedMaps = Collections.singletonList(Collections.singletonMap("key", "value"));
        when(mockOrderInfoRecordMapper.selectMaps(queryWrapper)).thenReturn(expectedMaps);

        List<Map<String, Object>> actualMaps = orderInfoRecordMapper.selectMaps(queryWrapper);
        assertEquals(expectedMaps, actualMaps);
    }

    @Test
    void testSelectObjs() {
        Wrapper<OrderInfoRecord> queryWrapper = mock(Wrapper.class);
        List<Object> expectedObjs = Collections.singletonList(new Object());
        when(mockOrderInfoRecordMapper.selectObjs(queryWrapper)).thenReturn(expectedObjs);

        List<Object> actualObjs = orderInfoRecordMapper.selectObjs(queryWrapper);
        assertEquals(expectedObjs, actualObjs);
    }

    @Test
    void testSelectPage() {
        Page<OrderInfoRecord> page = new Page<>(1, 10);
        Wrapper<OrderInfoRecord> queryWrapper = mock(Wrapper.class);
        Page<OrderInfoRecord> expectedPage = new Page<>();
        when(mockOrderInfoRecordMapper.selectPage(page, queryWrapper)).thenReturn(expectedPage);

        Page<OrderInfoRecord> actualPage = orderInfoRecordMapper.selectPage(page, queryWrapper);
        assertEquals(expectedPage, actualPage);
    }

    @Test
    void testGetOrderInfoRecordDAC() {
        Map<String, Object> params = Collections.singletonMap("key", "value");
        List<OrderInfoRecord> expectedRecords = Collections.singletonList(new OrderInfoRecord());
        when(mockOrderInfoRecordMapper.getOrderInfoRecordDAC(params)).thenReturn(expectedRecords);

        List<OrderInfoRecord> actualRecords = orderInfoRecordMapper.getOrderInfoRecordDAC(params);
        assertEquals(expectedRecords, actualRecords);
    }

    @Test
    void testGetOrderInfoRecordDACCount() {
        Map<String, Object> params = Collections.singletonMap("key", "value");
        Long expectedCount = 10L;
        when(mockOrderInfoRecordMapper.getOrderInfoRecordDACCount(params)).thenReturn(expectedCount);

        Long actualCount = orderInfoRecordMapper.getOrderInfoRecordDACCount(params);
        assertEquals(expectedCount, actualCount);
    }

    @Test
    void testSelectMapsPage() {
        Page<OrderInfoRecord> page = new Page<>(1, 10);
        Wrapper<OrderInfoRecord> queryWrapper = mock(Wrapper.class);
        Page<Map<String, Object>> expectedPage = new Page<>();
        when(mockOrderInfoRecordMapper.selectMapsPage(page, queryWrapper)).thenReturn(expectedPage);

        Page<Map<String, Object>> actualPage = orderInfoRecordMapper.selectMapsPage(page, queryWrapper);
        assertEquals(expectedPage, actualPage);
    }
}
