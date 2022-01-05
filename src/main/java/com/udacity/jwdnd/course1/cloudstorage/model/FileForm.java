package com.udacity.jwdnd.course1.cloudstorage.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * This ModelAttributeName is important because of the form on home.html
 * <form action="#" enctype="multipart/form-data" method="POST">
 *
 * I use the logic from the lesson 6 - Chapter 19
 * where the ChatForm and ChatMessage is added on the package 'ModelAttributeName'
 * but the methode to add the file I put on the package 'service'
 * like by the example chat (addMessage)
 * This class will be used by class HomeController
 *
 * Some Infos:
 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/multipart/MultipartFile.html
 * https://spring.io/guides/gs/uploading-files/
 * https://www.codejava.net/java-se/networking/upload-files-by-sending-multipart-request-programmatically
 *
 */
public class FileForm {
    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
