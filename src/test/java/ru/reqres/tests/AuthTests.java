package ru.reqres.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.reqres.config.TestBase;
import ru.reqres.models.LoginBodyModel;
import ru.reqres.models.LoginResponseErrorModel;
import ru.reqres.models.LoginResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.reqres.specs.ReqresSpecs.basicRequestSpec;
import static ru.reqres.specs.ReqresSpecs.responseSpec;

@Tag("regress")
public class AuthTests extends TestBase {
  
  @Test
  @Feature("Reqres.Авторизация")
  @Story("Успешный логин")
  @DisplayName("Успещный логин")
  @Owner("Абросимов Федор")
  void loginTest() {
    LoginBodyModel authData = new LoginBodyModel();
    authData.setEmail("eve.holt@reqres.in");
    authData.setPassword("cityslicka");
    
    LoginResponseModel response = step("Делаем запрос", () ->
      given(basicRequestSpec)
        .contentType(JSON)
        .body(authData)
        .when()
        .post("/login")
        .then()
        .spec(responseSpec(200))
        .body(matchesJsonSchemaInClasspath("schemas/success-login-schema.json"))
        .extract().as(LoginResponseModel.class));
    
    step("Проверяем ответ", () ->
      assertThat(response.getToken()).isNotNull());
  }
  
  @Test
  @Feature("Reqres.Авторизация")
  @Story("Неуспешный логин")
  @DisplayName("Неуспешный логин")
  @Owner("Абросимов Федор")
  void loginWithNoContentTypeTest() {
    LoginBodyModel authData = new LoginBodyModel();
    authData.setEmail("eve.holt@reqres.in");
    authData.setPassword("cityslicka");
    
    LoginResponseErrorModel response = step("Делаем запрос", () ->
      given(basicRequestSpec)
        .body(authData)
        .when()
        .post("/login")
        .then()
        .spec(responseSpec(400))
        .extract().as(LoginResponseErrorModel.class));
    
    step("Проверяем ответ", () ->
      assertThat(response.getError()).isEqualTo("Missing email or username"));
  }
  
  @Test
  @Feature("Reqres.Авторизация")
  @Story("Неуспешный логин")
  @DisplayName("Неуспешный логин, пароль не введен")
  @Owner("Абросимов Федор")
  void loginWithoutPasswordTest() {
    LoginBodyModel authData = new LoginBodyModel();
    authData.setEmail("eve.holt@reqres.in");
    
    LoginResponseErrorModel response = step("Make request", () ->
      given(basicRequestSpec)
        .contentType(JSON)
        .body(authData)
        .when()
        .post("/login")
        .then()
        .spec(responseSpec(400))
        .extract().as(LoginResponseErrorModel.class));
    
    step("Проверяем ответ", () ->
      assertThat(response.getError()).isEqualTo("Missing password"));
  }
}
