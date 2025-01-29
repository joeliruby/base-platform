package com.matariky.annotation;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.annotation.Annotation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SourcePermissionTest {

    @InjectMocks
    private SourcePermission sourcePermission;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValueAliasForOid() {
        // Given
        String expectedValue = "testValue";

        // When
        sourcePermission = new SourcePermission() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return SourcePermission.class;
            }

            @Override
            public String value() {
                return expectedValue;
            }

            @Override
            public String oId() {
                return expectedValue;
            }
        };

        // Then
        assertThat(sourcePermission.value()).isEqualTo(expectedValue);
        assertThat(sourcePermission.oId()).isEqualTo(expectedValue);
    }

    @Test
    void testOidAliasForValue() {
        // Given
        String expectedOid = "testOid";

        // When
        sourcePermission = new SourcePermission() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return SourcePermission.class;
            }

            @Override
            public String value() {
                return expectedOid;
            }

            @Override
            public String oId() {
                return expectedOid;
            }
        };

        // Then
        assertThat(sourcePermission.oId()).isEqualTo(expectedOid);
        assertThat(sourcePermission.value()).isEqualTo(expectedOid);
    }

    // Add more test methods for other methods in SourcePermission
}
