package com.matariky.bizservice.assetitm.base.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.bizservice.assetitm.base.bean.BasicBaseRfidfactory;
import com.matariky.bizservice.assetitm.base.mapper.BasicBaseRfidfactoryMapper;
import org.mockito.Mock;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BasicBaseRfidfactoryServiceTest {

    @InjectMocks
    private BasicBaseRfidfactoryService basicBaseRfidfactoryService;

    @Mock
    private BasicBaseRfidfactoryMapper basicBaseRfidfactoryMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBasicBaseRfidfactoryAll() {
        // Given
        BasicBaseRfidfactory bean = new BasicBaseRfidfactory();
        String tenantId = "tenantId";
        List<BasicBaseRfidfactory> expectedList = Collections.singletonList(new BasicBaseRfidfactory());
        when(basicBaseRfidfactoryMapper.getBasicBaseRfidfactoryAll(bean)).thenReturn(expectedList);

        // When
        List<BasicBaseRfidfactory> result = basicBaseRfidfactoryService.getBasicBaseRfidfactoryAll(bean, tenantId, null,
                1, 10);

        // Then
        assertEquals(expectedList, result);
    }

    @Test
    void testCreateBasicBaseRfidfactory() {
        // Given
        BasicBaseRfidfactory bean = new BasicBaseRfidfactory();
        when(basicBaseRfidfactoryMapper.createBasicBaseRfidfactory(bean)).thenReturn(1);

        // When
        int result = basicBaseRfidfactoryService.createBasicBaseRfidfactory(bean);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetBasicBaseRfidfactoryById() {
        // Given
        Long id = 1L;
        BasicBaseRfidfactory expected = new BasicBaseRfidfactory();
        when(basicBaseRfidfactoryMapper.getBasicBaseRfidfactoryById(id)).thenReturn(expected);

        // When
        BasicBaseRfidfactory result = basicBaseRfidfactoryService.getBasicBaseRfidfactoryById(id);

        // Then
        assertEquals(expected, result);
    }

    @Test
    void testDelBasicBaseRfidfactoryById() {
        // Given
        Long id = 1L;
        when(basicBaseRfidfactoryMapper.delBasicBaseRfidfactoryById(id)).thenReturn(1);

        // When
        int result = basicBaseRfidfactoryService.delBasicBaseRfidfactoryById(id);

        // Then
        assertEquals(1, result);
    }

    // Add more test methods for other methods in BasicBaseRfidfactoryService
}
