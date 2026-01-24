package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WikipediaHomePage;


public class WikipediaSearchTest extends BaseTest {

    @Test
    public void searchAndOpenArticle() {
        String keyword = "Selenium WebDriver";

        logger.info("Open Wikipedia");
        WikipediaHomePage home = new WikipediaHomePage();
        home.open();

        logger.info("Search keyword: {}", keyword);
        home.search(keyword);

        String title = driver.getTitle();
        logger.info("Page title: {}", title);

        Assert.assertTrue(
                title.toLowerCase().contains("selenium"),
                "Page title does not contain keyword"
        );
    }

}
