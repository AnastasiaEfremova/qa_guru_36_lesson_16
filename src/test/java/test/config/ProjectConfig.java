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
}