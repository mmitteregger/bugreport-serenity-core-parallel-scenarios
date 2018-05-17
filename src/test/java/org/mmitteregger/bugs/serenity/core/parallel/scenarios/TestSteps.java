package org.mmitteregger.bugs.serenity.core.parallel.scenarios;

import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.api.java8.En;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class TestSteps implements En {

    public TestSteps() {
        Given("^WireMock returns a response with status code (\\d+) on a GET request to (.*)$",
                (final Integer statusCode, final String url) -> {
                    final TestData testData = TestData.get();
                    final WireMockServer wireMockServer = testData.getWireMockServer();
                    wireMockServer.stubFor(get(urlEqualTo(url))
                            .willReturn(aResponse()
                                    .withStatus(statusCode)));
                });

        When("^I send a GET request to WireMock on (.*)$",
                (final String url) -> {
                    final Response response = RestAssured.get(url);
                    final TestData testData = TestData.get();
                    testData.setResponse(response);
                });

        Then("^the response status code should be (\\d+)$",
                (final Integer expectedStatusCode) -> {
                    final TestData testData = TestData.get();
                    final Response response = testData.getResponse();
                    response.then().statusCode(expectedStatusCode);
                });
    }

}
