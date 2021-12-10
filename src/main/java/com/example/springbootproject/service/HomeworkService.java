package com.example.springbootproject.service;

import com.example.springbootproject.dao.HomeworkFileRepository;
import com.example.springbootproject.dao.HomeworkRepository;
import com.example.springbootproject.model.Homework;
import com.example.springbootproject.model.HomeworkFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class HomeworkService {
    @Autowired
    HomeworkRepository homeworkRepository;
    @Autowired
    HomeworkFileRepository homeworkFileRepository;

    public Homework saveHomework(Homework homework){
        try {
            return homeworkRepository.save(homework);
        } catch (Exception e) {
            return null;
        }
    }
    public boolean deleteHomework(Homework homework) {
        try {
            homeworkRepository.delete(homework);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public Homework findHomework(Long homeworkID){
        try {
            Optional<Homework> homework = homeworkRepository.findById(homeworkID);
            if (homework.isPresent()) {
                return homework.get();
            } else {
                return null;
            }
        } catch (Exception e){
            return null;
        }
    }
    public List<Homework> getHomework(){
        try {
            return homeworkRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    public HomeworkFile saveHomeworkFile(HomeworkFile homeworkFile) {
        try {
            Optional<HomeworkFile> _homeworkFile =
                    Optional.ofNullable(homeworkFileRepository.findByHomeworkFileURL(homeworkFile.getHomeworkFileURL()));
            if(_homeworkFile.isPresent()) {
                HomeworkFile __homeworkFile = _homeworkFile.get();
                __homeworkFile.setHomeworkFileURL(homeworkFile.getHomeworkFileURL());
                return homeworkFileRepository.save(__homeworkFile);
            } else {
                return homeworkFileRepository.save(homeworkFile);
            }
        } catch (Exception e) {
            return null;
        }
    }
    public boolean deleteHomeworkFile(HomeworkFile homeworkFile){
        try {
            homeworkFileRepository.delete(homeworkFile);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
