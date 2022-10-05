package apiTests;

import org.junit.Assert;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.hamcrest.Matchers.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class RestAssuredTests {

    @Test
    public void getAuthorizationToken () {
        String authUrl = "https://demoqa.com/Account/v1/User";
        String profileUrl = "https://demoqa.com/Account/v1/User/";
        String userName = "ganzyuk_tests_" + Utils.givenRandomString();
        String password = "P@ssword2";

        //1. registration user
        RequestSpecification authRequest = RestAssured.given();
        authRequest.header("Content-Type", "application/json");

        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("password", password);
        authRequest.body(requestBody.toString());

        Response responseAuth = authRequest.post(authUrl);
        responseAuth.then().statusCode(201);
        String userId = responseAuth.then().extract().path("userID");

        //end registration

        //2. get token for registered user

        String token = Utils.getTokenByUserCredentials(userName, password);

        //3. get profile registered user
        RequestSpecification profileRequest = RestAssured.given();
        profileRequest.header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");

        Response responseUser = profileRequest.get(profileUrl + userId);
        responseUser.then().statusCode(200);
        String actualUserName = responseUser.then().extract().path("username");

        //Asserts username
        Assert.assertEquals(userName, actualUserName);
    }

    @Test
    public void getUserBooks () {
        String authUrl = "https://demoqa.com/Account/v1/User";
        String booksUrl = "https://demoqa.com/BookStore/v1/Books";
        String userName = "ganzyuk_tests_" + Utils.givenRandomString();
        String password = "P@ssword2";

        //1. registration user
        RequestSpecification authRequest = RestAssured.given();
        authRequest.header("Content-Type", "application/json");

        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("password", password);
        authRequest.body(requestBody.toString());

        Response responseAuth = authRequest.post(authUrl);
        responseAuth.then().statusCode(201);
        //end registration

        //2. get token for registered user

        String token = Utils.getTokenByUserCredentials(userName, password);

        //3.get user books
        RequestSpecification booksRequest = RestAssured.given();
        booksRequest.header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");

        Response responseBooks = booksRequest.get(booksUrl);
        responseBooks.then().statusCode(200);

        ArrayList books = responseBooks.then().extract().path("books");


        String actualBookTitle = "?????";

        //asserts
        Assert.assertEquals("Git Pocket Guide", actualBookTitle);
        Assert.assertEquals(8, books.size());
    }
}
