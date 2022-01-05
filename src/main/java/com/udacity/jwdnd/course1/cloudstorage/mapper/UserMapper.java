package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Helpfully is the file schema.sql we can see which variable are needed for the queries
 * CREATE TABLE IF NOT EXISTS USERS (
 *   userid INT PRIMARY KEY auto_increment,
 *   username VARCHAR(20),
 *   salt VARCHAR,
 *   password VARCHAR,
 *   firstname VARCHAR(20),
 *   lastname VARCHAR(20)
 * );
 */
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);

    @Select("SELECT * FROM USERS WHERE userid = #{userId}")
    User getUserByUserId(Integer userId);

    @Delete("DELETE FROM USERS WHERE userId = #{userId}")
    void deleteUserByUserId(Integer userId);

    // I need a method by UserService to prepare the new password (encodedSalt) before update
    //
    @Update("UPDATE USERS SET username = #{username}, password = #{hashedPassword}, firstname = "
            + "#{firstName}, lastname = #{lastName} WHERE userId = #{userId}")
    void updateUserByUserId(Integer userId, String username, String hashedPassword, String firstName, String lastName);
}