package com.example.demo.controller;

import com.example.demo.entity.LunchInfo;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestPropertySource(
//        locations = "classpath:application.properties")
public class HtmlGenerationTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private EntityManager entityManager;

    private Date currentDate =new Date ();



    private void insertLunchInfo(){

        LunchInfo xuda = LunchInfo.builder ().id (1).name ("xuda").vegetarian (false).number (1).addedDate (currentDate).build ();
        LunchInfo vs = LunchInfo.builder ().id (2).name ("vs").vegetarian (true).number (1).addedDate (currentDate).build ();
        entityManager.persist(xuda);
        entityManager.persist(vs);
        entityManager.flush();
    }

    @Test
    public void deleteLunchByName() throws Exception{
//        insertLunchInfo();
//
//        String url="/deleteUser/xuda";
//        mvc.perform (get( url))
//                .andExpect (status().isOk ());
//        LunchInfo reference = entityManager.getReference (LunchInfo.class, 1);

    }

    @Test
    public void updateLunchByName() {
        System.out.println (currentDate);
    }
}