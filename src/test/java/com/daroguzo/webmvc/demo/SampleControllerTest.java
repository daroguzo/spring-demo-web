package com.daroguzo.webmvc.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void eventForm() throws Exception{
        mockMvc.perform(get("/events/form"))
                .andDo(print())
                .andExpect(view().name("/events/form"))
                .andExpect(model().attributeExists("event"))
        ;
    }

    @Test
    public void postEvent() throws Exception {
        mockMvc.perform(post("/events")
                    .param("name", "daroguzo")
                    .param("limit", "-10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
        ;
    }

    @Test
    public void getEvents() throws Exception {
        Event newEvent = new Event();
        newEvent.setName("Conference");
        newEvent.setLimit(8);

        mockMvc.perform(get("/events/list")
                .sessionAttr("visitTime", LocalDateTime.now())
                .flashAttr("newEvent", newEvent))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(xpath("//p").nodeCount(2))
        ;
    }
}