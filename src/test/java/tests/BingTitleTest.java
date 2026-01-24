package tests;

import base.BaseTest;
import core.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BingHomePage;

public class BingTitleTest extends BaseTest {

    @Test
    public void verifyBingTitle() {
        logger.info("Open Wikipedia - Title Test");

        BingHomePage home = new BingHomePage();
        home.open();

        String title = DriverManager.getDriver().getTitle();
        logger.info(
                "Page title: {} | Thread ID: {}",
                title,
                Thread.currentThread().getId()
        );

        Assert.assertTrue(
                title.toLowerCase().contains("bing"),
                "Title does not contain bing"
        );
    }
}
