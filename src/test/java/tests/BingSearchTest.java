package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BingHomePage;
import pages.BingSearchResultsPage;
import utils.CsvDataProvider;

public class BingSearchTest extends base.BaseTest {

    @Test(dataProvider = "searchData",
            dataProviderClass = CsvDataProvider.class)
    public void search_should_return_min_results(
            String keyword,
            int expectedMin) {

        BingHomePage home = new BingHomePage();
        home.open();

        BingSearchResultsPage results = home.search(keyword);

        int actual = results.getResultCount();

        Assert.assertTrue(
                actual >= expectedMin,
                "Expected >= " + expectedMin + " but got " + actual
        );
    }
}
