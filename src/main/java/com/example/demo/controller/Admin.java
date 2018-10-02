package com.example.demo.controller;

import com.example.demo.Dto.LunchInfoDto;
import com.example.demo.Dto.UserInfoDto;
import com.example.demo.Repository.LunchRepo;
import com.example.demo.service.Login;
import com.example.demo.service.LunchReserves;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/xuda")
@RequiredArgsConstructor
public class Admin {

    private final Login login;
    private final LunchReserves lunchReserves;
    private final LunchRepo lunchRepo;


    @GetMapping("/getalluserinfo")
    public List<UserInfoDto> getAllUserLoginList() throws IOException {
        return login.getAllUserList ();
    }

    @GetMapping("/getalllunchinfo")
    public Object getAllLunchList() throws IOException {
        return lunchRepo.findAll ();
    }

    @GetMapping("/reset")
    public String Reset()  {
        Date date = new Date ();
        List<LunchInfoDto> newLunchList = Arrays.asList (

                LunchInfoDto.builder ().name ("xuda").number (1).vegetarian (false).mark ("osca").addedDate (date).build (),
                LunchInfoDto.builder ().name ("huajie").number (1).vegetarian (false).mark ("osca").addedDate (date).build (),
                LunchInfoDto.builder ().name ("zouxuan").number (1).vegetarian (false).mark ("osca").addedDate (date).build (),
                LunchInfoDto.builder ().name ("yuyang").number (1).vegetarian (false).mark ("osca").addedDate (date).build (),
                LunchInfoDto.builder ().name ("kexing").number (1).vegetarian (false).mark ("osca").addedDate (date).build ()
        );
        lunchReserves.saveLunchInfo (newLunchList);
        return "success!";
    }
}
