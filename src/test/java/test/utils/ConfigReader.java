package test.utils;

import test.config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;

public class ConfigReader {

    private static final ProjectConfig config = ConfigFactory.create(ProjectConfig.class);

    public static String getUsername() {
        return config.username();
    }

    public static String getPassword() {
        return config.password();
    }
}