package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Helpfully is the file schema.sql we can see which variable are needed for the queries
 * CREATE TABLE IF NOT EXISTS CREDENTIALS (
 *     credentialid INT PRIMARY KEY auto_increment,
 *     url VARCHAR(100),
 *     username VARCHAR (30),
 *     key VARCHAR,
 *     password VARCHAR,
 *     userid INT,
 *     foreign key (userid) references USERS(userid)
 * );
 */
@Mapper
public interface CredentialMapper {
    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    Credential[] getCredentialListUserId(Integer userId);

    @Select("SELECT * FROM CREDENTIALS")
    Credential[] getCredentialListAll();

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    Credential getCredentialByCredId(Integer credentialId);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    void deleteCredentialByCredId(Integer credentialId);

    @Update("UPDATE CREDENTIALS SET url = #{url}, key = #{keyEncoded}, password = #{passwordEncrypted}, username = #{newUsername} WHERE credentialid = #{credentialId}")
    void updateCredentialByCredId(String url, String newUsername, Integer credentialId, String keyEncoded, String passwordEncrypted);
}
