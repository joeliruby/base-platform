package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class AddExtendFieldInfoVOTest {

    @InjectMocks
    private AddExtendFieldInfoVO addextendfieldinfovo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFieldName() {
        // Given
        String fieldName = "Test Field";
        addextendfieldinfovo.setFieldName(fieldName);

        // When
        String result = addextendfieldinfovo.getFieldName();

        // Then
        assertThat(result).isEqualTo(fieldName);
    }

    @Test
    void testFieldType() {
        // Given
        String fieldType = "String";
        addextendfieldinfovo.setFieldType(fieldType);

        // When
        String result = addextendfieldinfovo.getFieldType();

        // Then
        assertThat(result).isEqualTo(fieldType);
    }

    @Test
    void testIsRequired() {
        // Given
        Boolean isRequired = true;
        addextendfieldinfovo.setIsRequired(isRequired);

        // When
        Boolean result = addextendfieldinfovo.getIsRequired();

        // Then
        assertThat(result).isEqualTo(isRequired);
    }

    @Test
    void testId() {
        // Given
        Long id = 123L;
        addextendfieldinfovo.setId(id);

        // When
        Long result = addextendfieldinfovo.getId();

        // Then
        assertThat(result).isEqualTo(id);
    }

    // Add more test methods for other methods in AddExtendFieldInfoVO if any
}
