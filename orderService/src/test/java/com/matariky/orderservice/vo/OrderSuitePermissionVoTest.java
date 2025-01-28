package com.matariky.orderservice.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class OrderSuitePermissionVoTest {

    @InjectMocks
    private OrderSuitePermissionVo ordersuitepermissionvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSuiteCode() {
        // Given
        String suiteCode = "testSuiteCode";
        ordersuitepermissionvo.setSuiteCode(suiteCode);

        // When
        String result = ordersuitepermissionvo.getSuiteCode();

        // Then
        assertThat(result).isEqualTo(suiteCode);
    }

    @Test
    void testSetSuiteCode() {
        // Given
        String suiteCode = "newSuiteCode";

        // When
        ordersuitepermissionvo.setSuiteCode(suiteCode);

        // Then
        assertThat(ordersuitepermissionvo.getSuiteCode()).isEqualTo(suiteCode);
    }

    @Test
    void testGetPermissionId() {
        // Given
        Long permissionId = 123L;
        ordersuitepermissionvo.setPermissionId(permissionId);

        // When
        Long result = ordersuitepermissionvo.getPermissionId();

        // Then
        assertThat(result).isEqualTo(permissionId);
    }

    @Test
    void testSetPermissionId() {
        // Given
        Long permissionId = 456L;

        // When
        ordersuitepermissionvo.setPermissionId(permissionId);

        // Then
        assertThat(ordersuitepermissionvo.getPermissionId()).isEqualTo(permissionId);
    }
}
