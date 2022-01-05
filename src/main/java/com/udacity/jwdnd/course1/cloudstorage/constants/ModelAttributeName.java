package com.udacity.jwdnd.course1.cloudstorage.constants;

public final class ModelAttributeName {
	// used by @RequestMapping(...) without SLASH
	public static final String FILE = "file";
	public static final String NOTE = "note";
	public static final String CREDENTIAL = "credential";

	public static final String FILES = "files";
	public static final String NOTES = "notes";
	public static final String CREDENTIALS = "credentials";
	public static final String ENCRY_SERVICE = "encryptionService";
	public static final String RESULT = "result";
	public static final String MESSAGE = "message";

	public static final String FILE_NEW = "newFile";
	public static final String NOTE_NEW = "newNote";
	public static final String CREDENTIAL_NEW = "newCredential";

	/**
	 * constructor private -> class can not be instantiated
	 */
	private ModelAttributeName() {
	}
}
