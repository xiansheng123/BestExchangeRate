package com.example.demo.Repository;

import com.example.demo.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, String> {

}
