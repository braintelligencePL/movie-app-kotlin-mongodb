package pl.braintelligence.requirement.task.infrastructure.config

import com.google.common.base.Predicates
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
@EnableConfigurationProperties
open class AppConfig {

    @Value("\${application.connectTimeout}")
    private val connectTimeout: Int = 0

    @Value("\${application.readTimeout}")
    private val readTimeout: Int = 0

    @Bean
    open fun restTemplate(restTemplateBuilder: RestTemplateBuilder): RestTemplate = restTemplateBuilder
            .setConnectTimeout(connectTimeout)
            .setReadTimeout(readTimeout)
            .build()

    @Bean
    open fun apiDocket(): Docket =
            Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(Predicates.not(PathSelectors.regex("/error.*")))
                    .paths(Predicates.not(PathSelectors.regex("/actuator.*")))
                    .build()
}
