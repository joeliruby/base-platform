package com.matariky.user.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.user.customer.external.Customer;

@SpringBootTest
public class UserAdapterTest {

    @InjectMocks
    private UserAdapter userAdapter;

    @Mock
    private KeycloakSession session;

    @Mock
    private RealmModel realm;

    @Mock
    private ComponentModel model;

    @Mock
    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(customer.getUsername()).thenReturn("testuser");
        when(customer.getFirstName()).thenReturn("Test");
        when(customer.getLastName()).thenReturn("User");
        when(customer.getEmail()).thenReturn("testuser@example.com");
        when(customer.getBirthday()).thenReturn("2000-01-01");
        when(customer.getGender()).thenReturn("male");
        userAdapter = new UserAdapter(session, realm, model, customer);
    }

    @Test
    void testGetUsername() {
        assertThat(userAdapter.getUsername()).isEqualTo("testuser");
    }

    @Test
    void testGetFirstName() {
        assertThat(userAdapter.getFirstName()).isEqualTo("Test");
    }

    @Test
    void testGetLastName() {
        assertThat(userAdapter.getLastName()).isEqualTo("User");
    }

    @Test
    void testGetEmail() {
        assertThat(userAdapter.getEmail()).isEqualTo("testuser@example.com");
    }

    @Test
    void testGetAttributes() {
        Map<String, List<String>> attributes = userAdapter.getAttributes();
        assertThat(attributes.get(UserModel.USERNAME)).containsExactly("testuser");
        assertThat(attributes.get(UserModel.EMAIL)).containsExactly("testuser@example.com");
        assertThat(attributes.get(UserModel.FIRST_NAME)).containsExactly("Test");
        assertThat(attributes.get(UserModel.LAST_NAME)).containsExactly("User");
        assertThat(attributes.get("birthday")).containsExactly("2000-01-01");
        assertThat(attributes.get("gender")).containsExactly("male");
    }

    @Test
    void testGetFirstAttribute() {
        assertThat(userAdapter.getFirstAttribute(UserModel.USERNAME)).isEqualTo("testuser");
        assertThat(userAdapter.getFirstAttribute("nonexistent")).isNull();
    }

    // Add more test methods for other methods in UserAdapter
}
