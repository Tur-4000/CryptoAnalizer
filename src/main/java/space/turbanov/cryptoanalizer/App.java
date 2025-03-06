package space.turbanov.cryptoanalizer;

import space.turbanov.cryptoanalizer.constants.Alphabet;
import space.turbanov.cryptoanalizer.controllers.MainController;
import space.turbanov.cryptoanalizer.entity.Result;
import space.turbanov.cryptoanalizer.entity.ResultCode;
import space.turbanov.cryptoanalizer.exceptions.AppException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class App
{
    private static final String TXT_DIR = System.getProperty("user.dir") + File.separator + "texts" + File.separator;

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
            throw new AppException();
        }
//
//
//        String sourceFile = parameters[0];
//        String targetFile = parameters[1];
//        int key = Integer.parseInt(parameters[2]);
//
//        return switch (operation) {
//            case "encode" -> encode(key, sourceFile, targetFile);
//            case "decode" -> decode(-1 * key, sourceFile, targetFile);
//            case "brutforce" -> brutForce();
//            default -> new Result(ResultCode.ERROR, "some error");
//        };
    }

    private static BufferedReader readFile(String fileName) throws IOException {
        Path path = Path.of(fileName);

        if (path.isAbsolute()) {
            return Files.newBufferedReader(path);
        }

        return Files.newBufferedReader(Path.of(TXT_DIR + fileName));
    }

    private static BufferedWriter writeFile(String fileName) throws IOException {
        Path path = Path.of(fileName);

        if (path.isAbsolute()) {
            return Files.newBufferedWriter(path);
        }

        return Files.newBufferedWriter(Path.of(TXT_DIR + fileName));
    }

    private static Result processFiles(int key, BufferedReader source, BufferedWriter target) throws IOException {
        while (source.ready()) {
            String line = source.readLine();
            String processedLine = processLine(line, key);
            target.write(processedLine);
            target.write("\n");
        }

        return new Result(ResultCode.OK, "operation complete successful");
    }

    private static Result encode(int key, String sourceFile, String targetFile) throws IOException {
        BufferedReader source = readFile(sourceFile);
        BufferedWriter target = writeFile(targetFile);

        Result result = processFiles(key, source, target);

        source.close();
        target.close();

        return result;
    }

    private static Result decode(int key, String sourceFile, String targetFile) throws IOException {
        BufferedReader source = readFile(sourceFile);
        BufferedWriter target = writeFile(targetFile);

        Result result = processFiles(key, source, target);

        source.close();
        target.close();

        return result;
    }

//    TODO: написать метод brutForce()
    private static Result brutForce() {

        return new Result(ResultCode.OK, "operation complete successful");
    }

    private static char shiftSymbol(int index, int offset, char symbol) {
        int length = Alphabet.ALPHABET.length;

        if (index != -1) {
            int shiftedIndex = (index + offset + (Math.abs(offset) * length)) % length;

            return Alphabet.ALPHABET[shiftedIndex];
        }

        return symbol;
    }

    private static String processLine(String line, int key) {
        StringBuilder processedLine = new StringBuilder();

        for (char aChar : line.toCharArray()) {
            int index = Alphabet.ALPHABET_INDEX.getOrDefault(aChar, -1);
            processedLine.append(shiftSymbol(index, key, aChar));
        }

        return processedLine.toString();
    }
}
