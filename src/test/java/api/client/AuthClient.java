package api.client;

import core.ApiConfig;
//import io.restassured.RestAssured;

public class AuthClient {

    private static String cachedToken;

    private AuthClient() {}

   /* public static String getAccessToken() {
        if (cachedToken == null) {
            cachedToken = loadTokenFromConfig();
        }
        return cachedToken;
    }

    private static String loadTokenFromConfig() {
        return RestAssured
                .given()
                .baseUri(ApiConfig.BASE_URL)
                .contentType("application/json")
                .body(buildLoginPayload())
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }

    private static String buildLoginPayload() {
        return String.format("""
            {
              "username": "%s",
              "password": "%s"
            }
            """,
                ApiConfig.USERNAME,
                ApiConfig.PASSWORD
        );
    } */
    /**
       for token pattern (GoRest API )
       token static ( manually created on GoRest page and
       config in the config file already )
     */
    public static String getAccessToken() {
        if (cachedToken == null) {
            cachedToken = loadTokenFromConfig();
        }
        return cachedToken;
    }

    private static String loadTokenFromConfig() {
        //  CI first
        String envToken = System.getenv("GOREST_TOKEN");
        //for debug only
        System.out.println(">>> [AuthClient] GOREST_TOKEN from env = "
                + (envToken == null ? "null" : "***loaded***"));

        if (envToken != null && !envToken.isBlank()) {
            return envToken;
        }

        //  Local fallback
        if (ApiConfig.API_TOKEN != null && !ApiConfig.API_TOKEN.isBlank()) {
            return ApiConfig.API_TOKEN;
        }

        //  Fail fast
        throw new RuntimeException("""
            GoRest API token is missing.
            Please set:
            - GOREST_TOKEN as environment variable (CI), OR
            - api.token in config-<env>.properties (local)
        """);
    }
}
