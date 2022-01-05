package com.udacity.jwdnd.course1.cloudstorage.constants;

/**
 * I always try to use variable for text content or for values there are repeated. This is my style to code.
 * At time I collect this content on DB instead of class. But here I use classes too.
 * Below is an example:
 * https://github.com/PatriziaGessa/UdacityCloudStorage/blob/master/src/main/java/com/udacity/jwdnd/course1/cloudstorage/utils/Constants.java
 */
public final class Text {

	// Success File
	public static final String FILE_UPLOAD_OK = "File upload is finished.";

	// Error Signup
	public static final String SIGNUP_GENERAL_ERROR = "Please try to sign up once more. There is a problem by sign up. ";
	public static final String SIGNUP_USERNAME_ALREADY_EXISTS_ERROR = "Please try with an other username. This username is already available.";

	// Error File
	public static final String FILE_ALREADY_EXISTS_ERROR = "You have tried to add a duplicate file.";

	/**
	 * constructor private -> class can not be instantiated
	 */
	private Text() {
	}
}
