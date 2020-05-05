package com.daroguzo.webmvc.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@RequestMapping(method = RequestMethod.GET, value = "/hello")
@Retention(RetentionPolicy.RUNTIME)
public @interface GetHelloMapping {
}
