package com.example.springbootproject.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class HomeworkFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "homeworkFileID", nullable = false)
    private Long homeworkFileID;

    public Long getHomeworkFileID() {
        return homeworkFileID;
    }

    @Column(name = "studentID")
    private Long studentID;

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    @Column(name = "studentName")
    private String studentName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Column(name = "homeworkFileURL")
    private String homeworkFileURL;

    public String getHomeworkFileURL() {
        return homeworkFileURL;
    }

    public void setHomeworkFileURL(String homeworkFileURL) {
        this.homeworkFileURL = homeworkFileURL;
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

    public HomeworkFile() {
    }

    public HomeworkFile(Long studentID, String studentName, String homeworkFileURL) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.homeworkFileURL = homeworkFileURL;
    }
}
