package com.matariky.user.customer;

import org.keycloak.models.RealmModel;
import org.keycloak.models.cache.infinispan.RealmCacheSession;
import org.keycloak.models.cache.infinispan.RoleAdapter;
import org.keycloak.models.cache.infinispan.entities.CachedRole;
public class CustomerRoleAdapter extends RoleAdapter {
	 public CustomerRoleAdapter(CachedRole cached, RealmCacheSession session, RealmModel realm) {
		 super(cached, session, realm);
	 }
}
