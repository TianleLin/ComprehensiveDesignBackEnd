package com.example.springbootproject.dao;

import com.example.springbootproject.model.OneLevelMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OneLevelMenuRepository extends JpaRepository<OneLevelMenu, Long> {
//    OneLevelMenu findByOneLevelIndex(String index);
}
