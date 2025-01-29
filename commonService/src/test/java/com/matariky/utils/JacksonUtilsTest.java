package com.matariky.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class JacksonUtilsTest {

    @InjectMocks
    private JacksonUtils jacksonutils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToJsonString() {
        // Given
        Map<String, String> data = new HashMap<>();
        data.put("key", "value");

        // When
        String jsonString = JacksonUtils.toJsonString(data);

        // Then
        assertThat(jsonString).isEqualTo("{\"key\":\"value\"}");
    }

    @Test
    void testToBean() {
        // Given
        String jsonString = "{\"key\":\"value\"}";

        // When
        Map<String, String> result = JacksonUtils.toBean(jsonString, Map.class);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.get("key")).isEqualTo("value");
    }

    @Test
    void testToObject() {
        // Given
        String jsonString = "{\"key\":\"value\"}";
        TypeReference<Map<String, String>> typeReference = new TypeReference<Map<String, String>>() {
        };

        // When
        Map<String, String> result = JacksonUtils.toObject(jsonString, typeReference);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.get("key")).isEqualTo("value");
    }

    @Test
    void testToBeanWithParametricType() {
        // Given
        String jsonString = "[{\"key\":\"value\"}]";

        // When
        Map<String, String>[] result = JacksonUtils.toBean(jsonString, Map[].class);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.length).isEqualTo(1);
        assertThat(result[0].get("key")).isEqualTo("value");
    }

    @Test
    void testToJsonNode() {
        // Given
        String jsonString = "{\"key\":\"value\"}";

        // When
        JsonNode jsonNode = JacksonUtils.toJsonNode(jsonString);

        // Then
        assertThat(jsonNode).isNotNull();
        assertThat(jsonNode.get("key").asText()).isEqualTo("value");
    }
}
