package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cap_lunchinfo")
public class LunchInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lunch_id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "vegetarian")
    private Boolean vegetarian;
    @Column(name = "number")
    private Integer number;
    @Column(name = "description")
    private String mark;
    @Column(name = "added_date")
    private Date addedDate;

}
