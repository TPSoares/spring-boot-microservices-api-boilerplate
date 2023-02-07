package com.tpsoares.springbootmicroservicesapiboilerplate.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ServiceResponseUtils {

    public <T> ResponseEntity<Object> responseEntitySuccess(T dto, HttpStatus httpStatus) {
        return ResponseEntity
                .status(httpStatus)
                .body(dto);
    }
    public <T> ResponseEntity<Object> responseEntityError(T response) {
        if (response instanceof ResponseError) {
            return ResponseEntity.status(((ResponseError) response).getHttpStatus()).body(
                    new ResponseError.ResponseErrorBuilder()
                            .code(((ResponseError) response).getCode())
                            .detail(((ResponseError) response).getDetail())
                            .errorMessage(((ResponseError) response).getErrorMessage())
                            .timeStamp(((ResponseError) response).getTimeStamp())
                            .build());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseError.ResponseErrorBuilder()
                        .timeStamp(new Date())
                        .errorMessage("Internal server error")
                        .build());
    }
}
