package core;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

//    private DriverManager() {
//        // prevent object creation
//    }

    public static void setDriver(WebDriver driver) {
        System.out.println(
                "[DriverManager] Set driver | Thread ID = "
                    + Thread.currentThread().getId()
        );
        driverThreadLocal.set(driver);
    }

    public static WebDriver getDriver() {
        System.out.println(
                "[DriverManager] Get driver | Thread ID = "
                    + Thread.currentThread().getId()
        );
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        System.out.println(
                "[DriverManager] Quit driver | Thread ID = "
                    + Thread.currentThread().getId()
        );
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}
