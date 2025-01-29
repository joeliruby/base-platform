package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseDeviceRuleAddVOTest {

    @InjectMocks
    private BasicBaseDeviceRuleAddVO basicbasedeviceruleaddvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTypeIdNotNull() {
        // Given
        basicbasedeviceruleaddvo.setTypeId(1L);

        // When
        Long typeId = basicbasedeviceruleaddvo.getTypeId();

        // Then
        assertThat(typeId).isNotNull();
        assertThat(typeId).isEqualTo(1L);
    }

    @Test
    void testTypeIdNull() {
        // Given
        basicbasedeviceruleaddvo.setTypeId(null);

        // When
        Long typeId = basicbasedeviceruleaddvo.getTypeId();

        // Then
        assertThat(typeId).isNull();
    }

    @Test
    void testTypeIdSetter() {
        // Given
        Long expectedTypeId = 2L;

        // When
        basicbasedeviceruleaddvo.setTypeId(expectedTypeId);

        // Then
        assertThat(basicbasedeviceruleaddvo.getTypeId()).isEqualTo(expectedTypeId);
    }

    // Add more test methods for other methods in BasicBaseDeviceRuleAddVO
}
