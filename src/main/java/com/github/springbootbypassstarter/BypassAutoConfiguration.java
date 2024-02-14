package com.github.springbootbypassstarter;

import com.github.springbootbypassstarter.config.BypassProperties;
import com.github.springbootbypassstarter.filter.HeaderFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(BypassProperties.class)
public class BypassAutoConfiguration {

    private final BypassProperties properties;

    @Bean
    public OncePerRequestFilter headerFilter() {
        return new HeaderFilter(properties.getDefaultHeaders());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                authorizeHttpRequest -> authorizeHttpRequest.anyRequest().permitAll()
            ).build();
    }
}
