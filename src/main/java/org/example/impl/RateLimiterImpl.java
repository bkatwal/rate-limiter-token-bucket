package org.example.impl;

import org.example.RateLimitInfo;
import org.example.api.RateLimiter;
import org.example.util.Identifier;
import org.example.util.TokenBucketLimiter;

import java.util.HashMap;
import java.util.Map;

public class RateLimiterImpl implements RateLimiter {


    private final Map<String, RateLimitInfo> services;

    private final Map<String, TokenBucketLimiter> serviceBuckets;

    public RateLimiterImpl() {
        this.services = new HashMap<>();
        this.serviceBuckets = new HashMap<>();
    }

    @Override
    public void registerService(String operation, int operations, int seconds, Identifier identifier) {
        String key = operation.concat(identifier.toString());
        services.put(key, new RateLimitInfo(operation, identifier, operations, seconds));
    }

    @Override
    public boolean check(String operationName, Identifier identifier, String value) {
        String serviceKey = operationName.concat(identifier.toString());

        if (!services.containsKey(serviceKey)) {
            throw new RuntimeException("operation not registered");
        }

        RateLimitInfo rateLimitInfo = services.get(serviceKey);

        String opKey = operationName.concat(identifier.toString()).concat(value);

        if (!serviceBuckets.containsKey(opKey)) {
            serviceBuckets.put(opKey, new TokenBucketLimiter(rateLimitInfo.getOperations(), rateLimitInfo.getSeconds()));
        }

        TokenBucketLimiter tokenBucketLimiter = serviceBuckets.get(opKey);

        return tokenBucketLimiter.isAllowed();
    }
}
