package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BingSearchResultsPage extends BasePage {

    private final By resultsContainer = By.id("b_results");
    private final By results = By.cssSelector("#b_results > li");

    public int getResultCount() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("li.b_algo")
        ));
        return findAll(By.cssSelector("li.b_algo")).size();
    }
}
