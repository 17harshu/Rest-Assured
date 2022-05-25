package com.blz.restdemo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class BDDTest {
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
    public void postTestForPosts(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",1);
        jsonObject.put("title","json-server");
        jsonObject.put("author","Harshal");

        given().
                header("Content-Type", "application/json").
                body(jsonObject.toJSONString()).
                when().
                post("http://localhost:3000/posts").
                then().
                statusCode(201);
    }

@Test
    public void putTestForPosts(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title","json-server");
        jsonObject.put("author","Johny");

        given().
                header("Content-Type", "application/json").
                body(jsonObject.toJSONString()).
                when().
                put("http://localhost:3000/posts/1").
                then().
                statusCode(200);
    //int statusCode = response.statusCode();
    //Assert.assertEquals(statusCode(), 200);
    }

    @Test
    public void deleteTestForPosts(){

        given().
                header("Content-Type", "application/json").
                when().
                delete("http://localhost:3000/posts/1").
                then().
                statusCode(200);
    }

    @Test
    public void postTestForComments(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",3);
        jsonObject.put("body","QA Automation Notes");
        jsonObject.put("postId",1);

        given().
                header("Content-Type", "application/json").
                body(jsonObject.toJSONString()).
                when().
                post("http://localhost:3000/comments").
                then().
                statusCode(201);
    }
    /* Relation between posts and comments */
    @Test
    public void getTestForComments() {
        Response response = RestAssured.get("http://localhost:3000/posts/1/comments");
        response.prettyPrint();
        given().
                when().
                get("http://localhost:3000/posts/1/comments").
                then().
                statusCode(200);
    }

    @Test
    public void putTestForComments(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("body","QA Notes");

        given().
                header("Content-Type", "application/json").
                body(jsonObject.toJSONString()).
                when().
                put("http://localhost:3000/comments/1").
                then().
                statusCode(200);
        //int statusCode = response.statusCode();
        //Assert.assertEquals(statusCode(), 200);
    }
}
