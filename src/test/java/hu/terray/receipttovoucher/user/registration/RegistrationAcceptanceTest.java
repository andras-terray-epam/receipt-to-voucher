package hu.terray.receipttovoucher.user.registration;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import io.restassured.response.ExtractableResponse;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.net.URL;

import static io.restassured.RestAssured.given;

/**
 * Rest Assures acceptance test to validate registration endpoint.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegistrationAcceptanceTest {

    private static final String USER_TO_TEST_ON = "acceptanceTestUser@test.com";

    private static ExtractableResponse normalUserJTW;
    private static ExtractableResponse adminUserJTW;

    @Test
    public void step1RegistrationShouldGiveOkResponseWhenAllNecessaryInfoPosted() throws IOException {

        URL file = Resources.getResource("json/registration/normalUserRegistration.json");
        String userLoginJsonBody = Resources.toString(file, Charsets.UTF_8);

        given().contentType("application/json")
            .body(userLoginJsonBody)
            .relaxedHTTPSValidation()
        .when()
            .post("https://localhost:9000/api/v1/registration")
            .then()
            .statusCode(201);

    }

    @Test
    public void step2LoginShouldGiveOkResponseWhenEmailAndPAsswordCorrect() throws IOException {

        URL file = Resources.getResource("json/login/userlogin.json");
        String userLoginJsonBody = Resources.toString(file, Charsets.UTF_8);

        normalUserJTW = given().contentType("application/json")
            .body(userLoginJsonBody)
            .relaxedHTTPSValidation()
        .when()
            .post("https://localhost:9000/api/v1/login")
            .then()
            .statusCode(200)
        .extract();

    }

    @Test
    public void step3AdminLoginShouldGiveOkResponseWhenAllNecessaryInfoPosted() throws IOException {

        URL file = Resources.getResource("json/login/adminlogin.json");
        String userLoginJsonBody = Resources.toString(file, Charsets.UTF_8);

        adminUserJTW = given().contentType("application/json")
               .body(userLoginJsonBody)
               .relaxedHTTPSValidation()
        .when()
               .post("https://localhost:9000/api/v1/login")
        .then()
               .statusCode(200)
        .extract();
        System.out.println(adminUserJTW.body().asString());
    }

    @Test
    public void step4DeletionShouldGiveExceptionWhenNotAdminUserTryToDelete() throws IOException {
        given().contentType("application/json")
               .relaxedHTTPSValidation()
               .header("Authorization", "Bearer " + normalUserJTW.body().asString())
        .when()
               .delete("https://localhost:9000/api/v1/users/{email}/delete", USER_TO_TEST_ON)
        .then()
               .statusCode(403);

    }

    @Test
    public void step5DeletionShouldGiveOkWhenAdminUserTryToDelete() throws IOException {
        given().contentType("application/json")
               .relaxedHTTPSValidation()
               .header("Authorization", "Bearer " + adminUserJTW.body().asString())
        .when()
               .delete("https://localhost:9000/api/v1/users/{email}/delete", USER_TO_TEST_ON)
        .then()
               .statusCode(200);

    }
}
