package selenium.page.pattern;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Order extends tools.selenium {
	private String ASUCCESSFUL_ORDER_SENT = "span[text()='הזמנה לשימוש פנימי נשמרה בהצלחה']";

	@FindBy(id = "btn_confirm_win_send")
	WebElement btnSendOrder;

	@FindBy(id = "wrapper_close")
	WebElement btnCancelOrder;

	@FindBy(id = "wrapper_close")
	WebElement lblSuccessfulOrderSent;

	public Order() {
		PageFactory.initElements(driver, this);
	}

	public void clickOnSendOrder() {
		btnSendOrder.click();
	}

	public void clickOnCancelOrder() {
		btnCancelOrder.click();
	}

	public Boolean ValidateSuccessfulSendMessage() {
		if (findElement("xpath", ASUCCESSFUL_ORDER_SENT) == null) {
			return false;
		} else {
			return true;
		}
	}
}
