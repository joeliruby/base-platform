package com.matariky.aop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.lang.reflect.Method;

@SpringBootTest
public class LockPointTest {

    @InjectMocks
    private LockPoint lockpoint;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetArgs() {
        // Given
        Object[] args = new Object[] { "arg1", "arg2" };
        lockpoint.setArgs(args);

        // When
        Object[] result = lockpoint.getArgs();

        // Then
        assertThat(result).isEqualTo(args);
    }

    @Test
    void testSetArgs() {
        // Given
        Object[] args = new Object[] { "arg1", "arg2" };

        // When
        lockpoint.setArgs(args);

        // Then
        assertThat(lockpoint.getArgs()).isEqualTo(args);
    }

    @Test
    void testGetMethod() throws NoSuchMethodException {
        // Given
        Method method = LockPoint.class.getMethod("getArgs");
        lockpoint.setMethod(method);

        // When
        Method result = lockpoint.getMethod();

        // Then
        assertThat(result).isEqualTo(method);
    }

    @Test
    void testSetMethod() throws NoSuchMethodException {
        // Given
        Method method = LockPoint.class.getMethod("getArgs");

        // When
        lockpoint.setMethod(method);

        // Then
        assertThat(lockpoint.getMethod()).isEqualTo(method);
    }
}
