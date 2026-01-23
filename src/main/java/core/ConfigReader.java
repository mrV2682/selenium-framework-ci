package core;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    private ConfigReader() {
        // prevent new
    }

    static {
        String env = System.getProperty("env", "dev"); // default dev
        String fileName = "config-" + env + ".properties";

        System.out.println(">>> Loading config: " + fileName);

        try (InputStream is =
                     ConfigReader.class
                             .getClassLoader()
                             .getResourceAsStream(fileName)) {

            if (is == null) {
                throw new RuntimeException("file config not found");
            }
            properties.load(is);

        } catch (Exception e) {
            throw new RuntimeException("Cannot load config:" + fileName);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static String getBaseUrl(){
        return get("base.url");
    }

    public static String getBrowser(){
        return get("browser");
    }
}
