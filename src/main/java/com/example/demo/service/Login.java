package com.example.demo.service;

import com.example.demo.Dto.UserInfoDto;
import com.example.demo.Repository.UserInfoRepo;
import com.example.demo.entity.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class Login {

    private UserInfoRepo userInfoRepo;

    public UserInfoDto getUserInfo(String name) {

        Optional<UserInfo> byId = userInfoRepo.findById (name);
        return byId.map (x -> convertUserInfoDto (x)).orElse (new UserInfoDto ());
    }

    public void saveUserInfo(UserInfoDto user) {
        UserInfo userInfo = convertUserInfo (user);
        userInfoRepo.save (userInfo);
    }

    public List<UserInfoDto> getAllUserList() {
        return userInfoRepo.findAll ().stream ().map (this::convertUserInfoDto).collect (Collectors.toList ());
    }

    public boolean isValidUser(UserInfoDto userInfoDto) {
        String name = userInfoDto.getName ();
        String password = userInfoDto.getPassword ();
        if (StringUtils.isEmptyOrWhitespace (name) || StringUtils.isEmptyOrWhitespace (password)) {
            return false;
        }
        Optional<UserInfo> one = userInfoRepo.findById (name);
        return one.map (x ->
                x.getPassword ().equals (password)).orElse (false);
    }

    public String getRandomString() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random ();
        StringBuffer sb = new StringBuffer ();

        for (int i = 0; i < 6; ++i) {
            sb.append (str.charAt (random.nextInt (62)));
        }
        return sb.toString ();
    }

    private UserInfoDto convertUserInfoDto(UserInfo userInfo) {
        return UserInfoDto.builder ()
                .name (userInfo.getName ())
                .password (userInfo.getPassword ())
                .build ();
    }

    private UserInfo convertUserInfo(UserInfoDto userInfoDto) {
        return UserInfo.builder ()
                .name (userInfoDto.getName ())
                .password (userInfoDto.getPassword ())
                .build ();
    }
}
