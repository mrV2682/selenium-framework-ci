package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DuckDuckGoHomePage;
import utils.CsvDataProvider;

public class DuckDuckGoSearchTest extends BaseTest {
    @Test(
            dataProvider = "searchData",
            dataProviderClass = CsvDataProvider.class,
            retryAnalyzer = retry.RetryAnalyzer.class
          )

    public void searchAndOpenFirstResult(
            String keyword,
            int expectedMinResults
    ){
        logger.info("Open DuckDuckGo");
        DuckDuckGoHomePage home = new DuckDuckGoHomePage();
        home.open();

        logger.info("Search keyword: {} ", keyword);
        home.search(keyword);

        int count = home.getResultCount();
        logger.info("Result count: {}", count);
        Assert.assertTrue(
                count >=expectedMinResults,
                String.format(
                        "Expected at least %d result but found %d",
                        expectedMinResults, count
                )
        );

        String firstResultText = home.getFirstResultText();
        logger.info("First result text: {}", firstResultText);
        String[] words = keyword.toLowerCase().split("\\s+");
        for(String word : words) {
            Assert.assertTrue(
                    firstResultText.toLowerCase().contains(word),
                    "First result does not contain word: " + word
            );
        }

        logger.info("Click first result");
        home.clickFirstResult();
    }
}
