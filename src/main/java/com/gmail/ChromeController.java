package com.gmail;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Surkov Aleksey (stibium128@gmail.com)
 * @date 13.08.2020 23:54
 */
public class ChromeController {

    private static final String NODE_URL = "http://localhost:4444/wd/hub";
    private static final String DRIVER_PATH = "src/main/resources/chromedriver.exe";

    private WebDriver driver;

    public void start() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions()
                .addArguments("start-maximized");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new RemoteWebDriver(
                new URL(NODE_URL),
                capabilities
        );
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void openPage(String url) {
        driver.get(url);
    }

    public WebElement getElement(String xpath) {
        WebElement element = null;
        try {
            element = driver.findElement(By.xpath(xpath));
        } catch (StaleElementReferenceException e) {
            System.out.println(e.getMessage());
        }
        return element;
    }

    public void setText(String xpath, String text) {
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }

    public List<WebElement> getSearchedIncomingEmails(String xpath) {
        return driver.findElements(By.xpath(xpath));
    }

    public void timeout(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void click(String xpath) {
        try {
            getElement(xpath).click();
        } catch (StaleElementReferenceException e) {
            System.out.println(e.getMessage());
        }
    }

    public void quit() {
        timeout(2500);
        driver.quit();
    }
}
