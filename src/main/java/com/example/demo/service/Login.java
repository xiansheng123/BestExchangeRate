package com.example.demo.service;

import com.example.demo.Dto.UserInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@Slf4j
public class Login {
    private final String userFile = "logininfo.json";
    private final ObjectMapper mapper = new ObjectMapper ();

    public UserInfo getUserInfo(String userName) throws IOException {
        List<UserInfo> userInfoList = getAllUserList ();
        return userInfoList.stream ().filter (x -> userName.equals (x.getName ())).findFirst ().orElse (new UserInfo ());
    }

    public void saveUserInfo(UserInfo user) throws IOException {
        List<UserInfo> collect = getAllUserList ().stream ().filter (x -> !x.getName ().equals (user.getName ())).collect (Collectors.toList ());
        collect.add (user);
        String jsonText = mapper.writeValueAsString (collect);
        log.info ("all json user info :" + jsonText);
        FileUtils.writeStringToFile (new ClassPathResource (userFile).getFile (), jsonText, UTF_8);
    }

    public List<UserInfo> getAllUserList() throws IOException {
        try (FileInputStream inputStream = new FileInputStream (new ClassPathResource (userFile).getFile ())) {
            if (inputStream.available () > 0) {
                List<UserInfo> userInfoList = mapper.readValue (inputStream, mapper.getTypeFactory ().constructCollectionType (List.class, UserInfo.class));
                return userInfoList;
            }
        }
        return Collections.EMPTY_LIST;
    }

    public boolean isValidUser(UserInfo user) {
        try {
            return getAllUserList ().stream ().anyMatch (x -> user.getName ().equals (x.getName ()) &&
                    user.getPassword ().equals (x.getPassword ()));
        } catch (IOException ex) {
            log.info (ex.getMessage ());
            return false;
        }
    }

    public boolean isExistsUser(UserInfo user) {
        try {
            return getAllUserList ().stream ().anyMatch (x -> user.getName ().equals (x.getName ()));
        } catch (IOException ex) {
            log.info (ex.getMessage ());
            return false;
        }
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
}
