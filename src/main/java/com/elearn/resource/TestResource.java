package com.elearn.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Path;

@Path("/test")
public class TestResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getWelcomeMessage() {
		return "This is my First Rest API Call!";
	}

	@Path("/post")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response postRequest() {
		return ResponseUtil.writeSuccessRespnseToClient();
	}
}