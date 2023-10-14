package com.example.eCommerce.dao;

import com.example.eCommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User,Integer> {
    User findUserByEmail(String email);

}
