package com.gmail;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

/**
 * @author Surkov Aleksey (stibium128@gmail.com)
 * @date 14.08.2020 0:27
 */
public class LoginGmail {

    private static final String LOGIN = "//*[@id='identifierId']";
    private static final String PASSWORD = "//*[@id='password']/div[1]/div/div[1]/input";

    private ChromeController controller;

    public LoginGmail(ChromeController controller) {
        this.controller = controller;
    }

    @Step("Enter login")
    private void setLogin(String login) {
        controller.setText(LOGIN, login + Keys.ENTER);
    }

    @Step("Enter password")
    private void setPassword(String password) {
        controller.setText(PASSWORD, password + Keys.ENTER);
    }

    public void loginToEmail(String login, String password) {
        setLogin(login);
        setPassword(password);
    }
}
