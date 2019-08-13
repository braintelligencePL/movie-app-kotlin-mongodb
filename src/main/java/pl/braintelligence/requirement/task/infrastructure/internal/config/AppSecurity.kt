package pl.braintelligence.requirement.task.infrastructure.internal.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
open class AppSecurity : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
    }

    @Autowired
    fun configureGlobal(authentication: AuthenticationManagerBuilder) {
        authentication.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .authorities("ADMIN")
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
