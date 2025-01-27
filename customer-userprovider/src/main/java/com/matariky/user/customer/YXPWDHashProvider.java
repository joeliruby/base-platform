package com.matariky.user.customer;

import org.keycloak.Config.Scope;
import org.keycloak.credential.hash.PasswordHashProvider;
import org.keycloak.credential.hash.PasswordHashProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.models.PasswordPolicy;
import org.keycloak.models.credential.PasswordCredentialModel;

import com.matariky.user.customer.utils.EncryptionUtils;

public class YXPWDHashProvider implements PasswordHashProviderFactory, PasswordHashProvider {
	public static final String ID = "custom";

	@Override
	public PasswordHashProvider create(KeycloakSession session) {
		return this;
	}

	@Override
	public void init(Scope config) {

	}

	@Override
	public void postInit(KeycloakSessionFactory factory) {

	}

	@Override
	public void close() {

	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public boolean policyCheck(PasswordPolicy policy, PasswordCredentialModel credential) {
		return credential.getHashIterations() == policy.getHashIterations() && ID.equals(credential.getAlgorithm());
	}

	@Override
	public PasswordCredentialModel encodedCredential(String rawPassword, int iterations) {
		return null;
	}

	@Override
	public boolean verify(String rawPassword, PasswordCredentialModel credential) {
		String encodedPassword = encode(rawPassword, credential.getHashIterations(), credential.getSalt());
		return encodedPassword.equals(credential.getValue());
	}

	private String encode(String rawPassword, int iterations, byte[] salt) {
		return EncryptionUtils.getHash3(rawPassword, "SHA");
	}

}
