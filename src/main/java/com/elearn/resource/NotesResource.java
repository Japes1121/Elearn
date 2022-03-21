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
import com.elearn.storage.model.Notes;

@Path("/notes")
public class NotesResource {

	@Context
	UriInfo uriInfo;
	@Context
	HttpServletRequest httpRequest;
	
	@Path("/notes")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getANotes() {
		List<Note> noteList = NotesStorage.getAllNotes();
		JSONArray noteArr = new JSONArray();
		for (Note d : noteList) {
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

	@Path("/note")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response postRequest() {
		String uploadedTime= httpRequest.getParameter("uploadedTime");
        NotesStorage.addNote(uploadedTime);
        Int subjectId=httpRequest.getParameter("subjectId");
        NotesStorage.addNote(subjectId);
        Int postedBy=httpRequest.getParameter("postedBy");
        NotesStorage.addNote(postedBy);
		return ResponseUtil.writeSuccessRespnseToClient();
		
	
	}

	@Path("/note/{subject-id}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateNote(@PathParam("subject-Id") int subjectId) {
		Int subjectId = httpRequest.getParameter("subjectId");
		NotesStorage.updateNote(subjectId, subjectId);
		Int postedBy =httpRequest.getParameter("postedBy");
		NotesStorage.updateNote(postedBy, postedBy);
		String uploadedTime = httpRequest.getParameter("uploadedTime");
		NotesStorage.updatenote(uploadedTime, uploadedTime);
		String updatedTime = httpRequest.getParameter("updatedTime");
		NOtesStorage.updateNote(updatedTime, updatedTime);
		Int updatedBy =httpRequest.getParameter("updatedBy");
		NotesStorage.updateNote(updatedBy, updateBy);
		
		return ResponseUtil.writeSuccessRespnseToClient();
	}

	@Path("/note/{subject-id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteNote(@PathParam("Subject-Id") int subjectId) {
		NotesStorage.deleteNote(subjectId);
		return ResponseUtil.writeSuccessRespnseToClient();
	}

}




