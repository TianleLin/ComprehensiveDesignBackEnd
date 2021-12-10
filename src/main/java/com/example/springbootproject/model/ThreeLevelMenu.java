package com.example.springbootproject.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "ThreeLevelMenu")
public class ThreeLevelMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "threeLevelMenuID", nullable = false)
    private Long threeLevelMenuID;

    public Long getThreeLevelMenuID() {
        return threeLevelMenuID;
    }
    @Column(name = "parentID", nullable = false)
    private Long parentID;

    public Long getParentID() {
        return parentID;
    }

    public void setParentID(Long parentID) {
        this.parentID = parentID;
    }

//    @Column(name = "threeLevelIndex")
//    private String threeLevelIndex;
//
//    public String getThreeLevelIndex() {
//        return threeLevelIndex;
//    }
//
//    public void setThreeLevelIndex(String index) {
//        this.threeLevelIndex = index;
//    }

    @Column(name = "title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ApiModelProperty(example = "video/slide/compressed/pdf/file")
    @Column(name = "fileType")
    private String fileType;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Column(name = "fileURL")
    private String fileURL;

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    public ThreeLevelMenu(){};

    public ThreeLevelMenu(Long parentID, String title, String fileType, String fileURL) {
        this.parentID = parentID;
//        this.threeLevelIndex = threeLevelIndex;
        this.title = title;
        this.fileType = fileType;
        this.fileURL = fileURL;
    }
}
