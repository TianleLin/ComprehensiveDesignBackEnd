package com.example.springbootproject.service;


import com.example.springbootproject.dao.OneLevelMenuRepository;
import com.example.springbootproject.dao.ThreeLevelMenuRepository;
import com.example.springbootproject.dao.TwoLevelMenuRepository;
import com.example.springbootproject.dao.UserRepository;
import com.example.springbootproject.model.OneLevelMenu;
import com.example.springbootproject.model.ThreeLevelMenu;
import com.example.springbootproject.model.TwoLevelMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
    @Autowired
    OneLevelMenuRepository oneLevelMenuRepository;
    @Autowired
    TwoLevelMenuRepository twoLevelMenuRepository;
    @Autowired
    ThreeLevelMenuRepository threeLevelMenuRepository;
    @Autowired
    UserRepository userRepository;

    public List<OneLevelMenu> findAllOneLevelMenu(){
        try {
            return oneLevelMenuRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }
//    public OneLevelMenu findOneLevelMenu(String index){
//        try {
//            Optional<OneLevelMenu> oneLevelMenu = Optional.ofNullable(oneLevelMenuRepository.findByOneLevelIndex(index));
//            if(oneLevelMenu.isPresent()){
//                return oneLevelMenu.get();
//            } else {
//                return null;
//            }
//        } catch (Exception e) {
//            return null;
//        }
//    }
    public OneLevelMenu findOneLevelMenu(Long oneLevelMenuID){
        try {
            Optional<OneLevelMenu> oneLevelMenu = oneLevelMenuRepository.findById(oneLevelMenuID);
            if(oneLevelMenu.isPresent()){
                return oneLevelMenu.get();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    public OneLevelMenu saveOneLevelMenu(OneLevelMenu oneLevelMenu){
        try {
            return oneLevelMenuRepository.save(oneLevelMenu);
        } catch (Exception e) {
            return null;
        }
    }
    public boolean deleteOneLevelMenu(OneLevelMenu oneLevelMenu) {
        try {
            oneLevelMenuRepository.delete(oneLevelMenu);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<TwoLevelMenu> findAllTwoLevelMenu(){
        try {
            return twoLevelMenuRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }
    public List<TwoLevelMenu> findAllTwoLevelMenuByParentID(Long parentID){
        try {
            return twoLevelMenuRepository.findAllByParentID(parentID);
        } catch (Exception e) {
            return null;
        }
    }
//    public TwoLevelMenu findTwoLevelMenu(String index){
//        try {
//            Optional<TwoLevelMenu> twoLevelMenu = Optional.ofNullable(twoLevelMenuRepository.findByTwoLevelIndex(index));
//            if(twoLevelMenu.isPresent()){
//                return twoLevelMenu.get();
//            } else {
//                return null;
//            }
//        } catch (Exception e) {
//            return null;
//        }
//    }
    public TwoLevelMenu findTwoLevelMenu(Long twoLevelMenuID){
        try {
            Optional<TwoLevelMenu> twoLevelMenu = twoLevelMenuRepository.findById(twoLevelMenuID);
            if(twoLevelMenu.isPresent()){
                return twoLevelMenu.get();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    public TwoLevelMenu saveTwoLevelMenu(TwoLevelMenu twoLevelMenu){
        try {
            return twoLevelMenuRepository.save(twoLevelMenu);
        } catch (Exception e) {
            return null;
        }
    }
    public boolean deleteTwoLevelMenu(TwoLevelMenu twoLevelMenu) {
        try {
            twoLevelMenuRepository.delete(twoLevelMenu);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<ThreeLevelMenu> findAllThreeLevelMenuByParentID(Long parentID){
        try {
            return threeLevelMenuRepository.findAllByParentID(parentID);
        } catch (Exception e) {
            return null;
        }
    }
//    public ThreeLevelMenu findThreeLevelMenu(String index){
//        try {
//            Optional<ThreeLevelMenu> threeLevelMenu = Optional.ofNullable(threeLevelMenuRepository.findByThreeLevelIndex(index));
//            if(threeLevelMenu.isPresent()){
//                return threeLevelMenu.get();
//            } else {
//                return null;
//            }
//        } catch (Exception e) {
//            return null;
//        }
//    }
    public ThreeLevelMenu findThreeLevelMenu(Long threeMenuLevelMenuID){
        try {
            Optional<ThreeLevelMenu> threeLevelMenu = threeLevelMenuRepository.findById(threeMenuLevelMenuID);
            if(threeLevelMenu.isPresent()){
                return threeLevelMenu.get();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    public ThreeLevelMenu saveThreeLevelMenu(ThreeLevelMenu threeLevelMenu){
        try {
            return threeLevelMenuRepository.save(threeLevelMenu);
        } catch (Exception e) {
            return null;
        }
    }
    public boolean deleteThreeLevelMenu(ThreeLevelMenu threeLevelMenu) {
        try {
            threeLevelMenuRepository.delete(threeLevelMenu);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
