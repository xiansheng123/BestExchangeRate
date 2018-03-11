package com.example.demo.controller;

import com.example.demo.MoneyInfo;
import com.example.demo.service.ClientHttp;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/sgdToCn")
@AllArgsConstructor
public class SGToCNY {

    private ClientHttp clientHttp;

    @GetMapping(value = "/getAll")
    public List<MoneyInfo> getAll() {
        return Arrays.asList (
                clientHttp.convertHanShengToMoneyInfo (),
                clientHttp.convertChangeChengToMoneyInfo ()

        );

    }


}


