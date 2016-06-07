package selenium.page.tests;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import selenium.page.pattern.FillOrder;
import selenium.page.pattern.HomePage;
import selenium.page.pattern.LogIn;
import selenium.page.pattern.Dashboard;
import selenium.page.pattern.Order;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SOTest extends tools.selenium {
	String URL = "http://sandbox.simpleorder.com";

	@BeforeClass
	public static void setUp() {
		setDriver();
	}

	@AfterClass
	public static void tearDown() {
		killDriver();
	}

	@Test
	public void letsTest() {
		// load HP
		get(URL);
		// HP
		HomePage hp = new HomePage();
		hp.clickOnLogIn();
		// Login
		LogIn logIn = new LogIn();
		logIn.logInWithValidUser();
		// dashboard
		Dashboard dashBoard = new Dashboard();
		dashBoard.clickOnNewOrder();
		// fill order
		FillOrder order = new FillOrder();
		order.fillInOrder(10);
		Assert.assertTrue(order.validateItemsOrderUnderReservation());
		order.clickOnSend();
		Assert.assertTrue(order.validateEmailBeforeSend());
		// cancel the send order and validate nothing had changed
		Order orderSend = new Order();
		orderSend.clickOnCancelOrder();
		Assert.assertTrue(order.validateItemsOrderUnderReservation());
		order.clickOnSend();
		Assert.assertTrue(order.validateEmailBeforeSend());
		// send the order and validate that it had been sent
		orderSend.clickOnSendOrder();
		Assert.assertTrue(orderSend.ValidateSuccessfulSendMessage());
	}
}