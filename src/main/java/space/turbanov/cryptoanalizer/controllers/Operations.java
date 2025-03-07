package space.turbanov.cryptoanalizer.controllers;

import space.turbanov.cryptoanalizer.exceptions.AppException;
import space.turbanov.cryptoanalizer.operations.*;

public enum Operations {
    ENCODE(new Encoder()),
    DECODE(new Decoder()),
    BRUTEFORCE(new BruteForce()),
    EXIT(new Exit());

    private final Operation operation;

    Operations(Operation operation) {
        this.operation = operation;
    }

    public static Operation getOperation(String operationName) {
        try {
            Operations value = Operations.valueOf(operationName.toUpperCase());
            return value.operation;
        } catch (IllegalArgumentException e) {
            throw new AppException("not found " + operationName + " operation", e);
        }
    }
}
