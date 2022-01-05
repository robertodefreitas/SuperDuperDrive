package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.udacity.jwdnd.course1.cloudstorage.constants.ModelAttributeName;
import com.udacity.jwdnd.course1.cloudstorage.constants.TemplateHtml;
import com.udacity.jwdnd.course1.cloudstorage.constants.Text;
import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping(ModelAttributeName.FILE)
public class FileController {
	private EncryptionService encryptionService;
	private FileService fileService;
	private UserService userService;

	public FileController(EncryptionService encryptionService, FileService fileService, UserService userService) {
		this.encryptionService = encryptionService;
		this.fileService = fileService;
		this.userService = userService;
	}

	@PostMapping
	public String newFile(Authentication authentication, @ModelAttribute(ModelAttributeName.FILE_NEW) FileForm newFile, @ModelAttribute(ModelAttributeName.NOTE_NEW) NoteForm newNote, @ModelAttribute(ModelAttributeName.CREDENTIAL_NEW) CredentialForm newCredential, Model model) throws IOException {
		String username = authentication.getName();
		User user = userService.getUser(username);
		Integer userId = user.getUserId();

		if (userId != null) {
			// get an Array/List with all available filenames by userId
			String[] fileNameList = fileService.getFileNameListUserId(userId);
			MultipartFile multipartFile = newFile.getMultipartFile();
			String fileName = multipartFile.getOriginalFilename();

			// check file duplicate
			boolean fileIsDuplicate = false;
			for (String fileNameRow : fileNameList) {
				if (fileNameRow.equals(fileName)) {
					fileIsDuplicate = true;
					break;
				}
			}
			if (fileIsDuplicate) {
				model.addAttribute(ModelAttributeName.RESULT, "error");
				model.addAttribute(ModelAttributeName.MESSAGE, Text.FILE_ALREADY_EXISTS_ERROR);
			} else {
				fileService.addFile(multipartFile, userId);
				model.addAttribute(ModelAttributeName.RESULT, "success");
				model.addAttribute(ModelAttributeName.MESSAGE, Text.FILE_UPLOAD_OK);
			}
			model.addAttribute(ModelAttributeName.FILES, fileService.getFileNameListUserId(userId));

			return TemplateHtml.RESULT;
		}
		return TemplateHtml.SIGNUP;
	}

	// code reference
	// https://www.programcreek.com/java-api-examples/?class=org.springframework.http.MediaType&method=APPLICATION_OCTET_STREAM_VALUE
	@GetMapping(value = "/file-get/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody
	byte[] getFile(@PathVariable String fileName) {
		return fileService.getFile(fileName).getFileData();
	}

	@GetMapping(value = "/file-remove/{fileName}")
	public String deleteFile(Authentication authentication,@PathVariable String fileName,@ModelAttribute(ModelAttributeName.FILE_NEW) FileForm newFile,	@ModelAttribute(ModelAttributeName.NOTE_NEW) NoteForm newNote, @ModelAttribute(ModelAttributeName.CREDENTIAL_NEW) CredentialForm newCredential, Model model) {
		fileService.deleteFile(fileName);

		String userName = authentication.getName();
		User user = userService.getUser(userName);
		Integer userId = user.getUserId();

		if (userId != null) {
			model.addAttribute(ModelAttributeName.FILES, fileService.getFileNameListUserId(userId));
			model.addAttribute(ModelAttributeName.RESULT, "success");

			return TemplateHtml.RESULT;
		}
		return TemplateHtml.SIGNUP;
	}
}
