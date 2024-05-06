package ru.reqres.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.reqres.config.TestBase;
import ru.reqres.models.LoginResponseErrorModel;
import ru.reqres.models.UserDataResponseModel;
import ru.reqres.models.UsersListResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.reqres.specs.ReqresSpecs.basicRequestSpec;
import static ru.reqres.specs.ReqresSpecs.responseSpec;

@Tag("regress")
public class UserTests extends TestBase {
  
  
  @Feature("Reqres.Пользователи")
  @Story("Получение списка пользователей запросом /users?page= ")
  @DisplayName("Получение списка пользователей")
  @Owner("Абросимов Федор")
  @Test
  void userListIsDisplayedTest() {
    UsersListResponseModel response = step("Отправляем запрос", () ->
      given(basicRequestSpec)
        .when()
        .get("/users?page=2")
        .then()
        .body(matchesJsonSchemaInClasspath("schemas/success-users-list-schema.json"))
        .spec(responseSpec(200))
        .extract().as(UsersListResponseModel.class));
    
    step("Проверяем ответ", () ->
      assertThat(response.getPage()).isEqualTo(2));
  }
  
  @Feature("Reqres.Пользователи")
  @Story("Получение одного пользователя запросом /users?page= ")
  @DisplayName("Получение одного пользователя")
  @Owner("Абросимов Федор")
  @Test
  void singleUserIsDisplayedTest() {
    
    UserDataResponseModel response = step("Отправляем запрос", () ->
      given(basicRequestSpec)
        .when()
        .get("/users/3")
        .then()
        .spec(responseSpec(200))
        .body(matchesJsonSchemaInClasspath("schemas/success-single-user-schema.json"))
        .extract().as(UserDataResponseModel.class));
    
    step("Проверяем ответ", () -> {
      assertThat(response.getUser().getId()).isEqualTo(3);
      assertThat(response.getUser().getEmail()).isEqualTo("emma.wong@reqres.in");
      assertThat(response.getUser().getFirstName()).isEqualTo("Emma");
      assertThat(response.getUser().getLastName()).isEqualTo("Wong");
    });
  }
  
  @Feature("Reqres.Пользователи")
  @Story("Получение 404 кода и ошибки при некорректном запроск")
  @DisplayName("Получкние 404")
  @Owner("Абросимов Федор")
  @Test
  @Disabled("Баг на стороне reqress")
  void userNotFoundTest() {
    
    LoginResponseErrorModel response = step("Отправляем запрос", () ->
      given(basicRequestSpec)
        .when()
        .get("/users/100")
        .then()
        .spec(responseSpec(404))
        .extract().as(LoginResponseErrorModel.class));
    
    step("Проверяем ответ", () -> {
      assertThat(response.getError()).isEqualTo(null);
    });
  }
  
  
}
