package com.qa.test.common;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;

import com.qa.test.base.TestBase;
import com.qa.test.logger.LoggerHelper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestCallProcess {

	private static Logger log = LoggerHelper.getLogger(RestCallProcess.class);

	RequestSpecification request;
	Response response;
	JSONObject requestParam;
	HashMap<String, String> requestURL = new HashMap<String, String>();

	private static String SEARCH_PLACE = "?place_id=";
	private static String SEARCH_KEY1 = "?key=";
	private static String SEARCH_KEY2 = "&key=";
	private static String basePath = null;
	private static int statusCode = 0;

	public void postRequest(String key, String postRequest) {
		try {
			basePath = TestBase.getTestData("base.url") + TestBase.getTestData("post.resource") + SEARCH_KEY1 + key;
			log.info("$_Post_Request_$ : " + basePath);
			requestURL.put("post", basePath);
			requestURL.put("postReq", postRequest);
		} catch (Exception ex) {
			log.info("Error occured while construct post request URL" + "\n" + ex);
			Assert.assertFalse(true);
		}
	}

	@SuppressWarnings("unchecked")
	public void verifyPostResponse(String status, String postResponse) {
		try {
			request = RestAssured.given();
			requestParam = new JSONObject();
			HashMap<String, String> locationData = new HashMap<String, String>();
			ArrayList<String> typesData = new ArrayList<String>();
			String[] requestList = requestURL.get("postReq").split("\\|");
			locationData.put("lat", requestList[0]);
			locationData.put("lng", requestList[1]);
			requestParam.put("location", locationData);
			requestParam.put("accuracy", requestList[2]);
			requestParam.put("name", requestList[3]);
			requestParam.put("phone_number", requestList[4]);
			requestParam.put("address", requestList[5]);
			typesData.add(requestList[6]);
			typesData.add(requestList[7]);
			requestParam.put("types", typesData);
			requestParam.put("website", requestList[8]);
			requestParam.put("language", requestList[9]);
			request.header("Content-Type", "application/json");
			request.body(requestParam.toJSONString());
			response = request.post(requestURL.get("post"));
			log.info("$_Response_From_API_$ : " + response.asString());
			statusCode = Integer.parseInt(status);
			String[] responseList = postResponse.split("\\|");
			Assert.assertEquals(statusCode, response.getStatusCode());
			Assert.assertEquals(responseList[0], response.jsonPath().getString("status"));
			Assert.assertEquals(responseList[1], response.jsonPath().getString("scope"));
			// Trail and error method for retrieving the place id
			// requestURL.put("placeFromPost", response.jsonPath().getString("place_id"));
		} catch (Exception ex) {
			log.info("Error occured while check post response" + "\n" + ex);
			Assert.assertFalse(true);
		}
	}

	public void getRequest(String place, String key) {
		try {
			basePath = TestBase.getTestData("base.url") + TestBase.getTestData("get.resource") + SEARCH_PLACE + place
					+ SEARCH_KEY2 + key;
			log.info("$_Get_Request_$ : " + basePath);
			requestURL.put("get", basePath);
		} catch (Exception ex) {
			log.info("Error occured while construct get request URL" + "\n" + ex);
			Assert.assertFalse(true);
		}
	}

	public void verifyGetResponse(String status, String getResponse) {
		try {
			request = RestAssured.given();
			response = request.get(requestURL.get("get"));
			log.info("$_Response_From_API_$ : " + response.asString());
			statusCode = Integer.parseInt(status);
			System.out.println("Get Response : " + getResponse);
			String []responseList = getResponse.split("\\|");
			Assert.assertEquals(statusCode, response.getStatusCode());
			Assert.assertEquals(responseList[0], response.jsonPath().getMap("location").get("latitude"));
			Assert.assertEquals(responseList[1], response.jsonPath().getMap("location").get("longitude"));
			Assert.assertEquals(responseList[2], response.jsonPath().getString("accuracy"));
			Assert.assertEquals(responseList[3], response.jsonPath().getString("name"));
			Assert.assertEquals(responseList[4], response.jsonPath().getString("phone_number"));
			Assert.assertEquals(responseList[5], response.jsonPath().getString("address"));
			Assert.assertEquals(responseList[6], response.jsonPath().getString("types"));
			Assert.assertEquals(responseList[7], response.jsonPath().getString("website"));
			Assert.assertEquals(responseList[8], response.jsonPath().getString("language"));
		} catch (Exception ex) {
			log.info("Error occured while check get response" + "\n" + ex);
			Assert.assertFalse(true);
		}
	}

	public void putRequest(String place, String key, String putRequest) {
		try {
			basePath = TestBase.getTestData("base.url") + TestBase.getTestData("put.resource") + SEARCH_PLACE + place
					+ SEARCH_KEY2 + key;
			log.info("$_Put_Request_$ : " + basePath);
			requestURL.put("put", basePath);
			requestURL.put("putReq", putRequest);
		} catch (Exception ex) {
			log.info("Error occured while construct put request URL" + "\n" + ex);
			Assert.assertFalse(true);
		}
	}

	@SuppressWarnings("unchecked")
	public void verifyPutResponse(String status, String putResponse) {
		try {
			request = RestAssured.given();
			requestParam = new JSONObject();
			String[] requestList = requestURL.get("putReq").split("\\|");
			// Trial and error
			// requestParam.put("place_id", requestURL.get("placeFromPost"));
			requestParam.put("place_id", requestList[0]);
			requestParam.put("address", requestList[1]);
			requestParam.put("key", requestList[2]);
			request.header("Content-Type", "application/json");
			request.body(requestParam.toJSONString());
			response = request.put(requestURL.get("put"));
			log.info("$_Response_From_API_$ : " + response.asString());
			statusCode = Integer.parseInt(status);
			Assert.assertEquals(statusCode, response.getStatusCode());
			Assert.assertEquals(putResponse, response.jsonPath().getString("msg"));
		} catch (Exception ex) {
			log.info("Error occured while check put response" + "\n" + ex);
			Assert.assertFalse(true);
		}
	}

	public void deleteRequest(String key, String deleteRequest) {
		try {
			basePath = TestBase.getTestData("base.url") + TestBase.getTestData("delete.resource") + SEARCH_KEY1 + key;
			log.info("$_Delete_Request_$ : " + basePath);
			requestURL.put("delete", basePath);
			requestURL.put("deleteReq", deleteRequest);
		} catch (Exception ex) {
			log.info("Error occured while construct delete request URL" + "\n" + ex);
			Assert.assertFalse(true);
		}
	}

	@SuppressWarnings("unchecked")
	public void verifyDeleteResponse(String status, String deleteResponse) {
		try {
			request = RestAssured.given();
			requestParam = new JSONObject();
			// Trial and error
			// requestParam.put("place_id", requestURL.get("placeFromPost"));
			String requestList = requestURL.get("deleteReq");
			requestParam.put("place_id", requestList);
			request.header("Content-Type", "application/json");
			request.body(requestParam.toJSONString());
			response = request.delete(requestURL.get("delete"));
			log.info("$_Response_From_API_$ : " + response.asString());
			statusCode = Integer.parseInt(status);
			Assert.assertEquals(statusCode, response.getStatusCode());
			Assert.assertEquals(deleteResponse, response.jsonPath().getString("status"));
		} catch (Exception ex) {
			log.info("Error occured while check delete response" + "\n" + ex);
			Assert.assertFalse(true);
		}
	}
}
