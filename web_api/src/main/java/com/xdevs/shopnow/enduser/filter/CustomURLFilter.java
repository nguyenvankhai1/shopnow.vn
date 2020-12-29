package com.xdevs.shopnow.enduser.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

import static com.xdevs.shopnow.enduser.common.GlobalValue.REQUEST_PREFIX;

@Slf4j
@Component
public class CustomURLFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestId = UUID.randomUUID().toString();
        servletRequest.setAttribute(REQUEST_PREFIX, requestId);

        logRequest((HttpServletRequest) servletRequest, requestId);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private void logRequest(HttpServletRequest request, String requestId) {
        if (request != null) {
            StringBuilder headersInfo = new StringBuilder();
            Enumeration headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = (String) headerNames.nextElement();
                String value = request.getHeader(key);
                headersInfo.append(key).append("=").append(value).append(", ");
            }

            log.info(String.format("requestId: %s, path: %s, query string: %s, headers: %s", requestId, request.getRequestURI(),
                    request.getQueryString(), headersInfo.toString()));
        }
    }
}
