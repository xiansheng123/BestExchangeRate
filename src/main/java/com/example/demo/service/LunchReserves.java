package com.example.demo.service;

import com.example.demo.Dto.LunchInfoDto;
import com.example.demo.Repository.LunchRepo;
import com.example.demo.entity.LunchInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.runtime.options.Option;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@lombok.extern.slf4j.Slf4j
@AllArgsConstructor
public class LunchReserves {
    private LunchRepo lunchRepo;

    public List<LunchInfoDto> getAllLunchInfo() {
        List<LunchInfoDto> lunchInfoDtoList = lunchRepo.findAll ().stream ()
                .map (x -> convertLunchInfoDto (x))
                .collect (Collectors.toList ());
        lunchInfoDtoList.sort (Comparator.comparing (x -> x.getVegetarian ()));
        log.info ("Get AllLunchInfoDto: " + lunchInfoDtoList);
        return lunchInfoDtoList;
    }

    public void saveLunchInfo(LunchInfo lunchInfo) {
        log.info ("Save LunchInfoDto: " + lunchInfo);
        lunchRepo.save (lunchInfo);
    }

    public void saveLunchInfo(List<LunchInfoDto> lunchInfoDtoList) {
        List<LunchInfo> collect = lunchInfoDtoList.stream ().map (x -> convertLunchInfo (x)).collect (Collectors.toList ());
        lunchRepo.saveAll (collect);
        log.info ("Save LunchInfoDtoList: " + lunchInfoDtoList);
    }

    public void deleteLunchInfo(String name) {
        if (StringUtils.isEmptyOrWhitespace (name)) {
            log.info (" LunchInfoDto not exist: " + name);
            return;
        }
        Optional<LunchInfo> lunchInfo = lunchRepo.findOneByName (name);
        if (lunchInfo.isPresent ()) {
            lunchRepo.delete (lunchInfo.get ());
        }
        log.info ("Delete LunchInfoDto: " + lunchInfo);
    }

    public LunchInfo getLunchInfoByName(String name) {
        return lunchRepo.findOneByName (name)
                .orElse (LunchInfo.builder ().name (name).build ());
    }

    private LunchInfoDto convertLunchInfoDto(LunchInfo lunchInfo) {
        if (lunchInfo == null) {
            return new LunchInfoDto ();
        }
        return LunchInfoDto.builder ()
                .name (lunchInfo.getName ())
                .number (lunchInfo.getNumber ())
                .vegetarian (lunchInfo.getVegetarian ())
                .mark (lunchInfo.getMark ())
                .addedDate (lunchInfo.getAddedDate ())
                .build ();
    }

    private LunchInfo convertLunchInfo(LunchInfoDto lunchInfoDto) {
        if (lunchInfoDto == null) {
            return new LunchInfo ();
        }
        return LunchInfo.builder ()
                .name (lunchInfoDto.getName ())
                .vegetarian (lunchInfoDto.getVegetarian ())
                .number (lunchInfoDto.getNumber ())
                .mark (lunchInfoDto.getMark ())
                .addedDate (lunchInfoDto.getAddedDate ())
                .build ();
    }
}
