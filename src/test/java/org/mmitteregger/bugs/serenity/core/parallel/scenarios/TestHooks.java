package org.mmitteregger.bugs.serenity.core.parallel.scenarios;

import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class TestHooks {

    private static final Logger LOG = LoggerFactory.getLogger(TestHooks.class);

    @Before(order = 500)
    public void startWireMockServer() {
        final WireMockServer wireMockServer = new WireMockServer(wireMockConfig().dynamicPort());
        wireMockServer.start();

        final TestData testData = TestData.get();
        testData.setWireMockServer(wireMockServer);

        LOG.info("Started new WireMock server at localhost:{}", wireMockServer.port());
    }

    @Before(order = 1_000)
    public void configureRestAssured() {
        final TestData testData = TestData.get();
        final WireMockServer wireMockServer = testData.getWireMockServer();

        RestAssured.baseURI = "http://localhost:" + wireMockServer.port();

        LOG.info("Configured default RestAssured baseURI to: {}", RestAssured.baseURI);
    }

    @After
    public void stopWireMockServer() {
        final TestData testData = TestData.get();
        final WireMockServer wireMockServer = testData.getWireMockServer();
        wireMockServer.stop();

        LOG.info("Stopped WireMock server");
    }

}
