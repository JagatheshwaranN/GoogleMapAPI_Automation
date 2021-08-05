package com.qa.test.steps;

import com.qa.test.base.TestBase;
import com.qa.test.common.RestCallProcess;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

/**
 * 
 * @author Jaga
 *
 */
public class GoogleMapStepDefinitions {

	RestCallProcess restCallProcess = new RestCallProcess();

	@Given("user construct post request with {string} and request payload {string}")
	public void user_construct_post_request_with_and_request_payload(String key, String postRequest) {
		restCallProcess.postRequest(TestBase.getTestData(key), TestBase.getTestData(postRequest));
	}

	@Then("user verifies post response with {string} and {string}")
	public void user_verifies_post_response_with_and(String status, String response) {
		restCallProcess.verifyPostResponse(TestBase.getTestData(status), TestBase.getTestData(response));
	}

	@Given("user construct get request with {string} and {string}")
	public void user_construct_get_request_with_and(String place, String key) {
		restCallProcess.getRequest(TestBase.getTestData(place), TestBase.getTestData(key));
	}

	@Then("user verifies get response with {string} and {string}")
	public void user_verifies_get_response_with_and(String status, String response) {
		restCallProcess.verifyGetResponse(TestBase.getTestData(status), TestBase.getTestData(response));
	}

	@Given("user construct put request with {string} and {string} and request payload {string}")
	public void user_construct_put_request_with_and_and_request_payload(String place, String key, String putRequest) {
		restCallProcess.putRequest(TestBase.getTestData(place), TestBase.getTestData(key),
				TestBase.getTestData(putRequest));
	}

	@Then("user verifies put response with {string} and {string}")
	public void user_verifies_put_response_with_and(String status, String response) {
		restCallProcess.verifyPutResponse(TestBase.getTestData(status), TestBase.getTestData(response));
	}

	@Given("user construct delete request with {string} and request payload {string}")
	public void user_construct_delete_request_with_and_request_payload(String key, String deleteRequest) {
		restCallProcess.deleteRequest(TestBase.getTestData(key), TestBase.getTestData(deleteRequest));
	}

	@Then("user verifies delete response with {string} and {string}")
	public void user_verifies_delete_response_with_and(String status, String response) {
		restCallProcess.verifyDeleteResponse(TestBase.getTestData(status), TestBase.getTestData(response));
	}
}
