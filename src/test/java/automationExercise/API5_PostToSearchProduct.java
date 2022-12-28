package automationExercise;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apiguardian.api.API;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

//    API 5: POST To Search Product
public class API5_PostToSearchProduct {


//    API URL: https://automationexercise.com/api/searchProduct
//    Request Method: POST
//    Request Parameter: search_product (For example: top, tshirt, jean)
//    Response Code: 200
//    Response JSON: Searched products list

    // query param is not working in this test. Check it again later
    @Test
    public void postToSearchProduct() {
        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("search_product","top")
                .when().post("https://automationexercise.com/api/searchProduct");

        response.prettyPrint();

    }
}
