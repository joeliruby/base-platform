package com.matariky.commonservice.datasharing.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import org.mockito.Mock;
import com.matariky.commonservice.datasharing.bean.CommonSharingPool;

@SpringBootTest
public class CommonSharingPoolMapperTest {

    @InjectMocks
    private CommonSharingPoolMapper commonSharingPoolMapper;

    @Mock
    private CommonSharingPoolMapper mockCommonSharingPoolMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommonSharingPoolAll() {
        // Given
        List<CommonSharingPool> expectedList = Arrays.asList(new CommonSharingPool(), new CommonSharingPool());
        when(mockCommonSharingPoolMapper.getCommonSharingPoolAll()).thenReturn(expectedList);

        // When
        List<CommonSharingPool> actualList = commonSharingPoolMapper.getCommonSharingPoolAll();

        // Then
        assertEquals(expectedList, actualList);
    }

    @Test
    void testGetCommonSharingPoolAllCount() {
        // Given
        int expectedCount = 5;
        when(mockCommonSharingPoolMapper.getCommonSharingPoolAllCount()).thenReturn(expectedCount);

        // When
        int actualCount = commonSharingPoolMapper.getCommonSharingPoolAllCount();

        // Then
        assertEquals(expectedCount, actualCount);
    }

    @Test
    void testCreateCommonSharingPool() {
        // Given
        CommonSharingPool pool = new CommonSharingPool();
        when(mockCommonSharingPoolMapper.createCommonSharingPool(pool)).thenReturn(1);

        // When
        int result = commonSharingPoolMapper.createCommonSharingPool(pool);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testUpdateCommonSharingPool() {
        // Given
        CommonSharingPool pool = new CommonSharingPool();
        when(mockCommonSharingPoolMapper.updateCommonSharingPool(pool)).thenReturn(1);

        // When
        int result = commonSharingPoolMapper.updateCommonSharingPool(pool);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDelCommonSharingPoolById() {
        // Given
        long id = 1L;
        when(mockCommonSharingPoolMapper.delCommonSharingPoolById(id)).thenReturn(1);

        // When
        int result = commonSharingPoolMapper.delCommonSharingPoolById(id);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testGetCommonSharingPoolById() {
        // Given
        int id = 1;
        CommonSharingPool expectedPool = new CommonSharingPool();
        when(mockCommonSharingPoolMapper.getCommonSharingPoolById(id)).thenReturn(expectedPool);

        // When
        CommonSharingPool actualPool = commonSharingPoolMapper.getCommonSharingPoolById(id);

        // Then
        assertEquals(expectedPool, actualPool);
    }

    // Add more test methods for other methods in CommonSharingPoolMapper
}
