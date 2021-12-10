package com.example.springbootproject.service;

import com.example.springbootproject.dao.CommentRepository;
import com.example.springbootproject.dao.ReplyRepository;
import com.example.springbootproject.model.Comment;
import com.example.springbootproject.model.CommentReply;
import com.example.springbootproject.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentReplyService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    CommentReplyService commentReplyService;

    public Comment saveComment(Comment comment){
        try {
            return commentRepository.save(comment);
        } catch (Exception e){
            return null;
        }
    }
    public List<Comment> findAllCommentByThreeLevelMenuID(Long threeLevelMenuID){
        try {
            return commentRepository.findAllByThreeLevelMenuID(threeLevelMenuID);
        } catch (Exception e){
            return null;
        }
    }

    public boolean deleteCommentOrReply(CommentReply commentOrReply){
        try {
            if(commentOrReply instanceof Comment) {
                replyRepository.deleteAllByParentID(((Comment) commentOrReply).getCommentID());
                commentRepository.deleteById(((Comment) commentOrReply).getCommentID());
                return true;
            } else if(commentOrReply instanceof Reply) {
                replyRepository.deleteAllByParentID(((Reply) commentOrReply).getReplyID());
                replyRepository.deleteById(((Reply) commentOrReply).getReplyID());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }
    public CommentReply findCommentOrReply(Long commentOrReplyID){
        try {
            Optional<Comment> comment = commentRepository.findById(commentOrReplyID);
            Optional<Reply> reply = replyRepository.findById(commentOrReplyID);
            if(comment.isPresent()) {
                return comment.get();
            } else if(reply.isPresent()) {
                return reply.get();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }


    public Reply saveReply(Reply reply){
        try {
            return replyRepository.save(reply);
        } catch (Exception e) {
            return null;
        }
    }
    public List<Reply> findAllReplyByThreeLevelMenuID(Long threeLevelMenuID){
        try {
            return replyRepository.findAllByThreeLevelMenuID(threeLevelMenuID);
        } catch (Exception e){
            return null;
        }
    }
}
