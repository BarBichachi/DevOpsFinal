import java.time.Duration;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class StressTestSimulation extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("http://34.58.208.53:8080")
    .inferHtmlResources()
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,/;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.9,he;q=0.8")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64)");

  private ScenarioBuilder scn = scenario("StressTestSimulation")
    .exec(
      http("Request Home Page")
        .get("/index.jsp")
    );

  {
    setUp(
      scn.injectOpen(
        rampUsersPerSec(100).to(1200).during(Duration.ofMinutes(1)),    
        rampUsersPerSec(1200).to(200).during(Duration.ofMinutes(1)),   
        rampUsersPerSec(200).to(1200).during(Duration.ofMinutes(1)),    
        rampUsersPerSec(1200).to(0).during(Duration.ofMinutes(1))      
      ).protocols(httpProtocol)
    );
  }
}
