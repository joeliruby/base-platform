package com.matariky.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.matariky.commonservice.upload.utils.RenException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@SpringBootTest
public class ValidatorUtilsTest {

    @InjectMocks
    private ValidatorUtils validatorutils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidateEntity_validObject() {
        // Given
        Object validObject = new Object(); // Replace with a valid object
        Validator validator = mock(Validator.class);
        when(validator.validate(validObject)).thenReturn(Set.of());

        // When
        assertDoesNotThrow(() -> validatorutils.validateEntity(validObject));

        // Then
        verify(validator).validate(validObject);
    }

    @Test
    void testValidateEntity_invalidObject() {
        // Given
        Object invalidObject = new Object(); // Replace with an invalid object
        Validator validator = mock(Validator.class);
        ConstraintViolation<Object> violation = mock(ConstraintViolation.class);
        when(violation.getMessage()).thenReturn("Validation error");
        when(validator.validate(invalidObject)).thenReturn(Set.of(violation));

        // When
        RenException exception = assertThrows(RenException.class, () -> validatorutils.validateEntity(invalidObject));

        // Then
        assertEquals("Validation error", exception.getMessage());
        verify(validator).validate(invalidObject);
    }

    // Add more test methods for other methods in ValidatorUtils
}
