package praktikum.rest.client;

import io.qameta.allure.Step;
import praktikum.rest.model.User;

import static io.restassured.RestAssured.given;

public class UserClient extends BaseRestClient {
    private static final String USER_CREATE_URI = "auth/register";


    @Step("Create user {user}")
    public void create(User user) {
        given()
                .spec(getBaseReqSpec())
                .body(user)
                .when()
                .post(USER_CREATE_URI)
                .then();
    }
}