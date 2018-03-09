package com.example.demo.controller;

import com.example.demo.MoneyInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.awt.SunHints;

import java.util.*;

@Controller
@RequestMapping("/CN")
public class SGToCNY {
    @RequestMapping(value = "GetAll")
    public List<MoneyInfo> getAll() {
        return  new ArrayList<MoneyInfo> () {
            {
                add (new MoneyInfo ("CompanyOfDa", "4.85"));
                add (new MoneyInfo ("CompanyOfZou", "4.89"));
            }
        };
    }
}
