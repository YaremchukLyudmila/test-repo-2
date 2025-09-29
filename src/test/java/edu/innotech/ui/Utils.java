package edu.innotech.ui;

import java.time.Duration;

public class Utils {

    public static final Duration DEFAULT_DURATION = Duration.ofSeconds(5);

    public static void delay(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
