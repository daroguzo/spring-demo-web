package com.daroguzo.webmvc.demo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("event")
public class EventController {

    @ExceptionHandler({EventException.class, RuntimeException.class})
    public String eventErrorHandler(RuntimeException exception, Model model){
        model.addAttribute("message", "runtime error");
        return "/events/error";
    }


    @GetMapping("/events/error")
    public String error(Model model){
        throw new EventException();
    }

    @InitBinder("event")
    public void InitEventBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
        webDataBinder.addValidators(new EventValidator());
    }

    @ModelAttribute
    public void categories(Model model){
        model.addAttribute("categories", List.of("study", "seminar", "hobby", "social"));
    }

    @GetMapping("/events/form/name")
    public String eventsFormName(Model model){
        model.addAttribute("event", new Event());
        return "/events/form-name";
    }

    @PostMapping("/events/form/name")
    public String eventsFormNameSubmit(@Validated @ModelAttribute Event event,
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
    public String eventsFormLimitSubmit(@Validated @ModelAttribute Event event,
                                        BindingResult bindingResult,
                                        SessionStatus sessionStatus,
                                        RedirectAttributes redirectAttributes ) {
        if(bindingResult.hasErrors()){
            return "/events/form-limit";
        }
        sessionStatus.setComplete();

        redirectAttributes.addFlashAttribute("newEvent", event);

        // save
        return "redirect:/events/list";

    }

    @GetMapping("/events/list")
    public String getEvents(Model model,
                            @SessionAttribute LocalDateTime visitTime){
        System.out.println(visitTime);

        Event spring = new Event();
        spring.setName("spring");
        spring.setLimit(10);

        Event newEvent = (Event) model.asMap().get("newEvent");

        List<Event> eventList = new ArrayList<>();
        eventList.add(newEvent);
        eventList.add(spring);

        model.addAttribute(eventList);

        return "/events/list";
    }
}
