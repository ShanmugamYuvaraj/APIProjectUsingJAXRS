package com.qapitol.qa.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.qapitol.qa.pojos.DeviceResponse;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface DeviceService {
	@POST
	@Path("/auth/realms/demo_account/protocol/openid-connect/token")
	DeviceResponse doLogin(@FormParam("username") String u,@FormParam("password") String p,@FormParam("client_id") String c,@FormParam("grant_type") String g);

	@POST
	@Path("/applications/10000041/devices")
	Response create(DeviceResponse create);
	
	@GET
	@Path("/devices/{id}")
	Response getdevice(@PathParam("id") String id);
	
	@PUT
	@Path("/devices/{id}")
	Response updateDevice(@PathParam("id") String id,DeviceResponse update);
	
	@DELETE
	@Path("/devices/{id}")
	Response delete(@PathParam("id") String id);
	
}
