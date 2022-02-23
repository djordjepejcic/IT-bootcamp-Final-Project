package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage {

	public AuthPage(WebDriver driver, JavascriptExecutor js, WebDriverWait wait) {
		super(driver, js, wait);
	}

	public WebElement getUserMenuButton() {
		return this.driver.findElement(By.xpath("//a[@class='after-arrow user-trigger-js']"));
	}

	public WebElement getLogoutButton() {
		return this.driver.findElement(By.xpath("//div[@class='my-account-dropdown']/ul/li[2]/a"));
	}

	public void logout() {
		js.executeScript("arguments[0].click();", this.getUserMenuButton());
		waiter.until(ExpectedConditions.visibilityOf(this.getLogoutButton()));
		js.executeScript("arguments[0].click();", this.getLogoutButton());
	}
}
