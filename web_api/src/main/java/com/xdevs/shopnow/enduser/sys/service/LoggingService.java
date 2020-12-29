package com.xdevs.shopnow.enduser.sys.service;

import com.xdevs.shopnow.enduser.utils.GsonParserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.UUID;

import static com.xdevs.shopnow.enduser.common.GlobalValue.REQUEST_PREFIX;


@Service
@Slf4j
public class LoggingService {

    public void logRequest(HttpServletRequest httpServletRequest, Object body) {
        Object requestId = httpServletRequest.getAttribute(REQUEST_PREFIX);
        log.info(String.format("requestId: %s, body request: %s", requestId, GsonParserUtils.parseObjectToString(body)));
    }

    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
        Object requestId = httpServletRequest.getAttribute(REQUEST_PREFIX);
        if(requestId == null) requestId = UUID.randomUUID().toString();
        log.info(String.format("requestId: %s, body response: %s", requestId, GsonParserUtils.parseObjectToString(body)));
    }
}
