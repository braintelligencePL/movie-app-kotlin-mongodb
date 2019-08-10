package pl.braintelligence.requirement.task.news.base

import com.github.tomakehurst.wiremock.stubbing.StubMapping
import com.google.common.base.Charsets
import com.google.common.io.Resources
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo
import static com.github.tomakehurst.wiremock.client.WireMock.get
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching

class NewsEndpointStubs {

    private static final String VALID_URL_TOP_HEADLINES = "/?apiKey=test&category=technology&country=pl&page=1"
    private static final String VALID_URL_QUERY_FOR_ARTICLES = "/?apiKey=test&q=bitcoin"

    static StubMapping stubNewsApiTopHeadlinesResponse() {
        return stubFor(get(urlEqualTo(VALID_URL_TOP_HEADLINES))
                .willReturn(aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .withBody(getFileContent("stubs/NewsApiTopHeadlinesResponse.json"))))
    }

    static StubMapping stubNewsApiQueryForArticlesResponse() {
        return stubFor(get(urlEqualTo(VALID_URL_QUERY_FOR_ARTICLES))
                .willReturn(aResponse()
                .withStatus(HttpStatus.OK.value())
                .withHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .withBody(getFileContent("stubs/NewsApiQueryForArticlesResponse.json"))))
    }

    static StubMapping stubNewsApiNotResponding() {
        return stubFor(get(urlMatching(VALID_URL_TOP_HEADLINES)).willReturn(aResponse()
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())))
    }

    static String getFileContent(String filename) throws IOException {
        return Resources.toString(Resources.getResource(filename), Charsets.UTF_8)
    }
}
