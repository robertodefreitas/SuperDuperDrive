package com.udacity.jwdnd.course1.cloudstorage.model;

/**
 * Helpfully is the file schema.sql we can see which variable are needed for this ModelAttributeName
 * CREATE TABLE IF NOT EXISTS FILES (
 *     fileId INT PRIMARY KEY auto_increment,
 *     filename VARCHAR,
 *     contenttype VARCHAR,
 *     filesize VARCHAR,
 *     userid INT,
 *     filedata BLOB,
 *     foreign key (userid) references USERS(userid)
 * );
 *
 * Example how to save a file on the DB
 * https://stackoverflow.com/questions/4646842/how-to-save-doc-pdf-and-image-files-to-mysql-database-using-java
 * -> byte[] ...
 */
public class File {
    private Integer id;
    private Integer userId;
    private String name;
    private String contentType;
    private String size;
    private byte[] data;

    public File(Integer id, String name, String contentType, String size, Integer userId,
            byte[] data) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.data = data;
    }

    public int getFileId() {
        return id;
    }

    public void setFileId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return name;
    }

    public void setFileName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileSize() {
        return size;
    }

    public void setFileSize(String size) {
        this.size = size;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public byte[] getFileData() {
        return data;
    }

    public void setFileData(byte[] data) {
        this.data = data;
    }
}
