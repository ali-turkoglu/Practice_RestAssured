package automationExercise;

import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.given;

public class API6_PostToSearchWithoutParameters {

    @Test
    public void postToSearchWithoutParameter(){

//API 6: POST To Search Product without search_product parameter
//API URL: https://automationexercise.com/api/searchProduct
//Request Method: POST
//Response Code: 400
//Response Message: Bad request, search_product parameter is missing in POST request.

        Response response=given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when().post("https://automationexercise.com/api/searchProduct");

        //response.prettyPrint();

        String responseString = response.htmlPath().getString("html.body");
        JsonPath jsonPath=new JsonPath(responseString);

        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals("400",jsonPath.getString("responseCode"));
        Assertions.assertEquals("Bad request, search_product parameter is missing in POST request.",jsonPath.getString("message"));

    }


}
