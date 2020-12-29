package com.xdevs.shopnow.enduser.sys.controller;

import com.xdevs.shopnow.enduser.common.BusinessResponse;
import com.xdevs.shopnow.enduser.common.GlobalValue;
import com.xdevs.shopnow.enduser.common.ServiceError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RequestMapping
@RestController
@Validated
@Slf4j
public class BaseController {

    @Resource
    private HttpServletRequest httpServletRequest;

    public ResponseEntity<BusinessResponse> ok(Object data){
        return ResponseEntity.ok(new BusinessResponse(getRequestId(), ServiceError.Types.SUCCESS, data));
    }

    public ResponseEntity<BusinessResponse> ok(){
        return ok(null);
    }

    public String getRequestId(){
        return (String) httpServletRequest.getAttribute(GlobalValue.REQUEST_PREFIX);
    }

    @GetMapping("index")
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("Welcome to Notification Service !");
    }
}
