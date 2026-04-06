package edu.pe.cibertec.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.Matchers.*;


public class ProductSteps {

    private static final String BASE_URI = "https://fakestoreapi.com";
    private Response response;

    @Given("the Fake Store API service is available")
    public void theFakeStoreAPIServiceIsAvailable() {
        response = given()
                .baseUri(BASE_URI)
                .when()
                .get("/products")
                .then()
                .extract().response();
    }

    @When("I request the full list of products")
    public void iRequestTheFullListOfProducts() {
        response = given()
                .baseUri(BASE_URI)
                .when()
                .get("/products");
    }

    @When("I request the product with id {int}")
    public void iRequestTheProductWithId(int id) {
        response = given()
                .baseUri(BASE_URI)
                .pathParam("id", id)
                .when()
                .get("/products/{id}");
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    @And("the number of returned products should be greater than {int}")
    public void theNumberOfReturnedProductsShouldBe(int expectedNumberOfReturnedProducts) {
        response.then().body("size()", greaterThan(expectedNumberOfReturnedProducts));
    }

    @And("the returned product should have a non-empty title")
    public void theReturnedProductShouldHaveANonEmptyTitle() {
        response.then().body("title", not(empty()));
    }

    @And("the returned product should have a price greater than {double}")
    public void theReturnedProductShouldHaveAPriceGreaterThan(double expectedPrice) {
        response.then().body("price", greaterThan((float) expectedPrice));
    }

    @And("the returned product should have the category {string}")
    public void theReturnedProductShouldHaveTheCategory(String expectedCategory) {
        response.then().body("category", equalTo(expectedCategory));
    }

}
