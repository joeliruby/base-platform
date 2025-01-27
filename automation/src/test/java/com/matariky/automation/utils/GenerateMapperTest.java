package com.matariky.automation.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.automation.bean.DbBean;

@SpringBootTest
public class GenerateMapperTest {

    @InjectMocks
    private GenerateMapper generatemapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMapper() {
        // Given
        DbBean model = mock(DbBean.class);
        when(model.getTablename()).thenReturn("TestTable");
        when(model.getCompages()).thenReturn("com.matariky.automation");
        when(model.getZdname()).thenReturn("id--ID,name--NAME");

        // When
        String result = GenerateMapper.get_mapper(model);

        // Then
        assertNotNull(result);
        assertTrue(result.contains("TestTable"));
        assertTrue(result.contains("com.matariky.automation"));
    }

    @Test
    void testGetMapperXml() {
        // Given
        DbBean model = mock(DbBean.class);
        when(model.getTablename()).thenReturn("TestTable");
        when(model.getCompages()).thenReturn("com.matariky.automation");
        when(model.getZdname()).thenReturn("id--ID,name--NAME");

        // When
        String result = GenerateMapper.get_mapperXml(model);

        // Then
        assertNotNull(result);
        assertTrue(result.contains("TestTable"));
        assertTrue(result.contains("com.matariky.automation"));
    }

    // Add more test methods for other methods in GenerateMapper
}
