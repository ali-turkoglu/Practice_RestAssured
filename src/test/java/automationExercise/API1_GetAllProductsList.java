package automationExercise;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.config.XmlPathConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.path.json.JsonPath;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;


public class API1_GetAllProductsList {


    public Response response;
    public List<Map<String ,Object>> brands;


    @BeforeAll
    public static void init() {
        baseURI = "https://automationexercise.com/api";
    }


    @Test
    public void getAllProductsList() {

//        API URL: https://automationexercise.com/api/productsList
//        Request Method: GET
//        Response Code: 200
//        Response JSON: All products list

        int expectedStatusCode = 200;

        response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get("/productsList");



        //System.out.println("response = " + response.statusCode());
        //System.out.println("response.prettyPrint() = " + response.prettyPrint());

        Assertions.assertEquals(expectedStatusCode, response.statusCode());

    }

    @Test
    public void postToAllProductsList() {
        //API 2: POST To All Products List
        //API URL: https://automationexercise.com/api/productsList
        //Request Method: POST
        //Response Code: 405
        //Response Message: This request method is not supported.

        response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\n" +
                        "  \"products\": [\n" +
                        "    {\n" +
                        "      \"name\": \"Ahmet\",\n" +
                        "      \"price\": \"try. 500\",\n" +
                        "      \"brand\": \"Turkuaz\",\n" +
                        "      \"category\": {\n" +
                        "        \"usertype\": {\n" +
                        "          \"usertype\": \"Women\"\n" +
                        "        },\n" +
                        "        \"category\": \"Tops\"\n" +
                        "      }\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
                .when()
                .post("/productsList");



        System.out.println("response status code= " + response.statusCode());
        String expectedResponseCode = "\"responseCode\": 405";
        String expectedMessage = "This request method is not supported.";

        //response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200);

        Assertions.assertTrue(response.body().asString().contains(expectedMessage));
        Assertions.assertTrue(response.body().asString().contains(expectedResponseCode));
        //System.out.println("response.body().asString().contains(\"responseconde:405\") = " + response.body().asString().contains("\"responseCode\": 405"));

    }

    @Test
    public void getAllBrandsList(){
        //API URL: https://automationexercise.com/api/brandsList
        //Request Method: GET
        //Response Code: 200
        //Response JSON: All brands list

        response=given().accept(ContentType.JSON)
                .get("/brandsList");

//        response.then().assertThat()
//                .statusCode(200)
//                .log().all();

        //response.prettyPrint();

        String strbody=response.htmlPath().getString("html.body");

        JsonPath jsonPath=new JsonPath(strbody);

        brands = jsonPath.getList("brands");
        System.out.println("products.get(\"brand\") = " + brands.get(0));

        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertNotNull(jsonPath.getList("brands"));

    }




}
