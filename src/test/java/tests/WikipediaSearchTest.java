package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WikipediaHomePage;


public class WikipediaSearchTest extends BaseTest {

    @Test
    public void searchAndOpenFirstResult() {
        String keyword = "Selenium WebDriver";
        int expectedMinResults = 1;

        logger.info("Open Wikipedia");
        WikipediaHomePage home = new WikipediaHomePage();
        home.open();

        logger.info("Search keyword: {}", keyword);
        home.search(keyword);

        int count = home.getResultCount();
        logger.info("Result count: {}", count);

        Assert.assertTrue(
                count >= expectedMinResults,
                "Expected at least one search result"
        );

        String firstResult = home.getFirstResultText();
        logger.info("First result text: {}", firstResult);

        Assert.assertTrue(
                firstResult.toLowerCase().contains("selenium"),
                "First result does not contain keyword"
        );

        logger.info("Click first result");
        home.clickFirstResult();
    }
}
