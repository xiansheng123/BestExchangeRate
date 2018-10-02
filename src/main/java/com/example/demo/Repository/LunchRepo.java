package com.example.demo.Repository;

import com.example.demo.entity.LunchInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LunchRepo extends JpaRepository<LunchInfo, Integer> {
    Optional <LunchInfo> findOneByName(String name);
}
