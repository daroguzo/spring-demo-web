package com.daroguzo.webmvc.demo;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/events")
public class EventApi {

    @PostMapping
    @ResponseBody
    public Event createEvent(@RequestBody @Valid Event event,
                             BindingResult bindingResult){
        // save event
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                System.out.println(objectError);
            });
        }
        return event;
    }
}
