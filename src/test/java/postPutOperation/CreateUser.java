package postPutOperation;

import core.BaseTest;
import core.StatusCode;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.CreateUserPOJO;
import pojo.CreateUserResponsePOJO;
import utils.ExtentReport;
import utils.PropertyReader;
import utils.ReturnJSONArray;
import utils.SoftAssertUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.util.Locale;

import static io.restassured.RestAssured.baseURI;


public class CreateUser extends BaseTest {
    @Test(description = "validateCreateUser")
    public void validateCreateUser() throws IOException, ParseException {
        ExtentReport.extentlog =
                ExtentReport.extentreport
                        .startTest("validateCreateUser", "Validate 200 status code for get user by ID");

        baseURI = PropertyReader.getProperty("config.properties","baseURI");
        System.out.println("baseURI is "+ baseURI);
        CreateUserPOJO newuser = new CreateUserPOJO();
        newuser.setName("Abhishek");
        newuser.setJob("Lead QA - Automation");
        CreateUserResponsePOJO
                response =     given()
                                .contentType(ContentType.JSON)
                                .body(newuser)
                                .post(PropertyReader.getProperty("config.properties","getUsersURL"))
                                .then()
                                .statusCode(StatusCode.CREATED.code)
                                .extract()
                                .as(CreateUserResponsePOJO.class);
        System.out.println("Name in response is " + response.getName());
        System.out.println("Job in response is " + response.getJob());
        SoftAssertUtil.assertEquals(response.getName(), "Abhishek");
        SoftAssertUtil.assertEquals(response.getJob(), "Lead QA - Automation");
        SoftAssertUtil.assertAll();
    }
}
