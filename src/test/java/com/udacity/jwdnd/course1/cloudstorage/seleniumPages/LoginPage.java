package com.udacity.jwdnd.course1.cloudstorage.seleniumPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(css="#inputUsername")
    private WebElement usernameField;

    @FindBy(css="#inputPassword")
    private WebElement passwordField;

    @FindBy(css="#submit-button")
    private WebElement submitButton;


    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setUserName(String userName){
        this.usernameField.sendKeys(userName);
    }

    public void setPassword(String password){
        this.passwordField.sendKeys(password);
    }

    public void login(){
        this.submitButton.click();
    }
}
