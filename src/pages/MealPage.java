package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, JavascriptExecutor js, WebDriverWait wait) {
		super(driver, js, wait);
	}

	public WebElement getQuantityInput() {
		return this.driver.findElement(By.name("product_qty"));
	}

	public WebElement getAddToCartButton() {
		return this.driver
				.findElement(By.xpath("//a[(@class = 'btn btn--primary btn--large js-proceedtoAddInCart ')]"));
	}

	public WebElement getFavoriteButton() {
		return this.driver.findElement(By.className("favourite"));
	}

	public void addToCart(int numberOfProduct) throws InterruptedException {
		this.getQuantityInput().sendKeys(Keys.chord(Keys.CONTROL, "a"));
		String numberOfProducts = String.format("%d", numberOfProduct);
		this.getQuantityInput().sendKeys(numberOfProducts);
		this.getAddToCartButton().click();
	}

	public void addToFavourites() {
		js.executeScript("arguments[0].click();", getFavoriteButton());
	}

}
