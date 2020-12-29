package com.xdevs.shopnow.enduser.exception.handler;

import com.xdevs.shopnow.enduser.common.BusinessResponse;
import com.xdevs.shopnow.enduser.utils.LogFormatUtils;
import com.xdevs.shopnow.enduser.common.GlobalValue;
import com.xdevs.shopnow.enduser.common.ServiceError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Resource
    private HttpServletRequest httpServletRequest;

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(final Exception exception) {
        return buildResponseEntity(exception, ServiceError.Types.UNKNOWN);
    }

    private ResponseEntity<Object> buildResponseEntity(final Exception exception, ServiceError serviceError){
        String requestId = (String) httpServletRequest.getAttribute(GlobalValue.REQUEST_PREFIX);
        LogFormatUtils.warn(requestId, exception.getMessage(), exception);
        return new ResponseEntity<>(new BusinessResponse(requestId, serviceError, exception.getMessage()), HttpStatus.OK);
    }
}
