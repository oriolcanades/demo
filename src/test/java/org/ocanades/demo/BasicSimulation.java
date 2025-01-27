package org.ocanades.demo;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class BasicSimulation extends Simulation {

    // Load test for tenants read /api/v1/tenants

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .acceptHeader("application/json");

    ScenarioBuilder scn = scenario("Basic Simulation")
            .exec(http("request_hello")
                    .get("/api/v1/tenants")
                    .check(status().is(200)));

    {
        setUp(
                scn.injectOpen(atOnceUsers(10000)) // Simulate 1000 users at once
        ).protocols(httpProtocol);
    }

}
