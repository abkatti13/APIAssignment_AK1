package getOperation;

import core.BaseTest;
import core.StatusCode;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExtentReport;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetUserById extends BaseTest {

    @Test(description = "RestAssured Basic - Get user by ID - reqres.in")
    public void validateGetUserBasics(){
        ExtentReport.extentlog =
                ExtentReport.extentreport
                        .startTest("validateGetUserBasics", "Validate 200 status code for get user by ID");
        System.out.println("validateGetUserBasics");
        int statusCode = given()
                .when()
                .get("https://reqres.in/api/users/2")
                .statusCode();
        System.out.println("Http response status code is "+ statusCode);

    }

    @Test(description = "RestAssured validate user email")
    public void validateGetUserUsingResponse(){
        ExtentReport.extentlog =
                ExtentReport.extentreport
                        .startTest("validateGetUserUsingResponse", "Validate 200 status code for get user by ID");

       System.out.println("validateGetUserUsingResponse");
       baseURI = "https://reqres.in"; // fetch this baseURI from properties file
       Response response = given()
                .log().all()
                .get("/api/users/2");
       System.out.println("Response is "+ response.asString());
       Assert.assertEquals(response.statusCode(), StatusCode.SUCCESS.code);
       response.then().body("data.email", is("janet.weaver@reqres.in"));

    }

    @Test(description = "RestAssured validate user details using jsonpath")
    public void validateGetUserUsingResponseJSONPath(){
        ExtentReport.extentlog =
                ExtentReport.extentreport
                        .startTest("validateGetUserUsingResponseJSONPath", "Validate 200 status code for get user by ID");
        System.out.println("validateGetUserUsingResponse");
        baseURI = "https://reqres.in";
        Response response = given()
                .log().all()
                .get("/api/users/2");
        Headers responseHeaders = response.getHeaders();
        System.out.println("Response headers are "+ responseHeaders.toString());
        System.out.println("Response is "+ response.asString());
        Assert.assertEquals(response.statusCode(), StatusCode.SUCCESS.code);
        //int userId = response.getBody().jsonPath().get("id");
        response.then().body("data.id", is(2));
        response.then().body("data.email", is("janet.weaver@reqres.in"));
        String responseAsString = response.body().asString();
        Assert.assertEquals(response.jsonPath().get("data.first_name"),"Janet");
        Assert.assertEquals(response.jsonPath().get("data.last_name"),"Weaver");
        Assert.assertEquals(response.jsonPath().get("data.avatar"),"https://reqres.in/img/faces/2-image.jpg");
    }
}
