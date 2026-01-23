package base;

import core.DriverFactory;
import core.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeMethod
    public void setUp() {
        WebDriver driver = DriverFactory.createDriver();
        DriverManager.setDriver(driver);
        logger.info("Driver initialized - Thread ID: {}",
                Thread.currentThread().getId());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
        logger.info("Driver quit - Thread ID: {}",
                Thread.currentThread().getId());
    }
}
