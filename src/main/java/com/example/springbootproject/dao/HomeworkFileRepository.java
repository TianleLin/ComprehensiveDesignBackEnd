package com.example.springbootproject.dao;

import com.example.springbootproject.model.Homework;
import com.example.springbootproject.model.HomeworkFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkFileRepository extends JpaRepository<HomeworkFile, Long> {
    HomeworkFile findByHomeworkFileURL(String homeWorkURL);
}
