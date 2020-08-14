package com.gmail;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import junit.framework.TestCase;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * @author Surkov Aleksey (stibium128@gmail.com)
 * @date 13.08.2020 9:01
 */
public class GmailTest extends TestCase {
    private static final String BASE_URL = "https://www.gmail.com/";
    private static final String TEST_GMAIL_COM = "stibium15551@gmail.com";
    private static final String TEST_PASSWORD = "**********";
    private static final String ADDRESS = "farit.valiahmetov@simbirsoft.com";
    private static final String THEME = "Тестовое задание. Surkov";

    private ChromeController controller;
    private LoginGmail loginGmail;
    private EmailSender emailSender;

    @Description("Open browser")
    @Override
    protected void setUp() throws Exception {
        controller = new ChromeController();
        controller.start();
        loginGmail = new LoginGmail(controller);
        controller.getPage(BASE_URL);
        emailSender = new EmailSender(controller);
    }

    @Description("Login")
    @Story("Enter login and password")
    public void testSendEmail() {
        loginGmail.loginToEmail(TEST_GMAIL_COM, TEST_PASSWORD);
        emailSender.createEmail(ADDRESS, THEME);
    }

    @Description("Close browser")
    @Override
    protected void tearDown() throws Exception {
        controller.quit();
    }
}
