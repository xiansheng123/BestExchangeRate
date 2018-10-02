package com.example.demo.controller;

import com.example.demo.Dto.LunchInfoDto;
import com.example.demo.Dto.UserInfoDto;
import com.example.demo.entity.LunchInfo;
import com.example.demo.service.Login;
import com.example.demo.service.LunchReserves;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class HtmlGeneration {
    private final LunchReserves lunchReserves;
    private final Login login;

    @Value("${Cap.Learning.Topic}")
    private String topic;

    @GetMapping
    public String login(Model model) {
        model.addAttribute ("loginUser", new UserInfoDto ());
        return "login";
    }

    @PostMapping("/authenticate")
    public String authenticateInfo(@ModelAttribute UserInfoDto user) {
        log.info ("authenticate: " + user);
        if (user == null || StringUtils.isEmptyOrWhitespace (user.getName ()) ||
                StringUtils.isEmptyOrWhitespace (user.getPassword ())) {
            return "error";
        }
        if (topic.equals (user.getPassword ())) {
            UserInfoDto existsUserInfoDto = login.getUserInfo (user.getName ());
            if (StringUtils.isEmptyOrWhitespace (existsUserInfoDto.getPassword ())) {
                user.setPassword (login.getRandomString ());
            } else {
                user.setPassword (existsUserInfoDto.getPassword ());
            }
            log.info ("after set the UserName and Password: " + user);

            login.saveUserInfo (user);
            return String.format ("redirect:/lunch/%s/%s", user.getName (), user.getPassword ());
        } else {
            return "error";
        }
    }

    @GetMapping("/lunch/{name}/{password}")
    public String getLunch(@PathVariable String name, @PathVariable String password, Model model) {
        UserInfoDto user = UserInfoDto.builder ().name (name).password (password).build ();
        if (!login.isValidUser (user)) {
            return "error";
        }
        List<LunchInfoDto> allLunchInfoDto = lunchReserves.getAllLunchInfo ();
        model.addAttribute ("lunchInfoDto", allLunchInfoDto);
        model.addAttribute ("newUser", LunchInfoDto.builder ().name (name).number (1).addedDate (new Date ()).build ());
        model.addAttribute ("userSetting", UserInfoDto.builder ().name (name).password (password).build ());
        return "lunch";
    }

    @GetMapping(value = "/deleteUser/{name}")
    public String deleteLunchByName(@PathVariable String name, @RequestParam("data") String password) {

        log.info ("delete name and credential : {} {}", name, password);
        lunchReserves.deleteLunchInfo (name);
        return String.format ("redirect:/lunch/%s/%s", name, password);
    }

    @PostMapping(value = "/addUser")
    public String updateLunchByName(@ModelAttribute LunchInfoDto newlunchInfoDto) {
        log.info ("add Dto is : {}", newlunchInfoDto);

        if (newlunchInfoDto == null || StringUtils.isEmptyOrWhitespace (newlunchInfoDto.getName ())) {
            return "redirect:/error";
        }
        String name = newlunchInfoDto.getName ();
        LunchInfo lunchInfoByName = lunchReserves.getLunchInfoByName (name);
        lunchInfoByName.setNumber (1);
        lunchInfoByName.setAddedDate (new Date ());
        lunchInfoByName.setVegetarian (newlunchInfoDto.getVegetarian ());
        lunchInfoByName.setMark (newlunchInfoDto.getMark ());
        lunchReserves.saveLunchInfo (lunchInfoByName);
        String password = login.getUserInfo (name).getPassword ();
        return String.format ("redirect:/lunch/%s/%s", name, password);
    }

    @GetMapping("/bestRate")
    public String goBackLunch() {
        return "bestRate";
    }

}


