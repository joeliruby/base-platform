package com.matariky.commonservice.commondict.constant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class DictTypeKeyTest {

    @InjectMocks
    private DictTypeKey dicttypekey;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFullKeyWithNonEmptyLocal() {
        // Given
        String local = "FR";
        String key = DictTypeKey.READER_CONFIG;

        // When
        String result = DictTypeKey.getFullKey(local, key);

        // Then
        assertThat(result).isEqualTo("FR" + key);
    }

    @Test
    void testGetFullKeyWithEmptyLocal() {
        // Given
        String local = "";
        String key = DictTypeKey.SERVICE_MESSAGE;

        // When
        String result = DictTypeKey.getFullKey(local, key);

        // Then
        assertThat(result).isEqualTo("EN" + key);
    }

    @Test
    void testGetFullKeyWithNullLocal() {
        // Given
        String local = null;
        String key = DictTypeKey.LIBRARY_TYPE;

        // When
        String result = DictTypeKey.getFullKey(local, key);

        // Then
        assertThat(result).isEqualTo("EN" + key);
    }

    // Add more test methods for other methods in DictTypeKey if needed
}
