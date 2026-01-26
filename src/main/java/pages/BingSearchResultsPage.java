package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BingSearchResultsPage extends BasePage {

    private final By resultsContainer = By.id("b_results");
    private final By results = By.cssSelector("#b_results li.b_algo");

    public int getResultCount() {

        // 1. Chờ container kết quả hiển thị
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultsContainer));

        // 2. Chờ CÓ ÍT NHẤT 1 KẾT QUẢ (business condition)
        wait.until(driver ->
                driver.findElements(results).size() > 0
        );

        // 3. Đếm kết quả
        return findAll(results).size();
    }
}