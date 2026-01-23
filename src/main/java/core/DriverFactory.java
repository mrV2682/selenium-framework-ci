package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    public static WebDriver createDriver() {
        String browser = ConfigReader.get("browser");

        System.out.println(">>> Browser from config: " + browser);

        WebDriver driver;

        switch (browser.toLowerCase()) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();

                // ===== CI SAFE OPTIONS =====
                chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--window-size=1920,1080");

                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                // ===== CI SAFE OPTIONS =====
                firefoxOptions.addArguments("-headless");

                driver = new FirefoxDriver(firefoxOptions);

                // Firefox MUST set window size after init
                driver.manage().window()
                        .setSize(new Dimension(1920, 1080));
                break;

            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }

        return driver;
    }
}
