package com.matariky.orderservice.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class OrderSuitePermissionTest {

    @InjectMocks
    private OrderSuitePermission ordersuitepermission;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        ordersuitepermission.setId(expectedId);

        // When
        Long actualId = ordersuitepermission.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetSuiteCode() {
        // Given
        String expectedSuiteCode = "suiteCode";
        ordersuitepermission.setSuiteCode(expectedSuiteCode);

        // When
        String actualSuiteCode = ordersuitepermission.getSuiteCode();

        // Then
        assertThat(actualSuiteCode).isEqualTo(expectedSuiteCode);
    }

    @Test
    void testGetPermissionId() {
        // Given
        Long expectedPermissionId = 2L;
        ordersuitepermission.setPermissionId(expectedPermissionId);

        // When
        Long actualPermissionId = ordersuitepermission.getPermissionId();

        // Then
        assertThat(actualPermissionId).isEqualTo(expectedPermissionId);
    }

    @Test
    void testSetId() {
        // Given
        Long expectedId = 1L;

        // When
        ordersuitepermission.setId(expectedId);

        // Then
        assertThat(ordersuitepermission.getId()).isEqualTo(expectedId);
    }

    @Test
    void testSetSuiteCode() {
        // Given
        String expectedSuiteCode = "suiteCode";

        // When
        ordersuitepermission.setSuiteCode(expectedSuiteCode);

        // Then
        assertThat(ordersuitepermission.getSuiteCode()).isEqualTo(expectedSuiteCode);
    }

    @Test
    void testSetPermissionId() {
        // Given
        Long expectedPermissionId = 2L;

        // When
        ordersuitepermission.setPermissionId(expectedPermissionId);

        // Then
        assertThat(ordersuitepermission.getPermissionId()).isEqualTo(expectedPermissionId);
    }
}
