package space.turbanov.cryptoanalizer;

import space.turbanov.cryptoanalizer.controllers.MainController;
import space.turbanov.cryptoanalizer.entity.Result;
import space.turbanov.cryptoanalizer.exceptions.AppException;

import java.io.IOException;
import java.util.Arrays;

public class App
{
    private final MainController mainController;

    public App() {
        this.mainController = new MainController();
    }

    public Result run(String... args) throws IOException {
        if (args.length > 0) {
            String operation = args[0];
            String[] parameters = Arrays.copyOfRange(args, 1, args.length);
            return mainController.doOperation(operation, parameters);
        } else {
            throw new AppException("no arguments");
        }
    }
}
