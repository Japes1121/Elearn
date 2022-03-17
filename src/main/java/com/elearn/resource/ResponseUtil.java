package com.elearn.resource;

import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class ResponseUtil {
	private static final JSONObject SUCCESS_RESPONSE = new JSONObject().put("code", 200).put("description", "success");

	private static JSONObject getSuccessResponse(JSONObject dataMsg, JSONArray arr) {
		JSONObject resultObj = new JSONObject();
		resultObj.put("status", getStatusString());
		if (dataMsg != null) {
			resultObj.put("data", dataMsg);
		} else if (arr != null) {
			resultObj.put("data", arr);
		}

		return resultObj;
	}

	private static JSONObject getStatusString() {
		return SUCCESS_RESPONSE;
	}

	static Response writeJSONArrayRespnseToClient(JSONArray arr) {
		return Response.ok().entity(ResponseUtil.getSuccessResponse(null, arr).toString()).build();
	}

	static Response writeJSONObjectRespnseToClient(JSONObject obj) {
		return Response.ok().entity(ResponseUtil.getSuccessResponse(obj, null).toString()).build();
	}

	static Response writeSuccessRespnseToClient() {
		return Response.ok().entity(ResponseUtil.getSuccessResponse(null, null).toString()).build();
	}

}
