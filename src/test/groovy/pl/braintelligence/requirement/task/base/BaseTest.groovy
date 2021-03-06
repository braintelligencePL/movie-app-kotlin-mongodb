package pl.braintelligence.requirement.task.base

import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.apache.commons.codec.binary.Base64
import org.junit.Rule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.http.HttpHeaders
import org.springframework.test.context.ActiveProfiles
import pl.braintelligence.requirement.task.infrastructure.config.AppProfiles
import spock.lang.Specification

import java.nio.charset.Charset

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles(AppProfiles.LOCAL)
abstract class BaseTest extends Specification implements BaseHttpMethods {

    @Rule
    public WireMockRule reportingService = new WireMockRule(12345)

    @Autowired
    private MongoTemplate mongoTemplate

    void setup() {
        clearMongoDB()
    }

    void setupSpec() {
        setupWiremock()
    }

    private void clearMongoDB() {
        for (def collection : mongoTemplate.collectionNames) {
            mongoTemplate.dropCollection(collection)
        }
    }

    private static void setupWiremock() {
        System.setProperty('http.keepAlive', 'false')
        System.setProperty('http.maxConnections', '1')
    }
}
