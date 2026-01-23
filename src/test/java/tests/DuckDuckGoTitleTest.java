package tests;

import base.BaseTest;
import core.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DuckDuckGoHomePage;

public class DuckDuckGoTitleTest extends BaseTest {

    @Test
    public void verifyDuckDuckGoTitle() {
        logger.info("Open DuckDuckGo - Title Test");

        DuckDuckGoHomePage home = new DuckDuckGoHomePage();
        home.open();

        String title = DriverManager.getDriver().getTitle();
        logger.info(
                "Page title: {} | Thread ID: {}",
                title,
                Thread.currentThread().getId()
        );

        Assert.assertTrue(
                title.toLowerCase().contains("duckduckgo"),
                "Title does not contain DuckDuckGo"
        );
    }
}
