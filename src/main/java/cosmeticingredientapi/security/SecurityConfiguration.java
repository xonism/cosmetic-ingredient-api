package cosmeticingredientapi.security;

import cosmeticingredientapi.constants.Constants;
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

    private final String[] publicEndpoints = new String[] {"/", "/login", "/error"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth -> {
            auth.requestMatchers(publicEndpoints).permitAll();
            auth.anyRequest().hasAuthority(Constants.ADMIN);
        });
        httpSecurity.logout().logoutSuccessUrl("/login");
        httpSecurity.exceptionHandling().defaultAuthenticationEntryPointFor(
                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                new AntPathRequestMatcher("/api/**"));
        httpSecurity.oauth2Login();
        return httpSecurity.build();
    }
}
