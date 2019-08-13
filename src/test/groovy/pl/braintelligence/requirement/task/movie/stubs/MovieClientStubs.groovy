package pl.braintelligence.requirement.task.movie.stubs

import com.github.tomakehurst.wiremock.stubbing.StubMapping
import com.google.common.base.Charsets
import com.google.common.io.Resources
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus

import static com.github.tomakehurst.wiremock.client.WireMock.*

class MovieClientStubs {

    private static final String VALID_URL_MOVIES = "/?apikey=test&t=fast"
    private static final String VALID_URL_MOVIES_BY_ID = "/?apikey=test&i=imdbId"

    static StubMapping stubMovieApiResponse() {
        return stubFor(get(urlEqualTo(VALID_URL_MOVIES))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                        .withBody(getFileContent("stubs/ValidMovieApiResponse.json"))))
    }

    static StubMapping stubMovieApiResponseSearchById() {
        return stubFor(get(urlEqualTo(VALID_URL_MOVIES_BY_ID))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                        .withBody(getFileContent("stubs/ValidMovieApiResponse.json"))))
    }

    static StubMapping stubInvalidApiKey() {
        return stubFor(get(urlMatching(VALID_URL_MOVIES))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.BAD_GATEWAY.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, "application/json")))
    }

    static String getFileContent(String filename) throws IOException {
        return Resources.toString(Resources.getResource(filename), Charsets.UTF_8)
    }
}
