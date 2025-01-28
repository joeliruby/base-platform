package com.matariky.userservice.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class UserApplicationDTOTest {

    @InjectMocks
    private UserApplicationDTO userapplicationdto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPermissions() {
        // Given
        List<Long> permissions = Arrays.asList(1L, 2L, 3L);
        userapplicationdto.setPermissions(permissions);

        // When
        List<Long> result = userapplicationdto.getPermissions();

        // Then
        assertThat(result).isEqualTo(permissions);
    }

    @Test
    void testSetPermissions() {
        // Given
        List<Long> permissions = Arrays.asList(4L, 5L, 6L);

        // When
        userapplicationdto.setPermissions(permissions);

        // Then
        assertThat(userapplicationdto.getPermissions()).isEqualTo(permissions);
    }

    @Test
    void testNextId() {
        // Given
        Object entity = new Object();

        // When
        Number result = userapplicationdto.nextId(entity);

        // Then
        assertNull(result);
    }

    // Add more test methods for other methods in UserApplicationDTO
}
