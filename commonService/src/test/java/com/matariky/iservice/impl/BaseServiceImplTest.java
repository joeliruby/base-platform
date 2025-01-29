package com.matariky.iservice.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.matariky.commonservice.datasharing.service.CommonSharingPoolService;
import com.matariky.mybatis.MybatisPlusDataScopeInterceptor;

@SpringBootTest
public class BaseServiceImplTest {

    @InjectMocks
    private BaseServiceImpl<BaseMapper<Object>, Object> baseserviceimpl;

    @Mock
    private CommonSharingPoolService sharingPoolService;

    @Mock
    private MybatisPlusDataScopeInterceptor mybatisPlusDataScopeInterceptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPage() {
        // Given
        Map<String, Object> params = new HashMap<>();
        params.put("page", "1");
        params.put("limit", "10");
        String defaultOrderField = "id";
        boolean isAsc = true;

        // When
        IPage<Object> page = baseserviceimpl.getPage(params, defaultOrderField, isAsc);

        // Then
        assertNotNull(page);
        assertEquals(1, page.getCurrent());
        assertEquals(10, page.getSize());
    }

    @Test
    void testInsert() {
        // Given
        Object entity = new Object();
        when(baseserviceimpl.baseDao.insert(entity)).thenReturn(1);

        // When
        boolean result = baseserviceimpl.insert(entity);

        // Then
        assertTrue(result);
    }

    @Test
    void testUpdateById() {
        // Given
        Object entity = new Object();
        when(baseserviceimpl.baseDao.updateById(entity)).thenReturn(1);

        // When
        boolean result = baseserviceimpl.updateById(entity);

        // Then
        assertTrue(result);
    }

    @Test
    void testDeleteById() {
        // Given
        Serializable id = 1L;
        when(baseserviceimpl.baseDao.deleteById(id)).thenReturn(1);

        // When
        boolean result = baseserviceimpl.deleteById(id);

        // Then
        assertTrue(result);
    }

    @Test
    void testSelectById() {
        // Given
        Serializable id = 1L;
        Object expectedEntity = new Object();
        when(baseserviceimpl.baseDao.selectById(id)).thenReturn(expectedEntity);

        // When
        Object result = baseserviceimpl.selectById(id);

        // Then
        assertEquals(expectedEntity, result);
    }

    // Add more test methods for other methods in BaseServiceImpl
}
