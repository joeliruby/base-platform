package com.matariky.user.customer.external;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.ws.rs.core.Response;

@SpringBootTest
public class CustomerClientTest {

    @InjectMocks
    private CustomerClient customerclient;

    @Mock
    private CustomerClient mockCustomerClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomer() {
        // Given
        String search = "test";
        int first = 0;
        int max = 10;
        List<Customer> expectedCustomers = Arrays.asList(new Customer(), new Customer());
        when(mockCustomerClient.getcustomer(search, first, max)).thenReturn(expectedCustomers);

        // When
        List<Customer> actualCustomers = customerclient.getcustomer(search, first, max);

        // Then
        assertEquals(expectedCustomers, actualCustomers);
    }

    @Test
    void testGetCustomerCount() {
        // Given
        Integer expectedCount = 5;
        when(mockCustomerClient.getcustomerCount()).thenReturn(expectedCount);

        // When
        Integer actualCount = customerclient.getcustomerCount();

        // Then
        assertEquals(expectedCount, actualCount);
    }

    @Test
    void testGetCustomerById() {
        // Given
        String id = "123";
        Customer expectedCustomer = new Customer();
        when(mockCustomerClient.getCustomerById(id)).thenReturn(expectedCustomer);

        // When
        Customer actualCustomer = customerclient.getCustomerById(id);

        // Then
        assertEquals(expectedCustomer, actualCustomer);
    }

    @Test
    void testGetCredentialData() {
        // Given
        String id = "123";
        CredentialData expectedCredentialData = new CredentialData();
        when(mockCustomerClient.getCredentialData(id)).thenReturn(expectedCredentialData);

        // When
        CredentialData actualCredentialData = customerclient.getCredentialData(id);

        // Then
        assertEquals(expectedCredentialData, actualCredentialData);
    }

    @Test
    void testUpdateCredentialData() {
        // Given
        String id = "123";
        CredentialData credentialData = new CredentialData();
        Response expectedResponse = Response.ok().build();
        when(mockCustomerClient.updateCredentialData(id, credentialData)).thenReturn(expectedResponse);

        // When
        Response actualResponse = customerclient.updateCredentialData(id, credentialData);

        // Then
        assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
    }
}
