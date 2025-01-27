package com.matariky.user.customer;

import org.infinispan.Cache;
import org.keycloak.cluster.ClusterEvent;
import org.keycloak.cluster.ClusterProvider;
import org.keycloak.connections.infinispan.InfinispanConnectionProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.cache.CacheRealmProvider;
import org.keycloak.models.cache.infinispan.InfinispanCacheRealmProviderFactory;
import org.keycloak.models.cache.infinispan.RealmCacheManager;
import org.keycloak.models.cache.infinispan.entities.Revisioned;
import org.keycloak.models.cache.infinispan.events.InvalidationEvent;

public class CustomerRoleProviderFactory extends InfinispanCacheRealmProviderFactory {

	@Override
	public CacheRealmProvider create(KeycloakSession session) {
		lazyInit(session);
		return new CustomerRoleProvider(realmCache, session);
	}

	private void lazyInit(KeycloakSession session) {
		if (realmCache == null) {
			synchronized (this) {
				if (realmCache == null) {
					Cache<String, Revisioned> cache = session.getProvider(InfinispanConnectionProvider.class)
							.getCache(InfinispanConnectionProvider.REALM_CACHE_NAME);
					Cache<String, Long> revisions = session.getProvider(InfinispanConnectionProvider.class)
							.getCache(InfinispanConnectionProvider.REALM_REVISIONS_CACHE_NAME);
					realmCache = new RealmCacheManager(cache, revisions);

					ClusterProvider cluster = session.getProvider(ClusterProvider.class);
					cluster.registerListener(REALM_INVALIDATION_EVENTS, (ClusterEvent event) -> {

						InvalidationEvent invalidationEvent = (InvalidationEvent) event;
						realmCache.invalidationEventReceived(invalidationEvent);

					});

					cluster.registerListener(REALM_CLEAR_CACHE_EVENTS, (ClusterEvent event) -> {

						realmCache.clear();

					});

				}
			}
		}
	}
}
