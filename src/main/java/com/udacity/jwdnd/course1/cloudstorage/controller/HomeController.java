package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.constants.ModelAttributeName;
import com.udacity.jwdnd.course1.cloudstorage.constants.Path;
import com.udacity.jwdnd.course1.cloudstorage.constants.TemplateHtml;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(Path.HOME)
public class HomeController {
    private EncryptionService encryptionService;
    private UserService userService;
    private FileService fileService;
    private NoteService noteService;
    private CredentialService credentialService;

    public HomeController(EncryptionService encryptionService, UserService userService, FileService fileService, NoteService noteService, CredentialService credentialService) {
        this.encryptionService = encryptionService;
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping
    public String getHomePage(@ModelAttribute("fileModelNew") FileForm newFile, @ModelAttribute("noteModelNew") NoteForm newNote, @ModelAttribute("credentialModelNew") CredentialForm newCredential, Authentication authentication, Model model) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        Integer userId = user.getUserId();

        if (userId != null) {
            model.addAttribute(ModelAttributeName.ENCRY_SERVICE, encryptionService);
            model.addAttribute(ModelAttributeName.FILES, fileService.getFileNameListUserId(userId));
            model.addAttribute(ModelAttributeName.NOTES, noteService.getNoteListUserId(userId));
            model.addAttribute(ModelAttributeName.CREDENTIALS, credentialService.getCredentialListUserId(userId));

            return TemplateHtml.HOME;
        }
        return TemplateHtml.SIGNUP;
    }

}
