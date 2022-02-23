package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartSummaryPage extends BasicPage {

	public CartSummaryPage(WebDriver driver, JavascriptExecutor js, WebDriverWait wait) {
		super(driver, js, wait);
	}

	public WebElement getClearAllButton() {
		return this.driver.findElement(By.partialLinkText("Clear All"));
	}

	public void clearAllItems() {
		js.executeScript("arguments[0].click();", this.getClearAllButton());
	}

}
