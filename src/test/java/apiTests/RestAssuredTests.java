package apiTests;

import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import Utils.Utils;
import java.util.ArrayList;


public class RestAssuredTests {

    @Test
    public void getAuthorizationToken () {
        String profileUrl = "https://demoqa.com/Account/v1/User/";
        String userName = "ganzyuk_tests_" + Utils.givenRandomString();
        String password = "P@ssword2";

        //1. registration user
        String userId = Utils.registrationUserByCredentials(userName, password);

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
        String booksUrl = "https://demoqa.com/BookStore/v1/Books";
        String userName = "ganzyuk_tests_" + Utils.givenRandomString();
        String password = "P@ssword2";

        //1. registration user
        Utils.registrationUserByCredentials(userName, password);

        //2. get token for registered user
        String token = Utils.getTokenByUserCredentials(userName, password);

        //3.get user books
        RequestSpecification booksRequest = RestAssured.given();
        booksRequest.header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");

        Response responseBooks = booksRequest.get(booksUrl);
        responseBooks.then().statusCode(200);

        ArrayList books = responseBooks.then().extract().path("books");

        JSONArray booksArray = new JSONArray(books.toArray());

        String actualBookTitle = null;

        for (int i = 0; i < booksArray.length(); ++i) {
            JSONObject line = booksArray.getJSONObject(i);
            String isbn = line.getString("isbn");

            if(isbn.equals("9781449325862")){
                actualBookTitle = line.getString("title");
                break;
            }
        }

        //asserts
        Assert.assertEquals("Git Pocket Guide", actualBookTitle);
        Assert.assertEquals(8, books.size());
    }
}
