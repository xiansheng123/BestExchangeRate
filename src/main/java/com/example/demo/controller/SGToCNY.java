package com.example.demo.controller;

import com.example.demo.MoneyInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sgdToCn")
public class SGToCNY {
    @GetMapping(value = "/getAll")
    public List<MoneyInfo> getAll() {
        return  new ArrayList<MoneyInfo> () {
            {
                add (new MoneyInfo ("CompanyOfDa", "4.85"));
                add (new MoneyInfo ("CompanyOfZou", "4.89"));
            }
        };
    }
}
