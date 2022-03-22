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

import com.elearn.storage.NotesStorage;
import com.elearn.storage.model.Notes;

@Path("/{cateory-id}/{department-id}/{subject-id}/notes")
public class NotesResource {

	@Context
	UriInfo uriInfo;
	@Context
	HttpServletRequest httpRequest;

	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllNotesForASubject(@PathParam("category-id") Integer categoryId,
			@PathParam("department-id") Integer departmentId, @PathParam("subject-id") Integer subjectId) {
		List<Notes> noteList = NotesStorage.getNotes(subjectId);
		JSONArray noteArr = new JSONArray();
		for (Notes d : noteList) {
			JSONObject obj = new JSONObject();
			obj.put("subjectid", d.getSubjectId() + "");
			obj.put("notesid", d.getNotesId() + "");
			obj.put("postedBy", d.getPostedBy());
			obj.put("uploadedTime", d.getUploadedTime());
			obj.put("updatedTime", d.getUpdatedTime());
			obj.put("content", d.getContent());
			obj.put("updatedBy", d.getUpdatedBy());
			noteArr.put(obj);
		}
		return ResponseUtil.writeJSONArrayRespnseToClient(noteArr);
	}

	@Path("/{note-id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getANote(@PathParam("category-id") Integer categoryId,
			@PathParam("department-id") Integer departmentId, @PathParam("subject-id") Integer subjectId,
			@PathParam("note-id") Integer noteId) {
		Notes note = NotesStorage.getParticularNote(noteId);
		JSONObject obj = new JSONObject();
		obj.put("subjectid", note.getSubjectId() + "");
		obj.put("notesid", note.getNotesId() + "");
		obj.put("postedBy", note.getPostedBy());
		obj.put("uploadedTime", note.getUploadedTime());
		obj.put("updatedTime", note.getUpdatedTime());
		obj.put("content", note.getContent());
		obj.put("updatedBy", note.getUpdatedBy());
		return ResponseUtil.writeJSONObjectRespnseToClient(obj);
	}

	@Path("/")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response postRequest(@PathParam("category-id") Integer categoryId,
			@PathParam("department-id") Integer departmentId, @PathParam("subject-id") Integer subjectId) {
		String uploadedTime = httpRequest.getParameter("uploadedTime");
		Integer postedBy = Integer.parseInt(httpRequest.getParameter("postedBy"));
		NotesStorage.addNotes(subjectId, postedBy, uploadedTime);
		return ResponseUtil.writeSuccessRespnseToClient();

	}

	@Path("/{note-id}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateNote(@PathParam("category-id") Integer categoryId,
			@PathParam("department-id") Integer departmentId, @PathParam("subject-id") Integer subjectId,
			@PathParam("note-id") Integer noteId) {
		String updatedTime = httpRequest.getParameter("updatedTime");
		Integer updatedBy = Integer.parseInt(httpRequest.getParameter("updatedBy"));
		NotesStorage.updateNotes(noteId, updatedTime, updatedBy);
		return ResponseUtil.writeSuccessRespnseToClient();
	}

	@Path("/{note-id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteNote(@PathParam("category-id") Integer categoryId,
			@PathParam("department-id") Integer departmentId, @PathParam("subject-id") Integer subjectId,
			@PathParam("note-id") Integer noteId) {
		NotesStorage.deleteNote(noteId);
		return ResponseUtil.writeSuccessRespnseToClient();
	}

}
