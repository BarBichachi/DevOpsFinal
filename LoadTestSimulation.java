import java.time.Duration;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class LoadTestSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http
        .baseUrl("http://34.58.208.53:8080")
        .inferHtmlResources();

    ScenarioBuilder scn = scenario("LoadTestSimulation")
        .exec(
            http("Request Home Page")
            .get("/index.jsp")
        );

    {
        setUp(
            scn.injectOpen(
                rampUsersPerSec(1).to(750).during(Duration.ofMinutes(2)), 
                constantUsersPerSec(750).during(Duration.ofMinutes(2)),    
                rampUsersPerSec(750).to(1).during(Duration.ofMinutes(1))  
            )
        ).protocols(httpProtocol);
    }
}
