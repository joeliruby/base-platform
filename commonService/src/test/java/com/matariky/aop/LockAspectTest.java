package com.matariky.aop;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.annotation.Lock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.mockito.Mock;

@SpringBootTest
public class LockAspectTest {

    @InjectMocks
    private LockAspect lockAspect;

    @Mock
    private ProceedingJoinPoint joinPoint;

    @Mock
    private MethodSignature methodSignature;

    @Mock
    private Lock lock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDataFilterWithKey() throws Throwable {
        Lock lockAnnotation = mock(Lock.class);
        when(lockAnnotation.key()).thenReturn("testKey");
        when(lockAnnotation.keyMethod()).thenReturn("");
        when(joinPoint.proceed()).thenReturn("proceedResult");

        Object result = lockAspect.dataFilter(joinPoint, lockAnnotation);

        assertEquals("proceedResult", result);
    }

    @Test
    void testDataFilterWithKeyMethod() throws Throwable {
        Lock lockAnnotation = mock(Lock.class);
        when(lockAnnotation.key()).thenReturn("");
        when(lockAnnotation.keyMethod()).thenReturn("this.testMethod");
        when(joinPoint.getTarget()).thenReturn(this);
        when(joinPoint.getSignature()).thenReturn(methodSignature);
        when(methodSignature.getMethod()).thenReturn(this.getClass().getMethod("testMethod"));

        Object result = lockAspect.dataFilter(joinPoint, lockAnnotation);

        assertEquals("proceedResult", result);
    }

    public String testMethod() {
        return "testKey";
    }

    // Add more test methods for other scenarios in LockAspect
}
