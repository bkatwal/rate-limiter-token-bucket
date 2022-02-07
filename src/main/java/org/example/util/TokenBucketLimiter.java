package org.example.util;

import java.time.Instant;

public class TokenBucketLimiter {

    private int operations;
    private long lastRefillTime;
    private int seconds;
    private int maxOperations;

    public TokenBucketLimiter(int maxOperations, int seconds) {
        this.operations = maxOperations;
        this.maxOperations = maxOperations;
        this.lastRefillTime = Instant.now().getEpochSecond();
        this.seconds = seconds;
    }

    public synchronized boolean isAllowed() {
        refill();
        if (operations > 0) {
            operations--;
            return true;
        }
        return false;
    }

    private void refill() {
        long current = Instant.now().getEpochSecond();
        operations = (int) Math.min(maxOperations, operations + ((current - lastRefillTime) * (double) maxOperations / seconds));
        lastRefillTime = current;
    }
}
