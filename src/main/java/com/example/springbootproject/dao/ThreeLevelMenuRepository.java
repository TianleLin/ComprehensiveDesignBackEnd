package com.example.springbootproject.dao;

import com.example.springbootproject.model.ThreeLevelMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThreeLevelMenuRepository extends JpaRepository<ThreeLevelMenu, Long> {
    List<ThreeLevelMenu> findAllByParentID(Long parentID);
//    ThreeLevelMenu findByThreeLevelIndex(String index);
}
