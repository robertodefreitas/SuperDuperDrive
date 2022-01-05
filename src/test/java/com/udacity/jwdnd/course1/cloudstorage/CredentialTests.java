package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.seleniumPages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.seleniumPages.ResultPage;

/**
 * Test adding, editing and deleting credentials
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CredentialTests extends CloudStorageApplicationTests {

	private final String urlHttps_A = "https://www.aaa.de";
	private final String username_A = "aaa";
	private final String password_A = "aaaPW";
	private final String urlHttps_B = "https://www.bbb.de";
	private final String username_B = "bbb";
	private final String password_B = "bbbPW";

	// ### USED METHODS #########################

	private void createCredential(String url, String username, String password, HomePage homePage) {
		homePage.navToCredentialsTab();
		homePage.addNewCred();

		setCredentialFields(url, username, password, homePage);
		homePage.saveChangesCredential();

		ResultPage resultPage = new ResultPage(driver);
		resultPage.clickOk();
		homePage.navToCredentialsTab();
	}

	private void setCredentialFields(String url, String username, String password, HomePage homePage) {
		homePage.setCredentialUrl(url);
		homePage.setCredentialUsername(username);
		homePage.setCredentialPassword(password);
	}



	// ### TESTS ################################

	/**
	 * Write a Selenium test that logs in an existing user,
	 * creates a credential and verifies that the credential details
	 * are visible in the credential list.
	 * FYI: Compare the original password with the encrypted
	 */

	@Test
	public void testCreateAndShow() {
		HomePage homePage = signUpAndLogin();
		createCredential(urlHttps_A, username_A, password_A, homePage);
		Credential credential = homePage.getFirstCredential();

		Assertions.assertEquals(urlHttps_A, credential.getUrl());
		Assertions.assertEquals(username_A, credential.getUsername());
		Assertions.assertNotEquals(password_A, credential.getPassword());

		homePage.deleteCredential();
		ResultPage resultPage = new ResultPage(driver);
		resultPage.clickOk();

		homePage.logout();
	}


	/**
	 * Write a Selenium test that logs in an existing user with existing credentials,
	 * clicks the edit credential button on an existing credential, changes the credential data,
	 * saves the changes, and verifies that the changes appear in the credential list.
	 */

	@Test
	public void testModify() {
		HomePage homePage = signUpAndLogin();
		createCredential(urlHttps_A, username_A, password_A, homePage);

		homePage.editCredential();
		setCredentialFields(urlHttps_B, username_B, password_B, homePage);
		homePage.saveChangesCredential();

		ResultPage resultPage = new ResultPage(driver);
		resultPage.clickOk();

		homePage.navToCredentialsTab();

		Credential modifiedCredential = homePage.getFirstCredential();

		Assertions.assertEquals(urlHttps_B, modifiedCredential.getUrl());
		Assertions.assertEquals(username_B, modifiedCredential.getUsername());
		Assertions.assertNotEquals(password_B, modifiedCredential.getPassword());

		homePage.deleteCredential();
		resultPage.clickOk();

		homePage.logout();
	}


	/**
	 * Write a Selenium test that logs in an existing user with existing credentials,
	 * clicks the delete credential button on an existing credential, and verifies
	 * that the credential no longer appears in the credential list.
	 */

	@Test
	public void testDelete(){
		HomePage homePage = signUpAndLogin();
		createCredential(urlHttps_A, username_A, password_A, homePage);
		createCredential(urlHttps_B, username_B, password_B, homePage);

		Assertions.assertFalse(homePage.noCredentials(driver));

		// delete *_A
		homePage.deleteCredential();
		ResultPage resultPage = new ResultPage(driver);
		resultPage.clickOk();

		homePage.navToCredentialsTab();
		Assertions.assertFalse(homePage.noCredentials(driver));

		// delete *_B
		homePage.deleteCredential();
		resultPage.clickOk();

		homePage.navToCredentialsTab();
		Assertions.assertTrue(homePage.noCredentials(driver));

		homePage.logout();
	}
}