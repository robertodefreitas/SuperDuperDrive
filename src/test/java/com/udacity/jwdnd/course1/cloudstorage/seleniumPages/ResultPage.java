package com.udacity.jwdnd.course1.cloudstorage.seleniumPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="aResultSuccess")
    private WebElement aResultSuccess;

    public void clickOk() {
        this.aResultSuccess.click();
    }
}
