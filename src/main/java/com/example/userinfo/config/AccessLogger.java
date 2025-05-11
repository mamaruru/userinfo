package com.example.userinfo.config;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AccessLogger {

    private static final Logger logger = LoggerFactory.getLogger(AccessLogger.class);

    public void logRequest(HttpServletRequest request) {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String remoteAddr = request.getRemoteAddr();
        logger.info("[ACCESS] {} {} from {}", method, uri, remoteAddr);
    }
}