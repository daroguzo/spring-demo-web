package com.daroguzo.webmvc.demo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class SampleController {

    @GetMapping("/events/form")
    public String eventsForm(Model model){
        Event newEvent = new Event();
        newEvent.setLimit(50);
        model.addAttribute("event", newEvent);
        return "events/form";
    }

    @PostMapping("/events/name/{name}")
    @ResponseBody
    public Event getEvent(@Valid @ModelAttribute Event event, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("===================");
            bindingResult.getAllErrors().forEach(c -> {
                    System.out.println(c.toString());
            });
        }

        return event;
    }
}
