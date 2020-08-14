package com.gmail;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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

    public void getPage(String url) {
        driver.get(url);
    }

    public void setText(String xpath, String text) {
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }

    public List<WebElement> getCountSearchedIncomingEmails(String xpath) {
        return driver.findElements(By.xpath(xpath));
    }

    public void click(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    public void quit() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
