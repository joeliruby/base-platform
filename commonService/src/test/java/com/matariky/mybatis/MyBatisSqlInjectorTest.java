package com.matariky.mybatis;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;

@SpringBootTest
public class MyBatisSqlInjectorTest {

    @InjectMocks
    private MyBatisSqlInjector mybatissqlinjector;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMethodList() {
        // Given
        Class<?> mapperClass = Object.class;
        TableInfo tableInfo = mock(TableInfo.class);

        // When
        List<AbstractMethod> methodList = mybatissqlinjector.getMethodList(mapperClass, tableInfo);

        // Then
        assertNotNull(methodList);
        assertFalse(methodList.isEmpty());
        assertTrue(methodList.stream().anyMatch(method -> method instanceof InsertBatchSomeColumn));
    }

    // Add more test methods for other methods in MyBatisSqlInjector
}
