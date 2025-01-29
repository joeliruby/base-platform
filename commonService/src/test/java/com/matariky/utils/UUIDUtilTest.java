package com.matariky.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UUIDUtilTest {

    @InjectMocks
    private UUIDUtil uuidutil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUUID() {
        // When
        String uuid = UUIDUtil.getUUID();

        // Then
        assertThat(uuid).isNotNull();
        assertThat(uuid).hasSize(32);
        assertThat(uuid).doesNotContain("-");
    }

    // Add more test methods for other methods in UUIDUtil if needed
}
