package com.example.springbootproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LikeCommentReply {
    @Id
    @GeneratedValue
    @Column(name = "likeID", nullable = false)
    private Long likeID;

    public Long getLikeID() {
        return likeID;
    }

    @Column(name = "threeLevelMenuID", nullable = false)
    private Long threeLevelMenuID;

    public Long getThreeLevelMenuID() {
        return threeLevelMenuID;
    }

    public void setThreeLevelMenuID(Long threeLevelMenuID) {
        this.threeLevelMenuID = threeLevelMenuID;
    }

    @Column(name = "commentReplyID", nullable = false)
    private Long commentReplyID;

    public Long getCommentReplyID() {
        return commentReplyID;
    }

    public void setCommentReplyID(Long commentID) {
        this.commentReplyID = commentID;
    }

    @Column(name = "userID", nullable = false)
    private Long userID;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public LikeCommentReply() {}

    public LikeCommentReply(Long threeLevelMenuID, Long commentReplyID, Long userID) {
        this.threeLevelMenuID = threeLevelMenuID;
        this.commentReplyID = commentReplyID;
        this.userID = userID;
    }
}
