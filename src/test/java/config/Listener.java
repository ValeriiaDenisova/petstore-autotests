package config;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Listener extends TestListenerAdapter {

    @Override
    public void onTestStart(final ITestResult testResult) {
        Logger.out.info("START TEST: " + testResult.getName() + " " + testResult.getInstanceName());
    }

    @Override
    public void onTestSuccess(final ITestResult testResult) {
        Logger.out.info("TEST PASSED - " + testResult.getName() + " (" + getDuration(testResult) + " milliseconds)\n-------------------------------------------------------");
    }

    @Override
    public void onTestSkipped(final ITestResult testResult) {
        Logger.out.info("TEST SKIPPED - " + testResult.getName() + "\n-------------------------------------------------------");
    }

    @Override
    public void onTestFailure(final ITestResult testResult) {
        Logger.out.error("TEST FAILED - " + testResult.getName() + " (" + getDuration(testResult) + " milliseconds)\n-------------------------------------------------------");
    }

    private long getDuration(final ITestResult testResult) {
        return testResult.getEndMillis() - testResult.getStartMillis();
    }

    private void log(String methodName) {
        System.out.println(methodName);
    }
}
