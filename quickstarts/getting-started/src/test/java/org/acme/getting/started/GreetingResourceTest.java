package org.acme.getting.started;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class GreetingResourceTest {


    @Test
    public void testGreetingEndpoint() {
        String uuid = "Bamse";
        given()
                .pathParam("name", uuid)
                .when().get("/whois/{name}")
                .then()
                .statusCode(200)
                .body(containsString("Bamse"));
    }

}
