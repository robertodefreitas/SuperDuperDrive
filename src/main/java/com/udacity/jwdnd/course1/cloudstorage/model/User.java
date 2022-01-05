package com.udacity.jwdnd.course1.cloudstorage.model;

/**
 * Helpfully is the file schema.sql we can see which variable are needed for this ModelAttributeName
 * CREATE TABLE IF NOT EXISTS USERS (
 *   userid INT PRIMARY KEY auto_increment,
 *   username VARCHAR(20),
 *   salt VARCHAR,
 *   password VARCHAR,
 *   firstname VARCHAR(20),
 *   lastname VARCHAR(20)
 * );
 * This class will be used by lesson 6 into the Final Review (create a chat), too.
 */
public class User {
    private Integer id;
    private String user;
    private String salt;
    private String pw;
    private String fn;
    private String ln;

    public User(Integer id, String user, String salt, String pw, String fn, String ln) {
        this.id = id;
        this.user = user;
        this.salt = salt;
        this.pw = pw;
        this.fn = fn;
        this.ln = ln;
    }

    public Integer getUserId() {
        return id;
    }

    public void setUserId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return user;
    }

    public void setUsername(String user) {
        this.user = user;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return pw;
    }

    public void setPassword(String pw) {
        this.pw = pw;
    }

    public String getFirstName() {
        return fn;
    }

    public void setFirstName(String fn) {
        this.fn = fn;
    }

    public String getLastName() {
        return ln;
    }

    public void setLastName(String ln) {
        this.ln = ln;
    }
}
