package selenium.page.pattern;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Dashboard extends tools.selenium{
	@FindBy(xpath = "//div[@id='wrapper']/ul/li/a[@href='/orders/']")
	WebElement btnNewOrder;

	public Dashboard() {
		PageFactory.initElements(driver, this);
	}

	public void clickOnNewOrder() {
		btnNewOrder.click();
	}
}
