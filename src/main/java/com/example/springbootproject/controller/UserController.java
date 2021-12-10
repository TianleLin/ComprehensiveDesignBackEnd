package com.example.springbootproject.controller;

import com.example.springbootproject.model.User;
import com.example.springbootproject.service.FileService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import com.example.springbootproject.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@CrossOrigin
@RestController
@Api
@RequestMapping("/api")
public class UserController {
    @Autowired
    FileService fileService;
    @Autowired
    UserService userService;

    @PostMapping("/testSession")
    public ResponseEntity<Object> testSession(
            HttpServletRequest request
    ) {
        HttpSession httpSession = request.getSession();
        if(httpSession.isNew()) {
            httpSession.invalidate();
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        else
            return new ResponseEntity<>(httpSession.getId(), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<User> register(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password
    ){
        try{
            if (org.apache.commons.lang3.StringUtils.isBlank(username)
                    && org.apache.commons.lang3.StringUtils.isBlank(password)
            ) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            User user = userService.findUser(username);
            if(user!=null) {
                return new ResponseEntity<>(null, HttpStatus.ALREADY_REPORTED);
            } else {

                user = userService.saveUser(new User(username, password, "/image/avatar/default-avatar.png"));
                return new ResponseEntity<>(user, HttpStatus.CREATED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            HttpServletRequest request
    ){
        try {
            if (org.apache.commons.lang3.StringUtils.isBlank(username)
                    && org.apache.commons.lang3.StringUtils.isBlank(password)
            ) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            User user = userService.findUser(username);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("userID", user.getId());
                    return new ResponseEntity<>(user, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<User> updatePassword(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "oldPassword") String oldPassword,
            @RequestParam(name = "newPassword") String newPassword,
            HttpServletRequest request
    ){
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                if (org.apache.commons.lang3.StringUtils.isBlank(username)
                        && org.apache.commons.lang3.StringUtils.isBlank(newPassword)
                ) {
                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                }
                User user = userService.findUser(username);
                if (user != null) {
                    if (oldPassword.equals(user.getPassword())) {
                        user.setPassword(newPassword);
                        user = userService.saveUser(user);
                        if (user != null) {
                            return new ResponseEntity<>(user, HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    } else {
                        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
                    }
                } else {
                    httpSession.invalidate();
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/uploadAvatar")
    public ResponseEntity<User> uploadAvatar(@RequestParam("avatar") MultipartFile multipartFile,
                                               @RequestParam("userID") Long userID,
                                             HttpServletRequest request) {
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                User user = userService.findUser(userID);
                if (user != null) {
                    if (multipartFile.getOriginalFilename() == null) {
                        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                    }
                    String rawFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                    String fileName = userID +
                            rawFileName.substring(rawFileName.lastIndexOf("."));
                    String avatarURL = "/image/avatar/";
                    user.setAvatar(avatarURL + fileName);
                    user = userService.saveUser(user);

//                String uploadDir = "E:/SpringBootProject/pictures/avatar/";
                    String uploadDir = "/home/ubuntu/pictures/avatar/";

                    if (fileService.saveFile(uploadDir, fileName, multipartFile)) {
                        return new ResponseEntity<>(user, HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
            } else {
                httpSession.invalidate();
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<User> deleteUser(@RequestParam("deleteUsername") String deleteUsername
                                            , @RequestParam("teacherID") Long teacherID,
                                           HttpServletRequest request){
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                User teacher = userService.findUser(teacherID);
                if (teacher != null) {
                    if (teacher.isTeacher()) {
                        User userToBeDeleted = userService.findUser(deleteUsername);
                        if (userToBeDeleted != null) {
                            if (userService.deleteUser(userToBeDeleted)) {
                                return new ResponseEntity<>(userToBeDeleted, HttpStatus.OK);
                            } else {
                                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                            }
                        } else {
                            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                        }
                    } else {
                        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
                    }
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
            } else {
                httpSession.invalidate();
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping("/logout")
    public ResponseEntity<Object> logout(
            HttpServletRequest request
    ) {
        try {
            HttpSession httpSession = request.getSession();
            httpSession.invalidate();
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
