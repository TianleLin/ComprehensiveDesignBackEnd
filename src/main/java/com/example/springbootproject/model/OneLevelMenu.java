package com.example.springbootproject.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "OneLevelMenu")
public class OneLevelMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "oneLevelMenuID", nullable = false)
    private Long oneLevelMenuID;

    public Long getOneLevelMenuID() {
        return oneLevelMenuID;
    }
//    @Column(name = "OneLevelIndex")
//    private String oneLevelIndex;
//
//    public String getOneLevelIndex() {
//        return oneLevelIndex;
//    }
//
//    public void setOneLevelIndex(String index) {
//        this.oneLevelIndex = index;
//    }
    @Column(name = "title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @OneToMany(targetEntity = TwoLevelMenu.class, fetch = FetchType.EAGER)
    private List<TwoLevelMenu> children;

    public List<TwoLevelMenu> getChildren() {
        return children;
    }

    public void setChildren(List<TwoLevelMenu> children) {
        this.children = children;
    }

    public OneLevelMenu(){};
    public OneLevelMenu(String title){
//        this.oneLevelIndex = oneLevelIndex;
        this.title = title;
        if(this.children==null || this.children.size()==0)
            this.children = new LinkedList<>();
    }
}
