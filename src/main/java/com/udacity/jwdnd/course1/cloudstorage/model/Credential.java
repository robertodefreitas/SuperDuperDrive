package com.udacity.jwdnd.course1.cloudstorage.model;

/**
 * Helpfully is the file schema.sql we can see which variable are needed for this ModelAttributeName
 * CREATE TABLE IF NOT EXISTS CREDENTIALS (
 *     credentialid INT PRIMARY KEY auto_increment,
 *     url VARCHAR(100),
 *     username VARCHAR (30),
 *     key VARCHAR,
 *     password VARCHAR,
 *     userid INT,
 *     foreign key (userid) references USERS(userid)
 * );
 *
 * NOTE: I use by this class multiple constructor.
 */
public class Credential {
    private Integer id;
    private Integer userId;
    private String url;
    private String user;
    private String key;
    private String pw;

    public Credential(Integer id, String url, String user, String key, String pw, Integer userId) {
        this.id = id;
        this.userId = userId;
        this.url = url;
        this.user = user;
        this.key = key;
        this.pw = pw;
    }

    /**
     * This constructor will be used by the Selenium tests HomePage.getFirstCredential()
     * @param url
     * @param user
     * @param pw
     */
    public Credential(String url, String user, String pw) {
        this.url = url;
        this.user = user;
        this.pw = pw;
    }

    public Integer getCredentialId() {
        return id;
    }

    public void setCredentialId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return user;
    }

    public void setUsername(String user) {
        this.user = user;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return pw;
    }

    public void setPassword(String pw) {
        this.pw = pw;
    }

}
