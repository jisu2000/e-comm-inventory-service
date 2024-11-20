package org.jisu.e_comm_inventory_service.advices;

import java.util.*;

import org.jisu.e_comm_inventory_service.dto.response.*;
import org.jisu.e_comm_inventory_service.exception.*;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizeException.class)
    public ResponseEntity<?> handleUnauthorizeNotFoundException(UnauthorizeException ex) {

        return buildResponseEntityWithApiResponse(
                ErrorResponse.builder()
                        .status(401)
                        .error(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {

        return buildResponseEntityWithApiResponse(
                ErrorResponse.builder()
                        .status(404)
                        .error(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(FailureException.class)
    public ResponseEntity<?> handleServiceFailureException(FailureException ex) {

        return buildResponseEntityWithApiResponse(
                ErrorResponse.builder()
                        .status(408)
                        .error(ex.getMessage())
                        .suberrors(Arrays.asList("Some part of the System is down"))
                        .build());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex) {

        return buildResponseEntityWithApiResponse(
                ErrorResponse.builder()
                        .status(403)
                        .error(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
       
        return buildResponseEntityWithApiResponse(
                ErrorResponse.builder()
                        .error("Client has sent invalid values")
                        .suberrors(ex.getConstraintViolations()
                                .stream().map(e -> e.getMessage())
                                .toList())
                        .status(400)
                        .build()

        );
    }

    // Handle validation errors for request body or model attributes
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<?> handleInvalidReqException(InvalidRequestException ex){

        return buildResponseEntityWithApiResponse(
                ErrorResponse.builder()
                        .status(400)
                        .error(ex.getMessage())
                        .build());
    }

    private ResponseEntity<ApiResponse<?>> buildResponseEntityWithApiResponse(ErrorResponse errorResponse) {
        ApiResponse<ErrorResponse> errApiResponse = new ApiResponse<>(errorResponse);
        errApiResponse.setStatus(errorResponse.getStatus());
        return new ResponseEntity<>(errApiResponse, HttpStatus.valueOf(errorResponse.getStatus()));
    }
}
