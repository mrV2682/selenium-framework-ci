package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BingSearchResultsPage extends BasePage {

    private final By resultsContainer = By.id("b_results");
    private final By results = By.cssSelector("#b_results > li, #b_results .b_algo");

    public int waitAndGetResultCount() {

        // 1. Wait for container existed (No need visible)
        wait.until(ExpectedConditions.presenceOfElementLocated(resultsContainer));

        // 2. Wait until at least 1 result existed
        wait.until(driver ->
                !driver.findElements(results).isEmpty()
        );

        // 3. Get actual result size
        return driver.findElements(results).size();
    }

}