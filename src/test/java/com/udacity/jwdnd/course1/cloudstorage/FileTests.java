package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import com.udacity.jwdnd.course1.cloudstorage.seleniumPages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.seleniumPages.ResultPage;

/**
 * Test file upload and download with selenium.
 * This will require some extra research!
 *
 * FIRST IDEA
 * https://www.browserstack.com/guide/file-upload-in-selenium
 * https://www.softwaretestinghelp.com/file-upload-in-selenium/
 * https://riptutorial.com/selenium-webdriver/example/23020/basic-example
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FileTests extends CloudStorageApplicationTests {
	@LocalServerPort
	private int port;

	private final String pathAndFileToUploadWindows = "C:\\tmp\\01_login.png";

	@Test
	public void testUpload() throws InterruptedException {
		/*
		1. click the button select a file
		2. select a file
		3. click the button open (windows)
		4. click the button Upload
		5. Success > click the button OK
		 */

/*
		// ## Instantiation of driver object to launch Firefox browser
		//System.setProperty("webdriver.gecko.driver", "Path of the gecko driver");
		WebDriver driver = new ChromeDriver();

		//driver.manage().window().maximize(); // always write wait code after this OK
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS); // for page load OK
		driver.get(Path.LOCALHOST + this.port + Path.LOGIN);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // for Implicit wait OK

		JavascriptExecutor js = (JavascriptExecutor) driver; // Scrolling using JavascriptExecutor OK
		//js.executeScript("window.scrollBy(0,380)"); // Scroll Down to file upload button (+ve)
		Thread.sleep(3000);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUserName("k");
		loginPage.setPassword("k");
		loginPage.login();

		// ## FILE UPLOADING USING SENDKEYS ....
		// ## click on ‘Choose file’ to upload the desired file
		// ## <input type="file" class="form-control-file" id="buttonFileUpload" name="fileUpload" th:field="*{multipartFile}">
		WebElement browse = driver.findElement(By.xpath("//input[@id='buttonFileUpload']")); // OK
		browse.sendKeys(pathAndFileToUploadWindows); // Uploading the file using sendKeys OK

		// ## <button type="submit" class="btn btn-dark">Upload</button>
		//browse.findElement(By.id("buttonFileUpload")).click(); // WRONG
		//browse.findElement(By.xpath("//input[@id='buttonFileUpload']")).click(); // WRONG
		//driver.findElement(By.linkText("Set new profile picture")).click(); // WRONG

		//Thread.sleep(10000);
		System.out.println("File is Uploaded Successfully");
*/

		// WORKS
		HomePage homePage = signUpAndLogin();
		homePage.navToFilesTab();

		homePage.navToCredentialsTab();
		Thread.sleep(2000);

		homePage.navToFilesTab();
		Thread.sleep(2000);

		/**
		 * Need to comment by submit, because the file is not available
		 * by udacity
		 * org.openqa.selenium.InvalidArgumentException: invalid argument: File not found : C:\tmp\01_login.png
		 */
		//homePage.uploadFileInput(pathAndFileToUploadWindows);
		//homePage.uploadFileSubmit();
		//Thread.sleep(5000);

		//ResultPage resultPage = new ResultPage(driver);
		//resultPage.clickOk();

		homePage.logout();
	}
}