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
import com.elearn.storage.NotesStorage;
import com.elearn.storage.model.Category;
import com.elearn.storage.model.Department;
import com.elearn.storage.model.Notes;
import com.elearn.storage.model.Subject;

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

	@Path("/{category-id}/department")
	public class DepartmentDataResource{

		@Context
		UriInfo uriInfo;
		@Context
		HttpServletRequest httpRequest;

		@Path("/all")
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
		@Path("/{department-id}")
		@GET
		@Produces (MediaType.APPLICATION_JSON)
		public Response getADepartment(@PathParam ("category-id")Integer categoryId, @PathParam ("department-id")Integer departmentId) {
			Department dep = ApplicationDataStorage.getParticularDepartment(departmentId);
				JSONObject obj = new JSONObject();
				obj.put("categoryId",dep.getCategoryId() +"");
				obj.put("departmentId", dep.getDepartmentId() +"");
				obj.put("departmentName",dep.getDepartmentName());
				return ResponseUtil.writeJSObjectRespnseToClient(obj);
		}

		@Path("/")
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		public Response postRequest(@PathParam("category-id")Integer categoryId) {
			String departmentName = httpRequest.getParameter("departmentName");
			ApplicationDataStorage.addDepartment(categoryId,departmentName);
			return ResponseUtil.writeSuccessRespnseToClient();
		}

		@Path("/{department-id}")
		@PUT
		@Produces(MediaType.APPLICATION_JSON)
		public Response updateDepartment(@PathParam("category-id") int categoryId,@PathParam ("department-id")Integer departmentId) {
			
			String departmentName = httpRequest.getParameter("departmentName");
			ApplicationDataStorage.updateDepartment(departmentId,departmentName);
			return ResponseUtil.writeSuccessRespnseToClient();
		}

		@Path("/{department-id}")
		@DELETE
		@Produces(MediaType.APPLICATION_JSON)
		public Response deleteDepartment(@PathParam ("category-id")Integer categoryId, @PathParam ("department-id")Integer departmentId) {
			ApplicationDataStorage.deleteDepartment(departmentId);
			return ResponseUtil.writeSuccessRespnseToClient();
		}

	}

	@Path("/{cateory-id}/{department-id}/subjects")
	public class ApplicationDataResource {

		@Context
		UriInfo uriInfo;
		@Context
		HttpServletRequest httpRequest;

		@Path("/all")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response getAllSubjectsForADepartment(@PathParam("category-id") Integer categoryId,
				@PathParam("department-id") Integer departmentId) {
			List<Subject> subjectList =ApplicationDataStorage.getSubjects(departmentId);
			JSONArray subArr = new JSONArray();
			for (subject s : subjectList) {
				JSONObject obj = new JSONObject();
				obj.put("subjectid", s.getSubjectId() + "");
				obj.put("departmentid", s.getDepartmentId() + "");
				obj.put("subjectname", s.getSubjectName());
				subArr.put(obj);
			}
			return ResponseUtil.writeJSONArrayRespnseToClient(subArr);
		}

		@Path("/{subject-id}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response getANote(@PathParam("category-id") Integer categoryId,
				@PathParam("department-id") Integer departmentId, @PathParam("subject-id") Integer subjectId) {
				 
			Subject subject = ApplicationDataStorage.getAllSubject(subjectId);
			JSONObject obj = new JSONObject();
			obj.put("subjectid", s.getSubjectId() + "");
			obj.put("departmentid", s.getDepartmentId() + "");
			obj.put("subjectname", s.getSubjectName());
			return ResponseUtil.writeJSONObjectRespnseToClient(obj);
		}

		@Path("/")
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		public Response postRequest(@PathParam("category-id") Integer categoryId,
				@PathParam("department-id") Integer departmentId, @PathParam("subject-id") Integer subjectId) {
			String subjectCode = httpRequest.getParameter("subjectCode");
			String subjectName = httpRequest.getParameter("subjectName");
			Integer departmentid = Integer.parseInt(httpRequest.getParameter("departmentid"));
			ApplicationDataStorage.addSubject(departmentid, subjectCode, departmentid);
			return ResponseUtil.writeSuccessRespnseToClient();

		}

		@Path("/{subject-id}")
		@PUT
		@Produces(MediaType.APPLICATION_JSON)
		public Response updateSubject(@PathParam("category-id") Integer categoryId,
				@PathParam("department-id") Integer departmentId, @PathParam("subject-id") Integer subjectId) {
			String subjectCode = httpRequest.getParameter("subjectCode");
			String subjectName = httpRequest.getParameter("subjectName");
			Integer subjectId = Integer.parseInt(httpRequest.getParameter("subjectId"));
			ApplicationDataStorage.updateSubjects(subjectCode,subejctName,subjectId);
			return ResponseUtil.writeSuccessRespnseToClient();
		}

		@Path("/{subject-id}")
		@DELETE
		@Produces(MediaType.APPLICATION_JSON)
		public Response deleteNote(@PathParam("category-id") Integer categoryId,
				@PathParam("department-id") Integer departmentId, @PathParam("subject-id") Integer subjectId) {
			ApplicationDataStorage.deleteNote(subjectId);
			return ResponseUtil.writeSuccessRespnseToClient();
		}
	}
	

	

