package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HtmlGeneration {

    @GetMapping//(value = "/test")
    public String getIndex() {
      return "page1";
    }
    @GetMapping("/page2")
    public String page2() {
        return "page2";
    }
}


