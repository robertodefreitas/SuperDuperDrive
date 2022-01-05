package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public Note getNote(Integer noteId) {
        return noteMapper.getNoteByNoteId(noteId);
    }

    public Note[] getNoteListUserId(Integer userId) {
        return noteMapper.getNoteListUserId(userId);
    }

    public void updateNote(Integer noteId, String title, String description) { noteMapper.updateNoteByNoteId(noteId, title, description); }

    public void addNote(String title, String description, Integer userId) {
        Note note = new Note(null, title, description, userId);
        noteMapper.insert(note);
    }

    public void deleteNote(Integer noteId) {noteMapper.deleteNoteByNoteId(noteId); }
}
