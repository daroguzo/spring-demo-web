package com.daroguzo.webmvc.demo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("event")
public class SampleController {

    @GetMapping("/events/form/name")
    public String eventsFormName(Model model){
        model.addAttribute("event", new Event());
        return "/events/form-name";
    }

    @PostMapping("/events/form/name")
    public String eventsFormNameSubmit(@Valid @ModelAttribute Event event,
                              BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "/events/form-name";
        }
        // save
        return "redirect:/events/form/limit";

    }

    @GetMapping("/events/form/limit")
    public String eventsFormLimit(@ModelAttribute Event event, Model model){
        model.addAttribute("event", event);
        return "/events/form-limit";
    }

    @PostMapping("/events/form/limit")
    public String eventsFormLimitSubmit(@Valid @ModelAttribute Event event,
                                       BindingResult bindingResult,
                                       SessionStatus sessionStatus) {
        if(bindingResult.hasErrors()){
            return "/events/form-limit";
        }
        sessionStatus.setComplete();
        // save
        return "redirect:/events/list";

    }

    @GetMapping("/events/list")
    public String getEvents(Model model){
        Event event = new Event();
        event.setName("spring");
        event.setLimit(10);

        List<Event> eventList = new ArrayList<>();
        eventList.add(event);

        model.addAttribute(eventList);

        return "/events/list";
    }
}
