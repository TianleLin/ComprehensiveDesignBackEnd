package com.example.springbootproject.service;

import com.example.springbootproject.dao.UserRepository;
import com.example.springbootproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findUser(String username){
        try{
            Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
            if(user.isPresent())
                return user.get();
            else {
                return null;
            }
        } catch (Exception e){
            return null;
        }
    }
    public User findUser(Long userID){
        try{
            Optional<User> user = userRepository.findById(userID);
            if(user.isPresent())
                return user.get();
            else {
                return null;
            }
        } catch (Exception e){
            return null;
        }
    }
    public User saveUser(User user){
        try {
            return userRepository.save(user);
        } catch (Exception e){
            return null;
        }
    }

    public boolean deleteUser(User user){
        try {
            userRepository.delete(user);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
