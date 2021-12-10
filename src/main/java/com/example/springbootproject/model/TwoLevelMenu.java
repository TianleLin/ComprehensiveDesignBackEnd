package com.example.springbootproject.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "TwoLevelMenu")
public class TwoLevelMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "twoLevelMenuID", nullable = false)
    private Long twoLevelMenuID;

    public Long getTwoLevelMenuID() {
        return twoLevelMenuID;
    }
    @Column(name = "parentID", nullable = false)
    private Long parentID;

    public Long getParentID() {
        return parentID;
    }

    public void setParentID(Long parentID) {
        this.parentID = parentID;
    }
//    @Column(name = "twoLevelIndex")
//    private String twoLevelIndex;
//
//    public String getTwoLevelIndex() {
//        return twoLevelIndex;
//    }
//
//    public void setTwoLevelIndex(String index) {
//        this.twoLevelIndex = index;
//    }
    @Column(name = "title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @OneToMany(targetEntity = ThreeLevelMenu.class,fetch = FetchType.EAGER)
    private List<ThreeLevelMenu> children;

    public List<ThreeLevelMenu> getChildren() {
        return children;
    }

    public void setChildren(List<ThreeLevelMenu> children) {
        this.children = children;
    }

    public TwoLevelMenu(){};
    public TwoLevelMenu(Long parentID, String title){
        this.parentID = parentID;
//        this.twoLevelIndex = twoLevelIndex;
        this.title = title;
        if(this.children==null || this.children.size()==0)
            this.children = new LinkedList<>();
    }


}
