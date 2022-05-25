package com.blz.restdemo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestDemo {

    @Test
    public void Test() {
        /* To get the page 2 details */
        // Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        /* To get only the specific id details*/
        //Response response = RestAssured.get("https://reqres.in/api/users/8");

        /* To get the totals */
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        RestAssured.given().when().get("https://reqres.in/api/users?page=2")
              .then().body("data.email[0]",equalTo("michael.lawson@reqres.in"));

        System.out.println("Status code: " + response.statusCode());

        System.out.println("Response body: " + response.prettyPrint());
        Assert.assertEquals(response.statusCode(), 200);

    }

    @Test
    public void postTestForPosts() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "11");
        jsonObject.put("title", "Bridgelabz QA Learning");
        jsonObject.put("author", "Harshal Anandas");

        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        request.body(jsonObject.toJSONString());
        Response response = request.post("http://localhost:3000/posts");

        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

    @Test
    public void putTestForPosts() {
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "2");
        jsonObject.put("title", "Bridgelabz Automation");
        jsonObject.put("author", "Harshal Anandas");

        request.body(jsonObject.toJSONString());
        Response response = request.put("http://localhost:3000/posts/2");

        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, 200);
        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

    @Test
    public void getTestForPosts() {
        Response response = RestAssured.get("http://localhost:3000/posts");
        response.prettyPrint();

        given().
                when().
                get("http://localhost:3000/posts").
                then().
                statusCode(200);
    }

    @Test
    public void deleteTestForPosts() {
        RequestSpecification request = given();
        Response response = request.delete("http://localhost:3000/posts/11");

        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());
    }
}
