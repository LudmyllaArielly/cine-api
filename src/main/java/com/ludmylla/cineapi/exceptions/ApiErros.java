package com.ludmylla.cineapi.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;

public class ApiErros {

    private List<String> erros;

    public ApiErros(UserNotFoundException ex) {
        this.erros = Arrays.asList(ex.getMessage());
    }

    public ApiErros(StoryNotFoundException ex){
        this.erros = Arrays.asList(ex.getMessage());
    }

    public ApiErros(PeriodNotFoundException ex){
        this.erros = Arrays.asList(ex.getMessage());
    }

    public ApiErros(CategoryNotFoundException ex){
        this.erros = Arrays.asList(ex.getMessage());
    }

    public ApiErros(AccessDeniedException ex) {
        this.erros = Arrays.asList(ex.getMessage());
    }

    public ApiErros(StoryWithWrongStatusException ex) {
        this.erros = Arrays.asList(ex.getMessage());
    }

    public ApiErros(DataIntegrityViolationException ex) {
        this.erros = Arrays.asList("Category already exist!");
    }

    public List<String> getErros() {
        return erros;
    }

    public ApiErros(List<String> erros) {
        this.erros = erros;
    }

    public ApiErros(String messageError) {
        this.erros = Arrays.asList(messageError);
    }
}
