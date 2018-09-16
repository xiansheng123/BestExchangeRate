package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LunchInfo {
    private String name;
    private Boolean vegetarian;
    private Integer number;
    private String mark;
}
