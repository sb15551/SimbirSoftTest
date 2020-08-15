package com.gmail;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Surkov Aleksey (stibium128@gmail.com)
 * @date 14.08.2020 0:42
 */
public class EmailPage {

    private static final String INCOMING_EMAILS = "//*[@class='TK']/div[@class='aim ain']";
    private static final String SEARCHED_EMAILS = "//div[@class='yW']"; // //*[@email='farit.valiahmetov@simbirsoft.com']";
    private static final String NEW_EMAIL = "//*[@class='aic']/div/div";
    private static final String ADDRESS_FIELD = "//*[@name='to']";
    private static final String THEME_FIELD = "//*[@name='subjectbox']";
    private static final String EMAIL_BODY = "//*[@class='Ar Au']/div";
    private static final String SEND_BUTTON = "//*[@class='dC']/div";
    private static final String NEXT_PAGE = "//span[@class='Di']/div[3]";

    private ChromeController controller;

    public EmailPage(ChromeController controller) {
        this.controller = controller;
    }

    @Step("Count searched incoming emails")
    public int getCountSearchedIncomingEmails(String address) {
        Set<WebElement> searchedIncomingEmails = new HashSet<>();
        controller.click(INCOMING_EMAILS);
        controller.timeout(500);
        searchedIncomingEmails.addAll(controller.getSearchedIncomingEmails(
                String.format("%s//*[@email='%s']", SEARCHED_EMAILS, address))
        );

        while (controller.getElement(NEXT_PAGE).getAttribute("aria-disabled") == null) {
            controller.click(NEXT_PAGE);
            controller.timeout(500);
            searchedIncomingEmails.addAll(controller.getSearchedIncomingEmails(
                    String.format("%s//*[@email='%s']", SEARCHED_EMAILS, address)
            ));
        }
        return searchedIncomingEmails.size();
    }

    @Step("Set address")
    private void setAddress(String address) {
        controller.setText(ADDRESS_FIELD, address);
    }

    @Step("Set theme")
    private void setTheme(String theme) {
        controller.setText(THEME_FIELD, theme);
    }

    @Step("Set email body")
    private void setEmailBody(String emailBody) {
        controller.setText(EMAIL_BODY, emailBody);
    }

    @Step("Create new email")
    private void clickNewEmail() {
        controller.click(NEW_EMAIL);
    }

    @Step("Sending email")
    private void clickSendEmail() {
        controller.click(SEND_BUTTON);
    }

    public void createAndSendEmail(String address, String theme, String text) {
        clickNewEmail();
        setAddress(address);
        setTheme(theme);
        setEmailBody(text);
        clickSendEmail();
    }
}
