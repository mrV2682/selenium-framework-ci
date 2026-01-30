package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BingHomePage;
import pages.BingSearchResultsPage;
import utils.CsvDataProvider;
import base.BaseTest;

@Test(groups = {"ui"})
public class BingSearchTest extends BaseTest {

    @Test(dataProvider = "searchData",
            dataProviderClass = CsvDataProvider.class)
    public void search_should_return_min_results(
            String keyword,
            int expectedMin)
    {
        //GIVEN: User is on Bing hone page
        BingHomePage home = new BingHomePage();
        home.open();

        //WHEN: User searches by keyword
        BingSearchResultsPage results = home.search(keyword);

        //THEN: Search results should contain at least min results
        int actual = results.waitAndGetResultCount();

        Assert.assertTrue(
                actual >= expectedMin,
                "Expected >= " + expectedMin + " but got " + actual
        );
    }
}
