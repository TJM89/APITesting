package com.ThomasRestAssuredProject.backendlessLogin;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class BackendlessLoginTest {

	@Before
	public void before() {
		RestAssured.baseURI = "https://knowingtrade.backendless.app/api/users";
	}

	@Test
	public void testUserRegistrationPostRequest() {

		RequestSpecification request = RestAssured.given();

		String email = "Thomas119@gmail.com";
		String password = "Password123";

		request.header("Content-Type", "application/json");

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("email", email);
		jsonObject.put("password", password);

		request.body(jsonObject);

		Response response = request.post("/register");

		Assert.assertEquals(200, response.statusCode());

		JsonPath path = response.jsonPath();

		String respEmail = path.getString("email");
		String respPassword = path.getString("password");

		System.out.println("Response email : " + respEmail);
		System.out.println("Password is : " + password);

		Assert.assertEquals(respEmail, email);

		Response response1 = request.post("/login");

		Assert.assertEquals(200, response.statusCode());

		System.out.println("User is logged in as " + response);

	}

}
