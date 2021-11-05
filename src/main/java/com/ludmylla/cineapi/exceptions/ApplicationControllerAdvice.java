package com.ludmylla.cineapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleMethodNotValidException(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErros(errors);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handleUserNotFoundException(UserNotFoundException ex) {
        return new ApiErros(ex);
    }

    @ExceptionHandler(StoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handleStoryNotFoundException(StoryNotFoundException ex) {
        return new ApiErros(ex);
    }

    @ExceptionHandler(PeriodNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handlePeriodNotFoundException(PeriodNotFoundException ex) {
        return new ApiErros(ex);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return new ApiErros(ex);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErros handleAccessDeniedException(AccessDeniedException ex) {
        return new ApiErros(ex);
    }

    @ExceptionHandler(StoryWithWrongStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleStoryWithWrongStatusException(StoryWithWrongStatusException ex) {
        return new ApiErros(ex);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleIllegalArgumentExeption(IllegalArgumentException ex) {
        return new ApiErros(ex);
    }

}
