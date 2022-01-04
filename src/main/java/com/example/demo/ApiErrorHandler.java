package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Controller
@RestControllerAdvice
public class ApiErrorHandler {

    @ResponseBody
    @ExceptionHandler({
            Exception.class
    })
    public ApiResponse<String> exception(Exception e){
        return new ApiResponse<String>(false,(String) e.getMessage(),null);
    }
}
