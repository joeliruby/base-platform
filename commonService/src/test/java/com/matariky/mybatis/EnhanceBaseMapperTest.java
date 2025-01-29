package com.matariky.mybatis;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.matariky.model.QueryDataIsolation;
import org.mockito.Mock;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class EnhanceBaseMapperTest {

    @InjectMocks
    private EnhanceBaseMapper<Object> enhanceBaseMapper;

    @Mock
    private BaseMapper<Object> baseMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSelectList() {
        // Given
        QueryDataIsolation dataIsolation = new QueryDataIsolation();
        QueryWrapper queryWrapper = new QueryWrapper();
        List expectedList = Arrays.asList(new Object(), new Object());
        when(baseMapper.selectList(any())).thenReturn(expectedList);

        // When
        List result = enhanceBaseMapper.selectList(dataIsolation, queryWrapper);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testSelectToMapWithIdsAndKeyAndValue() {
        // Given
        List ids = Arrays.asList(1L, 2L, 3L);
        SFunction key = mock(SFunction.class);
        SFunction value = mock(SFunction.class);
        List list = Arrays.asList(new Object(), new Object());
        when(baseMapper.selectList(any())).thenReturn(list);

        // When
        Map result = enhanceBaseMapper.selectToMap(ids, key, value);

        // Then
        assertNotNull(result);
    }

    @Test
    void testSelectToMapWithIdsAndKey() {
        // Given
        List ids = Arrays.asList(1L, 2L, 3L);
        SFunction key = mock(SFunction.class);
        List list = Arrays.asList(new Object(), new Object());
        when(baseMapper.selectList(any())).thenReturn(list);

        // When
        Map result = enhanceBaseMapper.selectToMap(ids, key);

        // Then
        assertNotNull(result);
    }

    @Test
    void testSelectToMapWithQueryWrapperKeyAndValue() {
        // Given
        LambdaQueryWrapper queryWrapper = new LambdaQueryWrapper();
        SFunction key = mock(SFunction.class);
        SFunction value = mock(SFunction.class);
        List list = Arrays.asList(new Object(), new Object());
        when(baseMapper.selectList(any())).thenReturn(list);

        // When
        Map result = enhanceBaseMapper.selectToMap(queryWrapper, key, value);

        // Then
        assertNotNull(result);
    }

    @Test
    void testSelectToMapWithQueryWrapperAndKey() {
        // Given
        QueryWrapper queryWrapper = new QueryWrapper();
        SFunction key = mock(SFunction.class);
        List list = Arrays.asList(new Object(), new Object());
        when(baseMapper.selectList(any())).thenReturn(list);

        // When
        Map result = enhanceBaseMapper.selectToMap(queryWrapper, key);

        // Then
        assertNotNull(result);
    }

    @Test
    void testQw() {
        // When
        LambdaQueryWrapper result = enhanceBaseMapper.qw();

        // Then
        assertNotNull(result);
    }

    @Test
    void testUw() {
        // When
        LambdaUpdateWrapper result = enhanceBaseMapper.uw();

        // Then
        assertNotNull(result);
    }

    @Test
    void testSelectSum() {
        // Given
        List list = Arrays.asList(new Object(), new Object());
        when(baseMapper.selectList(any())).thenReturn(list);

        // When
        List result = enhanceBaseMapper.selectSum(wrapper -> {
        }, t -> null);

        // Then
        assertNotNull(result);
    }

    @Test
    void testSelectSumColumnValue() {
        // Given
        Object expected = new Object();
        when(baseMapper.selectOne(any())).thenReturn(expected);

        // When
        Object result = enhanceBaseMapper.selectSumColumnValue(wrapper -> {
        }, t -> expected);

        // Then
        assertEquals(expected, result);
    }

    @Test
    void testSelectColumnValueWithQueryWrapper() {
        // Given
        LambdaQueryWrapper queryWrapper = new LambdaQueryWrapper();
        SFunction column = mock(SFunction.class);
        Object expected = new Object();
        when(baseMapper.selectOne(any())).thenReturn(expected);

        // When
        Object result = enhanceBaseMapper.selectColumnValue(queryWrapper, column);

        // Then
        assertEquals(expected, result);
    }

    @Test
    void testSelectColumnValues() {
        // Given
        LambdaQueryWrapper queryWrapper = new LambdaQueryWrapper();
        SFunction column = mock(SFunction.class);
        List list = Arrays.asList(new Object(), new Object());
        when(baseMapper.selectList(any())).thenReturn(list);

        // When
        List result = enhanceBaseMapper.selectColumnValues(queryWrapper, column);

        // Then
        assertNotNull(result);
    }

    @Test
    void testSelectColumnValueWithId() {
        // Given
        Long id = 1L;
        Object expected = new Object();
        when(baseMapper.selectOne(any())).thenReturn(expected);

        // When
        Object result = enhanceBaseMapper.selectColumnValue(id, t -> expected);

        // Then
        assertEquals(expected, result);
    }

    @Test
    void testExists() {
        // Given
        QueryWrapper queryWrapper = new QueryWrapper();
        when(baseMapper.selectCount(any())).thenReturn(1L);

        // When
        boolean result = enhanceBaseMapper.exists(queryWrapper);

        // Then
        assertTrue(result);
    }

    @Test
    void testSelectNCount() {
        // Given
        QueryWrapper queryWrapper = new QueryWrapper();
        when(baseMapper.selectCount(any())).thenReturn(1L);

        // When
        long result = enhanceBaseMapper.selectNCount(queryWrapper);

        // Then
        assertEquals(1L, result);
    }

    @Test
    void testUpdate() {
        // Given
        LambdaUpdateWrapper updateWrapper = new LambdaUpdateWrapper();
        when(baseMapper.update(any(), any())).thenReturn(1);

        // When
        int result = enhanceBaseMapper.update(updateWrapper);

        // Then
        assertEquals(1, result);
    }
}
