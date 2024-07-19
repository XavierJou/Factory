package formation.conceptdev.facto.configurations;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@SecurityScheme(type=SecuritySchemeType.HTTP,name = "basicAuth", scheme = "basic")
public class SecurityRestConfig {

	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/swagger-ui/","/swagger-ui.html","/v3/").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/utilisateur/inscription").anonymous()
                .requestMatchers(HttpMethod.GET).authenticated()
                .anyRequest().hasAnyAuthority("ROLE_ADMIN");
        });

        //desactivation de la session utilisateur
        http.sessionManagement(manager->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.cors(c->{
            CorsConfigurationSource source=request ->{
                CorsConfiguration config=new CorsConfiguration();

                config.setAllowedOrigins(List.of(""));
                config.setAllowedMethods(List.of(""));
                config.setAllowedHeaders(List.of("*"));
                return config;
            };
            c.configurationSource(source);
        });

        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
