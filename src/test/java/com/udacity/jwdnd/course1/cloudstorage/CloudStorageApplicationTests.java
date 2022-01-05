package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import com.udacity.jwdnd.course1.cloudstorage.constants.Path;
import com.udacity.jwdnd.course1.cloudstorage.seleniumPages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.seleniumPages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.seleniumPages.SignupPage;

/**
 * Main Test File for NoteTests and CredentialTests via extends+protected
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	//private String baseURL = Path.LOCALHOST;

	// private -> protected
	// because this class is used as 'extends'
	// by other CredentialTests and NoteTests
	protected WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() throws InterruptedException {
		// it needs "throws InterruptedException"
		// unit: millis, also 1000 millis = 1s)
		Thread.sleep(5000);

		if (this.driver != null) {
			driver.quit();
		}
	}

//	@AfterAll
//	public void afterAll() {
//		driver.quit();
//		driver = null;
//	}

	/**
	 * Simple Test to show login.html
	 */
	@Test
	public void getLoginPage() {
		driver.get(Path.LOCALHOST + this.port + Path.LOGIN);

		// https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/remote/RemoteWebDriver.html#getTitle()
		Assertions.assertEquals("Login", driver.getTitle());
	}

	// protected because this class is used as 'extends'
	// by CredentialTests and NoteTests
	protected HomePage signUpAndLogin() {
		driver.get(Path.LOCALHOST + this.port + Path.SIGNUP);
		SignupPage signupPage = new SignupPage(driver);
		signupPage.setFirstName("AAAFN");
		signupPage.setLastName("AAALN");
		signupPage.setUserName("aaa");
		signupPage.setPassword("aaapw");
		signupPage.signUp();

		driver.get(Path.LOCALHOST + this.port + Path.LOGIN);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUserName("aaa");
		loginPage.setPassword("aaapw");
		loginPage.login();

		return new HomePage(driver);
	}
}
