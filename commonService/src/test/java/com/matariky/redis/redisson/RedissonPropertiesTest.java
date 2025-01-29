package com.matariky.redis.redisson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class RedissonPropertiesTest {

    @InjectMocks
    private RedissonProperties redissonproperties;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEnableDefaultValue() {
        // Given
        // No additional setup required

        // When
        boolean enable = redissonproperties.isEnable();

        // Then
        assertThat(enable).isFalse();
    }

    @Test
    void testDatabaseDefaultValue() {
        // Given
        // No additional setup required

        // When
        int database = redissonproperties.getDatabase();

        // Then
        assertThat(database).isEqualTo(0);
    }

    @Test
    void testSetEnable() {
        // Given
        redissonproperties.setEnable(true);

        // When
        boolean enable = redissonproperties.isEnable();

        // Then
        assertThat(enable).isTrue();
    }

    @Test
    void testSetDatabase() {
        // Given
        redissonproperties.setDatabase(5);

        // When
        int database = redissonproperties.getDatabase();

        // Then
        assertThat(database).isEqualTo(5);
    }

    // Add more test methods for other methods in RedissonProperties if needed
}
