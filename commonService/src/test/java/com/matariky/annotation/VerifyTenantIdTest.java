package com.matariky.annotation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class VerifyTenantIdTest {

    @InjectMocks
    private VerifyTenantId verifytenantid;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAnnotationPresenceOnClass() {
        assertTrue(VerifyTenantId.class.isAnnotationPresent(Target.class));
        assertTrue(VerifyTenantId.class.isAnnotationPresent(Retention.class));
    }

    @Test
    void testTargetAnnotation() {
        Target target = VerifyTenantId.class.getAnnotation(Target.class);
        assertThat(target.value()).containsExactlyInAnyOrder(ElementType.METHOD, ElementType.TYPE);
    }

    @Test
    void testRetentionAnnotation() {
        Retention retention = VerifyTenantId.class.getAnnotation(Retention.class);
        assertEquals(RetentionPolicy.RUNTIME, retention.value());
    }

    // Add more test methods for other methods in VerifyTenantId
}
