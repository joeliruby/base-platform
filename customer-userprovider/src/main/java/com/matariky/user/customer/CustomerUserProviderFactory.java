package com.matariky.user.customer;

import org.keycloak.component.ComponentModel;
import org.keycloak.component.ComponentValidationException;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;
import org.keycloak.storage.UserStorageProviderFactory;
import org.keycloak.utils.StringUtil;

import java.util.List;

/**
 * @author matariky
 */
public class CustomerUserProviderFactory implements UserStorageProviderFactory<CustomerUserProvider> {

	public static final String PROVIDER_ID = "customer-user-provider";

	@Override
	public CustomerUserProvider create(KeycloakSession session, ComponentModel model) {
		return new CustomerUserProvider(session, model);
	}

	@Override
	public String getId() {
		return PROVIDER_ID;
	}

	@Override
	public String getHelpText() {
		return "customer User Provider";
	}

	@Override
	public List<ProviderConfigProperty> getConfigProperties() {
		return ProviderConfigurationBuilder.create()
			.property(Constants.BASE_URL, "Base URL", "Base URL of the API", ProviderConfigProperty.STRING_TYPE, "", null)
			.property(Constants.AUTH_USERNAME, "BasicAuth Username", "Username for BasicAuth at the API", ProviderConfigProperty.STRING_TYPE, "", null)
			.property(Constants.AUTH_PASSWORD, "BasicAuth Password", "Password for BasicAuth at the API", ProviderConfigProperty.PASSWORD, "", null)
			.build();
	}

	@Override
	public void validateConfiguration(KeycloakSession session, RealmModel realm, ComponentModel config) throws ComponentValidationException {
		if (StringUtil.isBlank(config.get(Constants.BASE_URL))
			|| StringUtil.isBlank(config.get(Constants.AUTH_USERNAME))
			|| StringUtil.isBlank(config.get(Constants.AUTH_PASSWORD))) {
			throw new ComponentValidationException("Configuration not properly set, please verify.");
		}
	}
}
