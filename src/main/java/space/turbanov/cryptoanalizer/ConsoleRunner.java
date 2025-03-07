package space.turbanov.cryptoanalizer;

import space.turbanov.cryptoanalizer.constants.Constants;
import space.turbanov.cryptoanalizer.entity.Result;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleRunner {
    public static void main(String[] args) throws IOException {
        App application = new App();
        Result result = application.run(args);
        System.out.println(result);
    }

    private static void menu() {
        String[] args = new String[4];
//        String operation;
//        String source;
//        String target;
//        String key;
//        String dict;

        System.out.println("""
                Меню:
                  1. Зашифровать файл
                  2. Расшифровать файл
                  3. Взломать (методом brute force)
                  4. Выход
                """);
        System.out.print("Введите номер пункта меню: ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        args = switch (choice) {
            case "1" -> encode();
            case "2" -> decode();
            case "3" -> bruteforce();
            case "4" -> exit();
            default -> new String[4];
        };
    }

    private static String[] encode() {
        String[] args = new String[4];
        args[0] = Constants.ENCODE;

        return args;
    }

    private static String[] decode() {
        String[] args = new String[4];
        args[0] = Constants.DECODE;

        return args;
    }

    private static String[] bruteforce() {
        String[] args = new String[4];
        args[0] = Constants.BRUTEFORCE;

        return args;
    }

    private static String[] exit() {
        String[] args = new String[4];
        args[0] = Constants.EXIT;

        return args;
    }
}
