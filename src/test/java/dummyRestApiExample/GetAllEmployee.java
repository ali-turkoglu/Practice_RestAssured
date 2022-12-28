package dummyRestApiExample;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class GetAllEmployee {

    @Test
    public void getAllEmployee(){

        baseURI="https://dummy.restapiexample.com/api/v1";

        Response response= given().accept(ContentType.JSON)
                .when().get("/employees")
                .then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

//assert status message is "success"
        String expectedStatusMessage="success";
        String actualStatusMessage=response.path("status");
        Assertions.assertEquals(expectedStatusMessage,actualStatusMessage);

//get the first employee name
        String firstEmployeeName=response.path("data[0].employee_name");
        //System.out.println("firstEmployeeName = " + firstEmployeeName);
        Assertions.assertNotNull(firstEmployeeName);

//get response body as a string
        String bodyStr = response.body().asString();
        //System.out.println("bodyStr = " + bodyStr);

//get all employee into the List
        List<Map<String ,Object>> list=response.path("data");
        System.out.println(list.get(2).get("employee_name"));

//print each employee's name and age
        int i=1;
        for (Map<String, Object> each : list) {
            System.out.println(i++ +". name: "+each.get("employee_name")+", age: "+each.get("employee_age"));
        }


        //response.prettyPrint();

    }


}
