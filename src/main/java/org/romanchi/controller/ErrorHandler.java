package org.romanchi.controller;

import org.romanchi.exceptions.ImageLoadingException;
import org.romanchi.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.logging.Logger;

@ControllerAdvice
@RestController
public class ErrorHandler extends ResponseEntityExceptionHandler {

    private final static Logger logger = Logger.getLogger(ErrorHandler.class.getName());

    private class ErrorDetails implements Serializable {
        String message;
        ErrorDetails(String message){
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ErrorDetails handleNotFoundCategory(NotFoundException e){
        return new ErrorDetails(e.getMessage());
    }

    @ExceptionHandler(value = InvalidParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorDetails handleBadCategoryParametersExceptions(InvalidParameterException e){
        return new ErrorDetails(e.getMessage());
    }
    @ExceptionHandler(value = ImageLoadingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ErrorDetails handleIO(ImageLoadingException e){
        return new ErrorDetails(e.getMessage());
    }

}
