package com.matariky.bizservice.config;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.matariky.id.SnowflakeIdGenerator;

@SpringBootTest
public class MybatisPlusConfiguarationTest {

    @InjectMocks
    private MybatisPlusConfiguaration mybatisplusconfiguaration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlusPropertiesCustomizer() {
        // Given
        SnowflakeIdGenerator mockGenerator = mock(SnowflakeIdGenerator.class);
        when(mockGenerator.getWorkerIdByIP()).thenReturn(1L);

        // When
        MybatisPlusPropertiesCustomizer customizer = mybatisplusconfiguaration.plusPropertiesCustomizer();

        // Then
        assertThat(customizer).isNotNull();
        // Additional assertions can be added here to verify the behavior of the
        // customizer
    }

    // Add more test methods for other methods in MybatisPlusConfiguaration
}
