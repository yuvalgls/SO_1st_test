package tools;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class selenium {
	protected static WebDriver driver;
	WebElement element;

	public static void setDriver() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public static void killDriver() {
		driver.quit();
	}

	public void get(String url) {
		driver.get(url);
	}

	public List<WebElement> findelements(String by, String path) {
		try {
			switch (by) {
			case "id":
				return driver.findElements(By.id(path));
			case "name":
				return driver.findElements(By.name(path));
			default:
				return driver.findElements(By.xpath(path));
			}
		} catch (Exception e) {
			return null;
		}
	}

	public WebElement findElement(String by, String path) {
		try {
			waitforElement(by, path);
			switch (by) {
			case "id":
				return driver.findElement(By.id(path));
			case "name":
				return driver.findElement(By.name(path));
			default:
				return driver.findElement(By.xpath(path));
			}
		} catch (Exception e) {
			return null;
		}
	}

	public void waitforElement(String by, String path) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		switch (by) {
		case "id":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.id(path)));
			break;
		case "name":
			ExpectedConditions.visibilityOfElementLocated(By.name(path));
			break;
		default:
			wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath(path)));
			break;
		}
	}

	public void waitForElementToBeClickable(String by, String path) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		switch (by) {
		case "id":
			wait.until(ExpectedConditions.elementToBeClickable(By.id(path)));
			break;
		case "name":
			ExpectedConditions.elementToBeClickable(By.name(path));
			break;
		default:
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(path)));
			break;
		}
	}

	public void clickOnElement(String by, String path) {
		waitForElementToBeClickable(by, path);
		element = findElement(by, path);
		element.click();
	}
}
