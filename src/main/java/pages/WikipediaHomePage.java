package pages;

import org.openqa.selenium.By;

public class WikipediaHomePage extends BasePage {

    private final By searchInput = By.id("searchInput");
    private final By searchButton = By.cssSelector("button[type='submit']");
    private final By searchResults = By.cssSelector("ul.mw-search-results li");

    public void open() {
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
    }

    public void search(String keyword) {
        type(searchInput, keyword);
        click(searchButton);

        wait.until(driver ->
                driver.getCurrentUrl().contains("search=")
        );
    }

    public int getResultCount() {
        return findAll(searchResults).size();
    }

    public String getFirstResultText() {
        return findAll(searchResults).get(0).getText();
    }

    public void clickFirstResult() {
        findAll(searchResults).get(0)
                .findElement(By.tagName("a"))
                .click();
    }
}

