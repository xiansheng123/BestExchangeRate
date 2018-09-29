package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Controller
@RequestMapping("/resource")
@RequiredArgsConstructor
public class Resource {

    @GetMapping
    public void getLoginResource(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("src/css/**").addResourceLocations("src/css/**");
    }
}
