package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Helpfully is the file schema.sql we can see which variable are needed for the queries
 * CREATE TABLE IF NOT EXISTS NOTES (
 *     noteid INT PRIMARY KEY auto_increment,
 *     notetitle VARCHAR(20),
 *     notedescription VARCHAR (1000),
 *     userid INT,
 *     foreign key (userid) references USERS(userid)
 * );
 */
@Mapper
public interface NoteMapper {
    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Note note);

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    Note[] getNoteListUserId(Integer userId);

    @Select("SELECT * FROM NOTES")
    Note[] getNoteListAll();

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    Note getNoteByNoteId(Integer noteId);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    void deleteNoteByNoteId(Integer noteId);

    @Update("UPDATE NOTES SET notetitle = #{title}, notedescription = #{description} WHERE noteid = #{noteId}")
    void updateNoteByNoteId(Integer noteId, String title, String description);
}
