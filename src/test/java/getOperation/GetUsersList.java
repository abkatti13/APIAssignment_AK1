package getOperation;

import com.google.gson.JsonObject;
import core.BaseTest;
import core.StatusCode;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.UsersListResponsePOJO;
import utils.ExtentReport;
import utils.PropertyReader;
import utils.ReturnJSONArray;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.*;
import java.util.*;

public class GetUsersList extends BaseTest {

/*    @Test
    public void getUsersData(){
        System.out.println("getUsersData");
        Response response =
        given().
                when().get("https://reqres.in/api/users?page=2");
                *//*.then().
                assertThat().
                statusCode(StatusCode.SUCCESS.code);*//*
        System.out.println("response is "+response.getBody().asString());
    }
*/
    @Test
    public void validateListOfUsersUsingPropertyFile() throws IOException {
        ExtentReport.extentlog =
                ExtentReport.extentreport
                        .startTest("validateListOfUsersUsingPropertyFile", "Validate 200 status code for get user by ID");
        baseURI = "https://reqres.in";
        System.out.println("baseURI is "+ baseURI);
        String URL = PropertyReader.getProperty("config.properties","getUsersURL");
        Response response =
                given().log().all()
                        .queryParam("page",1)
                        .when()
                       .get(URL);
        System.out.println("Response is "+response.getBody().asString());
        int actualTotalUsers = response.jsonPath().get("total");
        Assert.assertEquals(actualTotalUsers,12);
    }

    @Test
    public void validateSpecificUserDetailsInListOfUsers() throws IOException, ParseException {

        ExtentReport.extentlog =
                ExtentReport.extentreport
                        .startTest("validateSpecificUserDetailsInListOfUsers", "Validate 200 status code for get user by ID");
        // Reference: https://docs.google.com/document/d/1X62mcGgHzbZOV1956-yq5HOmtrhMJdQWhBRQf8xN8IM/edit?tab=t.0#heading=h.mbctmj75oeic
        String actualFirstName, actualLastName, actualAvatar;
        baseURI = PropertyReader.getProperty("config.properties","baseURI");
        System.out.println("baseURI is "+ baseURI);
        Response response =
                given().log().all()
                        .queryParam("page",1)
                        .when()
                        .get(PropertyReader.getProperty("config.properties","getUsersURL"));
        int responseStatusCode = response.statusCode();

        if(responseStatusCode == 200){
            String getUsersPath = PropertyReader.getProperty("config.properties","getUsersPath");
            JSONArray dataArray = ReturnJSONArray.GetJSONArray(response.asString(),"data");
            for(Object obj : dataArray) {
                JSONObject jsonObject1 = (JSONObject) obj;
                String responseEmail = (String) jsonObject1.get("email");
                System.out.println("responseEmail is " + responseEmail);
                if (responseEmail.equals("george.bluth@reqres.in")) {
                    actualFirstName = (String) jsonObject1.get("first_name");
                    actualLastName = (String) jsonObject1.get("last_name");
                    actualAvatar = (String) jsonObject1.get("avatar");
                    System.out.println("First name is " + actualFirstName + " Last name is " + actualLastName + " avatar is " + actualAvatar);
                    //Add break statement when there is an unique emailID in the list
                    break;
                } else {
                    actualFirstName = "Incorrect";
                    actualLastName = "Incorrect";
                    actualAvatar = "Incorrect";
                    System.out.println("First name is " + actualFirstName + " Last name is " + actualLastName + " avatar is " + actualAvatar);
                }
                Assert.assertEquals(ReturnJSONArray.returnValues(getUsersPath, "firstname"), actualFirstName);
                Assert.assertEquals(ReturnJSONArray.returnValues(getUsersPath, "lastName"), actualLastName);
                Assert.assertEquals(ReturnJSONArray.returnValues(getUsersPath, "avatar"), actualAvatar);
            }
        }
        else{
            Assert.assertTrue(false);
        }
    }
    @Test(description = "RestAssured validateListOfUsersByDeserialization")
    public void validateListOfUsersByDeserialization() throws IOException {
        ExtentReport.extentlog =
                ExtentReport.extentreport
                        .startTest("validateListOfUsersByDeserialization", "Validate 200 status code for get user by ID");

        baseURI = "https://reqres.in";
        System.out.println("baseURI is "+ baseURI);
        String URL = PropertyReader.getProperty("config.properties","getUsersURL");
        UsersListResponsePOJO response = given()
                .log().all()
                .queryParam("page",1)
                .when()
                .get(URL)
                .then()
                .extract().as(UsersListResponsePOJO.class);
        System.out.println("Response is "+response);
        System.out.println(response.getSupport().getSupportURL());
        System.out.println(response.getData().get(0).getFirst_name());

    }
}
