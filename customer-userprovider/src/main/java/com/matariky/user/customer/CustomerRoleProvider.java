package com.matariky.user.customer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.keycloak.component.ComponentModel;
import org.keycloak.models.ClientModel;
//import org.keycloak.models.ClientModel;
import org.keycloak.models.ClientProvider;
import org.keycloak.models.ClientScopeProvider;
import org.keycloak.models.GroupProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RealmProvider;
import org.keycloak.models.RoleModel;
import org.keycloak.models.RoleProvider;
import org.keycloak.models.cache.CachedRealmModel;
import org.keycloak.models.cache.infinispan.ClientScopeAdapter;
import org.keycloak.models.cache.infinispan.GroupAdapter;
import org.keycloak.models.cache.infinispan.RealmAdapter;
import org.keycloak.models.cache.infinispan.RealmCacheManager;
import org.keycloak.models.cache.infinispan.RealmCacheSession;
import org.keycloak.models.cache.infinispan.entities.CachedClient;
import org.keycloak.models.cache.infinispan.entities.CachedClientRole;
import org.keycloak.models.cache.infinispan.entities.CachedRealm;
import org.keycloak.models.cache.infinispan.entities.CachedRealmRole;
import org.keycloak.models.cache.infinispan.entities.CachedRole;
import org.keycloak.models.cache.infinispan.entities.RoleListQuery;
import org.keycloak.models.cache.infinispan.events.RealmUpdatedEvent;
//import com.matariky.user.customer.CustomerRoleAdapter;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.client.ClientStorageProviderModel;

public class CustomerRoleProvider extends RealmCacheSession implements  RealmProvider, ClientProvider, ClientScopeProvider, GroupProvider, RoleProvider {
	protected Map<String, CustomerRoleAdapter> managedRoles = new HashMap<>();
	public CustomerRoleProvider(RealmCacheManager cache, KeycloakSession session)	{
		
		super (cache, session);
	}
	
	 static String getRolesCacheKey(String container) {
	        return container + ROLES_QUERY_SUFFIX;
	    }
	
	@Override
    public RealmModel getRealm(String id) {
        if (invalidations.contains(id)) {
            return getRealmDelegate().getRealm(id);
        } else if (managedRealms.containsKey(id)) {
            return managedRealms.get(id);
        }
        CachedRealm cached = cache.get(id, CachedRealm.class);
        RealmAdapter adapter;
        if (cached != null) {
            logger.tracev("by id cache hit: {0}", cached.getName());
            adapter = new CustomerRealmAdapter(session, cached, this);
        } else {
            adapter = cache.computeSerialized(session, id, this::prepareCachedRealm);
            if (adapter == null) {
                return null;
            }
        }
        managedRealms.put(id, adapter);
        return adapter;
    }
	
	
	 @Override
	    public RoleModel getRoleById(RealmModel realm, String id) {
	        CachedRole cached = cache.get(id, CachedRole.class);
	        if (cached != null && !cached.getRealm().equals(realm.getId())) {
	            cached = null;
	        }

	        if (cached == null) {
	            Long loaded = cache.getCurrentRevision(id);
	            RoleModel model = getRoleDelegate().getRoleById(realm, id);
	            if (model == null) return null;
	            if (invalidations.contains(id)) return model;
	            if (model.isClientRole()) {
	                cached = new CachedClientRole(loaded, model.getContainerId(), model, realm);
	            } else {
	                cached = new CachedRealmRole(loaded, model, realm);
	            }
	            cache.addRevisioned(cached, startupRevision);

	        } else if (invalidations.contains(id)) {
	            return getRoleDelegate().getRoleById(realm, id);
	        } else if (managedRoles.containsKey(id)) {
	            return managedRoles.get(id);
	        }
	        CustomerRoleAdapter adapter = new CustomerRoleAdapter(cached,this, realm);
	        managedRoles.put(id, adapter);
	        return adapter;
	    }
	 
	 private void invalidateRole(String id) {
	        invalidations.add(id);
	        CustomerRoleAdapter adapter =(CustomerRoleAdapter) managedRoles.get(id);
	        if (adapter != null) adapter.invalidate();
	 }
	 
	 @Override
	 public RealmProvider getRealmDelegate() {
		return session.getProvider(RealmProvider.class);
	 }
	 
	 
	 private void roleRemovalInvalidations(String roleId, String roleName, String roleContainerId) {
	        Set<String> newInvalidations = new HashSet<>();
	        cache.roleRemoval(roleId, roleName, roleContainerId, newInvalidations);
	        invalidations.addAll(newInvalidations);
	        // need to make sure that scope and group mapping clients and groups are invalidated
	        for (String id : newInvalidations) {
	           ClientModel adapter = managedApplications.get(id);
	            if (adapter != null && adapter instanceof CustomerClientAdapter){
	                ((CustomerClientAdapter)adapter).invalidate();
	                continue;
	            }
	            GroupAdapter group = managedGroups.get(id);
	            if (group != null) {
	                group.invalidate();
	                continue;
	            }
	            ClientScopeAdapter clientScope = managedClientScopes.get(id);
	            if (clientScope != null) {
	                clientScope.invalidate();
	                continue;
	            }
	            CustomerRoleAdapter role =(CustomerRoleAdapter) managedRoles.get(id);
	            if (role != null) {
	                role.invalidate();
	                continue;
	            }


	        }
	    }

	    private RealmAdapter prepareCachedRealm(String id, KeycloakSession session) {
	        CachedRealm cached = cache.get(id, CachedRealm.class);
	        RealmAdapter adapter;
	        if (cached == null) {
	            Long loaded = cache.getCurrentRevision(id);
	            RealmModel model = getRealmDelegate().getRealm(id);
	            if (model == null) {
	                return null;
	            }
	            cached = new CachedRealm(loaded, model);
	            cache.addRevisioned(cached, startupRevision);
	            adapter = new CustomerRealmAdapter(session, cached, this);
	            CachedRealmModel.RealmCachedEvent event = new CachedRealmModel.RealmCachedEvent() {
	                @Override
	                public CachedRealmModel getRealm() {
	                    return adapter;
	                }

	                @Override
	                public KeycloakSession getKeycloakSession() {
	                    return session;
	                }
	            };
	            session.getKeycloakSessionFactory().publish(event);
	        } else {
	            adapter = new CustomerRealmAdapter(session, cached, this);
	            logger.tracev("by id cache hit after locking: {0}", cached.getName());
	        }
	        return adapter;
	    }
	    
	    @Override
	    public void registerRealmInvalidation(String id, String name) {
	        cache.realmUpdated(id, name, invalidations);
	        CustomerRealmAdapter adapter = (CustomerRealmAdapter)managedRealms.get(id);
	        if (adapter != null) adapter.invalidateFlag();

	        invalidationEvents.add(RealmUpdatedEvent.create(id, name));
	    }
	    
	    
	    @Override
	    public Stream<RoleModel> getRealmRolesStream(RealmModel realm, Integer first, Integer max) {
	        return getRoleDelegate().getRealmRolesStream(realm, first, max);
	    }

	    @Override
	    public Stream<RoleModel> getRolesStream(RealmModel realm, Stream<String> ids, String search, Integer first, Integer max) {
	        return getRoleDelegate().getRolesStream(realm, ids, search, first, max);
	    }

	    @Override
	    public Stream<RoleModel> getClientRolesStream(ClientModel client, Integer first, Integer max) {
	        return getRoleDelegate().getClientRolesStream(client, first, max);
	    }

	    @Override
	    public Stream<RoleModel> searchForClientRolesStream(ClientModel client, String search, Integer first, Integer max) {
	        return getRoleDelegate().searchForClientRolesStream(client, search, first, max);
	    }

	    @Override
	    public Stream<RoleModel> searchForClientRolesStream(RealmModel realm, Stream<String> ids, String search, Integer first, Integer max) {
	        return getRoleDelegate().searchForClientRolesStream(realm, ids, search, first, max);
	    }

	    @Override
	    public Stream<RoleModel> searchForClientRolesStream(RealmModel realm, String search, Stream<String> excludedIds, Integer first, Integer max) {
	        return getRoleDelegate().searchForClientRolesStream(realm, search, excludedIds, first, max);
	    }

	    @Override
	    public Stream<RoleModel> searchForRolesStream(RealmModel realm, String search, Integer first, Integer max) {
	        return getRoleDelegate().searchForRolesStream(realm, search, first, max);
	    }
	    
	    
	    @Override
	    protected ClientModel cacheClient(RealmModel realm, ClientModel delegate, Long revision) {
	        if (invalidations.contains(delegate.getId())) return delegate;
	        StorageId storageId = new StorageId(delegate.getId());
	        CachedClient cached = null;
	        CustomerClientAdapter adapter = null;

	        if (!storageId.isLocal()) {
	            ComponentModel component = realm.getComponent(storageId.getProviderId());
	            ClientStorageProviderModel model = new ClientStorageProviderModel(component);
	            if (!model.isEnabled()) {
	                return delegate;
	            }
	            ClientStorageProviderModel.CachePolicy policy = model.getCachePolicy();
	            if (policy != null && policy == ClientStorageProviderModel.CachePolicy.NO_CACHE) {
	                return delegate;
	            }

	            cached = new CachedClient(revision, realm, delegate);
	            adapter = new CustomerClientAdapter(realm, cached, this);

	            long lifespan = model.getLifespan();
	            if (lifespan > 0) {
	                cache.addRevisioned(cached, startupRevision, lifespan);
	            } else {
	                cache.addRevisioned(cached, startupRevision);
	            }
	        } else {
	            cached = new CachedClient(revision, realm, delegate);
	            adapter = new CustomerClientAdapter(realm, cached, this);
	            cache.addRevisioned(cached, startupRevision);
	        }

	        return adapter;
	    }
	    
	    private void invalidateClient(String id) {
	        invalidations.add(id);
	        ClientModel adapter = managedApplications.get(id);
	        if (adapter != null && adapter instanceof CustomerClientAdapter) ((CustomerClientAdapter)adapter).invalidate();
	    }
	    
	    
	    
	    protected ClientModel validateCache(RealmModel realm, CachedClient cached) {
	        if (!realm.getId().equals(cached.getRealm())) {
	            return null;
	        }

	        StorageId storageId = new StorageId(cached.getId());
	        if (!storageId.isLocal()) {
	            ComponentModel component = realm.getComponent(storageId.getProviderId());
	            if (component == null) {
	                return null;
	            }
	            ClientStorageProviderModel model = new ClientStorageProviderModel(component);

	            // although we do set a timeout, Infinispan has no guarantees when the user will be evicted
	            // its also hard to test stuff
	            if (model.shouldInvalidate(cached)) {
	                registerClientInvalidation(cached.getId(), cached.getClientId(), realm.getId());
	                return getClientDelegate().getClientById(realm, cached.getId());
	            }
	        }
	        CustomerClientAdapter adapter = new CustomerClientAdapter(realm, cached, this);

	        return adapter;
	    }
	    
	    @Override
	    public Stream<RoleModel> getClientRolesStream(ClientModel client) {
	        String cacheKey = getRolesCacheKey(client.getId());
	        boolean queryDB = invalidations.contains(cacheKey) || listInvalidations.contains(client.getId()) || listInvalidations.contains(client.getRealm().getId());
	        if (queryDB) {
	            return getRoleDelegate().getClientRolesStream(client);
	        }

	        RoleListQuery query = cache.get(cacheKey, RoleListQuery.class);
	        
	        if (query != null) {
	            logger.tracev("getClientRoles cache hit: {0}", client.getClientId());
	        }
	        Set<RoleModel> model =new HashSet<RoleModel>();
	        if (query == null) {
	            Long loaded = cache.getCurrentRevision(cacheKey);
	            
	            model= getRoleDelegate().getClientRolesStream(client).collect(Collectors.toSet());
	            if (model == null) return null;
	            Set<String> ids = model.stream().map(RoleModel::getId).collect(Collectors.toSet());
	            query = new RoleListQuery(loaded, cacheKey, client.getRealm(), ids, client.getClientId());
	            logger.tracev("adding client roles cache miss: client {0} key {1}", client.getClientId(), cacheKey);
	            cache.addRevisioned(query, startupRevision);
	            
	            Set<RoleModel> list1=getRoleDelegate().getRealmRolesStream(client.getRealm()).collect(Collectors.toSet());
		        model.addAll(list1);
	            return model.stream();
	        }
	        Set<RoleModel> list = new HashSet<>();
	        for (String id : query.getRoles()) {
	            RoleModel role = session.roles().getRoleById(client.getRealm(), id);
	            if (role == null) {
	                invalidations.add(cacheKey);
	                return getRoleDelegate().getClientRolesStream(client);
	            }
	            list.add(role);
	        }
	        Set<RoleModel> list2=getRoleDelegate().getRealmRolesStream(client.getRealm()).collect(Collectors.toSet());
	        list.addAll(list2);
	        return list.stream();
	    }
}
