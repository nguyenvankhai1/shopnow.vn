package com.xdevs.shopnow.enduser.exception.handler;

import com.xdevs.shopnow.enduser.common.BusinessResponse;
import com.xdevs.shopnow.enduser.common.GlobalValue;
import com.xdevs.shopnow.enduser.common.ServiceError;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {
    @Resource
    private HttpServletRequest httpServletRequest;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        String requestId = (String) httpServletRequest.getAttribute(GlobalValue.REQUEST_PREFIX);
        List<String> details = ex.getAllErrors()
                .parallelStream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new BusinessResponse(requestId, ServiceError.Types.DATA_INVALID, details));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        String requestId = (String) httpServletRequest.getAttribute(GlobalValue.REQUEST_PREFIX);
        List<String> details = ex.getConstraintViolations()
                .parallelStream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new BusinessResponse(requestId, ServiceError.Types.DATA_INVALID, details));
    }
}
