package com.cspl.exception;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.workdocs.model.FailedDependencyException;
import com.cspl.commons.exception.BaseException;
import com.cspl.commons.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


import lombok.extern.slf4j.Slf4j;

import static com.cspl.commons.util.Constants.INTERNAL_ERROR;
import static com.cspl.commons.util.Constants.INVALID_INPUT;
import static com.cspl.commons.util.Constants.DEPENDENCY_FAILED;

/**
 * User related exceptions will be handled here
 * @author Ashutosh
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class UserManagementExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> handleValidationError(MethodArgumentNotValidException ex) {
        log.error("Error occurred while validating request", ex);
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String effectedKey = error.getDefaultMessage();
            errors.add(effectedKey);
        });
        return new ResponseEntity<>(new GenericResponse(INVALID_INPUT, "Invalid fields are present in request", errors), HttpStatus.OK);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<GenericResponse> handleBaseException(BaseException ex) {
        log.error("Error occurred while processing the request", ex);
        return new ResponseEntity<>(new GenericResponse(ex.getErrorCode(), ex.getMessage()), HttpStatus.OK);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse> handleException(Exception ex) {

        if (ex instanceof FailedDependencyException) {
            return new ResponseEntity<>(new GenericResponse(DEPENDENCY_FAILED, ex.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new GenericResponse(INTERNAL_ERROR, "Looks like something went wrong on our side. We are looking into it."),
                HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
