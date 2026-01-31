package api.tests;

import api.base.BaseApiTest;
import api.client.UserClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test(groups={"api"})
public class UserApiTest extends BaseApiTest {
    private UserClient userClient;

    @BeforeMethod
    public void setupClient(){
        userClient = new UserClient(requestSpec);
    }

    @Test
    public void shouldCreateAndGetUserSuccessfully() {



        //-----------GIVEN---------
        //A valid user payLoad
        String payload = """
        {
          "name": "Vuong Tran",
          "gender": "male",
          "email": "vuong%s@test.com",
          "status": "active"
        }
        """.formatted(System.currentTimeMillis());

        // ---------- WHEN ----------
        // Create user via POST /users
        Response createResponse = userClient.createUser(payload);

        // ---------- THEN ----------
        //Verify user is created successfully
        Assert.assertEquals(
                createResponse.statusCode(),
                201,
                "Response body = " + createResponse.asPrettyString()
        );

        int userId = createResponse.path("id");
        Assert.assertTrue(userId > 0, "User ID was not created");

        // ---------- WHEN ----------
        //Get created user via GET /users/{id}
        Response getResponse = userClient.getUserById(userId);

        // ---------- THEN ----------
        //Verify user detail
        Assert.assertEquals(getResponse.statusCode(), 200);
        int actualID=getResponse.path("id");
        Assert.assertEquals(actualID, userId);
        Assert.assertEquals(getResponse.path("name"), "Vuong Tran");
    }
}
