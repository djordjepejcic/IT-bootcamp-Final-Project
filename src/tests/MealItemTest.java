package tests;

import org.testng.annotations.Test;

import java.io.IOException;

import org.testng.Assert;

public class MealItemTest extends BasicTest {

	@Test(priority = 1)
	public void addMeal() throws InterruptedException {
		driver.navigate().to(super.baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");

		locationPopupPage.exitDialog();

		mealPage.addToCart(5);

		softAssert.assertTrue(notificationSystemPage.messageText().contains("The Following Errors Occurred:"),
				"ERROR: Unexpected message appears!");
		softAssert.assertTrue(notificationSystemPage.messageText().contains("Please Select Location"),
				"ERROR: Unexpected location message appears!");
		softAssert.assertAll();
		notificationSystemPage.waitMessageToDesapear();

		locationPopupPage.openLocationDialog();
		locationPopupPage.setTheLocation("City Center - Albany");
		driver.navigate().refresh();

		mealPage.addToCart(5);
		Assert.assertTrue(notificationSystemPage.messageText().contains("Meal Added To Cart"),
				"ERROR: Unexpected add meal message!");
		notificationSystemPage.waitMessageToDesapear();
	}

	@Test(priority = 2)
	public void addMealToFavourites() throws InterruptedException {
		driver.navigate().to(super.baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");

		locationPopupPage.exitDialog();

		mealPage.addToFavourites();
		Assert.assertTrue(notificationSystemPage.messageText().contains("Please login first!"),
				"ERROR: Unexpected login message!");
		notificationSystemPage.waitMessageToDesapear();

		driver.navigate().to(super.baseUrl + "guest-user/login-form");
		loginPage.logIn(super.email, super.password);
		Assert.assertTrue(notificationSystemPage.messageText().contains("Login Successfull"),
				"ERROR: Unexpected login message!");
		notificationSystemPage.waitMessageToDesapear();

		driver.navigate().to(super.baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		mealPage.addToFavourites();
		Assert.assertTrue(notificationSystemPage.messageText().contains("Product has been added to your favorites."),
				"ERROR: Unexpected add to cart message!");
		notificationSystemPage.waitMessageToDesapear();

		mealPage.addToFavourites();
		notificationSystemPage.waitMessageToDesapear();
	}

	@Test(priority = 3)
	public void clearCart() throws IOException, InterruptedException {
		driver.navigate().to(super.baseUrl + "meals");
		locationPopupPage.setTheLocation("City Center - Albany");

		driver.navigate().refresh();

		sheet = wb.getSheet("Meals");

		for (int i = 1; i < 6; i++) {
			String url = sheet.getRow(i).getCell(0).getStringCellValue();
			driver.navigate().to(url);
			mealPage.addToCart(2);
			softAssert.assertTrue(notificationSystemPage.messageText().contains("Meal Added To Cart"),
					"ERROR: Unexpected add meal message appears!");
			notificationSystemPage.waitMessageToDesapear();
		}
		softAssert.assertAll();

		cartSummaryPage.clearAllItems();
		Assert.assertTrue(notificationSystemPage.messageText().contains("All meals removed from Cart successfully"),
				"ERROR: Unexpected remove all from cart message!");
		notificationSystemPage.waitMessageToDesapear();
	}
}
