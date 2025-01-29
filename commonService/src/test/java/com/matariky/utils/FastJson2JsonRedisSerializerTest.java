package com.matariky.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.serializer.SerializationException;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;

@SpringBootTest
public class FastJson2JsonRedisSerializerTest {

    @InjectMocks
    private FastJson2JsonRedisSerializer<Object> fastjson2jsonredisserializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSerialize() {
        // Given
        Object objectToSerialize = new Object(); // Replace with actual object
        byte[] expectedBytes = JSON.toJSONString(objectToSerialize, JSONWriter.Feature.WriteClassName)
                .getBytes(FastJson2JsonRedisSerializer.DEFAULT_CHARSET);

        // When
        byte[] result = fastjson2jsonredisserializer.serialize(objectToSerialize);

        // Then
        assertThat(result).isEqualTo(expectedBytes);
    }

    @Test
    void testSerializeNull() {
        // When
        byte[] result = fastjson2jsonredisserializer.serialize(null);

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    void testDeserialize() {
        // Given
        String jsonString = "{\"@type\":\"java.lang.Object\"}"; // Replace with actual JSON string
        byte[] bytesToDeserialize = jsonString.getBytes(FastJson2JsonRedisSerializer.DEFAULT_CHARSET);
        Object expectedObject = JSON.parseObject(jsonString, Object.class, JSONReader.Feature.SupportAutoType);

        // When
        Object result = fastjson2jsonredisserializer.deserialize(bytesToDeserialize);

        // Then
        assertThat(result).isEqualTo(expectedObject);
    }

    @Test
    void testDeserializeEmptyBytes() {
        // When
        Object result = fastjson2jsonredisserializer.deserialize(new byte[0]);

        // Then
        assertThat(result).isNull();
    }

    @Test
    void testDeserializeNullBytes() {
        // When
        Object result = fastjson2jsonredisserializer.deserialize(null);

        // Then
        assertThat(result).isNull();
    }

    @Test
    void testSerializeWithCustomObject() {
        // Given
        CustomObject customObject = new CustomObject("test", 123);
        byte[] expectedBytes = JSON.toJSONString(customObject, JSONWriter.Feature.WriteClassName)
                .getBytes(FastJson2JsonRedisSerializer.DEFAULT_CHARSET);

        // When
        byte[] result = fastjson2jsonredisserializer.serialize(customObject);

        // Then
        assertThat(result).isEqualTo(expectedBytes);
    }

    @Test
    void testDeserializeWithCustomObject() {
        // Given
        String jsonString = "{\"@type\":\"com.matariky.utils.CustomObject\",\"name\":\"test\",\"value\":123}";
        byte[] bytesToDeserialize = jsonString.getBytes(FastJson2JsonRedisSerializer.DEFAULT_CHARSET);
        CustomObject expectedObject = new CustomObject("test", 123);

        // When
        Object result = fastjson2jsonredisserializer.deserialize(bytesToDeserialize);

        // Then
        assertThat(result).isEqualTo(expectedObject);
    }

    @Test
    void testDeserializeInvalidJson() {
        // Given
        byte[] invalidJsonBytes = "invalid json".getBytes(FastJson2JsonRedisSerializer.DEFAULT_CHARSET);

        // When
        Throwable thrown = catchThrowable(() -> fastjson2jsonredisserializer.deserialize(invalidJsonBytes));

        // Then
        assertThat(thrown).isInstanceOf(SerializationException.class);
    }

    // Add more test methods for other scenarios if needed

    // CustomObject class for testing purposes
    static class CustomObject {
        private String name;
        private int value;

        public CustomObject(String name, int value) {
            this.name = name;
            this.value = value;
        }

        // Getters, setters, equals, and hashCode methods

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            CustomObject that = (CustomObject) o;
            return value == that.value && Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, value);
        }
    }
}
