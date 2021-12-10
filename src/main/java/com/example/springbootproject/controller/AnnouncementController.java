package com.example.springbootproject.controller;

import com.example.springbootproject.model.Announcement;
import com.example.springbootproject.model.User;
import com.example.springbootproject.service.AnnouncementService;
import com.example.springbootproject.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@Api
//@CrossOrigin(value = "http://localhost:8080")
@RequestMapping("/api")
public class AnnouncementController {
    @Autowired
    AnnouncementService announcementService;
    @Autowired
    UserService userService;


    @PostMapping("/createAnnouncement")
    public ResponseEntity<Announcement> createAnnouncement(
            @RequestParam(name = "userID") Long userID,
            @RequestParam(name = "content") String content,
            HttpServletRequest request
    ) {
        try{
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                User user = userService.findUser(userID);
                if (user != null) {
                    if (user.isTeacher()) {
                        Announcement announcement = announcementService.saveAnnouncement(
                                new Announcement(
                                        userID,
                                        user.getUsername(),
                                        content
                                )
                        );
                        if (announcement != null) {
                            return new ResponseEntity<>(announcement, HttpStatus.OK);
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

    @PostMapping("/editAnnouncement")
    public ResponseEntity<Announcement> editAnnouncement(
            @RequestParam(name = "announcementID") Long announcementID,
            @RequestParam(name = "userID") Long userID,
            @RequestParam(name = "content") String content,
            HttpServletRequest request
    ) {
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                User user = userService.findUser(userID);
                Announcement announcement = announcementService.findAnnouncement(announcementID);
                if (user != null && announcement != null) {
                    if (user.isTeacher()) {
                        announcement.setContent(content);
                        announcement = announcementService.saveAnnouncement(announcement);
                        return new ResponseEntity<>(announcement, HttpStatus.OK);
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

    @PostMapping("/deleteAnnouncement")
    public ResponseEntity<Announcement> deleteAnnouncement(
            @RequestParam(name = "announcementID") Long announcementID,
            @RequestParam(name = "userID") Long userID,
            HttpServletRequest request
    ) {
        try {
            HttpSession httpSession = request.getSession();
            if(!httpSession.isNew()) {
                User user = userService.findUser(userID);
                Announcement announcement = announcementService.findAnnouncement(announcementID);
                if (user != null && announcement != null) {
                    if (user.isTeacher()) {
                        announcementService.deleteAnnouncement(announcement);
                        return new ResponseEntity<>(announcement, HttpStatus.OK);
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

//    @CrossOrigin(value = "http://localhost:8080")
    @GetMapping("/getAnnouncement")
    public ResponseEntity<List<Announcement>> getAnnouncement(
            HttpServletRequest request
    ){
        try {
            HttpSession httpSession = request.getSession();
                if(!httpSession.isNew()) {
                return new ResponseEntity<>(announcementService.getAnnouncement(), HttpStatus.OK);
            } else {
                httpSession.invalidate();
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
