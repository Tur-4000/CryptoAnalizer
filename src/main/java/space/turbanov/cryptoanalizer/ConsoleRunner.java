package space.turbanov.cryptoanalizer;

import space.turbanov.cryptoanalizer.entity.Result;

import java.io.IOException;
import java.util.Arrays;

import static space.turbanov.cryptoanalizer.CaesarCipher.RESULT_ERROR;

public class ConsoleRunner {
    public static void main(String[] args) {
        String operation = args[0];
        String[] parameters = Arrays.copyOfRange(args, 1, args.length);

        try {
            Result result = CaesarCipher.doOperation(operation, parameters);
            System.out.println(result);
        } catch (IOException e) {
            System.out.println(RESULT_ERROR);
        }
    }
}
