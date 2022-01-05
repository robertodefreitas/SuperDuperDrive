package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public void deleteCredential(Integer noteId) { credentialMapper.deleteCredentialByCredId(noteId); }

    public void updateCredential(String url, String newUserName, Integer credentialId, String keyEncoded, String passwordEncrypted) { credentialMapper.updateCredentialByCredId(url, newUserName, credentialId, keyEncoded, passwordEncrypted); }

    public Credential[] getCredentialListUserId(Integer userId) { return credentialMapper.getCredentialListUserId(userId); }

    public Credential getCredential(Integer noteId) { return credentialMapper.getCredentialByCredId(noteId); }

    public void addCredential(String url, String credentialUserName, Integer userId, String key, String password) {
        Credential credential = new Credential(null, url, credentialUserName, key, password, userId);
        credentialMapper.insert(credential);
    }
}
