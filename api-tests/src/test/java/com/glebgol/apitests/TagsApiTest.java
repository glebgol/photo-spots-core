package com.glebgol.apitests;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;


public class TagsApiTest {

    private static final String BASE_URL = "http://localhost:8080/tags";
    private static final Long EXISTING_ID = 1L;
    private static final Long NON_EXISTING_ID = -1L;
    private static final Map<String, String> EMPTY_BODY = new HashMap<>();

    @Test
    public void getTagsReturnsOkStatusCode() {
        given()
                .baseUri(BASE_URL)
                .when()
                .get(BASE_URL)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getTagDetailsByIdReturnsOkStatusCode() {
        given()
                .baseUri(BASE_URL)
                .when()
                .get("/{id}", EXISTING_ID)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getNotExistingTagDetailsReturnsNotFoundStatusCode() {
        given()
                .baseUri(BASE_URL)
                .when()
                .get("/{id}", NON_EXISTING_ID)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("info", containsString("Not found tag with id=" + NON_EXISTING_ID));
    }

    @Test
    public void createTagWithEmptyBodyReturnsBadRequestStatusCode() {
        given()
                .contentType("application/json")
                .body(EMPTY_BODY)
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void createTagWithMissingImageAndDescription() {
        Map<String, String> createTagRequest = new HashMap<>();
        createTagRequest.put("longitude", "1.0");
        createTagRequest.put("latitude", "1.0");

        given()
                .baseUri(BASE_URL)
                .body(createTagRequest)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("image", equalTo("image should be not null"))
                .body("description", equalTo("description should be not null"));
    }
}
