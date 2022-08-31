package com.deraah.demo_api.exceptions;


import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {


    @Autowired
    HttpServletRequest request;

    public CustomRestExceptionHandler() {
        super();
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
                                                                      HttpHeaders headers, HttpStatus status, WebRequest request) {

        ApiError apiError = new ApiError(
                HttpStatus.METHOD_NOT_ALLOWED,
                ex.getMessage(),
                new Date().getTime()
        );



        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex,
                                                               HttpHeaders headers, HttpStatus status, WebRequest request) {


        ApiError apiError = new ApiError(
                HttpStatus.METHOD_NOT_ALLOWED,
                ex.getMessage(),
                new Date().getTime()

        );

        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {


        ApiError apiError = new ApiError(
                HttpStatus.METHOD_NOT_ALLOWED,
                ex.getMessage(),
                new Date().getTime()

        );

        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {


        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                new Date().getTime()

        );

        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                new Date().getTime()

        );

        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                new Date().getTime()

        );

        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                new Date().getTime()

        );

        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                new Date().getTime()

        );

        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers,
                                                         HttpStatus status, WebRequest request) {

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                new Date().getTime()

        );

        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                new Date().getTime()

        );

        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex,
                                                                        HttpHeaders headers, HttpStatus status, WebRequest webRequest) {

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                new Date().getTime()

        );

        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                new Date().getTime()

        );

        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {


        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + " -> " + error.getDefaultMessage());
        }

        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + " -> " + error.getDefaultMessage());
        }



        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                errors,
                new Date().getTime()

        );

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {


        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ExceptionError.BAD_REQUEST_PARAMETER_MISSING.name,
                new Date().getTime()

        );

        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class,IllegalArgumentException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex,
                                                            WebRequest request) {


        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ExceptionError.INTERNAL_SERVER_ERROR.name,
                new Date().getTime()

        );

        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {


        ApiError apiError = new ApiError(
                HttpStatus.METHOD_NOT_ALLOWED,
                ex.getMessage(),
                new Date().getTime()

        );

        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {


        ApiError apiError = new ApiError(
                HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                ExceptionError.BAD_REQUEST_DATA_BAD_FORMAT.name,
                new Date().getTime()

        );

        return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(Exception ex, WebRequest request) {


        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                new Date().getTime()

        );

        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalOperatorException.class)
    public ResponseEntity<Object> handleIllegalOperationException(Exception ex, WebRequest request) {


        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                new Date().getTime()

        );

        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {


        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                new Date().getTime()

        );

        return new ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
