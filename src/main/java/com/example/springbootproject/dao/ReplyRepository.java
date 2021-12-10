package com.example.springbootproject.dao;

import com.example.springbootproject.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByParentID(Long parentID);
    List<Reply> findAllByThreeLevelMenuID(Long threeLevelMenuID);
    void deleteAllByParentID(Long parentID);
}
