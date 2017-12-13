package br.com.dotofcodex.restfull.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.dotofcodex.restfull.domain.Task;

@Path("/web")
public class SimpleHelloWorld {

	@GET
	@Path("/hello")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHelloWolrdsJSON() {
		Task task = new Task();
		task.setName("Hello Worlds!!!");
		
		return Response.status(200).entity(task).build();
	}
}
