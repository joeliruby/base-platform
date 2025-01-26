package com.matariky.user.customer;

import java.util.stream.Stream;

import org.keycloak.models.ClientModel;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RoleModel;
import org.keycloak.models.cache.infinispan.ClientAdapter;
import org.keycloak.models.cache.infinispan.RealmCacheSession;
import org.keycloak.models.cache.infinispan.entities.CachedClient;

public class CustomerClientAdapter extends ClientAdapter implements ClientModel{

	public CustomerClientAdapter(RealmModel cachedRealm, CachedClient cached, RealmCacheSession cacheSession) {
		super(cachedRealm, cached, cacheSession);
	}
	
	
	@Override
    public Stream<RoleModel> getRolesStream() {
        return cacheSession.getClientRolesStream(this);
    }
    
    @Override
    public Stream<RoleModel> getRolesStream(Integer first, Integer max) {
        return cacheSession.getClientRolesStream(this, first, max);
    }
    
    @Override
    public Stream<RoleModel> searchForRolesStream(String search, Integer first, Integer max) {
        return cacheSession.searchForClientRolesStream(this, search, first, max);
    }
    
    

}
