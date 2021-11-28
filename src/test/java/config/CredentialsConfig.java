package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/credentials.properties"})
public interface CredentialsConfig extends Config {
    String firstName();
    String lastName();
    String email();
    String mobile();
    String selenoidLogin();
    String selenoidPassword();

}
