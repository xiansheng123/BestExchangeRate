package com.example.demo.service;

import com.example.demo.Dto.LunchInfo;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LunchReservesTest {
    @Test
    public void saveLunchInfo() throws IOException {
        LunchReserves lunchReserves = new LunchReserves ();
        List<LunchInfo> lunchInfoList = new ArrayList<LunchInfo> () {
            {
                add (LunchInfo.builder ()
                        .name ("xuda")
                        .vegetarian (false)
                        .number (1)
                        .mark ("best one!")
                        .build ());
                add (LunchInfo.builder ()
                        .name ("xuda2")
                        .vegetarian (true)
                        .number (1)
                        .mark ("no best one!")
                        .build ());
            }
        };
        lunchReserves.saveLunchInfo (lunchInfoList);
    }

    @Test
    public void getLunchInfo() throws IOException {
        LunchReserves lunchReserves = new LunchReserves ();
        lunchReserves.getLunchInfo ();
    }

}