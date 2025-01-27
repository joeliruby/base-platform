package com.matariky.user.customer;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RoleModel;
import org.keycloak.models.cache.infinispan.RealmCacheSession;
import org.keycloak.models.cache.infinispan.entities.CachedRealm;

public class CustomerRealmAdapter extends org.keycloak.models.cache.infinispan.RealmAdapter {
	protected Map<String, CustomerRealmAdapter> managedRealms = new HashMap<>();

	public CustomerRealmAdapter(KeycloakSession session, CachedRealm cached, RealmCacheSession cacheSession) {
		super(session, cached, cacheSession);
	}

	@Override
	public Stream<RoleModel> getRolesStream() {
		return cacheSession.getRealmRolesStream(this);
	}

	@Override
	public Stream<RoleModel> getRolesStream(Integer first, Integer max) {
		return cacheSession.getRealmRolesStream(this, first, max);
	}

	@Override
	public Stream<RoleModel> searchForRolesStream(String search, Integer first, Integer max) {
		return cacheSession.searchForRolesStream(this, search, first, max);
	}

	protected void invalidateFlag() {
		super.invalidated = true;

	}

}
