package com.matariky.redis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LongRedisSerializerTest {

    @InjectMocks
    private LongRedisSerializer longRedisSerializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSerialize() {
        // Given
        Long value = 123456789L;

        // When
        byte[] result = longRedisSerializer.serialize(value);

        // Then
        assertThat(result).isNotNull();
        assertThat(new String(result)).isEqualTo("123456789");
    }

    @Test
    void testSerializeNull() {
        // Given
        Long value = null;

        // When
        byte[] result = longRedisSerializer.serialize(value);

        // Then
        assertThat(result).isNull();
    }

    @Test
    void testDeserialize() {
        // Given
        byte[] bytes = "123456789".getBytes();

        // When
        Long result = longRedisSerializer.deserialize(bytes);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(123456789L);
    }

    @Test
    void testDeserializeNull() {
        // Given
        byte[] bytes = null;

        // When
        Long result = longRedisSerializer.deserialize(bytes);

        // Then
        assertThat(result).isNull();
    }
}
