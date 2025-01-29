package com.matariky.commonservice.upload.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class SpringContextUtilsTest {

    @InjectMocks
    private SpringContextUtils springContextUtils;

    private ApplicationContext applicationContextMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        applicationContextMock = mock(ApplicationContext.class);
        SpringContextUtils.applicationContext = applicationContextMock;
    }

    @Test
    void testGetBeanByName() {
        // Given
        String beanName = "testBean";
        Object expectedBean = new Object();
        when(applicationContextMock.getBean(beanName)).thenReturn(expectedBean);

        // When
        Object actualBean = springContextUtils.getBean(beanName);

        // Then
        assertEquals(expectedBean, actualBean);
    }

    @Test
    void testGetBeanByType() {
        // Given
        Class<String> beanType = String.class;
        String expectedBean = "testBean";
        when(applicationContextMock.getBean(beanType)).thenReturn(expectedBean);

        // When
        String actualBean = springContextUtils.getBean(beanType);

        // Then
        assertEquals(expectedBean, actualBean);
    }

    @Test
    void testGetBeanByNameAndType() {
        // Given
        String beanName = "testBean";
        Class<String> beanType = String.class;
        String expectedBean = "testBean";
        when(applicationContextMock.getBean(beanName, beanType)).thenReturn(expectedBean);

        // When
        String actualBean = springContextUtils.getBean(beanName, beanType);

        // Then
        assertEquals(expectedBean, actualBean);
    }

    @Test
    void testContainsBean() {
        // Given
        String beanName = "testBean";
        when(applicationContextMock.containsBean(beanName)).thenReturn(true);

        // When
        boolean containsBean = springContextUtils.containsBean(beanName);

        // Then
        assertTrue(containsBean);
    }

    @Test
    void testIsSingleton() {
        // Given
        String beanName = "testBean";
        when(applicationContextMock.isSingleton(beanName)).thenReturn(true);

        // When
        boolean isSingleton = springContextUtils.isSingleton(beanName);

        // Then
        assertTrue(isSingleton);
    }

}
