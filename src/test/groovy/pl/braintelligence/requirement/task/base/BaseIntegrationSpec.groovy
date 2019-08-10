package pl.braintelligence.requirement.task.base

import org.junit.Rule
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import pl.braintelligence.requirement.task.Application
import pl.braintelligence.requirement.task.infrastructure.config.Profiles
import spock.lang.Specification

@SpringBootTest(
        classes = [Application],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles([Profiles.LOCAL])
abstract class BaseIntegrationSpec extends Specification {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(12346)

    void setupSpec() {
        fixWireMock()
    }

    private static void fixWireMock() {
        System.setProperty('http.keepAlive', 'false')
        System.setProperty('http.maxConnections', '1')
    }

}
