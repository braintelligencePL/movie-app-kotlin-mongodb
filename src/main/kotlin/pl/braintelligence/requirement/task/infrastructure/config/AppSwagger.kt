package pl.braintelligence.requirement.task.infrastructure.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.*

@Configuration
@EnableWebMvc
open class WebMvcConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
    }

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs")
        registry.addRedirectViewController("/api/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui")
        registry.addRedirectViewController("/api/swagger-resources/configuration/security", "/swagger-resources/configuration/security")
        registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources")
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/api/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html")
        registry.addResourceHandler("/api/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
    }
}
