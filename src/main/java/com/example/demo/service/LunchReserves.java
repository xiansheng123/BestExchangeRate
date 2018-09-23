package com.example.demo.service;

import com.example.demo.Dto.LunchInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@lombok.extern.slf4j.Slf4j
public class LunchReserves {

    private final ObjectMapper mapper = new ObjectMapper ();
    private final String fileName = "lunchinfo.json";

    public List<LunchInfo> getLunchInfo() throws IOException {
        List<LunchInfo> lunchInfoList = new ArrayList<> ();
        try (FileInputStream fileInputStream = new FileInputStream (new ClassPathResource (fileName).getFile ())) {
            if (fileInputStream.available () > 0) {
                lunchInfoList = mapper.readValue (fileInputStream,
                        mapper.getTypeFactory ()
                                .constructCollectionType (List.class, LunchInfo.class));
            }
        }
        log.info ("Get LunchInfo: " + lunchInfoList);
        return lunchInfoList;
    }

    public void saveLunchInfo(List<LunchInfo> lunchInfoList) throws IOException {
        String jsonText = mapper.writeValueAsString (lunchInfoList);
        log.info ("Save LunchInfo: " + jsonText);
        FileUtils.writeStringToFile (new ClassPathResource (fileName).getFile (), jsonText, UTF_8);
    }

}
