package com.daroguzo.webmvc.demo;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EventValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Event.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Event event = (Event)o;
         if(event.getName().equalsIgnoreCase("root")){
             errors.rejectValue("name", "wrongValue", "The value is not allowed");
         }
    }
}
