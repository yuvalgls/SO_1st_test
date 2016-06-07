package selenium.page.pattern;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FillOrder extends tools.selenium {
	private List<String> ITEMS_LIST = new ArrayList<String>();
	private String RESERVATION_ITEMS_NAMES = "//div[@id='order_body']/ul/li/div[@class='item_name']";
	private String EMAIL_ITEMS_NAMES = "//table[@id='oh_products']/tbody/tr/td[2]";
	private String RESERVATION_ITEMS_MINUS = "//ul[@id='request_items']/li/div/input[@class[contains(.,'quantity_minus')]]";
	private String ORDER_BODY = "cfrm_body";

	@FindBy(id = "send_button")
	WebElement btnSendOrder;

	public FillOrder() {
		PageFactory.initElements(driver, this);
	}

	private void waitForOrderConfermation() {
		waitforElement("id", ORDER_BODY);
	}

	public void fillInOrder(int numOfItems) {
		cleanOrder();
		WebElement element;
		for (int i = 0; i < numOfItems; i++) {
			element = findElement("xpath",
					"(//li[@id[contains(.,'product')]])[" + (i + 1)
							+ "]/div/div");
			ITEMS_LIST.add(element.getText().toString());
			element = findElement("xpath",
					"(//li[@id[contains(.,'product')]])[" + (i + 1)
							+ "]/div/input[@class='quantity_plus']");
			element.click();
			tools.time.sleep(100);
		}
	}

	public Boolean validateItemsOrderUnderReservation() {
		List<WebElement> reservationItems = new ArrayList<WebElement>();
		reservationItems = driver.findElements(By
				.xpath(RESERVATION_ITEMS_NAMES));
		if (reservationItems.size() != ITEMS_LIST.size()) {
			return false;
		}
		for (int i = 0; i < reservationItems.size(); i++) {
			if (!(reservationItems.get(i).getText().toString())
					.equals(ITEMS_LIST.get(i))) {
				return false;
			}
		}
		return true;
	}

	private void cleanOrder() {
		List<WebElement> reservationItemsMimus = new ArrayList<WebElement>();
		reservationItemsMimus = findelements("xpath", (RESERVATION_ITEMS_MINUS));
		if (reservationItemsMimus != null && reservationItemsMimus.size() > 0) {
			for (int i = 0; i < reservationItemsMimus.size(); i++) {
				reservationItemsMimus.get(i).click();
			}
			tools.time.sleep(500);
			cleanOrder();
		}
	}

	public void clickOnSend() {
		btnSendOrder.click();
	}

	public Boolean validateEmailBeforeSend() {
		waitForOrderConfermation();
		List<WebElement> emailItems = new ArrayList<WebElement>();
		emailItems = driver.findElements(By.xpath(EMAIL_ITEMS_NAMES));
		if (emailItems.size() != ITEMS_LIST.size()) {
			return false;
		}
		for (int i = 0; i < emailItems.size(); i++) {

			if (!(emailItems.get(i).getText().toString()).equals(ITEMS_LIST
					.get(i))) {
				System.out.println("Error: \nexpected - " + ITEMS_LIST.get(i)
						+ "\nactual - "
						+ emailItems.get(i).getText().toString() + "\non spot "
						+ (i + 1));
				return false;
			}
		}
		return true;
	}
}
