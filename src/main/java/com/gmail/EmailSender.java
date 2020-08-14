package com.gmail;

import io.qameta.allure.Step;

/**
 * @author Surkov Aleksey (stibium128@gmail.com)
 * @date 14.08.2020 0:42
 */
public class EmailSender {

    private static final String INCOMING_EMAILS = "//*[@class='TK']/div[@class='aim ain']";
    private static final String SEARCHED_EMAILS = "//div[@class='yW']//*[@email='farit.valiahmetov@simbirsoft.com']";
    private static final String NEW_EMAIL = "//*[@class='aic']/div/div";
    private static final String ADDRESS_FIELD = "//*[@name='to']";
    private static final String THEME_FIELD = "//*[@name='subjectbox']";
    private static final String EMAIL_BODY = "//*[@class='Ar Au']/div[@aria-label='Message Body']";
    private static final String SEND_BUTTON = "//*[@class='dC']/div";

    private ChromeController controller;

    public EmailSender(ChromeController controller) {
        this.controller = controller;
    }

    @Step("Count searched incoming emails")
    private int getCountSearchedIncomingEmails() {
        controller.click(INCOMING_EMAILS);
        return controller.getCountSearchedIncomingEmails(SEARCHED_EMAILS).size();
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

    public void createEmail(String address, String theme) {
        StringBuilder text = new StringBuilder();
        text.append("Amount test emails: ").append(getCountSearchedIncomingEmails());
        clickNewEmail();
        setAddress(address);
        setTheme(theme);
        setEmailBody(text.toString());
        clickSendEmail();
    }
}
