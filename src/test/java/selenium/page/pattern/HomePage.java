package selenium.page.pattern;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends tools.selenium{

	@FindBy(xpath = "//a[@class='lnk_login']")
	WebElement btnLogIn;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public void clickOnLogIn() {
		this.btnLogIn.click();
	}
}
