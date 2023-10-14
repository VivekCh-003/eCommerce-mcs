package com.example.eCommerce.dao;

import com.example.eCommerce.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Integer> {
}
