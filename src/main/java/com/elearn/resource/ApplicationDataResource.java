package com.elearn.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import com.elearn.storage.ApplicationDataStorage;
import com.elearn.storage.model.Category;

@Path("/application")
public class ApplicationDataResource {

	@Context
	UriInfo uriInfo;
	@Context
	HttpServletRequest httpRequest;

	@Path("/categories")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCategories() {
		List<Category> categoryList = ApplicationDataStorage.getAllCategories();
		JSONArray catArr = new JSONArray();
		for (Category c : categoryList) {
			JSONObject obj = new JSONObject();
			obj.put("id", c.getCategoryId() + "");
			obj.put("name", c.getCategoryName());
			catArr.put(obj);
		}
		return ResponseUtil.writeJSONArrayRespnseToClient(catArr);
	}

	@Path("/category")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response postRequest() {
		String name = httpRequest.getParameter("name");
		ApplicationDataStorage.addCategory(name);
		return ResponseUtil.writeSuccessRespnseToClient();
	}

	@Path("/category/{category-id}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCategory(@PathParam("category-id") int categoryId) {
		String name = httpRequest.getParameter("name");
		ApplicationDataStorage.updateCategory(categoryId, name);
		return ResponseUtil.writeSuccessRespnseToClient();
	}

	@Path("/category/{category-id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCategory(@PathParam("category-id") int categoryId) {
		ApplicationDataStorage.deleteCategory(categoryId);
		return ResponseUtil.writeSuccessRespnseToClient();
	}

}
