package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class BasicBaseDeviceRuleDetailUpdateDTOTest {

    @InjectMocks
    private BasicBaseDeviceRuleDetailUpdateDTO basicbasedeviceruledetailupdatedto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFilterConditions() {
        // Given
        String filterConditions = "some filter condition";
        basicbasedeviceruledetailupdatedto.setFilterConditions(filterConditions);

        // When
        String result = basicbasedeviceruledetailupdatedto.getFilterConditions();

        // Then
        assertThat(result).isEqualTo(filterConditions);
    }

    @Test
    void testGetConditionSetting() {
        // Given
        String conditionSetting = "some condition setting";
        basicbasedeviceruledetailupdatedto.setConditionSetting(conditionSetting);

        // When
        String result = basicbasedeviceruledetailupdatedto.getConditionSetting();

        // Then
        assertThat(result).isEqualTo(conditionSetting);
    }

    @Test
    void testGetSetValue() {
        // Given
        String setValue = "some set value";
        basicbasedeviceruledetailupdatedto.setSetValue(setValue);

        // When
        String result = basicbasedeviceruledetailupdatedto.getSetValue();

        // Then
        assertThat(result).isEqualTo(setValue);
    }

    @Test
    void testGetId() {
        // Given
        Long id = 123L;
        basicbasedeviceruledetailupdatedto.setId(id);

        // When
        Long result = basicbasedeviceruledetailupdatedto.getId();

        // Then
        assertThat(result).isEqualTo(id);
    }

    // Add more test methods for other methods in BasicBaseDeviceRuleDetailUpdateDTO
}
