package com.kalibu.pocBack.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
public class ApiError {

    private String error;
    private String message;
    private Calendar timestamp;

    public ApiError(Exception e){
        this.error = e.getClass().toString();
        this.message = e.getMessage();
        this.timestamp = Calendar.getInstance();
    }

    public ApiError(String message){
        this.message = message;
        this.timestamp = Calendar.getInstance();
    }

}
