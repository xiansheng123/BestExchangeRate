package com.example.demo.service;

import com.example.demo.Dto.LunchInfoDto;
import com.example.demo.Repository.LunchRepo;
import com.example.demo.entity.LunchInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@lombok.extern.slf4j.Slf4j
@AllArgsConstructor
public class LunchReserves {
    private LunchRepo lunchRepo;

    public List<LunchInfoDto> getAllLunchInfo() {
        List<LunchInfoDto> lunchInfoDtoList = lunchRepo.findAll ().stream ()
                .map (this::convertLunchInfoDto)
                .sorted (Comparator.comparing (LunchInfoDto::getVegetarian))
                .collect (Collectors.toList ());
        log.info ("Get AllLunchInfoDto: " + lunchInfoDtoList);
        return lunchInfoDtoList;
    }

    public void saveLunchInfo(LunchInfo lunchInfo) {
        log.info ("Save LunchInfoDto: " + lunchInfo);
        lunchRepo.save (lunchInfo);
    }

    public void saveLunchInfo(List<LunchInfoDto> lunchInfoDtoList) {
        List<LunchInfo> collect = lunchInfoDtoList.stream ()
                .map (this::convertLunchInfo)
                .collect (Collectors.toList ());
        lunchRepo.saveAll (collect);
        log.info ("Save LunchInfoDtoList: " + lunchInfoDtoList);
    }

    public void deleteLunchInfo(String name) {
        if (StringUtils.isEmptyOrWhitespace (name)) {
            log.info (" LunchInfoDto not exist: " + name);
            return;
        }
        Optional<LunchInfo> lunchInfo = lunchRepo.findOneByName (name);
        lunchInfo.ifPresent (lunchInfo1 -> lunchRepo.delete (lunchInfo1));
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
