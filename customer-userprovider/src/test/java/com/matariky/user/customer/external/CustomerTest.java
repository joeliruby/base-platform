package com.matariky.user.customer.external;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;

@SpringBootTest
public class CustomerTest {

    @InjectMocks
    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer.setUsername("testUser");
        customer.setFirstName("Test");
        customer.setLastName("User");
        customer.setEmail("testuser@example.com");
        customer.setBirthday("2000-01-01");
        customer.setGender("Male");
        customer.setGroups(Arrays.asList("group1", "group2"));
        customer.setRoles(Arrays.asList("role1", "role2"));
    }

    @Test
    void testGetUsername() {
        assertEquals("testUser", customer.getUsername());
    }

    @Test
    void testGetFirstName() {
        assertEquals("Test", customer.getFirstName());
    }

    @Test
    void testGetLastName() {
        assertEquals("User", customer.getLastName());
    }

    @Test
    void testGetEmail() {
        assertEquals("testuser@example.com", customer.getEmail());
    }

    @Test
    void testGetBirthday() {
        assertEquals("2000-01-01", customer.getBirthday());
    }

    @Test
    void testGetGender() {
        assertEquals("Male", customer.getGender());
    }

    @Test
    void testGetGroups() {
        assertEquals(Arrays.asList("group1", "group2"), customer.getGroups());
    }

    @Test
    void testGetRoles() {
        assertEquals(Arrays.asList("role1", "role2"), customer.getRoles());
    }

    // Add more test methods for other methods in Customer if needed
}
