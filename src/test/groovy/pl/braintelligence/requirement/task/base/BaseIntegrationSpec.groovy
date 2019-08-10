package pl.braintelligence.requirement.task.base

import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.Rule
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import pl.braintelligence.requirement.task.Application
import pl.braintelligence.requirement.task.infrastructure.internal.config.Profiles
import spock.lang.Specification

@SpringBootTest(
        classes = [Application],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles([Profiles.LOCAL])
abstract class BaseIntegrationSpec extends Specification implements BaseHttpMethodsSpec {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(12346)

    void setupSpec() {
        setupWiremock()
    }

    private static void setupWiremock() {
        System.setProperty('http.keepAlive', 'false')
        System.setProperty('http.maxConnections', '1')
    }

}
