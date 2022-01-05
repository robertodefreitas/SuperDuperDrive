package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Helpfully is the file schema.sql we can see which variable are needed for the queries
 * CREATE TABLE IF NOT EXISTS FILES (
 *     fileId INT PRIMARY KEY auto_increment,
 *     filename VARCHAR,
 *     contenttype VARCHAR,
 *     filesize VARCHAR,
 *     userid INT,
 *     filedata BLOB,
 *     foreign key (userid) references USERS(userid)
 * );
 */
@Mapper
public interface FileMapper {
    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Select("SELECT filename FROM FILES WHERE userid = #{userId}")
    String[] getFileNameListUserId(Integer userId);

    @Select("SELECT * FROM FILES")
    File[] getFileListAll();

/*
    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File getFileByFileId(Integer fileId);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    void deleteFileByFileId(Integer fileId);
*/
    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    File getFile(String fileName);

    @Delete("DELETE FROM FILES WHERE filename = #{fileName}")
    void deleteFile(String fileName);

    @Update("UPDATE FILES SET filename = #{fileName} WHERE fileId = #{fileId}")
    void updateFileNameByFileId(Integer fileId, String fileName);
}
