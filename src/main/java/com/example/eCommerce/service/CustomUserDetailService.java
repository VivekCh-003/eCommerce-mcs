package com.example.eCommerce.service;

import com.example.eCommerce.dao.UserDao;
import com.example.eCommerce.entity.CustomUserDetail;
import com.example.eCommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findUserByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }else{
            return new CustomUserDetail(user);
        }
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userDao.findUserByEmail(email);
//        user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        return user.map(CustomUserDetail::new).get();
////        return new CustomUserDetail(user);
//    }


}
