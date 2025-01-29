package com.matariky.bo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerTest {

    @InjectMocks
    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer();
        customer.setUsername("testUser");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setBirthday("1990-01-01");
        customer.setGender("Male");
        customer.setGroups(List.of("group1", "group2"));
        customer.setRoles(List.of("role1", "role2"));
    }

    @Test
    void testGetUsername() {
        assertThat(customer.getUsername()).isEqualTo("testUser");
    }

    @Test
    void testGetFirstName() {
        assertThat(customer.getFirstName()).isEqualTo("John");
    }

    @Test
    void testGetLastName() {
        assertThat(customer.getLastName()).isEqualTo("Doe");
    }

    @Test
    void testGetEmail() {
        assertThat(customer.getEmail()).isEqualTo("john.doe@example.com");
    }

    @Test
    void testGetBirthday() {
        assertThat(customer.getBirthday()).isEqualTo("1990-01-01");
    }

    @Test
    void testGetGender() {
        assertThat(customer.getGender()).isEqualTo("Male");
    }

    @Test
    void testGetGroups() {
        assertThat(customer.getGroups()).containsExactly("group1", "group2");
    }

    @Test
    void testGetRoles() {
        assertThat(customer.getRoles()).containsExactly("role1", "role2");
    }

    // Add more test methods for other methods in Customer if needed
}
