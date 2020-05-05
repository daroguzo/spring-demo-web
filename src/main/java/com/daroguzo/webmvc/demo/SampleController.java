package com.daroguzo.webmvc.demo;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class SampleController {

    @GetHelloMapping
    @ResponseBody
    public String hello(){
        return "hello";
    }

    @GetMapping("/events")
    @ResponseBody
    public String events(){
        return "events";
    }

    @GetMapping("/events/{id}")
    @ResponseBody
    public String eventsById(@PathVariable int id){
        return "event";
    }

    @PostMapping(value = "/events", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String eventsWithJson(){
        return "events";
    }

    @DeleteMapping("/events/{id}")
    @ResponseBody
    public String eventsWithDelete(@PathVariable int id){
        return "event";
    }

    @PutMapping(value = "/events", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateEvents(){
        return "events";
    }

}
