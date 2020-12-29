package com.xdevs.shopnow.enduser.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ServiceError {
    private Integer code;
    private String message;

    public static class Types {
        public static final ServiceError SUCCESS = new ServiceError(0, "SUCCESS");
        public static final ServiceError UNKNOWN = new ServiceError(99, "UNKNOWN");
        public static final ServiceError TOKEN_INVALID = new ServiceError(1, "TOKEN_INVALID");
        public static final ServiceError USER_AND_PASSWORD_NOT_CORRECT = new ServiceError(2, "USER_AND_PASSWORD_NOT_CORRECT");
        public static final ServiceError USER_INACTIVE = new ServiceError(3, "USER_INACTIVE");
        public static final ServiceError USER_LOCKED = new ServiceError(4, "USER_LOCKED");
        public static final ServiceError UPLOAD_FILE_FAILED = new ServiceError(5, "UPLOAD_FILE_FAILED");
        public static final ServiceError DATA_INVALID = new ServiceError(6, "DATA_INVALID");
        public static final ServiceError FIREBASE_MESSAGING_ERROR = new ServiceError(7, "FIREBASE_MESSAGING_ERROR");
        public static final ServiceError FIREBASE_MESSAGING_BATCH_ERROR = new ServiceError(7, "FIREBASE_MESSAGING_BATCH_ERROR");
    }
}
