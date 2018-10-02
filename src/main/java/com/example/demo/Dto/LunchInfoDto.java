package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LunchInfoDto {
    private String name;
    private Boolean vegetarian;
    private Integer number;
    private String mark;
    private Date addedDate;
}
