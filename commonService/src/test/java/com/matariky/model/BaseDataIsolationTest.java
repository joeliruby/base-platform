package com.matariky.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BaseDataIsolationTest {

    @InjectMocks
    private BaseDataIsolation basedataisolation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTenantId() {
        // Given
        String tenantId = "tenant123";
        basedataisolation.setTenantId(tenantId);

        // When
        String result = basedataisolation.getTenantId();

        // Then
        assertThat(result).isEqualTo(tenantId);
    }

    @Test
    void testGetLocal() {
        // Given
        String local = "en_US";
        basedataisolation.setLocal(local);

        // When
        String result = basedataisolation.getLocal();

        // Then
        assertThat(result).isEqualTo(local);
    }

    @Test
    void testGetUserId() {
        // Given
        Long userId = 12345L;
        basedataisolation.setUserId(userId);

        // When
        Long result = basedataisolation.getUserId();

        // Then
        assertThat(result).isEqualTo(userId);
    }

    @Test
    void testGetApplication() {
        // Given
        Integer application = 1;
        basedataisolation.setApplication(application);

        // When
        Integer result = basedataisolation.getApplication();

        // Then
        assertThat(result).isEqualTo(application);
    }

    // Add more test methods for other methods in BaseDataIsolation if needed
}
