package com.example.springbootproject.service;

import com.example.springbootproject.dao.LikeCommentReplyRepository;
import com.example.springbootproject.model.LikeCommentReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    @Autowired
    LikeCommentReplyRepository likeCommentReplyRepository;

    public LikeCommentReply findLikeByCommentOrReplyIDAndUserID(Long commentOrReplyID, Long userID){
        try {
            Optional<LikeCommentReply> like = Optional.ofNullable(
                    likeCommentReplyRepository.findByCommentReplyIDAndUserID(commentOrReplyID, userID));
            if(like.isPresent()){
                return like.get();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    public List<Long> findCommentOrReplyIDByThreeLevelMenuIDAndUserID(Long threeLevelMenuID, Long userID){
        try {
            List<LikeCommentReply> likeCommentReplyList =  likeCommentReplyRepository.findAllByThreeLevelMenuIDAndUserID(threeLevelMenuID, userID);
            List<Long> commentOrReplyIDList = new LinkedList<>();
            for (LikeCommentReply like :
                    likeCommentReplyList) {
                commentOrReplyIDList.add(like.getCommentReplyID());
            }
            return commentOrReplyIDList;
        } catch (Exception e) {
            return null;
        }
    }

    public LikeCommentReply saveLike(LikeCommentReply like) {
        try {
            return likeCommentReplyRepository.save(like);
        } catch (Exception e) {
            return null;
        }
    }
    public boolean deleteLike(LikeCommentReply like) {
        try {
            likeCommentReplyRepository.delete(like);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
