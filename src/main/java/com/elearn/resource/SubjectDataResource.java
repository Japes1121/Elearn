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
import com.elearn.storage.model.Subject;


@Path("{application}/{category}/{category-id}/{department}/{department-id}/subjects")
	public class SubjectDataResource {

		@Context
		UriInfo uriInfo;
		@Context
		HttpServletRequest httpRequest;

		@Path("{application}/{category}/{category-id}/{department}/{department-id}/subjects/all")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response getAllSubjectsForADepartment(@PathParam("category-id") Integer categoryId,
				@PathParam("department-id") Integer departmentId) {
			List<Subject> subjectList =ApplicationDataStorage.getAllSubjects(departmentId);
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

		@Path("{application}/{category}/{category-id}/{department}/{department-id}/{subjects}/{subject-id}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response getANote(@PathParam("category-id") Integer categoryId,
				@PathParam("department-id") Integer departmentId, @PathParam("subject-id") Integer subjectId) {
				 
			Subject subject = ApplicationDataStorage.getParticularSubject(subjectId);
			JSONObject obj = new JSONObject();
			obj.put("subjectid", subject.getSubjectId() + "");
			obj.put("departmentid", subject.getDepartmentId() + "");
			obj.put("subjectname", subject.getSubjectName());
			return ResponseUtil.writeJSONObjectRespnseToClient(obj);
		}

		@Path("{application}/{category}/{category-id}/{department}/{department-id}/{subjects}")
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

		@Path("{application}/{category}/{category-id}/{department}/{department-id}/{subjects}/{subject-id}")
		@PUT
		@Produces(MediaType.APPLICATION_JSON)
		public Response updateSubject(@PathParam("category-id") Integer categoryId,
				@PathParam("department-id") Integer departmentId, @PathParam("subject-id") Integer subjectId) {
			String subjectCode = httpRequest.getParameter("subjectCode");
			String subjectName = httpRequest.getParameter("subjectName");
			Integer subjectid = Integer.parseInt(httpRequest.getParameter("subjectId"));
			ApplicationDataStorage.updateSubject(subjectCode,subjectName,subjectid);
			return ResponseUtil.writeSuccessRespnseToClient();
		}

		@Path("{application}/{category}/{category-id}/{department}/{department-id}/{subjects}/{subject-id}")
		@DELETE
		@Produces(MediaType.APPLICATION_JSON)
		public Response deleteNote(@PathParam("category-id") Integer categoryId,
				@PathParam("department-id") Integer departmentId, @PathParam("subject-id") Integer subjectId) {
			ApplicationDataStorage.deleteSubject(subjectId);
			return ResponseUtil.writeSuccessRespnseToClient();
		}
	}
	

	