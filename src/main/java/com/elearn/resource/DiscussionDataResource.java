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

import com.elearn.storage.DiscussionStorage;
import com.elearn.storage.model.Discussion;

@Path("application/category/{category-id}/department/{department-id}/subject/{subject-id}/notes/{notes-id}/discussion")
public class DiscussionDataResource {

	@Context
	UriInfo uriInfo;
	@Context
	HttpServletRequest httpRequest;

	@Path("application/category/{category-id}/department/{department-id}/subject/{subject-id}/notes/{notes-id}/discussion/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllDiscussionForANotes(@PathParam("category-id") Integer categoryId,
			@PathParam("department-id") Integer departmentId, @PathParam("subject-id") Integer subjectId, @PathParam("note-id") Integer noteId) {
		List<Discussion> discussionList = DiscussionStorage.getDiscussion(noteId);
		JSONArray disArr = new JSONArray();
		for (Discussion d : discussionList) {
			JSONObject obj = new JSONObject();
			obj.put("noteid", d.getNoteId() + "");
			obj.put("discussionid", d.getDiscussionId() + "");
			obj.put("modifiedTime", d.getModifiedTime());
			obj.put("postedBy", d.getPostedBy());
			obj.put("createdTime", d.getCreatedTime());
			obj.put("content", d.getContent());
			disArr.put(obj);
		}
		return ResponseUtil.writeJSONArrayRespnseToClient(disArr);
	}

	@Path("application/category/{category-id}/department/{department-id}/subject/{subject-id}/notes/{notes-id}/discussion/{discussion-id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getANote(@PathParam("category-id") Integer categoryId,
			@PathParam("department-id") Integer departmentId, @PathParam("subject-id") Integer subjectId,
			@PathParam("note-id") Integer noteId, @PathParam ("discussion-id")Integer discussionId) {
		Discussion discussion = DiscussionStorage.getParticularDiscussion(discussionId);
		JSONObject obj = new JSONObject();
		obj.put("noteid", discussion.getNoteId() + "");
		obj.put("discussionid", discussion.getDiscussionId() + "");
		obj.put("modifiedTime", discussion.getModifiedTime());
		obj.put("postedBy", discussion.getPostedBy());
		obj.put("createdTime", discussion.getCreatedTime());
		obj.put("content", discussion.getContent());
		
		return ResponseUtil.writeJSONObjectRespnseToClient(obj);
	}

	@Path("pplication/category/{category-id}/department/{department-id}/subject/{subject-id}/notes/{notes-id}/discussion")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response postRequest(@PathParam("category-id") Integer categoryId,
			@PathParam("department-id") Integer departmentId, @PathParam("subject-id") Integer subjectId, @PathParam("note-id")Integer noteId) {
		String createdTime = httpRequest.getParameter("createdTime");
		Integer postedBy = Integer.parseInt(httpRequest.getParameter("postedBy"));
		DiscussionStorage.addDiscussion(noteId, createdTime, postedBy);
		return ResponseUtil.writeSuccessRespnseToClient();

	}

	@Path("pplication/category/{category-id}/department/{department-id}/subject/{subject-id}/notes/{notes-id}/discussion/{discussion-id}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateDiscussion(@PathParam("category-id") Integer categoryId,
			@PathParam("department-id") Integer departmentId, @PathParam("subject-id") Integer subjectId,
			@PathParam("note-id") Integer noteId, @PathParam("discussion-id") Integer discussionId) {
		String modifiedTime = httpRequest.getParameter("modifiedTime");
		DiscussionStorage.updateDiscussion(discussionId, modifiedTime);
		return ResponseUtil.writeSuccessRespnseToClient();
	}

	@Path("pplication/category/{category-id}/department/{department-id}/subject/{subject-id}/notes/{notes-id}/discussion/{discussion-id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteNote(@PathParam("category-id") Integer categoryId,
			@PathParam("department-id") Integer departmentId, @PathParam("subject-id") Integer subjectId,
			@PathParam("note-id") Integer noteId,@PathParam("discussion-id") Integer discussionId ) {
		DiscussionStorage.deleteDiscussion(discussionId);
		return ResponseUtil.writeSuccessRespnseToClient();
	}

}
