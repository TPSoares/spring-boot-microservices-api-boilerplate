package com.tpsoares.springbootmicroservicesapiboilerplate.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseError {

    private HttpStatus httpStatus;
    private String code;
    private String errorMessage;
    private Date timeStamp;
    private ErrorDetail detail;
}
