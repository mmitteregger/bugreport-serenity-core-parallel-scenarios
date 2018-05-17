package org.mmitteregger.bugs.serenity.core.parallel.scenarios;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.response.Response;

public class TestData {

    private static final ThreadLocal<TestData> THREAD_LOCAL =
            ThreadLocal.withInitial(TestData::new);

    private WireMockServer wireMockServer;
    private Response response;


    public static TestData get() {
        return THREAD_LOCAL.get();
    }

    public void setWireMockServer(final WireMockServer wireMockServer) {
        this.wireMockServer = wireMockServer;
    }

    public WireMockServer getWireMockServer() {
        return wireMockServer;
    }

    public void setResponse(final Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
