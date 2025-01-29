package com.matariky.iservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaseServiceTest {

    @InjectMocks
    private BaseService<Object> baseservice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsert() {
        // Given
        Object entity = new Object();
        when(baseservice.insert(entity)).thenReturn(true);

        // When
        boolean result = baseservice.insert(entity);

        // Then
        assertTrue(result);
        verify(baseservice, times(1)).insert(entity);
    }

    @Test
    void testInsertBatch() {
        // Given
        List<Object> entityList = Arrays.asList(new Object(), new Object());
        when(baseservice.insertBatch(entityList)).thenReturn(true);

        // When
        boolean result = baseservice.insertBatch(entityList);

        // Then
        assertTrue(result);
        verify(baseservice, times(1)).insertBatch(entityList);
    }

    @Test
    void testUpdateById() {
        // Given
        Object entity = new Object();
        when(baseservice.updateById(entity)).thenReturn(true);

        // When
        boolean result = baseservice.updateById(entity);

        // Then
        assertTrue(result);
        verify(baseservice, times(1)).updateById(entity);
    }

    @Test
    void testSelectById() {
        // Given
        Serializable id = 1L;
        Object expectedEntity = new Object();
        when(baseservice.selectById(id)).thenReturn(expectedEntity);

        // When
        Object result = baseservice.selectById(id);

        // Then
        assertEquals(expectedEntity, result);
        verify(baseservice, times(1)).selectById(id);
    }

    @Test
    void testDeleteById() {
        // Given
        Serializable id = 1L;
        when(baseservice.deleteById(id)).thenReturn(true);

        // When
        boolean result = baseservice.deleteById(id);

        // Then
        assertTrue(result);
        verify(baseservice, times(1)).deleteById(id);
    }

    @Test
    void testDeleteBatchIds() {
        // Given
        List<Serializable> idList = Arrays.asList(1L, 2L);
        when(baseservice.deleteBatchIds(idList)).thenReturn(true);

        // When
        boolean result = baseservice.deleteBatchIds(idList);

        // Then
        assertTrue(result);
        verify(baseservice, times(1)).deleteBatchIds(idList);
    }
}
