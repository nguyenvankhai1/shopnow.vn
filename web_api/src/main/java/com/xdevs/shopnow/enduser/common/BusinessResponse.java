package com.xdevs.shopnow.enduser.common;


import com.xdevs.shopnow.enduser.utils.DateFormatUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BusinessResponse {
    private String requestId;
    private String at;
    private ServiceError error;
    private Object data;

    public BusinessResponse(String requestId, ServiceError error, Object data){
        this.requestId = requestId;
        this.at = DateFormatUtils.formatDate(new Date());
        this.error = error;
        this.data = data;
    }

    public BusinessResponse(String requestId, ServiceError error){
        this.requestId = requestId;
        this.at = DateFormatUtils.formatDate(new Date());
        this.error = error;
    }
}
