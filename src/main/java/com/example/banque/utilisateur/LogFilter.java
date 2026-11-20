package com.example.banque.utilisateur;

import ch.qos.logback.classic.Logger;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class LogFilter extends OncePerRequestFilter {

    private Logger logger = (Logger) LoggerFactory.getLogger(LogFilter.class);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain)
            throws ServletException, IOException {
        logger.info("Requête sur {}", request.getRequestURI());
        chain.doFilter(request, response);
    }
}