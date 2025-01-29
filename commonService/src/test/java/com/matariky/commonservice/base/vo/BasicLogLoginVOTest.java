package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicLogLoginVOTest {

    @InjectMocks
    private BasicLogLoginVO basiclogloginvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUserName() {
        // Given
        String expectedUserName = "testUser";

        // When
        basiclogloginvo.setUserName(expectedUserName);

        // Then
        assertThat(basiclogloginvo.getUserName()).isEqualTo(expectedUserName);
    }

    @Test
    void testLoginAddress() {
        // Given
        String expectedLoginAddress = "127.0.0.1";

        // When
        basiclogloginvo.setLoginAddress(expectedLoginAddress);

        // Then
        assertThat(basiclogloginvo.getLoginAddress()).isEqualTo(expectedLoginAddress);
    }

    @Test
    void testTenantName() {
        // Given
        String expectedTenantName = "tenant1";

        // When
        basiclogloginvo.setTenantName(expectedTenantName);

        // Then
        assertThat(basiclogloginvo.getTenantName()).isEqualTo(expectedTenantName);
    }

    @Test
    void testCreateDateStart() {
        // Given
        String expectedCreateDateStart = "2023-01-01";

        // When
        basiclogloginvo.setCreateDateStart(expectedCreateDateStart);

        // Then
        assertThat(basiclogloginvo.getCreateDateStart()).isEqualTo(expectedCreateDateStart);
    }

    @Test
    void testCreateDateEnd() {
        // Given
        String expectedCreateDateEnd = "2023-12-31";

        // When
        basiclogloginvo.setCreateDateEnd(expectedCreateDateEnd);

        // Then
        assertThat(basiclogloginvo.getCreateDateEnd()).isEqualTo(expectedCreateDateEnd);
    }

    @Test
    void testIndex() {
        // Given
        Integer expectedIndex = 1;

        // When
        basiclogloginvo.setIndex(expectedIndex);

        // Then
        assertThat(basiclogloginvo.getIndex()).isEqualTo(expectedIndex);
    }

    @Test
    void testPerPage() {
        // Given
        Integer expectedPerPage = 10;

        // When
        basiclogloginvo.setPerPage(expectedPerPage);

        // Then
        assertThat(basiclogloginvo.getPerPage()).isEqualTo(expectedPerPage);
    }

    @Test
    void testCreateStartTime() {
        // Given
        Long expectedCreateStartTime = 1609459200000L;

        // When
        basiclogloginvo.setCreateStartTime(expectedCreateStartTime);

        // Then
        assertThat(basiclogloginvo.getCreateStartTime()).isEqualTo(expectedCreateStartTime);
    }

    @Test
    void testCreateEndTime() {
        // Given
        Long expectedCreateEndTime = 1640995200000L;

        // When
        basiclogloginvo.setCreateEndTime(expectedCreateEndTime);

        // Then
        assertThat(basiclogloginvo.getCreateEndTime()).isEqualTo(expectedCreateEndTime);
    }
}
