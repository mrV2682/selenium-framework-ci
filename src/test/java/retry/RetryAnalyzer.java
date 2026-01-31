package retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY = 2; // retry 2 times

    @Override
    public boolean retry(ITestResult result) {

        /**
         * Do not retry for API test
         */
        String[] groups = result.getMethod().getGroups();
        if (groups != null){
            for (String group : groups){
                if ("api".equalsIgnoreCase(group)){
                    return false;
                }
            }
        }
        /**
         * Retry onlu for NON-API test
         */
        if (retryCount < MAX_RETRY) {
            retryCount++;
            System.out.println(
                    ">>> Retrying test: "
                            + result.getName()
                            + " | Retry count = " + retryCount
            );
            return true; // retry test
        }
        return false; // stop retry
    }
    public boolean isRetryAvailable() {
        return retryCount < MAX_RETRY;
    }
}

