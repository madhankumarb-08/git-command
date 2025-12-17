package org.example;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Main
{
    public void develop()
    {
        System.out.println("Hi am developered a software");
        System.out.println("Hi am developered a software");
        System.out.println("Hi am developered a software");
        System.out.println("Hi am developered a software");
        System.out.println("Hi am developered a software");
        System.out.println("Hi am developered a software");
        System.out.println("Hi am developered a software");

    }
    public static void main(String[] args)
    {
        // rest assured works in with 3 principal ways
        // 1. given - all inputes (url  , header, body  )
        // given()
        // 2. given - resource , which http method ?
        // when()
        // 3. then - match the output
        // then()
        // Here is an example of each style:
        System.out.println("Fetching Api");


        System.out.println("Hi am Madhan working from Indian time zone ");

        System.out.println("Ok i am from asian AST i have pushed a new one ");



        //-----------------------------------Add place ---------------------------------------------
        RestAssured.baseURI = "https://rahulshettyacademy.com";
       String addplace =  given().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(Payload.AddPlace())
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString();
        System.out.println(addplace);

        JsonPath jsonPath = new JsonPath(addplace);
        String id = jsonPath.getString("place_id");
        System.out.println(id);


        //-----------------------------------update place ---------------------------------------------
        String newAddress = "Coimbatore , Rs puram";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body(Payload.updateAddress(id,newAddress))
                .when().put("/maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));


        // //-----------------------------------get place ---------------------------------------------

        String getPlace = given().queryParam("key","qaclick123").queryParam("place_id",id)
                .when().get("/maps/api/place/get/json")
                .then().assertThat().statusCode(200).extract().response().toString();
        JsonPath jsonPath1= new JsonPath(getPlace);
        String updatedAddress = jsonPath1.getString("address");
        System.out.println("Updated Address ✅"+updatedAddress);


    }
}