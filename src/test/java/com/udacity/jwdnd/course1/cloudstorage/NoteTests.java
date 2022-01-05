package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.seleniumPages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.seleniumPages.ResultPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test adding, editing, and deleting notes
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoteTests extends CloudStorageApplicationTests {

	private final String noteTitle = "My note";
	private final String noteDescription = "This is my note.";
	private final String modifiedNoteTitle = "My modified note";
	private final String modifiedNoteDescription = "This is my modified note.";

	// ### USED METHODS #########################

	private void createNote(String noteTitle, String noteDescription, HomePage homePage) {
		homePage.navToNotesTab();

		homePage.addNewNote();
		homePage.setNoteTitle(noteTitle);
		homePage.setNoteDescription(noteDescription);
		homePage.saveChangesNote();

		ResultPage resultPage = new ResultPage(driver);
		resultPage.clickOk();
		homePage.navToNotesTab();
	}

	private void deleteNote(HomePage homePage) {
		homePage.deleteNote();
		ResultPage resultPage = new ResultPage(driver);
		resultPage.clickOk();
	}


	// ### TESTS ################################

	/**
	 * Write a Selenium test that logs in an existing user,
	 * creates a note and verifies that the note details are visible in the note list.
	 */
	@Test
	public void testCreateAndShow() {
		HomePage homePage = signUpAndLogin();
		createNote(noteTitle, noteDescription, homePage);

		homePage = new HomePage(driver);
		Note note = homePage.getFirstNote();

		Assertions.assertEquals(noteTitle, note.getNoteTitle());
		Assertions.assertEquals(noteDescription, note.getNoteDescription());

		deleteNote(homePage);
		homePage.logout();
	}


	/**
	 * Write a Selenium test that logs in an existing user with existing notes,
	 * clicks the edit note button on an existing note, changes the note data,
	 * saves the changes, and verifies that the changes appear in the note list.
	 */
	@Test
	public void testModify() {
		HomePage homePage = signUpAndLogin();
		createNote(noteTitle, noteDescription, homePage);

		homePage = new HomePage(driver);
		homePage.editNote();
		homePage.modifyNoteTitle(modifiedNoteTitle);
		homePage.modifyNoteDescription(modifiedNoteDescription);
		homePage.saveChangesNote();

		ResultPage resultPage = new ResultPage(driver);
		resultPage.clickOk();

		homePage.navToNotesTab();
		Note modifiedNote = homePage.getFirstNote();

		Assertions.assertEquals(modifiedNoteTitle, modifiedNote.getNoteTitle());
		Assertions.assertEquals(modifiedNoteDescription, modifiedNote.getNoteDescription());

		deleteNote(homePage);
		homePage.logout();
	}


	/**
	 * Write a Selenium test that logs in an existing user with existing notes,
	 * clicks the delete note button on an existing note,
	 * and verifies that the note no longer appears in the note list.
	 */
	@Test
	public void testDelete() {
		// from class CloudStorageApplicationTests
		HomePage homePage = signUpAndLogin();
		createNote(noteTitle, noteDescription, homePage);

		homePage = new HomePage(driver);
		Assertions.assertFalse(homePage.noNotes(driver));

		deleteNote(homePage);
		Assertions.assertTrue(homePage.noNotes(driver));

		homePage.logout();
	}
}
