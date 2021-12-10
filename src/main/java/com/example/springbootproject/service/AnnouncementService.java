package com.example.springbootproject.service;

import com.example.springbootproject.dao.AnnouncementRepository;
import com.example.springbootproject.model.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementService {
    @Autowired
    AnnouncementRepository announcementRepository;

    public Announcement saveAnnouncement(Announcement announcement){
        try {
            return announcementRepository.save(announcement);
        } catch (Exception e) {
            return null;
        }
    }

    public Announcement findAnnouncement(Long announcementID){
        try {
          Optional<Announcement> announcement = announcementRepository.findById(announcementID);
          if(announcement.isPresent()) {
              return announcement.get();
          } else {
              return null;
          }
        } catch (Exception e){
            return null;
        }
    }

    public boolean deleteAnnouncement(Announcement announcement){
        try {
            announcementRepository.delete(announcement);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public List<Announcement> getAnnouncement() {
        try {
            return announcementRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }
}
