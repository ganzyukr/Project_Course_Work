package Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import java.util.Random;

public class Utils {

    public static String getTokenByUserCredentials (String userName, String userPassword) {
        String tokenUrl = "https://demoqa.com/Account/v1/GenerateToken";

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("password", userPassword);
        request.body(requestBody.toString());

        Response response = request.post(tokenUrl);
        response.then().statusCode(200);

        return response.then().extract().path("token");
    }

    public static String givenRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
