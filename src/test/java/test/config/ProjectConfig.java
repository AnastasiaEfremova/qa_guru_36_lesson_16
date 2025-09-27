package test.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:application.properties"})
public interface ProjectConfig extends Config {

    @Key("username")
    String username();

    @Key("password")
    String password();
}