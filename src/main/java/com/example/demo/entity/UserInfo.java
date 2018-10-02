package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cap_userinfo")
public class UserInfo {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;
}
