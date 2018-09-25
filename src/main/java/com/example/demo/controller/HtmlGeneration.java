package com.example.demo.controller;

import com.example.demo.Dto.LunchInfo;
import com.example.demo.Dto.UserInfo;
import com.example.demo.Dto.ValidationError;
import com.example.demo.service.Login;
import com.example.demo.service.LunchReserves;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import sun.security.util.Password;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
        model.addAttribute ("loginUser", new UserInfo ());
        return "login";
    }

    @PostMapping("/authenticate")
    public String authenticateInfo(@ModelAttribute UserInfo user) throws IOException {
        log.info ("authenticate: "+user);
        if (user == null || StringUtils.isEmptyOrWhitespace (user.getName ()) ||
                StringUtils.isEmptyOrWhitespace (user.getPassword ())) {
            return "error";
        }
        if (topic.equals (user.getPassword ())) {
            UserInfo ExistsUserInfo = login.getUserInfo (user.getName ());
            if (StringUtils.isEmptyOrWhitespace (ExistsUserInfo.getPassword ())) {
                user.setPassword (login.getRandomString ());
            } else {
                user.setPassword (ExistsUserInfo.getPassword ());
            }
            log.info ("after set the UserName and Password: " + user);

            login.saveUserInfo (user);
            return String.format ("redirect:/lunch/%s/%s", user.getName (), user.getPassword ());
        } else {
            return "error";
        }
    }

    @GetMapping("/lunch/{name}/{password}")
    public String getLunch(@PathVariable String name, @PathVariable String password, Model model) throws IOException {
        UserInfo user = UserInfo.builder ().name (name).password (password).build ();
        if (!login.isValidUser (user)) {
            return "error";
        }
        List<LunchInfo> allLunchInfo = lunchReserves.getLunchInfo ();
        model.addAttribute ("lunchInfo", allLunchInfo);
        model.addAttribute ("newUser", LunchInfo.builder ().name (name).build ());
        model.addAttribute ("userSetting", UserInfo.builder ().name (name).password (password).build ());
        return "lunch";
    }

    @GetMapping(value = "/deleteUser/{name}")
    public String deleteLunchByName(@PathVariable String name , @RequestParam("data") String password) throws IOException {

        log.info ("delete name and credential : {} {}", name,password);
        List<LunchInfo> lunchInfo = lunchReserves.getLunchInfo ();
        List<LunchInfo> newLunchList = lunchInfo.stream ().filter (x -> !name.equals (x.getName ())).collect (Collectors.toList ());
        lunchReserves.saveLunchInfo (newLunchList);
        //String password = login.getUserInfo (name).getPassword ();
        return String.format ("redirect:/lunch/%s/%s", name, password);
    }

    @PostMapping(value = "/addUser")
    public String addLunchByName(@ModelAttribute LunchInfo newlunchInfo) throws IOException {
        log.info ("add name is : {}", newlunchInfo);

        if (newlunchInfo == null || StringUtils.isEmptyOrWhitespace (newlunchInfo.getName ())) {
            return "redirect:/error";
        }

        newlunchInfo.setNumber (1);
        String name = newlunchInfo.getName ();
        List<LunchInfo> lunchList = lunchReserves.getLunchInfo ().stream ()
                .filter (x -> !newlunchInfo.getName ().equals (x.getName ()))
                .collect (Collectors.toList ());
        lunchList.add (newlunchInfo);
        lunchReserves.saveLunchInfo (lunchList);

        String password = login.getUserInfo (name).getPassword ();
        return String.format ("redirect:/lunch/%s/%s", name, password);
    }

    @GetMapping("/bestRate")
    public String goBackLunch() {
        return "bestRate";
    }


}


