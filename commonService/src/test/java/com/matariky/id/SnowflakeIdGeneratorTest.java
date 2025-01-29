package com.matariky.id;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class SnowflakeIdGeneratorTest {

    @InjectMocks
    private SnowflakeIdGenerator snowflakeidgenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        // Initialize your test data and mocks here

        // When
        long id = snowflakeidgenerator.getId();

        // Then
        assertThat(id).isNotNull();
    }

    @Test
    void testParseID() {
        // Given
        long id = snowflakeidgenerator.getId();

        // When
        String parsedId = snowflakeidgenerator.parseID(id);

        // Then
        assertThat(parsedId).contains("\"UID\":\"" + id + "\"");
    }

    @Test
    void testNextId() {
        // Given
        // Initialize your test data and mocks here

        // When
        Number id = snowflakeidgenerator.nextId(null);

        // Then
        assertThat(id).isNotNull();
    }

    @Test
    void testAllocate() {
        // Given
        long deltaSeconds = 1L;
        long workerId = 1L;
        long sequence = 1L;

        // When
        long allocatedId = snowflakeidgenerator.allocate(deltaSeconds, workerId, sequence);

        // Then
        assertThat(allocatedId).isNotNull();
    }

    @Test
    void testGetWorkerIdByIP() {
        // Given
        // Initialize your test data and mocks here

        // When
        long workerId = SnowflakeIdGenerator.getWorkerIdByIP();

        // Then
        assertThat(workerId).isNotNull();
    }

    // Add more test methods for other methods in SnowflakeIdGenerator
}
