/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wo.app.model;

/**
 *
 * @author windiwitari
 */
public class Pengguna {
    private String username;
    private String passwordHash; // Hash dari password
    private String salt;         // Salt untuk hashing


    public String getUsername() {
        return username;
    }

    public void setUsername(String u) {
        this.username = u;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String h) {
        this.passwordHash = h;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String s) {
        this.salt = s;
    }

}
