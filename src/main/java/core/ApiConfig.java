package core;

import org.checkerframework.checker.units.qual.C;

public class ApiConfig {

    private ApiConfig() {};

    public static final String BASE_URL =
            ConfigReader.get("api.base.url");

    public static final String USERNAME =
            ConfigReader.get("api.username");

    public static final String PASSWORD =
            ConfigReader.get("api.password");

    public static  final boolean AUTH_ENABLED =
            Boolean.parseBoolean(ConfigReader.get("api.auth.enabled"));

    public static final String API_TOKEN =
            ConfigReader.get("api.token");


}

