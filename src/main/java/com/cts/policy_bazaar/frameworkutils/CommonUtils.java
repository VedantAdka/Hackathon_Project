package com.cts.policy_bazaar.frameworkutils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtils {
    public static void sureWait(int seconds){
        try {
            Thread.sleep(seconds*1000L);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
    public static String getCurrentDateTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd-hh-mm-SS"));
    }

}
