package cosmeticingredientapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // @formatter:off
        httpSecurity.authorizeHttpRequests(auth ->
                        auth.requestMatchers("/", "/login").permitAll()
                                .anyRequest().authenticated())
                .logout()
                    .logoutSuccessUrl("/login").permitAll()
                .and()
                .exceptionHandling()
                    .defaultAuthenticationEntryPointFor(
                            new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                            new AntPathRequestMatcher("/api/**"))
                .and()
                .oauth2Login();
        // @formatter:on
        return httpSecurity.build();
    }
}
