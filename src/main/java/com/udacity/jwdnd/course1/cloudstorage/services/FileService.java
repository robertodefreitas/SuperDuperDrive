package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class FileService {
    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

/*
    public File getFile(Integer fileId) {
        return fileMapper.getFile(fileId);
    }

    public void deleteFile(Integer fileId) {
        fileMapper.deleteFile(fileId);
    }
*/

    public File getFile(String fileName) {
        return fileMapper.getFile(fileName);
    }

    public String[] getFileNameListUserId(Integer userId) {
        return fileMapper.getFileNameListUserId(userId);
    }

    public void deleteFile(String fileName) {
        fileMapper.deleteFile(fileName);
    }

    /**
     * Some Referenz:
     * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/multipart/MultipartFile.html
     * https://spring.io/guides/gs/uploading-files/
     * https://github.com/jwolfe890/superDuperDrive1/blob/master/src/main/java/com/udacity/jwdnd/course1/cloudstorage/controllers/FilesController.java
     *
     * @param multipartFile
     * @param userId
     * @throws IOException
     */
    public void addFile(MultipartFile multipartFile, Integer userId) throws IOException {
        byte[] fileData = multipartFile.getBytes();
        String fileName = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        String fileSize = String.valueOf(multipartFile.getSize());
        //Integer userId = userMapper.getUser(username).getUserId();
        File newFile = new File(null, fileName, contentType, fileSize, userId, fileData);
        fileMapper.insert(newFile);
    }
}
