package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DuckDuckGoHomePage extends BasePage {

    // Locators
    private final By searchBox = By.name("q");
    private final By resultLinks =
            By.cssSelector("article[data-testid='result'], div.results--main article");

    public void open() {
        openBaseUrl();
    }

    public void search(String keyword) {
        type(searchBox, keyword);
        // Human-like delay (VERY IMPORTANT for CI)
        try {
            Thread.sleep(800);
        } catch (InterruptedException ignored) {}

        find(searchBox).submit();
        //wait until search redirect completed
        //wait.until(ExpectedConditions.urlContains("q="));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("q="),
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("article[data-testid='result'], .results--main")
                )
        ));
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
