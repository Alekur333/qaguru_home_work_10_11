package kur.alexei;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.CredentialsConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static java.lang.String.format;

public class TestBase {

    @BeforeAll
    static void setup() {

        CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class);

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.startMaximized = true;
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";
//        String loginData = credentials.selenoidLogin() + ":" + credentials.selenoidPassword();
//        String selenoid = "https://" + loginData + "@selenoid.autotests.cloud/wd/hub/";

        String selenoidLogin = credentials.selenoidLogin();
        String selenoidPassword = credentials.selenoidPassword();
//        String selenoidUrl =
//                format("https://%s:%s@selenoid.autotests.cloud/wd/hub/", selenoidLogin, selenoidPassword);
        String selenoidUrl = format(System.getProperty("selenoidUrl"), selenoidLogin, selenoidPassword);

        Configuration.remote = selenoidUrl;
//        System.out.println(selenoidUrl);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}