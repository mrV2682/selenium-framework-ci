package api.base;

import api.client.AuthClient;
import core.ApiConfig;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {

    protected RequestSpecification requestSpec;

    @BeforeClass (alwaysRun = true)
    public void setupApi() {

        requestSpec = RestAssured
                .given()
                .log().all()
                .baseUri(ApiConfig.BASE_URL)
                .contentType("application/json");
        if (ApiConfig.AUTH_ENABLED) {
            String token = AuthClient.getAccessToken();
            requestSpec.header("Authorization", "Bearer " + token);
        }
    }
}
