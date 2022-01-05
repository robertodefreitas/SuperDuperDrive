package com.udacity.jwdnd.course1.cloudstorage.seleniumPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    @FindBy(id="inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id="inputLastName")
    private WebElement inputLastName;

    @FindBy(id="inputUsername")
    private WebElement inputUserName;

    @FindBy(id="inputPassword")
    private WebElement inputPassword;

    @FindBy(id="submit-button")
    private WebElement submitButton;


    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setFirstName(String firstName) {
        this.inputFirstName.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        this.inputLastName.sendKeys(lastName);
    }

    public void setUserName(String userName) {
        this.inputUserName.sendKeys(userName);
    }

    public void setPassword(String password) {
        this.inputPassword.sendKeys(password);
    }

    public void signUp() {
        this.submitButton.click();
    }
}
