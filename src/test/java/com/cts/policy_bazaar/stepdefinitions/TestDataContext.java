package com.cts.policy_bazaar.stepdefinitions;

import java.util.Map;

// Utility class to manage test data context across test execution
public class TestDataContext {

    // Stores test case ID
    private static String tcId;

    // Stores test data as key-value pairs
    private static Map<String, String> testData;

    // Returns current test case ID
    public static String getTcId() {
        return tcId;
    }

    // Sets the test case ID
    public static void setTcId(String tcId) {
        TestDataContext.tcId = tcId;
    }

    // Returns the test data map
    public static Map<String, String> getTestData() {
        return testData;
    }

    // Sets the test data map
    public static void setTestData(Map<String, String> testData) {
        TestDataContext.testData = testData;
    }

    // Retrieves a value from test data using a key
    public static String get(String key) {
        return testData.get(key);
    }
}