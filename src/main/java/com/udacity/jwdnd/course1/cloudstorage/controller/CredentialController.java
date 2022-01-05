package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.constants.ModelAttributeName;
import com.udacity.jwdnd.course1.cloudstorage.constants.TemplateHtml;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import java.security.SecureRandom;
import java.util.Base64;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(ModelAttributeName.CREDENTIAL)
public class CredentialController {
    private EncryptionService encryptionService;
    private UserService userService;
    private CredentialService credentialService;
    private NoteService noteService;
    private FileService fileService;


    public CredentialController(EncryptionService encryptionService, UserService userService, CredentialService credentialService, NoteService noteService, FileService fileService) {
        this.encryptionService = encryptionService;
        this.userService = userService;
        this.credentialService = credentialService;
        this.noteService = noteService;
        this.fileService = fileService;
    }

    @PostMapping("credential-new")
    public String credentialNew(Authentication authentication, @ModelAttribute(ModelAttributeName.FILE_NEW) FileForm newFile, @ModelAttribute(ModelAttributeName.NOTE_NEW) NoteForm newNote, @ModelAttribute(ModelAttributeName.CREDENTIAL_NEW) CredentialForm newCredential, Model model)  {
        String username = authentication.getName();
        User user = userService.getUser(username);
        Integer userId = user.getUserId();
        String newUrl = newCredential.getUrl();
        String credentIdString = newCredential.getCredentialId();
        String password = newCredential.getPassword();

        // source https://www.geeksforgeeks.org/securerandom-nextbytes-method-in-java-with-examples/
        SecureRandom secRandom = new SecureRandom();
        byte[] keyByte = new byte[16];
        secRandom.nextBytes(keyByte);
        String keyEncoded = Base64.getEncoder().encodeToString(keyByte);
        String passwordEncrypted = encryptionService.encryptValue(password, keyEncoded);

        if (!credentIdString.isEmpty()) {
            Credential credAvailable = getCredential(Integer.parseInt(credentIdString));
            credentialService.updateCredential(newUrl, newCredential.getUsername(), credAvailable.getCredentialId(),  keyEncoded, passwordEncrypted);
        } else {
            credentialService.addCredential(newUrl, newCredential.getUsername(), userId, keyEncoded, passwordEncrypted);
        }

        if (userId != null) {
            model.addAttribute(ModelAttributeName.CREDENTIALS, credentialService.getCredentialListUserId(userId));
            model.addAttribute(ModelAttributeName.ENCRY_SERVICE, encryptionService);
            model.addAttribute(ModelAttributeName.RESULT, "success");

            return TemplateHtml.RESULT;
        }
        return TemplateHtml.SIGNUP;
    }

    @GetMapping(value = "/credential-get/{credentialId}")
    public Credential getCredential(@PathVariable Integer credentialId) {
        return credentialService.getCredential(credentialId);
    }

    @GetMapping(value = "/credential-remove/{credentialId}")
    public String deleteCredential(Authentication authentication, @PathVariable Integer credentialId, @ModelAttribute(ModelAttributeName.FILE_NEW) FileForm newFile, @ModelAttribute(ModelAttributeName.NOTE_NEW) NoteForm newNote, @ModelAttribute(ModelAttributeName.CREDENTIAL_NEW) CredentialForm newCredential,
            Model model) {
        credentialService.deleteCredential(credentialId);
        String username = authentication.getName();
        User user = userService.getUser(username);
        Integer userId = user.getUserId();

        if (userId != null) {
            model.addAttribute(ModelAttributeName.CREDENTIALS, credentialService.getCredentialListUserId(userId));
            model.addAttribute(ModelAttributeName.ENCRY_SERVICE, encryptionService);
            model.addAttribute(ModelAttributeName.RESULT, "success");

            return TemplateHtml.RESULT;
        }
        return TemplateHtml.SIGNUP;
    }
}
