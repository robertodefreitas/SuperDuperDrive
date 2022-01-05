package com.udacity.jwdnd.course1.cloudstorage.model;

/**
 * Helpfully is the file schema.sql we can see which variable are needed for this ModelAttributeName
 * CREATE TABLE IF NOT EXISTS NOTES (
 *     noteid INT PRIMARY KEY auto_increment,
 *     notetitle VARCHAR(20),
 *     notedescription VARCHAR (1000),
 *     userid INT,
 *     foreign key (userid) references USERS(userid)
 * );
 *
 * NOTE: I use by this class multiple constructor.
 */
public class Note {
    private Integer id;
    private Integer userId;
    private String title;
    private String description;

    public Note(Integer id, String title, String description, Integer userId) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    /**
     * This constructor will be used by the Selenium tests HomePage.getFirstNote()
     * @param title
     * @param description
     */
    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Integer getNoteId() {
        return id;
    }

    public void setNoteId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNoteTitle() {
        return title;
    }

    public void setNoteTitle(String title) {
        this.title = title;
    }

    public String getNoteDescription() {
        return description;
    }

    public void setNoteDescription(String description) {
        this.description = description;
    }

}
