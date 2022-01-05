package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.concurrent.TimeUnit;

import com.udacity.jwdnd.course1.cloudstorage.constants.Path;
import com.udacity.jwdnd.course1.cloudstorage.seleniumPages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.seleniumPages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.seleniumPages.SignupPage;

/**
 * Test signup and login flow
 * Signup, Login, and restricted Access
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserTests {

	@LocalServerPort
	private int port;

	//private String baseURL = Path.LOCALHOST;

	private WebDriver driver;

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
	 * Write a Selenium test that verifies that the home page
	 * is not accessible without logging in.
	 */
	@Test
	public void testPageAccess() {
		driver.get(Path.LOCALHOST + this.port + Path.LOGIN);
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get(Path.LOCALHOST + this.port + Path.SIGNUP);
		Assertions.assertEquals("Sign Up", driver.getTitle());

		// WebSecurityConfigurerAdapter redirect /home to /login
		// because the access is not possible without login
		driver.get(Path.LOCALHOST + this.port + Path.HOME);
		Assertions.assertEquals("Login", driver.getTitle());
	}

	/**
	 * Write a Selenium test that signs up a new user, logs that user in,
	 * verifies that they can access the home page,
	 * then logs out and verifies that the home page is no longer accessible.
	 */
	@Test
	public void testSignUpLoginLogout() throws InterruptedException {
		driver.get(Path.LOCALHOST + this.port + Path.SIGNUP);
		Assertions.assertEquals("Sign Up", driver.getTitle());

		SignupPage signupPage = new SignupPage(driver);
		signupPage.setFirstName("AAAFN");
		signupPage.setLastName("AAALN");
		signupPage.setUserName("aaa");
		signupPage.setPassword("aaapw");
		Thread.sleep(5000);
		signupPage.signUp();

		// used to check the values on DB H2
		// http://localhost:[RANDOM_PORT]/h2-console
		Thread.sleep(5000);

		driver.get(Path.LOCALHOST + this.port + Path.LOGIN);
		Assertions.assertEquals("Login", driver.getTitle());

		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUserName("aaa");
		loginPage.setPassword("aaapw");
		Thread.sleep(5000);
		loginPage.login();

		HomePage homePage = new HomePage(driver);
		homePage.logout();

		driver.get(Path.LOCALHOST + this.port + Path.HOME);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assertions.assertEquals("Login", driver.getTitle());
	}
}
