package com.example.springbootproject.dao;

import com.example.springbootproject.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByThreeLevelMenuID(Long threeLevelMenuID);
}
