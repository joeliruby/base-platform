package com.matariky.userservice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.userservice.bean.Role;

@SpringBootTest
public class RoleMapperTest {

    @InjectMocks
    private RoleMapper rolemapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertSelective() {
        // Given
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("Admin");
        when(rolemapper.insertSelective(role)).thenReturn(1);

        // When
        int result = rolemapper.insertSelective(role);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testSelectByPrimaryKey() {
        // Given
        Long id = 1L;
        Role role = new Role();
        role.setId(id);
        role.setRoleName("Admin");
        when(rolemapper.selectByPrimaryKey(id)).thenReturn(role);

        // When
        Role result = rolemapper.selectByPrimaryKey(id);

        // Then
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void testUpdateByPrimaryKeySelective() {
        // Given
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("Admin");
        when(rolemapper.updateByPrimaryKeySelective(role)).thenReturn(1);

        // When
        int result = rolemapper.updateByPrimaryKeySelective(role);

        // Then
        assertEquals(1, result);
    }

    @Test
    void testDeleteByPrimaryKey() {
        // Given
        Long id = 1L;
        when(rolemapper.deleteByPrimaryKey(id)).thenReturn(1);

        // When
        int result = rolemapper.deleteByPrimaryKey(id);

        // Then
        assertEquals(1, result);
    }

    // Add more test methods for other methods in RoleMapper
}
