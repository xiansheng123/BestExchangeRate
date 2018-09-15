package com.example.demo.service;

import org.aopalliance.intercept.Joinpoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CheckArrary {

     public boolean checkList(List<String> sublist, List<String> parentList) {

        if (!parentList.containsAll (sublist)) {
            return false;
        }

        Map<String, String> parent = parentList.stream ().collect (Collectors.toMap (x -> x, Function.identity ()));

        String sub1 = sublist.stream ().map (parent::get).collect (Collectors.joining (","));
        String sub2 = sublist.stream ().map (parent::get).sorted ().collect (Collectors.joining (","));
        return sub1.equals (sub2);
    }
}
