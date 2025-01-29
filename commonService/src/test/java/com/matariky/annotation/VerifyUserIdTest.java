package com.matariky.annotation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VerifyUserIdTest {

    @InjectMocks
    private VerifyUserId verifyuserid;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAnnotationPresentOnClass() {
        assertTrue(VerifyUserId.class.isAnnotationPresent(Target.class));
        assertTrue(VerifyUserId.class.isAnnotationPresent(Retention.class));
    }

    @Test
    void testTargetAnnotation() {
        Target target = VerifyUserId.class.getAnnotation(Target.class);
        ElementType[] value = target.value();
        assertThat(value).containsExactlyInAnyOrder(ElementType.METHOD, ElementType.TYPE);
    }

    @Test
    void testRetentionAnnotation() {
        Retention retention = VerifyUserId.class.getAnnotation(Retention.class);
        RetentionPolicy value = retention.value();
        assertThat(value).isEqualTo(RetentionPolicy.RUNTIME);
    }

    // Add more test methods for other methods in VerifyUserId
}
