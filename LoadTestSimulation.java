import java.time.Duration;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class LoadTestSimulation extends Simulation {

    private HttpProtocolBuilder httpProtocol = http
        .baseUrl("http://localhost:8080")
        .inferHtmlResources()
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("en-US,en;q=0.9,he;q=0.8")
        .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.0.0 Safari/537.36");

    private ScenarioBuilder scn = scenario("LoadTestSimulation")
        .exec(
            http("Load_Homepage_Stress")
                .get("/AmitAmitBar/")
        );

    {
            setUp(
            scn.injectOpen(
                rampUsersPerSec(1).to(380).during(Duration.ofMinutes(2)), 
                constantUsersPerSec(380).during(Duration.ofMinutes(2)),    
                rampUsersPerSec(380).to(1).during(Duration.ofMinutes(1))  
            )
        ).protocols(httpProtocol);

    }
}