package api.client;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class UserClient {

    private final RequestSpecification spec;

    public UserClient(RequestSpecification spec) {
        this.spec = Objects.requireNonNull(spec, "RequestSpecification must not be null");
    }

    public Response getUserById(int userId) {
        return spec
                .when()
                .get("/users/" + userId);
    }

    public Response createUser(String payload) {
        return spec
                .body(payload)
                .when()
                .post("/users");
    }
}
