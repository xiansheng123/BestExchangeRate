package com.example.demo.controller;

import com.example.demo.Dto.LunchInfo;
import com.example.demo.Dto.UserInfo;
import com.example.demo.service.Login;
import com.example.demo.service.LunchReserves;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/xuda")
@RequiredArgsConstructor
public class Admin {

    private final Login login;
    private final LunchReserves lunchReserves;


    @GetMapping("/getalluserinfo")
    public List<UserInfo> getAllUserLoginList() throws IOException {
        return login.getAllUserList ();
    }

    @GetMapping("/getalllunchinfo")
    public List<LunchInfo> getAllLunchList() throws IOException {
        return lunchReserves.getLunchInfo ();
    }

    @GetMapping("/reset")
    public String Reset() throws IOException {
        List<LunchInfo> newLunchList = Arrays.asList (
                LunchInfo.builder ().name ("xuda").number (1).vegetarian (false).mark ("osca").build (),
                LunchInfo.builder ().name ("huajie").number (1).vegetarian (false).mark ("osca").build (),
                LunchInfo.builder ().name ("zouxuan").number (1).vegetarian (false).mark ("osca").build (),
                LunchInfo.builder ().name ("yuyang").number (1).vegetarian (false).mark ("osca").build (),
                LunchInfo.builder ().name ("kexing").number (1).vegetarian (false).mark ("osca").build ()
        );
        lunchReserves.saveLunchInfo (newLunchList);
        return "success!";
    }
}
