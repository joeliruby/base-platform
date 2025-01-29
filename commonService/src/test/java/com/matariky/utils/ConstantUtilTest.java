package com.matariky.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.constant.PermissionConstant;

@SpringBootTest
public class ConstantUtilTest {

    @InjectMocks
    private ConstantUtil constantUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllConstantValuesByCategory() {
        // Given
        String category = "RESOURCE_TYPE";
        Class<PermissionConstant> clazz = PermissionConstant.class;

        // When
        List<Object> result = ConstantUtil.getAllConstantValuesByCategory(clazz, category);

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        // Add more assertions based on the expected values in PermissionConstant
    }

    @Test
    void testGetAllConstantValuesByCategoryWithEmptyCategory() {
        // Given
        String category = "";
        Class<PermissionConstant> clazz = PermissionConstant.class;

        // When
        List<Object> result = ConstantUtil.getAllConstantValuesByCategory(clazz, category);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllConstantValuesByCategoryWithNonExistentCategory() {
        // Given
        String category = "NON_EXISTENT_CATEGORY";
        Class<PermissionConstant> clazz = PermissionConstant.class;

        // When
        List<Object> result = ConstantUtil.getAllConstantValuesByCategory(clazz, category);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    // Add more test methods for other scenarios if needed
}
