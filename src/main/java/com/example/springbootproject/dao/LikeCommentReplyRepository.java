package com.example.springbootproject.dao;

import com.example.springbootproject.model.LikeCommentReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeCommentReplyRepository extends JpaRepository<LikeCommentReply, Long> {
    LikeCommentReply findByCommentReplyIDAndUserID(Long commentID, Long userID);
    List<LikeCommentReply> findAllByThreeLevelMenuIDAndUserID(Long threeLevelMenuID, Long userID);
}
