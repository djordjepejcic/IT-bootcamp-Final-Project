package tests;

import org.testng.annotations.Test;
import java.io.IOException;

public class SearchTest extends BasicTest {

	@Test
	public void searchResults() throws IOException {
		driver.navigate().to(super.baseUrl + "meals");
		locationPopupPage.setTheLocation("City Center - Albany");

		driver.navigate().refresh();

		sheet = wb.getSheet("Meal Search Results");

		for (int i = 1; i < 7; i++) {
			String url = sheet.getRow(i).getCell(1).getStringCellValue();
			String location = sheet.getRow(i).getCell(0).getStringCellValue();

			driver.navigate().to(url);

			locationPopupPage.openLocationDialog();
			locationPopupPage.setTheLocation(location);
			driver.navigate().refresh();

			int numberOfResults = (int) sheet.getRow(i).getCell(2).getNumericCellValue();
			int pageResultNumber = searchResultPage.getNumberOfResults();
			softAssert.assertTrue(pageResultNumber == numberOfResults,
					"[ERROR] Unexpected message for number of search results!");

			for (int j = 0; j < numberOfResults; j++) {
				String nameOfMeal = sheet.getRow(i).getCell(j + 3).getStringCellValue();
				String[] meal = searchResultPage.getItemNames();
				softAssert.assertTrue(meal[j].contains(nameOfMeal), "[ERROR] Results don't match!");
			}
			softAssert.assertAll();
			driver.navigate().refresh();
		}
	}
}
