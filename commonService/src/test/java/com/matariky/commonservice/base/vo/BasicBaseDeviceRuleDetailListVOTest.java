package com.matariky.commonservice.base.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseDeviceRuleDetailListVOTest {

    @InjectMocks
    private BasicBaseDeviceRuleDetailListVO basicbasedeviceruledetaillistvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTypeIdNotNull() {
        // Given
        basicbasedeviceruledetaillistvo.setTypeId(1L);

        // When
        Long typeId = basicbasedeviceruledetaillistvo.getTypeId();

        // Then
        assertNotNull(typeId);
        assertThat(typeId).isEqualTo(1L);
    }

    @Test
    void testRuleId() {
        // Given
        basicbasedeviceruledetaillistvo.setRuleId(2L);

        // When
        Long ruleId = basicbasedeviceruledetaillistvo.getRuleId();

        // Then
        assertThat(ruleId).isEqualTo(2L);
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        BasicBaseDeviceRuleDetailListVO anotherInstance = new BasicBaseDeviceRuleDetailListVO();
        anotherInstance.setTypeId(1L);
        anotherInstance.setRuleId(2L);
        basicbasedeviceruledetaillistvo.setTypeId(1L);
        basicbasedeviceruledetaillistvo.setRuleId(2L);

        // When & Then
        assertThat(basicbasedeviceruledetaillistvo).isEqualTo(anotherInstance);
        assertThat(basicbasedeviceruledetaillistvo.hashCode()).isEqualTo(anotherInstance.hashCode());
    }

    // Add more test methods for other methods in BasicBaseDeviceRuleDetailListVO
}
