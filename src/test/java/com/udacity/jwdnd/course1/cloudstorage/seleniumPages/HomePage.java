package com.udacity.jwdnd.course1.cloudstorage.seleniumPages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * ISSUE: this.[NAME].click();
 * -> org.openqa.selenium.ElementNotInteractableException: element not interactable
 * WORKAROUND
 * use JavascriptExecutor AND WebDriverWait
 * SOURCE for the solution/workaround with
 * https://stackoverflow.com/questions/49864965/org-openqa-selenium-elementnotinteractableexception-element-is-not-reachable-by

 */
public class HomePage {

    @FindBy(id="buttonLogout")
    private WebElement logoutButton;


    // TAB NAVIGATION ---------------------------
    // <div class="nav nav-tabs" id="nav-tab" role="tablist">
    @FindBy(id="nav-files-tab")
    private WebElement navFilesTab;

    @FindBy(id="nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id="nav-credentials-tab")
    private WebElement navCredentialsTab;


    // FILE -------------------------------------

    // ## <input type="file" class="form-control-file" id="buttonFileUpload" name="fileUpload" th:field="*{multipartFile}">
    @FindBy(xpath="//input[@id='buttonFileUpload']")
    private WebElement fileUploadInputButton;

    // ## <button type="submit" class="btn btn-dark">Upload</button>
    @FindBy(id="fileSubmit")
    private WebElement fileUploadSubmitButton;

	// TO-DO


    // NOTE -------------------------------------

    @FindBy(id="buttonAddNewNote")
    private WebElement addNewNoteButton;

    @FindBy(id="buttonEditNote")
    private WebElement editNoteButton;

    @FindBy(id="buttonSaveChangesNote")
    private WebElement saveChangesNoteButton;

    @FindBy(id="buttonDeleteNote")
    private WebElement deleteNoteButton;


    @FindBy(id="note-title")
    private WebElement txtNoteTitle;

    @FindBy(id="note-description")
    private WebElement txtModifyNoteDescription;

    @FindBy(id="note-description")
    private WebElement txtNoteDescription;


    @FindBy(id="showNoteTitle")
    private WebElement showNoteTitle;

    @FindBy(id="showNoteDescription")
    private WebElement showNoteDescription;


    // CREDENTIAL -------------------------------

    @FindBy(id="buttonAddNewCred")
    private WebElement addNewCredButton;

    @FindBy(id="buttonEditCredential")
    private WebElement editCredentialButton;

    @FindBy(id="buttonSaveChangesCredential")
    private WebElement saveChangesCredentialButton;

    @FindBy(id="buttonDeleteCredential")
    private WebElement deleteCredentialButton;


    @FindBy(id="credential-url")
    private WebElement txtCredentialUrl;

    @FindBy(id="credential-username")
    private WebElement txtCredentialUsername;

    @FindBy(id="credential-password")
    private WebElement txtCredentialPassword;


    @FindBy(id="showCredentialUrl")
    private WebElement tblCredentialUrl;

    @FindBy(id="showCredentialUsername")
    private WebElement tblCredentialUsername;

    @FindBy(id="showCredentialPassword")
    private WebElement tblCredentialPassword;


    // ==========================================

    // needed because of issue with this.<VAR>.click()
    // org.openqa.selenium.ElementNotInteractableException: element not interactable
    private JavascriptExecutor js;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
        // Used in Lesson 6 - Chapter 18
        // Explicit Wait
        wait = new WebDriverWait(driver, 600);
    }



    // TAB NAVIGATION ---------------------------

    public void logout() {
        this.logoutButton.click();
    }

    public void navToFilesTab() {
        this.navFilesTab.click();
    }

    public void navToNotesTab() {
        this.navNotesTab.click();
    }

    public void navToCredentialsTab() {
        this.navCredentialsTab.click();
    }



    // FILE -------------------------------------

	public void uploadFileInput(String pathAndFilenameToUpload) throws InterruptedException {
		//js.executeScript("arguments[0].click();", fileUploadInputButton);
        //Thread.sleep(2000);
        // to close the window after executeScript....click()
        //keyClick(KeyEvent.VK_ESCAPE);
        //Thread.sleep(30000);
		this.fileUploadInputButton.sendKeys(pathAndFilenameToUpload);
        Thread.sleep(5000);
	}

    public void uploadFileSubmit() {
        //this.fileUploadButton.click(); // WRONG
        js.executeScript("arguments[0].click();", fileUploadSubmitButton);
    }

    // TO-DO


    // NOTE -------------------------------------

    public void addNewNote() {
        //this.addNewNoteButton.click(); // WRONG
        //this.addNewNoteButton.sendKeys(Keys.RETURN); // WRONG
        js.executeScript("arguments[0].click();", addNewNoteButton);
    }

    public void editNote() {
        //this.editNoteButton.click(); // WRONG
        js.executeScript("arguments[0].click();", editNoteButton);
    }

    public void saveChangesNote() {
        //this.saveChangesNoteButton.click(); // WRONG
        js.executeScript("arguments[0].click();", saveChangesNoteButton);
    }

    public void deleteNote() {
        //this.deleteNoteButton.click(); // WRONG
        js.executeScript("arguments[0].click();", deleteNoteButton);
    }


    public void setNoteTitle(String noteTitle) {
        //this.txtNoteTitle.sendKeys(noteTitle); // WRONG
        js.executeScript("arguments[0].value='" + noteTitle + "';", txtNoteTitle);
    }

    public void setNoteDescription(String noteDescription) {
        //this.txtNoteDescription.sendKeys(noteDescription); // WRONG
        js.executeScript("arguments[0].value='"+ noteDescription +"';", txtNoteDescription);
    }


    public void modifyNoteTitle(String newNoteTitle) {
        // Used in Lesson 6 - Chapter 18
        // Explicit Wait
        // SOURCE for elementToBeClickable
        // https://www.browserstack.com/guide/expectedconditions-in-selenium
        // https://stackoverflow.com/questions/38673465/in-selenium-webdriver-expectedcondition-elementtobeclickable-is-not-waiting-unt
        wait.until(ExpectedConditions.elementToBeClickable(txtNoteTitle)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(txtNoteTitle)).sendKeys(newNoteTitle);
    }

    public void modifyNoteDescription(String newNoteDescription) {
        wait.until(ExpectedConditions.elementToBeClickable(txtModifyNoteDescription)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(txtModifyNoteDescription)).sendKeys(newNoteDescription);
    }


    public Note getFirstNote() {
        String title = wait.until(ExpectedConditions.elementToBeClickable(showNoteTitle)).getText();
        String description = showNoteDescription.getText();

        return new Note(title, description);
    }

    public boolean noNotes(WebDriver driver) {
        return !isElementPresent(By.id("showNoteTitle"), driver) && !isElementPresent(By.id(
                "showNoteDescription"), driver);
    }



    // CREDENTIALS ------------------------------

    public void addNewCred() {
        js.executeScript("arguments[0].click();", addNewCredButton);
    }

    public void editCredential() {
        js.executeScript("arguments[0].click();", editCredentialButton);
    }

    public void saveChangesCredential() {
        js.executeScript("arguments[0].click();", saveChangesCredentialButton);
    }

    public void deleteCredential() {
        js.executeScript("arguments[0].click();", deleteCredentialButton);
    }


    public void setCredentialUrl(String url) {
        js.executeScript("arguments[0].value='" + url + "';", txtCredentialUrl);
    }

    public void setCredentialUsername(String username) {
        js.executeScript("arguments[0].value='" + username + "';", txtCredentialUsername);
    }

    public void setCredentialPassword(String password) {
        js.executeScript("arguments[0].value='" + password + "';", txtCredentialPassword);
    }


    public Credential getFirstCredential() {
        String url = wait.until(ExpectedConditions.elementToBeClickable(tblCredentialUrl)).getText();
        String username = tblCredentialUsername.getText();
        String password = tblCredentialPassword.getText();

        return new Credential(url, username, password);
    }

    public boolean noCredentials(WebDriver driver) {
        return !isElementPresent(By.id("showCredentialUrl"), driver) && !isElementPresent(By.id("showCredentialUsername"), driver) && !isElementPresent(By.id("showCredentialPassword"), driver);
    }

    // UTILS ------------------------------------

    // used by noNotes
    public boolean isElementPresent(By locatorKey, WebDriver driver) {
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    // https://stackoverflow.com/questions/33253805/how-to-close-windows-file-upload-window-when-using-selenium
    public void keyClick(Integer keyEvent) {
        // KeyEvent.VK_ESCAPE
        try {
            Robot robot = new Robot();
            robot.keyPress(keyEvent);
            robot.keyRelease(keyEvent);
        } catch (AWTException ae) {
            ae.printStackTrace();
        }
    }
}