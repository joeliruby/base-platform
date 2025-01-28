package com.matariky.userservice.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UserApplicationTest {

    @InjectMocks
    private UserApplication userapplication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsActive() {
        // Given
        userapplication.setIsActive(true);

        // When
        Boolean isActive = userapplication.getIsActive();

        // Then
        assertThat(isActive).isTrue();
    }

    @Test
    void testApplicationName() {
        // Given
        String appName = "TestApp";
        userapplication.setApplicationName(appName);

        // When
        String result = userapplication.getApplicationName();

        // Then
        assertThat(result).isEqualTo(appName);
    }

    @Test
    void testVersionNumber() {
        // Given
        String version = "1.0.0";
        userapplication.setVersionNumber(version);

        // When
        String result = userapplication.getVersionNumber();

        // Then
        assertThat(result).isEqualTo(version);
    }

    @Test
    void testCreateTime() {
        // Given
        Long createTime = System.currentTimeMillis();
        userapplication.setCreateTime(createTime);

        // When
        Long result = userapplication.getCreateTime();

        // Then
        assertThat(result).isEqualTo(createTime);
    }

    @Test
    void testUpdateTime() {
        // Given
        Long updateTime = System.currentTimeMillis();
        userapplication.setUpdateTime(updateTime);

        // When
        Long result = userapplication.getUpdateTime();

        // Then
        assertThat(result).isEqualTo(updateTime);
    }

    @Test
    void testTenantId() {
        // Given
        String tenantId = "tenant123";
        userapplication.setTenantId(tenantId);

        // When
        String result = userapplication.getTenantId();

        // Then
        assertThat(result).isEqualTo(tenantId);
    }

    // Add more test methods for other fields and methods in UserApplication
}
