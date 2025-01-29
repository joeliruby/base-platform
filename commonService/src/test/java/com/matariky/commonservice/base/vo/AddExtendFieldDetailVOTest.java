package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class AddExtendFieldDetailVOTest {

    @InjectMocks
    private AddExtendFieldDetailVO addextendfielddetailvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFieldNameNotBlank() {
        // Given
        addextendfielddetailvo.setFieldName("Test Field");

        // When
        String fieldName = addextendfielddetailvo.getFieldName();

        // Then
        assertThat(fieldName).isNotBlank();
    }

    @Test
    void testFieldTypeNotBlank() {
        // Given
        addextendfielddetailvo.setFieldType("String");

        // When
        String fieldType = addextendfielddetailvo.getFieldType();

        // Then
        assertThat(fieldType).isNotBlank();
    }

    @Test
    void testIsRequiredDefaultValue() {
        // Given
        // No initialization needed

        // When
        Boolean isRequired = addextendfielddetailvo.getIsRequired();

        // Then
        assertThat(isRequired).isFalse();
    }

    @Test
    void testDisableField() {
        // Given
        addextendfielddetailvo.setDisable(true);

        // When
        Boolean disable = addextendfielddetailvo.getDisable();

        // Then
        assertThat(disable).isTrue();
    }

    @Test
    void testFieldMap() {
        // Given
        addextendfielddetailvo.setFieldMap("mapValue");

        // When
        String fieldMap = addextendfielddetailvo.getFieldMap();

        // Then
        assertThat(fieldMap).isEqualTo("mapValue");
    }

    // Add more test methods for other methods in AddExtendFieldDetailVO
}
