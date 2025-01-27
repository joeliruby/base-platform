package com.matariky.automation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@SpringBootTest
public class AutomationApplicationTest {

    @InjectMocks
    private AutomationApplication automationapplication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSpringDataDialectBean() {
        // Given
        // No initialization needed for this test

        // When
        SpringDataDialect springDataDialect = automationapplication.springDataDialect();

        // Then
        assertNotNull(springDataDialect);
    }

    @Test
    void testMainMethod() {
        // Given
        String[] args = {};

        // When
        // Call the main method
        AutomationApplication.main(args);

        // Then
        // No exception should be thrown
    }

    @Test
    void testJpaRepositoriesEnabled() {
        // Given
        // No initialization needed for this test

        // When
        boolean isJpaRepositoriesEnabled = automationapplication.getClass()
                .isAnnotationPresent(EnableJpaRepositories.class);

        // Then
        assertThat(isJpaRepositoriesEnabled).isTrue();
    }

    @Test
    void testEntityScanEnabled() {
        // Given
        // No initialization needed for this test

        // When
        boolean isEntityScanEnabled = automationapplication.getClass().isAnnotationPresent(EntityScan.class);

        // Then
        assertThat(isEntityScanEnabled).isTrue();
    }

    // Add more test methods for other methods in AutomationApplication
}
