package at.htl.MovieManager;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class MovieResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/movie")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}