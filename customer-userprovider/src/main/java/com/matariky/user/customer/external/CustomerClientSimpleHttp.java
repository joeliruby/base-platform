package com.matariky.user.customer.external;

import com.fasterxml.jackson.core.type.TypeReference;
import com.matariky.user.customer.Constants;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.keycloak.broker.provider.util.SimpleHttp;
import org.keycloak.component.ComponentModel;
import org.keycloak.connections.httpclient.HttpClientProvider;
import org.keycloak.models.KeycloakSession;

import java.util.List;

/**
 * @author matariky
 */
@Slf4j
public class CustomerClientSimpleHttp implements CustomerClient {

	private final CloseableHttpClient httpClient;
	private final String baseUrl;
	private final String basicUsername;
	private final String basicPassword;

	public CustomerClientSimpleHttp(KeycloakSession session, ComponentModel model) {
		this.httpClient = session.getProvider(HttpClientProvider.class).getHttpClient();
		this.baseUrl = model.get(Constants.BASE_URL);
		this.basicUsername = model.get(Constants.AUTH_USERNAME);
		this.basicPassword = model.get(Constants.AUTH_PASSWORD);
	}

	@Override
	@SneakyThrows
	public List<Customer> getcustomer(String search, int first, int max) {
		SimpleHttp simpleHttp = SimpleHttp.doGet(baseUrl, httpClient).authBasic(basicUsername, basicPassword)
			.param("first", String.valueOf(first))
			.param("max", String.valueOf(max));
		if (search != null) {
			simpleHttp.param("search", search);
		}
		return simpleHttp.asJson(new TypeReference<>() {});
	}

	@Override
	@SneakyThrows
	public Integer getcustomerCount() {
		String url = String.format("%s/count", baseUrl);
		String count = SimpleHttp.doGet(url, httpClient).authBasic(basicUsername, basicPassword).asString();
		return Integer.valueOf(count);
	}

	@Override
	@SneakyThrows
	public Customer getCustomerById(String id) {
		String url = String.format("%s/%s", baseUrl, id);
		SimpleHttp  sh=SimpleHttp.doGet(url, httpClient).authBasic(basicUsername, basicPassword);
		SimpleHttp.Response response =sh.asResponse();
		if (response.getStatus() == 404) {
			throw new WebApplicationException(response.getStatus());
		}
		return response.asJson(Customer.class);
	}

	@Override
	@SneakyThrows
	public CredentialData getCredentialData(String id) {
		String url = String.format("%s/%s/credentials", baseUrl, id);
		SimpleHttp  sh=SimpleHttp.doGet(url, httpClient).authBasic(basicUsername, basicPassword);
		SimpleHttp.Response response =sh.asResponse();
		if (response.getStatus() == 404) {
			throw new WebApplicationException(response.getStatus());
		}
		return response.asJson(CredentialData.class);
	}

	@Override
	@SneakyThrows
	public Response updateCredentialData(String id, CredentialData credentialData) {
		String url = String.format("%s/%s/credentials", baseUrl, id);
		int status = SimpleHttp.doPut(url, httpClient).authBasic(basicUsername, basicPassword).json(credentialData).asStatus();
		return Response.status(status).build();
	}

}
