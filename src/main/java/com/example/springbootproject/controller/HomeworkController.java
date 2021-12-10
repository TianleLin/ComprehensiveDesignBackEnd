package com.example.springbootproject.controller;

import com.example.springbootproject.model.Announcement;
import com.example.springbootproject.model.Homework;
import com.example.springbootproject.model.HomeworkFile;
import com.example.springbootproject.model.User;
import com.example.springbootproject.service.FileService;
import com.example.springbootproject.service.HomeworkService;
import com.example.springbootproject.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Api
@RestController
@RequestMapping("/api")
public class HomeworkController {
    @Autowired
    UserService userService;
    @Autowired
    FileService fileService;
    @Autowired
    HomeworkService homeworkService;

    @PostMapping("/createHomework")
    public ResponseEntity<Homework> createHomework(
            @RequestParam(name = "userID")Long userID,
            @RequestParam(name = "content")String content,
            HttpServletRequest request
    ){
        try{
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                User user = userService.findUser(userID);
                if (user != null) {
                    if (user.isTeacher()) {
                        Homework homework = homeworkService.saveHomework(
                                new Homework(
                                        user.getUsername(),
                                        content)
                        );
                        if (homework != null) {
                            return new ResponseEntity<>(homework, HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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

    @PostMapping("/editHomework")
    public ResponseEntity<Homework> editAnnouncement(
            @RequestParam(name = "homeworkID") Long homeworkID,
            @RequestParam(name = "userID") Long userID,
            @RequestParam(name = "content") String content,
            HttpServletRequest request
    ) {
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                User user = userService.findUser(userID);
                Homework homework = homeworkService.findHomework(homeworkID);
                if (user != null && homework != null) {
                    if (user.isTeacher()) {
                        homework.setContent(content);
                        homework = homeworkService.saveHomework(homework);
                        return new ResponseEntity<>(homework, HttpStatus.OK);
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
    @PostMapping("/deleteHomework")
    public ResponseEntity<Homework> deleteHomework(
            @RequestParam(name = "homeworkID") Long homeworkID,
            @RequestParam(name = "userID") Long userID,
            HttpServletRequest request
    ) {
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                User user = userService.findUser(userID);
                Homework homework = homeworkService.findHomework(homeworkID);
                if (user != null && homework != null) {
                    if (user.isTeacher()) {
                        homeworkService.deleteHomework(homework);
                        return new ResponseEntity<>(homework, HttpStatus.OK);
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
    @GetMapping("/getHomework")
    public ResponseEntity<List<Homework>> getHomework(
            HttpServletRequest request
    ){
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                return new ResponseEntity<>(homeworkService.getHomework(), HttpStatus.OK);
            } else {
            httpSession.invalidate();
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/uploadHomework")
    public ResponseEntity<User> uploadHomework(
            @RequestParam("homeworkID") Long homeworkID,
            @RequestParam("homeworkFile") MultipartFile multipartFile,
            @RequestParam("userID") Long userID,
            HttpServletRequest request) {
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                User user = userService.findUser(userID);
                Homework homework = homeworkService.findHomework(homeworkID);
                if (user != null && homework != null) {
                    if (multipartFile.getOriginalFilename() == null) {
                        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                    }
                    String rawFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                    String fileName = homeworkID + "-" + userID +
                            rawFileName.substring(rawFileName.lastIndexOf("."));
                    String homeworkFileURL = "/homework/";
                    HomeworkFile homeworkFile = homeworkService.saveHomeworkFile(
                            new HomeworkFile(
                                    userID,
                                    user.getUsername(),
                                    homeworkFileURL + fileName
                            )
                    );

//                String uploadDir = "E:/SpringBootProject/pictures/avatar/";
                    String uploadDir = "/home/ubuntu/homework/";

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
}
