package com.matariky.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.JsonMappingException;

@SpringBootTest
public class WebDataConvertConfigTest {

    @InjectMocks
    private WebDataConvertConfig webdataconvertconfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConfigureMessageConverters() {
        // Given
        List<HttpMessageConverter<?>> converters = new ArrayList<>();

        // When
        webdataconvertconfig.configureMessageConverters(converters);

        // Then
        assertThat(converters).hasSize(1);
        assertThat(converters.get(0)).isInstanceOf(MappingJackson2HttpMessageConverter.class);
        MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) converters.get(0);
        try {
            assertThat(converter.getObjectMapper().getSerializerProvider().findValueSerializer(Long.class)).isNotNull();
        } catch (JsonMappingException e) {
            fail("JsonMappingException was thrown: " + e.getMessage());
        }
    }

    // Add more test methods for other methods in WebDataConvertConfig if needed
}
