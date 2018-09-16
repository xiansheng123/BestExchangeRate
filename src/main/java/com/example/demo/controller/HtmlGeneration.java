package com.example.demo.controller;

import com.example.demo.Dto.LunchInfo;
import com.example.demo.service.LunchReserves;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@AllArgsConstructor
@Slf4j
public class HtmlGeneration {
    private LunchReserves lunchReserves;

    @GetMapping
    public String getIndex(Model model) throws IOException {
        model.addAttribute ("lunchInfo", lunchReserves.getLunchInfo ());
        model.addAttribute ("newUser", LunchInfo.builder ().number (1).build ());
        return "lunch";
    }

    @GetMapping(value = "/deleteUser/{name}")
    public String deleteLunchByName(@PathVariable String name) throws IOException {
        log.info ("delete name is : ${}", name);
        List<LunchInfo> lunchInfo = lunchReserves.getLunchInfo ();
        List<LunchInfo> newLunchList = lunchInfo.stream ().filter (x -> !name.equals (x.getName ())).collect (Collectors.toList ());
        lunchReserves.saveLunchInfo (newLunchList);
        return "redirect:/";
    }

    @PostMapping(value = "/addUser")
    public String addLunchByName(@ModelAttribute LunchInfo newlunchInfo) throws IOException {
        log.info ("add name is : ${}", newlunchInfo);
        List<LunchInfo> lunchInfo = lunchReserves.getLunchInfo ();
        List<LunchInfo> lunchList = lunchInfo.stream ().filter (x -> !newlunchInfo.getName ().equals (x.getName ())).collect (Collectors.toList ());
        lunchList.add (newlunchInfo);
        lunchReserves.saveLunchInfo (lunchList);
        return "redirect:/";
    }

    @GetMapping("/bestRate")
    public String goBackLunch() {
        return "bestRate";
    }


}


