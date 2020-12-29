package com.xdevs.shopnow.enduser.sys.controller;

import com.xdevs.shopnow.enduser.common.BusinessResponse;
import com.xdevs.shopnow.enduser.sys.model.order.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
@Validated
@Slf4j
public class OrderController extends BaseController {

    @PostMapping
    public ResponseEntity<BusinessResponse> order(@RequestBody OrderRequest orderRequest){
        //todo handle order ...
        return ok();
    }
}
