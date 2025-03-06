package space.turbanov.cryptoanalizer.controllers;

import space.turbanov.cryptoanalizer.entity.Result;
import space.turbanov.cryptoanalizer.operations.Operation;

import java.io.IOException;

public class MainController {

    public Result doOperation(String operationName, String[] parameters) throws IOException {

        Operation operation = Operations.find(operationName);

        return operation.execute(parameters);
    }
}
