package org.example;

import org.example.util.Identifier;

public class RateLimitInfo {

    private String operation;
    private Identifier identifier;
    private int operations;
    private int seconds;

    public RateLimitInfo(String operation, Identifier identifier, int operations, int seconds) {
        this.operation = operation;
        this.identifier = identifier;
        this.operations = operations;
        this.seconds = seconds;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public int getOperations() {
        return operations;
    }

    public void setOperations(int operations) {
        this.operations = operations;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
