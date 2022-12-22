package automationExercise;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apiguardian.api.API;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class API4_PutToAllBrandsList {

    @Test
    public void putToAllBrandsList(){

//        API 4: PUT To All Brands List
//        API URL: https://automationexercise.com/api/brandsList
//        Request Method: PUT
//        Response Code: 405
//        Response Message: This request method is not supported.


        String newbrand="{id=1, brand=Toyoto}";

        Response response=given().contentType(ContentType.JSON)
                                .accept(ContentType.JSON)
                                //.pathParams("id",1)
                                .body(newbrand)
                                .when().put("https://automationexercise.com/api/brandsList");

        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        JsonPath jsonPath=new JsonPath(response.htmlPath().getString("html.body"));

        Assertions.assertEquals("405",jsonPath.getString("responseCode"));

        String expectedMessage="This request method is not supported.";
        Assertions.assertEquals(expectedMessage,jsonPath.getString("message"));


    }


}
