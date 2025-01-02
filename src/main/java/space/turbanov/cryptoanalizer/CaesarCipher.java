package space.turbanov.cryptoanalizer;

import space.turbanov.cryptoanalizer.constants.Alphabet;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class CaesarCipher
{
    private static final String TXT_DIR = System.getProperty("user.dir") + File.separator + "texts" + File.separator;
    private static final String RESULT_OK = "OK";
    private static final String RESULT_ERROR = "ERROR";
    
    private static Alphabet alphabet = new Alphabet();


    public static void main(String[] args) {
        String operation = args[0];
        String[] parameters = Arrays.copyOfRange(args, 1, args.length);

        try {
            String result = doOperation(operation, parameters);
            System.out.println(result);
        } catch (IOException e) {
            System.out.println(RESULT_ERROR);
        }
    }

    private static String doOperation(String operation, String[] parameters) throws IOException {
        String sourceFile = parameters[0];
        String targetFile = parameters[1];
        int key = Integer.parseInt(parameters[2]);

        return switch (operation) {
            case "encode" -> encode(key, sourceFile, targetFile);
            case "decode" -> decode(-1 * key, sourceFile, targetFile);
            case "brutforce" -> brutForce();
            default -> RESULT_ERROR;
        };
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

    private static String processFiles(int key, BufferedReader source, BufferedWriter target) throws IOException {
        while (source.ready()) {
            String line = source.readLine();
            String processedLine = processLine(line, key);
            target.write(processedLine);
            target.write("\n");
        }

        return RESULT_OK;
    }

    private static String encode(int key, String sourceFile, String targetFile) throws IOException {
        BufferedReader source = readFile(sourceFile);
        BufferedWriter target = writeFile(targetFile);

        String result = processFiles(key, source, target);

        source.close();
        target.close();

        return result;
    }

    private static String decode(int key, String sourceFile, String targetFile) throws IOException {
        BufferedReader source = readFile(sourceFile);
        BufferedWriter target = writeFile(targetFile);

        String result = processFiles(key, source, target);

        source.close();
        target.close();

        return result;
    }

//    TODO: написать метод brutForce()
    private static String brutForce() {

        return RESULT_OK;
    }

    private static char shiftSymbol(int index, int offset, char symbol) {
        int length = alphabet.ALPHABET.length;

        if (index != -1) {
            int shiftedIndex = (index + offset + (Math.abs(offset) * length)) % length;
            return alphabet.ALPHABET[shiftedIndex];
        }

        return symbol;
    }

    private static String processLine(String line, int key) {
        StringBuilder processedLine = new StringBuilder();

        for (char aChar : line.toCharArray()) {
            int index = alphabet.ALPHABET_INDEX.getOrDefault(aChar, -1);
            processedLine.append(shiftSymbol(index, key, aChar));
        }

        return processedLine.toString();
    }
}
