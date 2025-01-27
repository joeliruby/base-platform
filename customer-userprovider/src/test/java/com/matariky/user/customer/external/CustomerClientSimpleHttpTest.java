package com.matariky.user.customer.external;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keycloak.broker.provider.util.SimpleHttp;
import org.keycloak.component.ComponentModel;
import org.keycloak.connections.httpclient.HttpClientProvider;
import org.keycloak.models.KeycloakSession;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.matariky.user.customer.Constants;

import jakarta.ws.rs.core.Response;

@SpringBootTest
public class CustomerClientSimpleHttpTest {

    @InjectMocks
    private CustomerClientSimpleHttp customerclientsimplehttp;

    @Mock
    private KeycloakSession session;

    @Mock
    private ComponentModel model;

    @Mock
    private CloseableHttpClient httpClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(session.getProvider(HttpClientProvider.class)).thenReturn(mock(HttpClientProvider.class));
        when(session.getProvider(HttpClientProvider.class).getHttpClient()).thenReturn(httpClient);
        when(model.get(Constants.BASE_URL)).thenReturn("http://example.com");
        when(model.get(Constants.AUTH_USERNAME)).thenReturn("username");
        when(model.get(Constants.AUTH_PASSWORD)).thenReturn("password");
    }

    @Test
    void testGetCustomer() throws Exception {
        // Given
        SimpleHttp simpleHttp = mock(SimpleHttp.class);
        when(SimpleHttp.doGet(anyString(), any(CloseableHttpClient.class))).thenReturn(simpleHttp);
        when(simpleHttp.authBasic(anyString(), anyString())).thenReturn(simpleHttp);
        when(simpleHttp.param(anyString(), anyString())).thenReturn(simpleHttp);
        when(simpleHttp.asJson(any(TypeReference.class))).thenReturn(List.of(new Customer()));

        // When
        List<Customer> result = customerclientsimplehttp.getcustomer("search", 0, 10);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetCustomerCount() throws Exception {
        // Given
        SimpleHttp simpleHttp = mock(SimpleHttp.class);
        when(SimpleHttp.doGet(anyString(), any(CloseableHttpClient.class))).thenReturn(simpleHttp);
        when(simpleHttp.authBasic(anyString(), anyString())).thenReturn(simpleHttp);
        when(simpleHttp.asString()).thenReturn("5");

        // When
        Integer result = customerclientsimplehttp.getcustomerCount();

        // Then
        assertNotNull(result);
        assertEquals(5, result);
    }

    @Test
    void testGetCustomerById() throws Exception {
        // Given
        SimpleHttp simpleHttp = mock(SimpleHttp.class);
        SimpleHttp.Response response = mock(SimpleHttp.Response.class);
        when(SimpleHttp.doGet(anyString(), any(CloseableHttpClient.class))).thenReturn(simpleHttp);
        when(simpleHttp.authBasic(anyString(), anyString())).thenReturn(simpleHttp);
        when(simpleHttp.asResponse()).thenReturn(response);
        when(response.getStatus()).thenReturn(200);
        when(response.asJson(Customer.class)).thenReturn(new Customer());

        // When
        Customer result = customerclientsimplehttp.getCustomerById("1");

        // Then
        assertNotNull(result);
    }

    @Test
    void testGetCredentialData() throws Exception {
        // Given
        SimpleHttp simpleHttp = mock(SimpleHttp.class);
        SimpleHttp.Response response = mock(SimpleHttp.Response.class);
        when(SimpleHttp.doGet(anyString(), any(CloseableHttpClient.class))).thenReturn(simpleHttp);
        when(simpleHttp.authBasic(anyString(), anyString())).thenReturn(simpleHttp);
        when(simpleHttp.asResponse()).thenReturn(response);
        when(response.getStatus()).thenReturn(200);
        when(response.asJson(CredentialData.class)).thenReturn(new CredentialData());

        // When
        CredentialData result = customerclientsimplehttp.getCredentialData("1");

        // Then
        assertNotNull(result);
    }

    @Test
    void testUpdateCredentialData() throws Exception {
        // Given
        SimpleHttp simpleHttp = mock(SimpleHttp.class);
        when(SimpleHttp.doPut(anyString(), any(CloseableHttpClient.class))).thenReturn(simpleHttp);
        when(simpleHttp.authBasic(anyString(), anyString())).thenReturn(simpleHttp);
        when(simpleHttp.json(any(CredentialData.class))).thenReturn(simpleHttp);
        when(simpleHttp.asStatus()).thenReturn(200);

        // When
        Response result = customerclientsimplehttp.updateCredentialData("1", new CredentialData());

        // Then
        assertNotNull(result);
        assertEquals(200, result.getStatus());
    }

    // Add more test methods for other methods in CustomerClientSimpleHttp if needed
}
