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
import com.elearn.storage.model.Department;

@Path("/{category-id}/department")
public class DepartmentDataResource{

	@Context
	UriInfo uriInfo;
	@Context
	HttpServletRequest httpRequest;

	@Path("{application}/{category}/{category-id}/{department}/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllDeaprtmentForACategory(@PathParam("category-id")Integer categoryId) {
		List<Department> departmentList = ApplicationDataStorage.getAllDepartment();
		JSONArray depArr = new JSONArray();
		for (Department e : departmentList) {
			JSONObject obj = new JSONObject();
			obj.put("categoryId", e.getCategoryId() + "");
			obj.put("departmentId", e.getDepartmentId());
			obj.put("departmentName", e.getDepartmentName());
			depArr.put(obj);
		}
		return ResponseUtil.writeJSONArrayRespnseToClient(depArr);
	}
	@Path("{application}/{category}/{category-id}/{department}/{department-id}")
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public Response getADepartment(@PathParam ("category-id")Integer categoryId, @PathParam ("department-id")Integer departmentId) {
		Department department = ApplicationDataStorage.getParticularDepartment(departmentId);
			JSONObject obj = new JSONObject();
			obj.put("categoryId",department.getCategoryId() +"");
			obj.put("departmentId", department.getDepartmentId() +"");
			obj.put("departmentName",department.getDepartmentName());
			return ResponseUtil.writeJSObjectRespnseToClient(obj);
	}

	@Path("{application}/{category}/{category-id}/{department}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response postRequest(@PathParam("category-id")Integer categoryId) {
		String departmentName = httpRequest.getParameter("departmentName");
		ApplicationDataStorage.addDepartment(categoryId,departmentName);
		return ResponseUtil.writeSuccessRespnseToClient();
	}

	@Path("{application}/{category}/{category-id}/{department}/{department-id}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateDepartment(@PathParam("category-id") int categoryId,@PathParam ("department-id")Integer departmentId) {
		
		String departmentName = httpRequest.getParameter("departmentName");
		ApplicationDataStorage.updateDepartment(departmentId,departmentName);
		return ResponseUtil.writeSuccessRespnseToClient();
	}

	@Path("{application}/{category}/{category-id}/{department}/{department-id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteDepartment(@PathParam ("category-id")Integer categoryId, @PathParam ("department-id")Integer departmentId) {
		ApplicationDataStorage.deleteDepartment(departmentId);
		return ResponseUtil.writeSuccessRespnseToClient();
	}
}
