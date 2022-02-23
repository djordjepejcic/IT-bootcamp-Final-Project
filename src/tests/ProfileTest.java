package tests;

import java.io.File;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	@Test(priority = 1)
	public void editProfile() throws InterruptedException {
		driver.navigate().to(super.baseUrl + "guest-user/login-form");

		locationPopupPage.exitDialog();

		loginPage.logIn(super.email, super.password);
		Assert.assertTrue(notificationSystemPage.messageText().contains("Login Successfull"),
				"ERROR: Unexpected login message!");
		notificationSystemPage.waitMessageToDesapear();

		driver.navigate().to(super.baseUrl + "member/profile");
		profilPage.changeAllInformation("Djordje", "Pejcic", "Dane's lane", "01525245784", "27500", "United States",
				"Massachusetts", "Orleans");
		Assert.assertTrue(notificationSystemPage.messageText().contains("Setup Successful"),
				"ERROR: Unexpected registration message!");
		notificationSystemPage.waitMessageToDesapear();

		authPage.logout();
		Assert.assertTrue(notificationSystemPage.messageText().contains("Logout Successfull!"),
				"ERROR: Unexpected logout message!");
		notificationSystemPage.waitMessageToDesapear();
	}

	@Test(priority = 2)
	public void changeProfileImage() throws InterruptedException, IOException {
		driver.navigate().to(super.baseUrl + "guest-user/login-form");

		locationPopupPage.exitDialog();

		loginPage.logIn(super.email, super.password);
		Assert.assertTrue(notificationSystemPage.messageText().contains("Login Successfull"),
				"ERROR: Unexpected login message!");
		notificationSystemPage.waitMessageToDesapear();

		driver.navigate().to(super.baseUrl + "member/profile");

		String imgPath = new File("img/profile_picture.png").getCanonicalPath();
		profilPage.uploadPicture(imgPath);
		Assert.assertTrue(notificationSystemPage.messageText().contains("Profile Image Uploaded Successfully"),
				"ERROR: Picture upload problem!");
		notificationSystemPage.waitMessageToDesapear();

		profilPage.removePicture();
		Assert.assertTrue(notificationSystemPage.messageText().contains("Profile Image Deleted Successfully"),
				"ERROR: Picture removal problem!");
		notificationSystemPage.waitMessageToDesapear();

		authPage.logout();
		Assert.assertTrue(notificationSystemPage.messageText().contains("Logout Successfull!"),
				"ERROR: Unexpected logout message!");
		notificationSystemPage.waitMessageToDesapear();
	}
}
