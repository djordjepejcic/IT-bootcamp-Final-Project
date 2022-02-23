package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, JavascriptExecutor js, WebDriverWait wait) {
		super(driver, js, wait);
	}

	public WebElement getFirstName() {
		return this.driver.findElement(By.name("user_first_name"));
	}

	public WebElement getLastName() {
		return this.driver.findElement(By.name("user_last_name"));
	}

	public WebElement getEmail() {
		return this.driver.findElement(By.name("user_email"));
	}

	public WebElement getAddress() {
		return this.driver.findElement(By.name("user_address"));
	}

	public WebElement getPhone() {
		return this.driver.findElement(By.name("user_phone"));
	}

	public WebElement getZipCode() {
		return this.driver.findElement(By.name("user_zip"));
	}

	public Select selectCountry() {
		Select select = new Select(this.driver.findElement(By.id("user_country_id")));
		return select;
	}

	public Select selectState() {
		Select select = new Select(this.driver.findElement(By.id("user_state_id")));
		return select;
	}

	public Select selectCity() {
		Select select = new Select(this.driver.findElement(By.id("user_city")));
		return select;
	}

	public WebElement getSaveButton() {
		return this.driver.findElement(By.xpath(
				"//div[@class='col-lg-12 col-md-12 col-sm-12 col-lg-12 align--right']//input[@name='btn_submit']"));
	}

	public WebElement getUploadPictureButton() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfo']/div/div[1]/div/a[1]"));
	}

	public WebElement getRemovePictureButton() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfo']/div/div[1]/div/a[2]"));
	}

	public void uploadPicture(String imagePath) {
		WebElement hoveringProfilePicture = this.driver.findElement(By.xpath("//*[@id='profileInfo']/div/div[1]/img"));
		js.executeScript("arguments[0].scrollIntoView();", hoveringProfilePicture);

		Actions actions = new Actions(this.driver);
		actions.moveToElement(hoveringProfilePicture);

		waiter.until(ExpectedConditions.elementToBeClickable(this.getUploadPictureButton()));

		js.executeScript("arguments[0].click();", this.getUploadPictureButton());
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(imagePath);
	}

	public void removePicture() {
		WebElement hoveringProfilePicture = this.driver.findElement(By.xpath("//*[@id='profileInfo']/div/div[1]/img"));
		js.executeScript("arguments[0].scrollIntoView();", hoveringProfilePicture);

		Actions actions = new Actions(driver);
		actions.moveToElement(hoveringProfilePicture);

		waiter.until(ExpectedConditions.elementToBeClickable(this.getRemovePictureButton()));

		js.executeScript("arguments[0].click();", this.getRemovePictureButton());
	}

	public void changeAllInformation(String firstName, String lastName, String address, String phone, String zipCode,
			String country, String state, String city) {
		this.getFirstName().clear();
		this.getFirstName().sendKeys(firstName);

		this.getLastName().clear();
		this.getLastName().sendKeys(lastName);

		this.getAddress().clear();
		this.getAddress().sendKeys(address);

		this.getPhone().clear();
		this.getPhone().sendKeys(phone);

		this.getZipCode().clear();
		this.getZipCode().sendKeys(zipCode);

		this.selectCountry().selectByVisibleText(country);
		this.selectState().selectByVisibleText(state);
		this.selectCity().selectByVisibleText(city);

		js.executeScript("arguments[0].click();", this.getSaveButton());
	}
}
