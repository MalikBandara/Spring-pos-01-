package org.example.springboot01.advisor;

import org.example.springboot01.util.ResponseUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class AppWideExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseUtil exceptionHandler(Exception ex ){
        return new ResponseUtil(500,"Server Error" , ex.getMessage());
    }

}
