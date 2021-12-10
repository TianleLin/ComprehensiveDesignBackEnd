package com.example.springbootproject.dao;

import com.example.springbootproject.model.TwoLevelMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TwoLevelMenuRepository extends JpaRepository<TwoLevelMenu, Long> {
    List<TwoLevelMenu> findAllByParentID(Long parentID);
//    TwoLevelMenu findByTwoLevelIndex(String index);
}
