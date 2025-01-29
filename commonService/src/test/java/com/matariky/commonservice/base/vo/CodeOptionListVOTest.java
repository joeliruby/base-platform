package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CodeOptionListVOTest {

    @InjectMocks
    private CodeOptionListVO codeoptionlistvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTypeId() {
        // Given
        Long expectedTypeId = 123L;
        codeoptionlistvo.setTypeId(expectedTypeId);

        // When
        Long actualTypeId = codeoptionlistvo.getTypeId();

        // Then
        assertThat(actualTypeId).isEqualTo(expectedTypeId);
    }

    @Test
    void testSetTypeId() {
        // Given
        Long expectedTypeId = 456L;

        // When
        codeoptionlistvo.setTypeId(expectedTypeId);

        // Then
        assertThat(codeoptionlistvo.getTypeId()).isEqualTo(expectedTypeId);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        CodeOptionListVO vo1 = new CodeOptionListVO();
        vo1.setTypeId(789L);
        CodeOptionListVO vo2 = new CodeOptionListVO();
        vo2.setTypeId(789L);

        // When & Then
        assertThat(vo1).isEqualTo(vo2);
        assertThat(vo1.hashCode()).isEqualTo(vo2.hashCode());
    }

    // Add more test methods for other methods in CodeOptionListVO
}
