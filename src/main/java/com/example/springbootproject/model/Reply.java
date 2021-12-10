package com.example.springbootproject.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Reply implements CommentReply {
    @Id
    @GeneratedValue
    @Column(name = "reply_id", nullable = false)
    private Long replyID;

    public Long getReplyID() {
        return replyID;
    }
    @Column(name = "three_level_menu_id", nullable = false)
    private Long threeLevelMenuID;

    public void setThreeLevelMenuID(Long threeLevelMenuID) {
        this.threeLevelMenuID = threeLevelMenuID;
    }

    public Long getThreeLevelMenuID() {
        return threeLevelMenuID;
    }

    @Column(name = "parent_id", nullable = false)
    private Long parentID;

    public Long getParentID() {
        return parentID;
    }

    public void setParentID(Long parentID) {
        this.parentID = parentID;
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

    @Column(name = "replyLike")
    private Long replyLike;

    public Long getReplyLike() {
        return replyLike;
    }

    public void setReplyLike(Long like) {
        this.replyLike = like;
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
        if(children==null || children.size()==0)
            children = new LinkedList<>();
        return children;
    }

    public void setChildren(List<Reply> children) {
        this.children = children;
    }

    public Reply(){};
    public Reply(Long threeLevelMenuID,
                 Long parentID,
                 Long userID,
                 String username,
                 String avatar,
                 String content,
                 Long replyLike
                 ) {
        this.threeLevelMenuID = threeLevelMenuID;
        this.parentID = parentID;
        this.userID = userID;
        this.username = username;
        this.avatar = avatar;
        this.content  = content;
        this.replyLike = replyLike;
    }
}
