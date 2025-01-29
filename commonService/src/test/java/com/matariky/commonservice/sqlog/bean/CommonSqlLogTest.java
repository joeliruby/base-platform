package com.matariky.commonservice.sqlog.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CommonSqlLogTest {

    @InjectMocks
    private CommonSqlLog commonsqllog;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        String expectedId = "123";
        commonsqllog.setId(expectedId);

        // When
        String actualId = commonsqllog.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetSqlStatemant() {
        // Given
        String expectedSqlStatemant = "SELECT * FROM table";
        commonsqllog.setSqlStatemant(expectedSqlStatemant);

        // When
        String actualSqlStatemant = commonsqllog.getSqlStatemant();

        // Then
        assertThat(actualSqlStatemant).isEqualTo(expectedSqlStatemant);
    }

    @Test
    void testGetCreatedBy() {
        // Given
        Long expectedCreatedBy = 1L;
        commonsqllog.setCreatedBy(expectedCreatedBy);

        // When
        Long actualCreatedBy = commonsqllog.getCreatedBy();

        // Then
        assertThat(actualCreatedBy).isEqualTo(expectedCreatedBy);
    }

    @Test
    void testGetCreateTime() {
        // Given
        Long expectedCreateTime = 1000L;
        commonsqllog.setCreateTime(expectedCreateTime);

        // When
        Long actualCreateTime = commonsqllog.getCreateTime();

        // Then
        assertThat(actualCreateTime).isEqualTo(expectedCreateTime);
    }

    @Test
    void testGetExecutionTime() {
        // Given
        Long expectedExecutionTime = 500L;
        commonsqllog.setExecutionTime(expectedExecutionTime);

        // When
        Long actualExecutionTime = commonsqllog.getExecutionTime();

        // Then
        assertThat(actualExecutionTime).isEqualTo(expectedExecutionTime);
    }

    @Test
    void testGetStartTime() {
        // Given
        Long expectedStartTime = 2000L;
        commonsqllog.setStartTime(expectedStartTime);

        // When
        Long actualStartTime = commonsqllog.getStartTime();

        // Then
        assertThat(actualStartTime).isEqualTo(expectedStartTime);
    }

    @Test
    void testGetEndTime() {
        // Given
        Long expectedEndTime = 3000L;
        commonsqllog.setEndTime(expectedEndTime);

        // When
        Long actualEndTime = commonsqllog.getEndTime();

        // Then
        assertThat(actualEndTime).isEqualTo(expectedEndTime);
    }

    @Test
    void testGetExecutionDuration() {
        // Given
        Long expectedExecutionDuration = 1500L;
        commonsqllog.setExecutionDuration(expectedExecutionDuration);

        // When
        Long actualExecutionDuration = commonsqllog.getExecutionDuration();

        // Then
        assertThat(actualExecutionDuration).isEqualTo(expectedExecutionDuration);
    }
}
