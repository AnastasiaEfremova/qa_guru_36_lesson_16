package test.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env"
})
public interface ProjectConfig extends Config {

    @Key("username")
    String username();

    @Key("password")
    String password();

    @Key("remote.url")
    String remoteUrl();

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("browser.version")
    @DefaultValue("127.0")
    String browserVersion();

    @Key("browser.size")
    @DefaultValue("1920x1080")
    String browserSize();
}