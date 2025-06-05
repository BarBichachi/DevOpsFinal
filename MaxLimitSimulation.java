import java.time.Duration;
import java.util.Map;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class MaxLimitSimulation extends Simulation {

    private HttpProtocolBuilder httpProtocol = http
        .baseUrl("http://localhost:8080")
        .inferHtmlResources(AllowList(), DenyList(".*\\.js", ".*\\.css", ".*\\.gif", ".*\\.jpeg", ".*\\.jpg", ".*\\.ico", ".*\\.woff", ".*\\.woff2", ".*\\.(t|o)tf", ".*\\.png", ".*\\.svg", ".*detectportal\\.firefox\\.com.*"))
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
        .acceptEncodingHeader("gzip, deflate, br")
        .acceptLanguageHeader("en-US,en;q=0.9,he;q=0.8")
        .upgradeInsecureRequestsHeader("1")
        .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36");
    
    private Map<CharSequence, String> headers_0 = Map.ofEntries(
        Map.entry("Sec-Fetch-Dest", "document"),
        Map.entry("Sec-Fetch-Mode", "navigate"),
        Map.entry("Sec-Fetch-Site", "same-origin"),
        Map.entry("Sec-Fetch-User", "?1"),
        Map.entry("sec-ch-ua", "Chromium\";v=\"136\", \"Google Chrome\";v=\"136\", \"Not.A/Brand\";v=\"99"),
        Map.entry("sec-ch-ua-mobile", "?0"),
        Map.entry("sec-ch-ua-platform", "Windows")
    );

    private ScenarioBuilder scn = scenario("MaxLimitSimulation")
        .exec(
            http("Request_Homepage_Spike")
                .get("/AmitAmitBar/?username=AmitBar")
                .headers(headers_0)
        );

    {

        setUp(scn.injectClosed‎(rampConcurrentUsers(0).to(500).during(Duration.ofSeconds(60))).protocols(httpProtocol));
    }
}

