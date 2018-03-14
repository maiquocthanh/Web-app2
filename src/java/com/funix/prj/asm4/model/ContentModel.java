/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funix.prj.asm4.model;

/**
 *
 * @author hung
 */
public class ContentModel {
    private int id;  
    private String title;
    private String description;
    private String contents;
    private int isPublish;
    private String created;
    private String userID;

    public ContentModel() {
    }

    public ContentModel(int id, String title, String description,String contents, int isPublish, String created, String userID) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.contents = contents;
        this.isPublish = isPublish;
        this.created = created;
        this.userID = userID;
    }
    public ContentModel(String title, String description,String contents, int isPublish, String created, String userID) {
      
        this.title = title;
        this.description = description;
        this.contents = contents;
        this.isPublish = isPublish;
        this.created = created;
        this.userID = userID;
    }
    

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(int isPublish) {
        this.isPublish = isPublish;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    
    
    
}
