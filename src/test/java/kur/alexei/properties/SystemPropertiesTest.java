package kur.alexei.properties;

import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;

public class SystemPropertiesTest {


    public CredentialsConfig credentials =
            ConfigFactory.create(CredentialsConfig.class);

    @Test
    void someTest1() {
        String value = System.getProperty("value");
        System.out.println(value);
    }

    @Test
    void someTest2() {
        String value = System.getProperty("value", "default_value");
        System.out.println(value);
    }

    @Test
    void someTest3() {
        System.setProperty("value", "another_value");
        String value = System.getProperty("value", "default_value");
        System.out.println(value);
    }

    @Test
    @Tag("properties")
    @Tag("skip")
    void someTest4() {
        String value = System.getProperty("browser", "chrome");
        System.out.println(value);

        //gradle clean properties_tests -Dbrowser=opera
        //opera
    }

    @Test
    @Tag("properties")
    void someTest5() {
        String browser = System.getProperty("browser", "chrome");
        String browserVersion = System.getProperty("version", "91");
        String browserSize = System.getProperty("size", "1200x800");
        System.out.println(browser);
        System.out.println(browserVersion);
        System.out.println(browserSize);

        //gradle clean properties_tests -Dbrowser=opera -Dversion=93 -Dsize=300x300
        //opera  93 300x300
    }


}
