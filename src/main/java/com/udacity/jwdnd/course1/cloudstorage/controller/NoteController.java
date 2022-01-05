package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.constants.ModelAttributeName;
import com.udacity.jwdnd.course1.cloudstorage.constants.TemplateHtml;
import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(ModelAttributeName.NOTE)
public class NoteController {
    private EncryptionService encryptionService;
    private NoteService noteService;
    private UserService userService;

    public NoteController(EncryptionService encryptionService, NoteService noteService, UserService userService) {
        this.encryptionService = encryptionService;
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("note-new")
    public String newNote(Authentication authentication, @ModelAttribute(ModelAttributeName.FILE_NEW) FileForm newFile, @ModelAttribute(ModelAttributeName.NOTE_NEW) NoteForm newNote, @ModelAttribute(ModelAttributeName.CREDENTIAL_NEW) CredentialForm newCredential, Model model) {
        String username = authentication.getName();
        User user = userService.getUser(username);
        Integer userId = user.getUserId();
        String newTitle = newNote.getTitle();
        String noteIdStr = newNote.getNoteId();
        String newDescription = newNote.getDescription();

        if (!noteIdStr.isEmpty()) {
            Note existingNote = getNote(Integer.parseInt(noteIdStr));
            noteService.updateNote(existingNote.getNoteId(), newTitle, newDescription);
        } else {
            noteService.addNote(newTitle, newDescription, userId);
        }

        if (userId != null) {
            model.addAttribute(ModelAttributeName.NOTES, noteService.getNoteListUserId(userId));
            model.addAttribute(ModelAttributeName.RESULT, "success");

            return TemplateHtml.RESULT;
        }
        return TemplateHtml.SIGNUP;
    }

    @GetMapping(value = "/note-get/{noteId}")
    public Note getNote(@PathVariable Integer noteId) { return noteService.getNote(noteId); }

    @GetMapping(value = "/note-remove/{noteId}")
    public String deleteNote(Authentication authentication, @PathVariable Integer noteId, @ModelAttribute(ModelAttributeName.FILE_NEW) FileForm newFile, @ModelAttribute(ModelAttributeName.NOTE_NEW) NoteForm newNote, @ModelAttribute(ModelAttributeName.CREDENTIAL_NEW) CredentialForm newCredential, Model model) {
        String username = authentication.getName();
        User user = userService.getUser(username);
        Integer userId = user.getUserId();
        noteService.deleteNote(noteId);

        if (userId != null) {
            model.addAttribute(ModelAttributeName.NOTES, noteService.getNoteListUserId(userId));
            model.addAttribute(ModelAttributeName.RESULT, "success");

            return TemplateHtml.RESULT;
        }
        return TemplateHtml.SIGNUP;
    }
}
