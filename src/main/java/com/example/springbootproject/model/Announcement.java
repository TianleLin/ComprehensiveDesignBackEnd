package com.example.springbootproject.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "announcement")
public class Announcement {
    @Id
    @GeneratedValue
    @Column(name = "announcementID", nullable = false)
    private Long announcementID;

    public Long getAnnouncementID() {
        return announcementID;
    }

    @Column(name = "userID", nullable = false)
    private Long userID;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @Column(name = "username", nullable = false)
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Announcement() {}
    public Announcement(Long userID, String username, String content) {
        this.userID = userID;
        this.username = username;
        this.content = content;
    }

}
