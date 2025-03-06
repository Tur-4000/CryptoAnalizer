package space.turbanov.cryptoanalizer;

import space.turbanov.cryptoanalizer.entity.Result;

import java.io.IOException;
import java.util.Arrays;

public class ConsoleRunner {
    public static void main(String[] args) throws IOException {
        App application = new App();
        Result result = application.run(args);
        System.out.println(result);
    }
}
