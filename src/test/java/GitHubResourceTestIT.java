import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class GitHubResourceTestIT {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    public void shouldReturnRepositoriesForValidUser() {

        String user = "pelemistrz";

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/repos/"+user)
                .then()
                .statusCode(200)
                .body("size()",is(13))
                .body("[0].repositoryName",is("car-rental-back-end"))
                .body("[3].ownerLogin",is(user))
                .body("[3].branches.size()",is(1));
    }


}
