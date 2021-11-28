package kur.alexei;

import com.codeborne.selenide.Configuration;
import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;

@Tag("smoke")
public class StudentRegistrationFormTest extends TestBase {

    public CredentialsConfig credentials =
            ConfigFactory.create(CredentialsConfig.class);

    String firstName = System.getProperty("firstName", "Alexei");
    String lastName = System.getProperty("lastName", "Kurochkin");
    String email = System.getProperty("email", credentials.email());
    String mobile = System.getProperty("mobile", credentials.mobile());
    String gender = "Male";
    String subject = "comp";
    File filePath = new File("src/test/resources/files/filePath.png");
    String currentAddress = "Students Street 9";
    String state = "NCR";
    String city = "Delhi";
    String testPage = "https://demoqa.com/automation-practice-form";

    @Test
    @DisplayName("Проверка формы с запуском в jenkins и Allure report")
    void studentRegistrationFormTest() {

        open(testPage);
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        //$("#gender-radio-1").click();
        $(byText("Male")).click();
        $("#userNumber").setValue(mobile);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-dropdown-container").click();
        $(".react-datepicker__year-select").selectOptionByValue("1974");
        $(".react-datepicker__month-dropdown-container").click();
        $(".react-datepicker__month-select").selectOptionByValue("2");
        $x("//div[text()='30']").click();
        $("#subjectsInput").setValue(subject).pressEnter();
        $(byText("Music")).click();
//        $("#uploadPicture").uploadFile(filePath);
        $("#uploadPicture").uploadFromClasspath("files/filePath.png");
        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();
        $("#submit").click();

        $(".table").shouldHave(text(firstName), text(lastName), text(email),
                text("Male"), text(mobile), text("30 March,1974"), text("Computer Science"),
                text("Music"), text("filePath.png"), text(currentAddress), text(state), text(city));

        System.out.println(lastName);
        System.out.println(mobile);

    }

}
