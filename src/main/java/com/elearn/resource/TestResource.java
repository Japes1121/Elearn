package com.elearn.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

@Path("/learn/test")
public class TestResource {

	@GET
	@Produces("text/plain")
	public String getClichedMessage() {
		return "This is my First Rest API Call!";
	}
}