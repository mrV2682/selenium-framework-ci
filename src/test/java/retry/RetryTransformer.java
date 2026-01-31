package retry;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import org.testng.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryTransformer implements IAnnotationTransformer {

    @Override
    public void transform(
            ITestAnnotation annotation,
            Class testClass,
            Constructor testConstructor,
            Method testMethod
    ) {
        if (testMethod != null) {
            Test test = testMethod.getAnnotation(Test.class);
            if (test != null) {
                for (String group : test.groups()) {
                    if ("api".equalsIgnoreCase(group)) {
                        return; // Not retry for API
                    }
                }
            }
        }
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
