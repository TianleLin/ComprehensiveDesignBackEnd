package com.example.springbootproject.model;

import com.example.springbootproject.dao.UserRepository;
import com.example.springbootproject.service.FileService;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "homework")
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "homeworkID", nullable = false)
    private Long homeworkID;

    public Long getHomeworkID() {
        return homeworkID;
    }

    @Column(name = "teacherName")
    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Column(name = "content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "timeCreatedAt", nullable = false)
    @CreationTimestamp
    private Timestamp timeCreatedAt;

    public Timestamp getTimeCreatedAt() {
        return timeCreatedAt;
    }

    public void setTimeCreatedAt(Timestamp timeCreatedAt) {
        this.timeCreatedAt = timeCreatedAt;
    }

    @Column(name = "timeUpdatedAt", nullable = false)
    @UpdateTimestamp
    private Timestamp timeUpdatedAt;

    public Timestamp getTimeUpdatedAt() {
        return timeUpdatedAt;
    }

    public void setTimeUpdatedAt(Timestamp timeUpdatedAt) {
        this.timeUpdatedAt = timeUpdatedAt;
    }

    public Homework() {}

    public Homework(String teacherName, String content) {
        this.teacherName = teacherName;
        this.content = content;
    }
}
