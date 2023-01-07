package petStore;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AvailablePets {

@BeforeAll
public static void initial(){
    RestAssured.baseURI="https://petstore.swagger.io/v2";
}


    @Test
    public void getAvailablePets(){

        Response response= RestAssured.given().accept(ContentType.JSON)
                .queryParam("status","available")
                .when().get("/pet/findByStatus")
                .then().assertThat().statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        //response.prettyPrint();

        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertTrue(response.contentType().equals("application/json"));
        Assertions.assertEquals("available",response.path("status[0]"));
        Assertions.assertNotEquals("sold",response.path("status[0]"));
        Assertions.assertNotEquals("pending",response.path("status[0]"));

        JsonPath jsonPath=response.jsonPath();
        //jsonPath.prettyPrint();

        List<Map<String ,Object>> list=response.body().as(List.class);

        int no=1;
        for (Map each :list){
            System.out.println(no+". Name= "+each.get("name")+" Status= " + each.get("status"));
            no++;
            Assertions.assertEquals("available",each.get("status"));
        }
        

    }
}
