package selenium.page.pattern;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogIn extends tools.selenium {
	String VALID_USER = "batel+test@simpleorder.co.il";
	String VALID_PASSWORD = "1234!@#$";

	@FindBy(name = "login_username")
	WebElement fldUserName;

	@FindBy(name = "login_password")
	WebElement fldPassWord;

	@FindBy(xpath = "//input[@value='Log In' and @class='pull-right']")
	WebElement btnLogIn;

	public LogIn() {
		PageFactory.initElements(driver, this);
	}

	public void logInWithValidUser() {
		enterValidUserAndPassWord();
		clickOnLogIn();
	}

	private void enterValidUserAndPassWord() {
		fldUserName.sendKeys(VALID_USER);
		fldPassWord.sendKeys(VALID_PASSWORD);
	}

	private void clickOnLogIn() {
		btnLogIn.click();
	}
}
