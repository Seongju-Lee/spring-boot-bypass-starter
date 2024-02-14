package com.github.springbootbypassstarter.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;


@Getter
@ConfigurationProperties(prefix = "dev.bypass")
public class BypassProperties {
    private boolean bypassSecurity = false;
    private Map<String, String> defaultHeaders = new HashMap<>();
}
