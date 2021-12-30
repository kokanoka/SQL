package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;


public class AuthTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void deleteData() {
        DataHelper.clearAllData();
    }

    @Test
    void shouldValidLogin() throws SQLException {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getValidLogin();
        var verificationPage = loginPage.validLogin(authInfo);
        var dashboardPage = verificationPage.validVerify(DataHelper.getValidVerificationCode());
    }

    @Test
    void shouldLoginWithInvalidPassword() {
        var loginPage = new LoginPage();
        loginPage.invalidLogin(DataHelper.getAuthWithInvalidPassword());
    }

    @Test
    void shouldLoginWith3InvalidPassword() {
        var loginPage = new LoginPage();
        loginPage.invalidLogin(DataHelper.getAuthWithInvalidPassword());
        loginPage.clearForm();
        loginPage.invalidLogin(DataHelper.getAuthWithInvalidPassword());
        loginPage.clearForm();
        loginPage.invalidLogin(DataHelper.getAuthWithInvalidPassword());
        loginPage.clearForm();
        loginPage.invalidLogin(DataHelper.getAuthWithInvalidPassword());
    }
}
