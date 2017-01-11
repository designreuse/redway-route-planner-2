package org.mkhackathon.routing;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @ExceptionHandler
    public String handler(Exception e) {

    }
}
