package com.gmail;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import junit.framework.TestCase;

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
    private LoginPage loginPage;
    private EmailPage emailPage;

    @Description("Open browser")
    @Override
    protected void setUp() throws Exception {
        controller = new ChromeController();
        controller.start();
        loginPage = new LoginPage(controller);
        controller.openPage(BASE_URL);
        emailPage = new EmailPage(controller);
    }

    @Description("Login")
    @Story("Enter login and password")
    public void testSendEmail() {
        loginPage.loginToEmail(TEST_GMAIL_COM, TEST_PASSWORD);
        emailPage.createAndSendEmail(
                ADDRESS,
                THEME,
                "Amount test emails: " + emailPage.getCountSearchedIncomingEmails(ADDRESS)
        );
    }

    @Description("Close browser")
    @Override
    protected void tearDown() {
        controller.quit();
    }
}
