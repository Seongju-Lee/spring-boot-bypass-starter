package com.github.springbootbypassstarter.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class HeaderFilter extends OncePerRequestFilter {

    private final Map<String, String> defaultHeaders;


    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {

        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(request) {

            @Override
            public String getHeader(String name) {
                if (defaultHeaders.containsKey(name) && super.getHeader(name) == null) {
                    return defaultHeaders.get(name);
                }
                return super.getHeader(name);
            }
        };

        filterChain.doFilter(requestWrapper, response);
    }
}
