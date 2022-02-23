package tests;

import java.io.File;


import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import pages.AuthPage;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;
import pages.SearchResultPage;

public abstract class BasicTest {

	protected String baseUrl = "http://demo.yo-meals.com/";
	protected String email = "customer@dummyid.com";
	protected String password = "12345678a";

	protected WebDriver driver;
	protected JavascriptExecutor js;
	protected WebDriverWait waiter;
	protected SoftAssert softAssert;

	protected LocationPopupPage locationPopupPage;
	protected LoginPage loginPage;
	protected NotificationSystemPage notificationSystemPage;
	protected ProfilePage profilPage;
	protected AuthPage authPage;
	protected MealPage mealPage;
	protected CartSummaryPage cartSummaryPage;
	protected SearchResultPage searchResultPage;

	protected File data_file;
	protected FileInputStream fis;
	protected XSSFWorkbook wb;
	protected XSSFSheet sheet;

	@BeforeMethod
	public void beforeMethod() throws IOException {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		waiter = new WebDriverWait(driver, Duration.ofSeconds(20));
		js = (JavascriptExecutor) driver;
		softAssert = new SoftAssert();

		data_file = new File("data/Data.xlsx");
		fis = new FileInputStream(data_file);
		wb = new XSSFWorkbook(fis);

		locationPopupPage = new LocationPopupPage(driver, js, waiter);
		loginPage = new LoginPage(driver, js, waiter);
		notificationSystemPage = new NotificationSystemPage(driver, js, waiter);
		profilPage = new ProfilePage(driver, js, waiter);
		authPage = new AuthPage(driver, js, waiter);
		mealPage = new MealPage(driver, js, waiter);
		cartSummaryPage = new CartSummaryPage(driver, js, waiter);
		searchResultPage = new SearchResultPage(driver, js, waiter);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception {
		LocalDateTime localTime = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				TakesScreenshot screenshot = (TakesScreenshot) driver;
				File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
				String imageAddress = "./screenshots/" + localTime.format(dateFormat) + ".png";
				FileUtils.copyFile(sourceFile, new File(imageAddress));
			} catch (Exception e) {
				System.out.println("Exception while screenshotting " + e.getMessage() + "!");
			}
		}
		driver.quit();
	}
}
