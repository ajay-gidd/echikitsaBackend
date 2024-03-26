package com.example.login.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityFilterChainConfig {


    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    public SecurityFilterChainConfig(AuthenticationEntryPoint authenticationEntryPoint ,JWTAuthenticationFilter jwtAuthenticationFilter) {
        this.authenticationEntryPoint =  authenticationEntryPoint;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //disable CORS
        httpSecurity.cors(corsConfig->corsConfig.configurationSource(getConfigurationSource()));

        //disable csrf
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        //filter our request
        httpSecurity.authorizeHttpRequests(
                requestMatcher->
                        requestMatcher.requestMatchers("/api/login").permitAll()
                                .requestMatchers("/api/signUp").permitAll()
                                .requestMatchers("/api/hospital/register").permitAll()
                                .anyRequest().authenticated()


        );


        // exception handling [authentication entry point]
        httpSecurity.exceptionHandling(
                exceptionConfig->exceptionConfig.authenticationEntryPoint(authenticationEntryPoint)
        );

        //session policy [stateless]
        httpSecurity.sessionManagement(
                sessionConfig->sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // jwt authentication filter
        httpSecurity.addFilterBefore(jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class);

        //httpSecurity.cors(Customizer.withDefaults());


        return httpSecurity.build();

    }
    private static CorsConfigurationSource getConfigurationSource(){
        var corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedMethod("OPTIONS");
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.addAllowedHeader("*");

        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:8080"));
        corsConfiguration.setAllowedHeaders(List.of("Content-Type","text/plain","Authorization"));

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return  source;
    }
}
