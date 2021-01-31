package com.kaan.airportt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Locale;

@CrossOrigin(origins = "*", exposedHeaders = {"Content-Disposition"})
public abstract class AbstractController {
    @Autowired
    private MessageSource messageSource;

    public String getMessage(String key){
        return messageSource.getMessage(key, null, Locale.getDefault());
    }

    public String getMessage(String key, Object... params){
        return messageSource.getMessage(key, params, Locale.getDefault());
    }

}
