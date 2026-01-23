package pages;

import org.openqa.selenium.By;

public class DuckDuckGoHomePage extends BasePage {

    // Locators
    private final By searchBox = By.name("q");
    private final By resultLinks = By.cssSelector("article[data-testid='result'] h2 a");

    public void open() {
        openBaseUrl();
    }

    public void search(String keyword) {
        type(searchBox, keyword);
        find(searchBox).submit();
        //driver.findElement(searchBox).submit();
    }

    public int getResultCount() {
        return findAll(resultLinks).size();
    }

    public String getFirstResultText() {
        return findAll(resultLinks).get(0).getText();
    }

    public void clickFirstResult() {
        findAll(resultLinks).get(0).click();
    }
}
