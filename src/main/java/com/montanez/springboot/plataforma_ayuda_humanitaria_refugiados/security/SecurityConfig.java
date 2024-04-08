package com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.exception.CustomAuthenticationEntryPoint;
import com.montanez.springboot.plataforma_ayuda_humanitaria_refugiados.handler.CustomAccessDeniedHandler;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    @Autowired
    SecurityFilterChain securityFilterChain(HttpSecurity http, JWTValidationFilter jwtValidationFilter)
            throws Exception {
        var requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
        http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/api/usuario/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/swagger-resources")
                .permitAll()
                .requestMatchers(SWAGGER_WHILELIST).permitAll()
                .anyRequest().permitAll())
                .addFilterAfter(jwtValidationFilter, BasicAuthenticationFilter.class);
        http.cors(cors -> corsConfigurationSource());
        // http.csrf(csrf -> csrf.disable());
        http.csrf(csrf -> csrf
                .csrfTokenRequestHandler(requestHandler)
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .exceptionHandling(exeptionHandling -> exeptionHandling
                        .accessDeniedHandler(accessDeniedHandler())
                        .authenticationEntryPoint(authenticationEntryPoint()));

        return http.build();
    }

    private static final String[] SWAGGER_WHILELIST = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-resources"
    };

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        var config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("*"));
        config.setAllowedHeaders(List.of("*"));

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
