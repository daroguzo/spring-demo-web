package com.daroguzo.webmvc.demo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SampleController {

    @RequestMapping("/events/{id}")
    @ResponseBody
    public Event getEvent(@PathVariable Integer id, @MatrixVariable String name){
        Event event = new Event();
        event.setId(1);
        event.setName("daroguzo");
        return event;
    }
}
