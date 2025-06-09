import java.time.Duration;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class StressTestSimulation extends Simulation {

    private HttpProtocolBuilder httpProtocol = http
        .baseUrl("http://localhost:8080")
        .inferHtmlResources()
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("en-US,en;q=0.9,he;q=0.8")
        .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.0.0 Safari/537.36");

    private ScenarioBuilder scn = scenario("StressTestSimulation")
        .exec(
            http("Load_Homepage_Stress")
                .get("/AmitAmitBar/")
        );

    {
            setUp(
      scn.injectOpen(
        rampUsersPerSec(25).to(500).during(Duration.ofMinutes(1)),    
        rampUsersPerSec(500).to(50).during(Duration.ofMinutes(1)),   
        rampUsersPerSec(50).to(500).during(Duration.ofMinutes(1)),    
        rampUsersPerSec(500).to(0).during(Duration.ofMinutes(1))      
      ).protocols(httpProtocol)
    );

    }
}