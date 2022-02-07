package org.example.api;

import org.example.util.Identifier;

public interface RateLimiter {

    void registerService(String operation, int operations, int seconds, Identifier identifier);

    boolean check(String operationName, Identifier identifier, String value);
}
