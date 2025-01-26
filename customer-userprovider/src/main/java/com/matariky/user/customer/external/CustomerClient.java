package com.matariky.user.customer.external;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * @author matariky
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface CustomerClient {

	@GET
	List<Customer> getcustomer(@QueryParam("search") String search, @QueryParam("first") int first, @QueryParam("max") int max);

	@GET
	@Path("/count")
	Integer getcustomerCount();

	@GET
	@Path("/{id}")
	Customer getCustomerById(@PathParam("id") String id);

	@GET
	@Path("/{id}/credentials")
	CredentialData getCredentialData(@PathParam("id") String id);

	@PUT
	@Path("/{id}/credentials")
	Response updateCredentialData(@PathParam("id") String id, CredentialData credentialData);

}
