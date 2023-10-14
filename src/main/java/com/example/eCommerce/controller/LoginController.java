package com.example.eCommerce.controller;

import com.example.eCommerce.dao.RoleDao;
import com.example.eCommerce.dao.UserDao;
import com.example.eCommerce.entity.Role;
import com.example.eCommerce.entity.User;
import com.example.eCommerce.global.GlobalData;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;

    @GetMapping("/login")
    public String login(Model model){
        GlobalData.cart.clear();
        return "login";
    }

    @GetMapping("/register")
    public String registerGet(){
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("user")User user, HttpServletRequest request) throws ServletException {
        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        roles.add(roleDao.findById(2).get());
        user.setRoles(roles);
        userDao.save(user);
        request.login(user.getEmail(),password);

        return "redirect:/";
    }

}
