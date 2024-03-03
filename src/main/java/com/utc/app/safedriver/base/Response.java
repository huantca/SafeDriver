package com.utc.app.safedriver.base;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Response<T> {
    private Integer status;
    private String error;
    private String message;
    private T data;
}
