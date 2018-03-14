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
public class UserModel {
    private String userName;
    private String passWord;
    private boolean isLogined;

    public UserModel() {
    }
    
    public UserModel(String userName, String passWord, boolean isLogined) {
        this.userName = userName;
        this.passWord = passWord;
        this.isLogined = isLogined;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isIsLogined() {
        return isLogined;
    }

    public void setIsLogined(boolean isLogined) {
        this.isLogined = isLogined;
    }
    
    
    
    
}
