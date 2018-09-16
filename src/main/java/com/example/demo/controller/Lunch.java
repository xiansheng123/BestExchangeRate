package com.example.demo.controller;

import com.example.demo.Dto.LunchInfo;
import com.example.demo.service.LunchReserves;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lunch")
@AllArgsConstructor
public class Lunch {
    private LunchReserves lunchReserves;

    @GetMapping(value = "/getLunch")
    public List<LunchInfo> getLunch() throws IOException {
        return lunchReserves.getLunchInfo ();
    }

    @PostMapping(value = "/saveLunch")
    public ResponseEntity<String> getLunch(@NonNull @RequestBody List<LunchInfo> lunchInfo) throws IOException {
        if (lunchInfo == null || lunchInfo.size () < 1) {
            return ResponseEntity.badRequest ().body ("don't pass null entity");
        }
        lunchReserves.saveLunchInfo (lunchInfo);
        return ResponseEntity.ok ("successfully!");
    }
}


