package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends BasicPage {

	public SearchResultPage(WebDriver driver, JavascriptExecutor js, WebDriverWait wait) {
		super(driver, js, wait);
	}

	public List<WebElement> getAllResults() {
		return this.driver.findElements(By.xpath("//*[@class='product-name']/a"));
	}

	public String[] getItemNames() {
		String[] itemNames = new String[this.getAllResults().size()];
		for (int i = 0; i < this.getAllResults().size(); i++) {
			itemNames[i] = this.getAllResults().get(i).getText();
		}
		return itemNames;
	}

	public int getNumberOfResults() {
		return this.getAllResults().size();
	}

}
