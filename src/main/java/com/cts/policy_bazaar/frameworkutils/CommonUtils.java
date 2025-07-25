package com.cts.policy_bazaar.frameworkutils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtils {

    // Pauses execution for the given number of seconds
    public static void sureWait(int seconds){
        try {
            Thread.sleep(seconds*1000L);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    // Returns current date and time in a specific format
    public static String getCurrentDateTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd-hh-mm-SS"));
    }

}
