package com.example.demo.controller;

import com.example.demo.Dto.LunchInfo;
import com.example.demo.service.LunchReserves;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    //private final ObjectMapper mapper = new ObjectMapper ();

    @GetMapping
    public String getIndex(Model model) throws IOException {
        model.addAttribute ("lunchInfo", lunchReserves.getLunchInfo ());
        return "lunch";
    }

    @GetMapping(value = "/deleteUser/{name}")
    public String deleteLunchByName(@PathVariable String name) throws IOException {
        log.info ("delete name is : ${}", name);
        List<LunchInfo> lunchInfo = lunchReserves.getLunchInfo ();
        List<LunchInfo> newLunchList = lunchInfo.stream ().filter (x -> !name.equals (x.getName ())).sorted ().collect (Collectors.toList ());
        lunchReserves.saveLunchInfo (newLunchList);
        return "redirect:/";
    }

    @GetMapping(value = "/addUser")
    public String addLunchByName(@ModelAttribute LunchInfo Lunchinfo ) throws IOException {
        log.info ("add name is : ${}", Lunchinfo);
        List<LunchInfo> lunchInfo = lunchReserves.getLunchInfo ();
        //LunchInfo newUser = mapper.readValue (strUser, LunchInfo.class);
        lunchInfo.add (Lunchinfo);
        lunchReserves.saveLunchInfo (lunchInfo);
        return "redirect:/";
    }


    @GetMapping("/bestRate")
    public String goBackLunch() {
        return "bestRate";
    }


}


