package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BingHomePage extends BasePage {

    private final By searchBox = By.name("q");

    public void open() {
        open("/"); // base.url = https://www.bing.com
    }

    public BingSearchResultsPage search(String keyword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));

        type(searchBox, keyword);
        find(searchBox).sendKeys(Keys.ENTER);

        return new BingSearchResultsPage();
    }
}
