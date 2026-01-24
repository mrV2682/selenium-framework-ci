package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WikipediaHomePage extends BasePage {

    private final By searchInput = By.cssSelector("input[name='search']");
    // private final By searchButton = By.cssSelector("button[type='submit']");
    private final By firstHeading = By.cssSelector("firstHeading");

    public void open() {
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
    }

    public void search(String keyword) {
        type(searchInput, keyword);
    //    click(searchButton);
        find(searchInput).sendKeys(Keys.ENTER);

        // ✅ CI SAFE WAIT
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("wiki"),
                ExpectedConditions.visibilityOfElementLocated(firstHeading)
        ));
    }

    public int getResultCount() {
        return findAll(By.cssSelector("#mw-content-text p")).size();
    }

    public void clickFirstResult() {
        // Wikipedia search goes directly to page → nothing to click
    }
}

