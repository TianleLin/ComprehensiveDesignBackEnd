package com.example.springbootproject.model;

import org.hibernate.annotations.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="comment")
public class Comment implements CommentReply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id", nullable = false)
    private Long commentID;

    public Long getCommentID() {
        return commentID;
    }


    @Column(name = "three_level_menu_id", nullable = false)
    private Long threeLevelMenuID;

    public void setThreeLevelMenuID(Long threeLevelMenuID) {
        this.threeLevelMenuID = threeLevelMenuID;
    }

    public Long getThreeLevelMenuID() {
        return threeLevelMenuID;
    }

    @Column(name = "user_id", nullable = false)
    private Long userID;

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getUserID() {
        return userID;
    }

    @Column(name = "username", nullable = false)
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Column(name = "avatar")
    private String avatar;

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    @Column(name = "content")
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Column(name = "commentLike")
    private Long commentLike;

    public Long getCommentLike() {
        return commentLike;
    }

    public void setCommentLike(Long like) {
        this.commentLike = like;
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

    @OneToMany(targetEntity = Reply.class, fetch=FetchType.EAGER)
    private List<Reply> children;

    public List<Reply> getChildren() {
        if(this.children==null || this.children.size()==0)
            this.children = new LinkedList<>();
        return children;
    }

    public void setChildren(List<Reply> children) {
        this.children = children;
    }

    public Comment(){};
    public Comment(Long threeLevelMenuID,
                   Long userID,
                   String username,
                   String avatar,
                   String content,
                   Long commentLike
                   ){
        this.threeLevelMenuID = threeLevelMenuID;
        this.userID = userID;
        this.username = username;
        this.avatar = avatar;
        this.content = content;
        this.commentLike = commentLike;
    }


}
